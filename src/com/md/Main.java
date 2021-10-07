package com.md;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Game.Choice choose = null;

        while (choose == null) {
            try {
                System.out.println("choose word (rock, paper, scissors):");
                choose = Game.Choice.valueOf(scanner.nextLine().toUpperCase());
            } catch (IllegalArgumentException ex) {
                System.out.println("Invalid answer, try again.");
            }
        }

        Game game = new Game(choose);

        System.out.println("User chose: " + game.getUserChoice());
        System.out.println("Computer chose: " + game.getComputerChoice());
        System.out.println("The winner is: " + game.determineWinner());

    }

    private static class Game {
        private final Choice userChoice;
        private final Choice computerChoice;


        public Game(Choice userChoice) {
            this.userChoice = userChoice;
            this.computerChoice = Choice.getRandom();
        }

        public Choice getUserChoice() {
            return this.userChoice;
        }

        public Choice getComputerChoice() {
            return this.computerChoice;
        }

        public Result determineWinner() {
            if (userChoice.equals(computerChoice)) return Result.TIE;
            else {
                if (userChoice.equals(Choice.ROCK)) {
                    if (computerChoice.equals(Choice.SCISSORS)) return Result.USER;
                    if (computerChoice.equals(Choice.PAPER)) return Result.COMPUTER;
                }
                else if (userChoice.equals(Choice.PAPER)) {
                    if (computerChoice.equals(Choice.ROCK)) return Result.USER;
                    if (computerChoice.equals(Choice.SCISSORS)) return Result.COMPUTER;
                }
                else if (userChoice.equals(Choice.SCISSORS)) {
                    if (computerChoice.equals(Choice.PAPER)) return Result.USER;
                    if (computerChoice.equals(Choice.ROCK)) return Result.COMPUTER;
                }
            }
            return null;
        }

        private enum Choice {
            ROCK,
            PAPER,
            SCISSORS;

            public static Choice getRandom() {
                int rIndex = ThreadLocalRandom.current().nextInt(0, Choice.values().length);
                return Choice.values()[rIndex];
            }
        }

        private enum Result {
            USER,
            COMPUTER,
            TIE
        }
    }
}

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class BullsandCows {

    private Deque<String> guessFromFile = new ArrayDeque<>();
    private String result = "Bulls and Cows game result.\n";

    private void start() {
        //create new player object
        PlayerOne playerOne = new PlayerOne();
        //decide whether input guess manually or input from a file
        //update guess from file to arraydeque if player decide to input manually
        if (decideReadFile()) {
            readGuess();
        }
        System.out.println("Please select an AI opponent, 1 for Easy AI, 2 for Medium AI, and 3 for HARD AI!!!");
        //create computer difficulty based on input
        ComputerPlayer computerPlayer = chooseAI();
        System.out.println("Please enter your secret code:");
        //use setter method to set secret code
        playerOne.setPlayerCode(playerOne.generateCode());
        result += "Your code: " + playerOne.getPlayerCode() + "\n";
        System.out.println("---");
        computerPlayer.setComputerCode(computerPlayer.generateCode());
        result += "Computer's code: " + computerPlayer.getComputerCode() + "\n";
        checkMatch(playerOne, computerPlayer);
        saveResult();
    }

    private void checkMatch(PlayerOne playerOne, ComputerPlayer computerPlayer) {
        int count = 1;
        //loop seven times as seven rounds for each player
        for (int i = 0; i < 7; i++) {
            result += "---\n";
            System.out.print("You guess: ");
            //use if statement to decide whether to read from a file or input via console
            //this arraydeque is empty when the player choose not to read from file or running out of line
            if (!guessFromFile.isEmpty()) {
                String guessThisTime = guessFromFile.removeFirst();
                System.out.print(guessThisTime);
                System.out.println();
                playerOne.setPlayerGuess(guessThisTime);
            } else {
                playerOne.setPlayerGuess(playerOne.generateGuess());
            }
            //use array to store bulls and cows since there will always be two element
            int[] bullsandCowstotal = checkBullsandCows(playerOne.getPlayerGuess(), computerPlayer.getComputerCode());
            result += "Turn " + count + "\n" + "You guessed " + playerOne.getPlayerGuess() + ", scoring ";
            printResult(bullsandCowstotal[0], bullsandCowstotal[1]);
            //if anyone got four bulls, end this loop
            if (bullsandCowstotal[0] == 4) {
                System.out.println(playerOne.winGame());
                result += playerOne.winGame();
                return;
            }

            computerPlayer.setComputerGuess(computerPlayer.generateGuess());
            System.out.println("Computer guess: " + computerPlayer.getComputerGuess());
            result += "Computer guessed " + computerPlayer.getComputerGuess() + ", scoring ";
            bullsandCowstotal = checkBullsandCows(computerPlayer.getComputerGuess(), playerOne.getPlayerCode());
            //hard Ai need bulls and cows information to calculate, so pass to it use setter method
            if (computerPlayer instanceof HardAI) {
                ((HardAI) computerPlayer).setBulls(bullsandCowstotal[0]);
                ((HardAI) computerPlayer).setCows(bullsandCowstotal[1]);
            }
            printResult(bullsandCowstotal[0], bullsandCowstotal[1]);
            if (bullsandCowstotal[0] == 4) {
                System.out.println(computerPlayer.winGame());
                result += computerPlayer.winGame();
                return;
            }
            count++;
        }
        System.out.println("Both sides have tried seven times, nobody wins");
        result += "Both sides have tried seven times, nobody wins";
    }

    public static int[] checkBullsandCows(String guess, String secretCode) {
        //use for loop to loop thorough secret code match guess
        int[] bullsandCowstotal = new int[]{0, 0};
        for (int i = 0; i < 4; i++) {
            if (guess.charAt(i) == secretCode.charAt(i)) {
                bullsandCowstotal[0]++;
            } else if (secretCode.contains(guess.charAt(i) + "")) {
                bullsandCowstotal[1]++;
            }
        }
        return bullsandCowstotal;
    }

    private void printResult(int bulls, int cows) {
        //this method is use to print result for each round, notice that we need "s" after bull and cow sometimes
        if (bulls == 1 && cows == 1) {
            System.out.println("Result: 1 bull and 1 cow");
            result += "1 bull and 1 cow \n";
        } else if (bulls == 1) {
            System.out.println("Result: " + bulls + " bull and " + cows + " cows");
            result += "1 bull and " + cows + " cows\n";
        } else if (cows == 1) {
            System.out.println("Result: " + bulls + " bulls and " + cows + " cow");
            result += bulls + " bulls and " + cows + " cow\n";
        } else {
            System.out.println("Result: " + bulls + " bulls and " + cows + " cows");
            result += bulls + " bulls and " + cows + " cows\n";
        }
        System.out.println();
    }

    private boolean decideReadFile() {
        System.out.println("Do you want to enter your guess manually (Press 1) or read a file (Press 2) ?");
        //ues while loop to make sure input is 1 or 2 only
        while (true) {
            try {
                String playerReadFileChoice = Keyboard.readInput();
                if (!(playerReadFileChoice.equals("1") || playerReadFileChoice.equals("2"))) {
                    throw new InvalidInputException();
                }
                switch (Integer.parseInt(playerReadFileChoice)) {
                    case 1:
                        return false;
                    case 2:
                        return true;
                }
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private ComputerPlayer chooseAI() {
        //based on player input to choose ai difficulty
        int aiNumber = 1;
        ComputerPlayer cOne = new EasyAI();
        try {
            aiNumber = Integer.parseInt(Keyboard.readInput());
            if (aiNumber < 1 || aiNumber > 3) {
                throw new InvalidInputException();
            }
        } catch (InvalidInputException i) {
            System.out.println(i.getMessage());
            chooseAI();
        } catch (Exception e) {
            System.out.println(e.getMessage() + "Please choose again!");
            chooseAI();
        }
        switch (aiNumber) {
            case 1:
                cOne = new EasyAI();
                break;
            case 2:
                cOne = new MediumAI();
                break;
            case 3:
                cOne = new HardAI();
                break;
        }
        return cOne;
    }


    private void readGuess() {
        //check if the file exists
        System.out.println("Please enter a file name:");
        File guessFile;
        while (true) {
            try {
                String guessInput = Keyboard.readInput() + ".txt";
                guessFile = new File(guessInput);
                if (!guessFile.exists()) {
                    throw new InvalidInputException();
                }
                break;
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }
        //use scanner to read line
        try (Scanner scanner = new Scanner(guessFile)) {
            while (scanner.hasNextLine()) {
                guessFromFile.addLast(scanner.nextLine());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void saveResult() {
        System.out.println("Do you wish to save the result to a text file? 1 for Yes and 2 for No");
        outerLoop:
        while (true) {
            try {
                // check player input
                String playerReadFileChoice = Keyboard.readInput();
                if (!(playerReadFileChoice.equals("1") || playerReadFileChoice.equals("2"))) {
                    throw new InvalidInputException();
                }
                switch (Integer.parseInt(playerReadFileChoice)) {
                    case 1:
                        System.out.println("Please input a file name");
                        //use print writer to write all information to a file
                        File resultFile = new File(Keyboard.readInput() + ".txt");
                        try (PrintWriter printWriter = new PrintWriter(new FileWriter(resultFile))) {
                            printWriter.print(result);
                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                        }
                        break outerLoop;
                    case 2:
                        return;
                }
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    //start point
    public static void main(String[] args) {
        BullsandCows p = new BullsandCows();
        p.start();
    }
}

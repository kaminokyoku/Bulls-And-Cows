public class PlayerOne implements Players {

    private String playerCode;
    private String playerGuess;

    public String getPlayerCode() {
        return playerCode;
    }

    public void setPlayerCode(String playerCode) {
        this.playerCode = playerCode;
    }

    public String getPlayerGuess() {
        return playerGuess;
    }

    public void setPlayerGuess(String playerGuess) {
        this.playerGuess = playerGuess;
    }

    @Override
    public String generateCode() {
        //check if player inputted a valid code/guess
        String playerInput;
        try {
            playerInput = Keyboard.readInput();
            if (playerInput.length() != 4 || !playerInput.matches("[0-9]+")) {
                throw new InvalidInputException();
            }
            for (int i = 0; i < 3; i++) {
                for (int j = i + 1; j < 4; j++) {
                    if (playerInput.charAt(i) == playerInput.charAt(j)) {
                        throw new InvalidInputException();
                    }
                }
            }
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
            playerInput = generateCode();
        }
        return playerInput;
    }

    @Override
    public String generateGuess() {
        return generateCode();
    }

    @Override
    public String winGame() {
        return "You win! :)";
    }

}

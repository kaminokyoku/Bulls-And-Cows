import java.util.ArrayList;
import java.util.Collections;

public abstract class ComputerPlayer implements Players {
    private String computerCode;
    private String computerGuess;

    public String getComputerCode() {
        return computerCode;
    }

    public void setComputerCode(String computerCode) {
        this.computerCode = computerCode;
    }

    public String getComputerGuess() {
        return computerGuess;
    }

    public void setComputerGuess(String computerGuess) {
        this.computerGuess = computerGuess;
    }

    @Override
    public String generateCode() {
        //generate arraylist, fill it with int from 0 - 9,
        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            numbers.add(i);
        }
        //shuffle arraylist, so the int inside it got random position
        Collections.shuffle(numbers);
        //return the secret computerCode
        return "" + numbers.get(0) + numbers.get(1) + numbers.get(2) + numbers.get(3);
    }

    @Override
    public String winGame() {
        return "Computer win! :)";
    }
}
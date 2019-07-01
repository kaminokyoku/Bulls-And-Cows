import java.util.HashSet;
import java.util.Set;

public class MediumAI extends ComputerPlayer {

    private Set<String> guesses = new HashSet<>();

    @Override
    public String generateGuess() {
    //use hashset to check if new guess already exist
        String thisGuess = generateCode();
        while (!guesses.add(thisGuess)) {
            generateGuess();
        }

        return thisGuess;
    }
}


public class EasyAI extends ComputerPlayer {

    @Override
    public String generateGuess() {
        //generate random four digits same way as secret code, so use same method
        return generateCode();
    }
}

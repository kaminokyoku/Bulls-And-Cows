import java.util.*;

public class HardAI extends ComputerPlayer {

    private int bulls;
    private int cows;
    private int count = 0;
    private Deque<String> allPossibleGuesses = new ArrayDeque<>();

    public void setBulls(int bulls) {
        this.bulls = bulls;
    }

    public void setCows(int cows) {
        this.cows = cows;
    }

    @Override
    public String generateGuess() {
        // in first round, computer generate an arraydeque with all possible guesses, pick the first one as this round guess
        // from round two, compare remaining possible guess to previous guess based on bulls and cows
        // remove a guess from guess list if not match
        if (count == 0) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    if (j == i) continue;
                    for (int k = 0; k < 10; k++) {
                        if ((k == j) || (k == i)) continue;
                        for (int l = 0; l < 10; l++) {
                            if ((l == i) || (l == j) || (l == k)) continue;
                            allPossibleGuesses.addFirst("" + i + j + k + l);
                        }
                    }
                }
            }
            count++;
            return allPossibleGuesses.getFirst();
        } else {
            String lastGuess = allPossibleGuesses.removeFirst();
            allPossibleGuesses.removeIf(n -> (BullsandCows.checkBullsandCows(n, lastGuess)[0] != bulls || BullsandCows.checkBullsandCows(n, lastGuess)[1] != cows));
            return allPossibleGuesses.getFirst();
        }
    }

}

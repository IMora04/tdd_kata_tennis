package tennis;

public class SetTranslator {

    public String translate(Set set) {
        if(set.minScore() < 0) throw new IllegalArgumentException("Won games cannot be negative numbers");

        if(set.scoreDiff().equals(2) && set.maxScore() >= 4) {
            return set.leader() + " wins the match";
        }

        if(set.scoreDiff() < 2 || set.maxScore() < 4) {
            return set.player1Score + "-" + set.player2Score;
        }

        throw new IllegalArgumentException("Match should have already ended");
    }

}

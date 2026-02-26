package tennis;

public class GameScoreTranslator {

    ScoreTranslator scoreTranslator;

    GameScoreTranslator() {
        this.scoreTranslator = new ScoreTranslator();
    }

    public String translate(Game game) {
        Integer score1 = game.player1Score;
        Integer score2 = game.player2Score;
        boolean scoreIsEqual = score1.equals(score2);
        Integer biggestScore = Integer.max(score1, score2);
        String playerWithBiggestScore = score1 > score2 ? "Player 1" : "Player 2";
        Integer scoreDiff = Math.abs(score1 - score2);

        if(!scoreIsEqual) {
            if(biggestScore < 4) {
                return scoreTranslator.translate(score1) + "-" + scoreTranslator.translate(score2);
            }
            if(scoreDiff.equals(1)) {
                return "Advantage " + playerWithBiggestScore;
            }
            if(scoreDiff.equals(2)) {
                return playerWithBiggestScore + " wins the set";
            }
            throw new IllegalArgumentException("Set should have already ended");
        }
        if(score1 >= 0 && score1 < 3) {
            return scoreTranslator.translate(score1) + "-" + "All";
        }
        if(score1 >= 3) {
            return "Deuce";
        }
        throw new IllegalArgumentException("Scores cannot be negative numbers");
    }

}

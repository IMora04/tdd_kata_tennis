package tennis.game;

public class ScoreTranslator {

    public String translate(Integer score) {
        if(score.equals(0)) {
            return "Love";
        }
        if(score.equals(1)) {
            return "Fifteen";
        }
        if(score.equals(2)) {
            return "Thirty";
        }
        if(score.equals(3)) {
            return "Forty";
        }
        throw new IllegalArgumentException("This score cannot be translated");
    }

}

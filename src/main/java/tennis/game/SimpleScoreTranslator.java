package tennis.game;

import tennis.Annotable;
import tennis.IScoreMultipleTranslator;

public class SimpleScoreTranslator implements IScoreMultipleTranslator {

    ScoreTranslator scoreTranslator;

    SimpleScoreTranslator() {
        this.scoreTranslator = new ScoreTranslator();
    }

    @Override
    public boolean applies(Annotable annotable) {
        return !annotable.isEqualScore() && annotable.maxScore() < 4;
    }

    @Override
    public String translate(Annotable annotable) {
        if (!applies(annotable)) throw new IllegalArgumentException("Invalid score combination");
        return scoreTranslator.translate(annotable.player1Score) + "-" + scoreTranslator.translate(annotable.player2Score);
    }
}

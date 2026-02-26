package tennis.set;

import tennis.Annotable;
import tennis.IScoreMultipleTranslator;

public class SimpleSetTranslator implements IScoreMultipleTranslator {

    @Override
    public boolean applies(Annotable annotable) {
        return annotable.scoreDiff() < 2 || annotable.maxScore() < 4;
    }

    @Override
    public String translate(Annotable annotable) {
        if (!applies(annotable)) throw new IllegalArgumentException("Invalid score combination");
        return annotable.player1Score + "-" + annotable.player2Score;
    }

}

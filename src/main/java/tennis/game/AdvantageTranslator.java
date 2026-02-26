package tennis.game;

import tennis.Annotable;
import tennis.IScoreMultipleTranslator;

public class AdvantageTranslator implements IScoreMultipleTranslator {

    @Override
    public boolean applies(Annotable annotable) {
        return annotable.scoreDiff().equals(1) && annotable.maxScore() >= 4;
    }

    @Override
    public String translate(Annotable annotable) {
        if (!applies(annotable)) throw new IllegalArgumentException("Invalid score combination");
        return "Advantage " + annotable.leader();
    }

}

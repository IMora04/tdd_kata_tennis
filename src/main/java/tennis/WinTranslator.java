package tennis;

public abstract class WinTranslator implements IScoreMultipleTranslator {

    @Override
    public boolean applies(Annotable annotable) {
        return annotable.scoreDiff().equals(2) && annotable.maxScore() >= 4;
    }

}

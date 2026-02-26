package tennis;

public class DeuceTranslator implements IScoreMultipleTranslator {

    ScoreTranslator scoreTranslator;

    DeuceTranslator() {
        this.scoreTranslator = new ScoreTranslator();
    }

    @Override
    public boolean applies(Annotable annotable) {
        return annotable.isEqualScore() && annotable.player1Score >= 3;
    }

    @Override
    public String translate(Annotable annotable) {
        if (!applies(annotable)) throw new IllegalArgumentException("Invalid score combination");
        return "Deuce";
    }

}

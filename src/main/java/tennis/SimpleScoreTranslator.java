package tennis;

public class SimpleScoreTranslator implements IGameScoreMultipleTranslator {

    ScoreTranslator scoreTranslator;

    SimpleScoreTranslator() {
        this.scoreTranslator = new ScoreTranslator();
    }

    @Override
    public boolean applies(Game game) {
        return !game.isEqualScore() && game.maxScore() < 4;
    }

    @Override
    public String translate(Game game) {
        if (!applies(game)) throw new IllegalArgumentException("Invalid score combination");
        return scoreTranslator.translate(game.player1Score) + "-" + scoreTranslator.translate(game.player2Score);
    }
}

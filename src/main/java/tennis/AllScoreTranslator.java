package tennis;

public class AllScoreTranslator implements IGameScoreMultipleTranslator {

    ScoreTranslator scoreTranslator;

    AllScoreTranslator() {
        this.scoreTranslator = new ScoreTranslator();
    }

    @Override
    public boolean applies(Game game) {
        boolean scoreIsEqual = game.player1Score.equals(game.player2Score);
        return scoreIsEqual && game.player1Score < 3;
    }

    @Override
    public String translate(Game game) {
        if (!applies(game)) throw new IllegalArgumentException("Invalid score combination");
        return scoreTranslator.translate(game.player1Score) + "-" + "All";
    }
}

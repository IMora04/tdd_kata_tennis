package tennis;

public class GameScoreTranslator {

    ScoreTranslator scoreTranslator;

    private final IGameScoreMultipleTranslator[] translators;

    GameScoreTranslator() {
        this.scoreTranslator = new ScoreTranslator();
        translators = new IGameScoreMultipleTranslator[]{
                new SimpleScoreTranslator(),
                new AdvantageTranslator(),
                new SetWinTranslator(),
                new AllScoreTranslator(),
                new DeuceTranslator()
        };
    }

    public String translate(Game game) {
        Integer lowestScore = Integer.min(game.player1Score, game.player2Score);
        if(lowestScore < 0) throw new IllegalArgumentException("Scores cannot be negative numbers");

        StringBuilder sb = new StringBuilder();

        for (IGameScoreMultipleTranslator translator : translators) {
            if (translator.applies(game)) {
                return translator.translate(game);
            }
        }

        throw new IllegalArgumentException("Set should have already ended");
    }

}

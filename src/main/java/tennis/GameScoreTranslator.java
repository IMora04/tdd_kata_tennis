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
        if(game.minScore() < 0) throw new IllegalArgumentException("Scores cannot be negative numbers");

        for (IGameScoreMultipleTranslator translator : translators) {
            if (translator.applies(game)) {
                return translator.translate(game);
            }
        }

        throw new IllegalArgumentException("Set should have already ended");
    }

}

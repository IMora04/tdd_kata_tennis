package tennis;

public class GameScoreTranslator {

    ScoreTranslator scoreTranslator;

    private final IScoreMultipleTranslator[] translators;

    GameScoreTranslator() {
        this.scoreTranslator = new ScoreTranslator();
        translators = new IScoreMultipleTranslator[]{
                new SimpleScoreTranslator(),
                new AdvantageTranslator(),
                new SetWinTranslator(),
                new AllScoreTranslator(),
                new DeuceTranslator()
        };
    }

    public String translate(Game game) {
        if(game.minScore() < 0) throw new IllegalArgumentException("Scores cannot be negative numbers");

        for (IScoreMultipleTranslator translator : translators) {
            if (translator.applies(game)) {
                return translator.translate(game);
            }
        }

        throw new IllegalArgumentException("Set should have already ended");
    }

}

package tennis;

public class SetTranslator {

    ScoreTranslator setTranslator;

    private final IScoreMultipleTranslator[] translators;

    SetTranslator() {
        this.setTranslator = new ScoreTranslator();
        translators = new IScoreMultipleTranslator[]{
                new MatchWinTranslator(),
                new SimpleSetTranslator(),
        };
    }

    public String translate(Set set) {
        if(set.minScore() < 0) throw new IllegalArgumentException("Won games cannot be negative numbers");

        for (IScoreMultipleTranslator translator : translators) {
            if (translator.applies(set)) {
                return translator.translate(set);
            }
        }

        throw new IllegalArgumentException("Match should have already ended");
    }

}

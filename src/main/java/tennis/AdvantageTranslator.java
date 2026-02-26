package tennis;

public class AdvantageTranslator implements IGameScoreMultipleTranslator {

    @Override
    public boolean applies(Game game) {
        return !game.isEqualScore() && game.scoreDiff().equals(1);
    }

    @Override
    public String translate(Game game) {
        if (!applies(game)) throw new IllegalArgumentException("Invalid score combination");
        return "Advantage " + game.leader();
    }

}

package tennis;

public class SetWinTranslator implements IGameScoreMultipleTranslator {

    @Override
    public boolean applies(Game game) {
        return !game.isEqualScore() && game.scoreDiff().equals(2);
    }

    @Override
    public String translate(Game game) {
        if (!applies(game)) throw new IllegalArgumentException("Invalid score combination");
        return game.leader() + " wins the set";
    }

}

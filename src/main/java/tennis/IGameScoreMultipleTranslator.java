package tennis;

public interface IGameScoreMultipleTranslator {
    boolean applies(Game game);

    String translate(Game game);
}

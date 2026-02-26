package tennis;

public interface IScoreMultipleTranslator {
    boolean applies(Annotable annotable);

    String translate(Annotable annotable);
}

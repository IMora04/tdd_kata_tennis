package tennis;

public class MatchWinTranslator extends WinTranslator {

    @Override
    public String translate(Annotable annotable) {
        if (!applies(annotable)) throw new IllegalArgumentException("Invalid score combination");
        return annotable.leader() + " wins the match";
    }

}

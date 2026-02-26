package tennis.game;

import tennis.Annotable;
import tennis.WinTranslator;

public class SetWinTranslator extends WinTranslator {

    @Override
    public String translate(Annotable annotable) {
        if (!applies(annotable)) throw new IllegalArgumentException("Invalid score combination");
        return annotable.leader() + " wins the set";
    }

}

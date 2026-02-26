package tennis;

public class SetWinTranslator implements IGameScoreMultipleTranslator {

    @Override
    public boolean applies(Game game) {
        boolean scoreIsEqual = game.player1Score.equals(game.player2Score);
        Integer scoreDiff = Math.abs(game.player1Score - game.player2Score);

        return !scoreIsEqual && scoreDiff.equals(2);
    }

    @Override
    public String translate(Game game) {
        if (!applies(game)) throw new IllegalArgumentException("Invalid score combination");
        String playerWithBiggestScore = game.player1Score > game.player2Score ? "Player 1" : "Player 2";
        return playerWithBiggestScore + " wins the set";
    }

}

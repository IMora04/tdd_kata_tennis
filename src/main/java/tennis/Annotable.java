package tennis;

public abstract class Annotable {

    public Integer player1Score;
    public Integer player2Score;

    protected Annotable(Integer player1Score, Integer player2Score) {
        this.player1Score = player1Score;
        this.player2Score = player2Score;
    }

    public Integer scoreDiff() {
        return Math.abs(player1Score - player2Score);
    }

    public String leader() {
        return player1Score > player2Score ? "Player 1" : "Player 2";
    }

    public Integer maxScore() {
        return Math.max(player1Score, player2Score);
    }

    public Integer minScore() {
        return Math.min(player1Score, player2Score);
    }

    public boolean isEqualScore() {
        return player1Score.equals(player2Score);
    }

}

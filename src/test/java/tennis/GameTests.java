package tennis;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

public class GameTests {
    
    static Stream<Game> differentScoreAndBiggestIsLowerThan3Cases() {
        return Stream.of(
            new Game(0, 1),
            new Game(0, 2),
            new Game(1, 0),
            new Game(1, 2),
            new Game(2, 0),
            new Game(2, 1)
        );
    }

    static Stream<Game> sameScoreAndLowerThan3Cases() {
        return Stream.of(
            new Game(0, 0),
            new Game(1, 1),
            new Game(2, 2)
        );
    }

    static Stream<Game> sameScoreAndGreaterOrEqualTo3Cases() {
        return Stream.of(
            new Game(3, 3),
            new Game(10, 10),
            new Game(12345, 12345)
        );
    }

    static Stream<Game> player1DiffOf1WithPlayer2AndGreaterOrEqualTo4Cases() {
        return Stream.of(
            new Game(4,3),
            new Game(10,9),
            new Game(12345, 12344)
        );
    }

    static Stream<Game> player2DiffOf1WithPlayer1AndGreaterOrEqualTo4Cases() {
        return Stream.of(
            new Game(3, 4),
            new Game(9,10),
            new Game(12344, 12345)
        );
    }

    static Stream<Game> player1DiffOf2WithPlayer2AndGreaterOrEqualTo4Cases() {
        return Stream.of(
            new Game(4,2),
            new Game(10,8),
            new Game(12345, 12343)
        );
    }

    static Stream<Game> player2DiffOf2WithPlayer1AndGreaterOrEqualTo4Cases() {
        return Stream.of(
            new Game(2, 4),
            new Game(8,10),
            new Game(12343, 12345)
        );
    }

    static Stream<Game> scoreDiffGreaterThan2AndBiggestGreaterOrEqualTo4Cases() {
        return Stream.of(
            new Game(1, 4),
            new Game(1, 12345),
            new Game(12342, 12345),
            new Game(12342, 67890),
            new Game(4, 1),
            new Game(12345, 1),
            new Game(12345, 12342),
            new Game(67890, 12342)
        );
    }

    static Stream<Game> negativeScoreCases() {
        return Stream.of(
            new Game(-1, 4),
            new Game(4, -7),
            new Game(-3, -6)
        );
    }

    @ParameterizedTest
    @MethodSource("differentScoreAndBiggestIsLowerThan3Cases")
    void differentScoreAndBiggestIsLowerThan3ShouldReturnLiteralTranslation(Game testCase) {
        GameScoreTranslator gameTranslator = new GameScoreTranslator();
        ScoreTranslator scoreTranslator = new ScoreTranslator();

        String res = gameTranslator.translate(testCase);

        assertEquals(res, scoreTranslator.translate(testCase.player1Score) + "-" + scoreTranslator.translate(testCase.player2Score));
    }

    @ParameterizedTest
    @MethodSource("sameScoreAndLowerThan3Cases")
    void sameScoreAndAtLeastOneLowerThan3ShouldTranslateAll(Game testCase) {
        GameScoreTranslator gameTranslator = new GameScoreTranslator();
        ScoreTranslator scoreTranslator = new ScoreTranslator();

        String res = gameTranslator.translate(testCase);

        assertEquals(res, scoreTranslator.translate(testCase.player1Score) + "-All");
        assertEquals(res, scoreTranslator.translate(testCase.player2Score) + "-All");
    }

    @ParameterizedTest
    @MethodSource("sameScoreAndGreaterOrEqualTo3Cases")
    void sameScoreAndGreaterOrEqualTo3ShouldTranslateDeuce(Game testCase) {
        GameScoreTranslator gameTranslator = new GameScoreTranslator();

        String res = gameTranslator.translate(testCase);

        assertEquals(res, "Deuce");
    }

    @ParameterizedTest
    @MethodSource("player1DiffOf1WithPlayer2AndGreaterOrEqualTo4Cases")
    void player1DiffOf1WithPlayer2AndGreaterOrEqualTo4ShouldTranslateAdvantagePlayer1(Game testCase) {
        GameScoreTranslator gameTranslator = new GameScoreTranslator();

        String res = gameTranslator.translate(testCase);

        assertEquals(res, "Advantage Player 1");
    }

    @ParameterizedTest
    @MethodSource("player2DiffOf1WithPlayer1AndGreaterOrEqualTo4Cases")
    void player2DiffOf1WithPlayer1AndGreaterOrEqualTo4ShouldTranslateAdvantagePlayer2(Game testCase) {
        GameScoreTranslator gameTranslator = new GameScoreTranslator();

        String res = gameTranslator.translate(testCase);

        assertEquals(res, "Advantage Player 2");
    }

    @ParameterizedTest
    @MethodSource("player1DiffOf2WithPlayer2AndGreaterOrEqualTo4Cases")
    void player1DiffOf2WithPlayer2AndGreaterOrEqualTo4ShouldTranslateWinPlayer1(Game testCase) {
        GameScoreTranslator gameTranslator = new GameScoreTranslator();

        String res = gameTranslator.translate(testCase);

        assertEquals(res, "Player 1 wins the set");
    }

    @ParameterizedTest
    @MethodSource("player2DiffOf2WithPlayer1AndGreaterOrEqualTo4Cases")
    void player2DiffOf2WithPlayer1AndGreaterOrEqualTo4ShouldTranslateWinPlayer2(Game testCase) {
        GameScoreTranslator gameTranslator = new GameScoreTranslator();

        String res = gameTranslator.translate(testCase);

        assertEquals(res, "Player 2 wins the set");
    }

    @ParameterizedTest
    @MethodSource("scoreDiffGreaterThan2AndBiggestGreaterOrEqualTo4Cases")
    void scoreDiffGreaterThan2AndBiggestGreaterOrEqualTo4ShouldRaiseException(Game testCase) {
        GameScoreTranslator gameTranslator = new GameScoreTranslator();

        assertThrows(IllegalArgumentException.class, () -> gameTranslator.translate(testCase));
    }

    @ParameterizedTest
    @MethodSource("negativeScoreCases")
    void negativeScoreShouldRaiseException(Game testCase) {
        GameScoreTranslator gameTranslator = new GameScoreTranslator();

        assertThrows(IllegalArgumentException.class, () -> gameTranslator.translate(testCase));
    }

}

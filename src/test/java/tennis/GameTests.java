package tennis;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

public class GameTests {

    static class TestCase {
        Integer player1Score;
        Integer player2Score;

        TestCase(Integer player1Score, Integer player2Score) {
            this.player1Score = player1Score;
            this.player2Score = player2Score;
        }
    }

    static Stream<TestCase> differentScoreAndBiggestIsLowerThan3Cases() {
        return Stream.of(
            new TestCase(0, 1),
            new TestCase(0, 2),
            new TestCase(1, 0),
            new TestCase(1, 2),
            new TestCase(2, 0),
            new TestCase(2, 1)
        );
    }

    static Stream<TestCase> sameScoreAndLowerThan3Cases() {
        return Stream.of(
            new TestCase(0, 0),
            new TestCase(1, 1),
            new TestCase(2, 2)
        );
    }

    static Stream<TestCase> sameScoreAndGreaterOrEqualTo3Cases() {
        return Stream.of(
            new TestCase(3, 3),
            new TestCase(10, 10),
            new TestCase(12345, 12345)
        );
    }

    static Stream<TestCase> player1DiffOf1WithPlayer2AndGreaterOrEqualTo4Cases() {
        return Stream.of(
            new TestCase(4,3),
            new TestCase(10,9),
            new TestCase(12345, 12344)
        );
    }

    static Stream<TestCase> player2DiffOf1WithPlayer1AndGreaterOrEqualTo4Cases() {
        return Stream.of(
            new TestCase(3, 4),
            new TestCase(9,10),
            new TestCase(12344, 12345)
        );
    }

    static Stream<TestCase> player1DiffOf2WithPlayer2AndGreaterOrEqualTo4Cases() {
        return Stream.of(
            new TestCase(4,2),
            new TestCase(10,8),
            new TestCase(12345, 12343)
        );
    }

    static Stream<TestCase> player2DiffOf2WithPlayer1AndGreaterOrEqualTo4Cases() {
        return Stream.of(
            new TestCase(2, 4),
            new TestCase(8,10),
            new TestCase(12343, 12345)
        );
    }

    static Stream<TestCase> scoreDiffGreaterThan2AndBiggestGreaterOrEqualTo4Cases() {
        return Stream.of(
            new TestCase(1, 4),
            new TestCase(1, 12345),
            new TestCase(12342, 12345),
            new TestCase(12342, 67890),
            new TestCase(4, 1),
            new TestCase(12345, 1),
            new TestCase(12345, 12342),
            new TestCase(67890, 12342)
        );
    }

    static Stream<TestCase> negativeScoreCases() {
        return Stream.of(
            new TestCase(-1, 4),
            new TestCase(4, -7),
            new TestCase(-3, -6)
        );
    }

    @ParameterizedTest
    @MethodSource("differentScoreAndBiggestIsLowerThan3Cases")
    void differentScoreAndBiggestIsLowerThan3ShouldReturnLiteralTranslation(TestCase testCase) {
        GameScoreTranslator gameTranslator = new GameScoreTranslator();
        ScoreTranslator scoreTranslator = new ScoreTranslator();

        String res = gameTranslator.translate(testCase);

        assertEquals(res, scoreTranslator.translate(testCase.player1Score) + "-" + scoreTranslator.translate(testCase.player2Score));
    }

    @ParameterizedTest
    @MethodSource("sameScoreAndLowerThan3Cases")
    void sameScoreAndAtLeastOneLowerThan3ShouldTranslateAll(TestCase testCase) {
        GameScoreTranslator gameTranslator = new GameScoreTranslator();
        ScoreTranslator scoreTranslator = new ScoreTranslator();

        String res = gameTranslator.translate(testCase);

        assertEquals(res, scoreTranslator.translate(testCase.player1Score) + "-All");
        assertEquals(res, scoreTranslator.translate(testCase.player2Score) + "-All");
    }

    @ParameterizedTest
    @MethodSource("sameScoreAndGreaterOrEqualTo3Cases")
    void sameScoreAndGreaterOrEqualTo3ShouldTranslateDeuce(TestCase testCase) {
        GameScoreTranslator gameTranslator = new GameScoreTranslator();

        String res = gameTranslator.translate(testCase);

        assertEquals(res, "Deuce");
    }

    @ParameterizedTest
    @MethodSource("player1DiffOf1WithPlayer2AndGreaterOrEqualTo4Cases")
    void player1DiffOf1WithPlayer2AndGreaterOrEqualTo4ShouldTranslateAdvantagePlayer1(TestCase testCase) {
        GameScoreTranslator gameTranslator = new GameScoreTranslator();

        String res = gameTranslator.translate(testCase);

        assertEquals(res, "Advantage Player 1");
    }

    @ParameterizedTest
    @MethodSource("player2DiffOf1WithPlayer1AndGreaterOrEqualTo4Cases")
    void player2DiffOf1WithPlayer1AndGreaterOrEqualTo4ShouldTranslateAdvantagePlayer2(TestCase testCase) {
        GameScoreTranslator gameTranslator = new GameScoreTranslator();

        String res = gameTranslator.translate(testCase);

        assertEquals(res, "Advantage Player 2");
    }

    @ParameterizedTest
    @MethodSource("player1DiffOf2WithPlayer2AndGreaterOrEqualTo4Cases")
    void player1DiffOf2WithPlayer2AndGreaterOrEqualTo4ShouldTranslateWinPlayer1(TestCase testCase) {
        GameScoreTranslator gameTranslator = new GameScoreTranslator();

        String res = gameTranslator.translate(testCase);

        assertEquals(res, "Player 1 wins the set");
    }

    @ParameterizedTest
    @MethodSource("player2DiffOf2WithPlayer1AndGreaterOrEqualTo4Cases")
    void player2DiffOf2WithPlayer1AndGreaterOrEqualTo4ShouldTranslateWinPlayer2(TestCase testCase) {
        GameScoreTranslator gameTranslator = new GameScoreTranslator();

        String res = gameTranslator.translate(testCase);

        assertEquals(res, "Player 2 wins the set");
    }

    @ParameterizedTest
    @MethodSource("scoreDiffGreaterThan2AndBiggestGreaterOrEqualTo4Cases")
    void scoreDiffGreaterThan2AndBiggestGreaterOrEqualTo4ShouldRaiseException(TestCase testCase) {
        GameScoreTranslator gameTranslator = new GameScoreTranslator();

        assertThrows(IllegalArgumentException.class, () -> gameTranslator.translate(testCase));
    }

    @ParameterizedTest
    @MethodSource("negativeScoreCases")
    void negativeScoreShouldRaiseException(TestCase testCase) {
        GameScoreTranslator gameTranslator = new GameScoreTranslator();

        assertThrows(IllegalArgumentException.class, () -> gameTranslator.translate(testCase));
    }

}

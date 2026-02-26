package tennis;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

public class SetTests {

    static class TestCase {
        Integer player1WonGames;
        Integer player2WonGames;

        TestCase(Integer player1WonGames, Integer player2WonGames) {
            this.player1WonGames = player1WonGames;
            this.player2WonGames = player2WonGames;
        }
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

    static Stream<TestCase> gameDiffGreaterThan2AndBiggestGreaterOrEqualTo4Cases() {
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

    static Stream<TestCase> negativeGameCases() {
        return Stream.of(
                new TestCase(-1, 4),
                new TestCase(4, -7),
                new TestCase(-3, -6)
        );
    }

    static Stream<TestCase> biggestIsLowerThan4OrDiffLowerThan2Cases() {
        return Stream.of(
            new TestCase(0, 0),
            new TestCase(1, 3),
            new TestCase(6, 7),
            new TestCase(150, 149)
        );
    }

    @ParameterizedTest
    @MethodSource("player1DiffOf2WithPlayer2AndGreaterOrEqualTo4Cases")
    void player1DiffOf2WithPlayer2AndGreaterOrEqualTo4ShouldTranslateWinPlayer1(TestCase testCase) {
        SetTranslator setTranslator = new SetTranslator();

        String res = setTranslator.translate(testCase);

        assertEquals(res, "Player 1 wins the match");
    }

    @ParameterizedTest
    @MethodSource("player2DiffOf2WithPlayer1AndGreaterOrEqualTo4Cases")
    void player2DiffOf2WithPlayer1AndGreaterOrEqualTo4ShouldTranslateWinPlayer2(TestCase testCase) {
        SetTranslator setTranslator = new SetTranslator();

        String res = setTranslator.translate(testCase);

        assertEquals(res, "Player 2 wins the match");
    }

    @ParameterizedTest
    @MethodSource("gameDiffGreaterThan2AndBiggestGreaterOrEqualTo4Cases")
    void gameDiffGreaterThan2AndBiggestGreaterOrEqualTo4ShouldRaiseException(TestCase testCase) {
        SetTranslator setTranslator = new SetTranslator();

        assertThrows(IllegalArgumentException.class, () -> setTranslator.translate(testCase));
    }

    @ParameterizedTest
    @MethodSource("negativeGameCases")
    void negativeGameShouldRaiseException(TestCase testCase) {
        SetTranslator setTranslator = new SetTranslator();

        assertThrows(IllegalArgumentException.class, () -> setTranslator.translate(testCase));
    }

    @ParameterizedTest
    @MethodSource("biggestIsLowerThan4OrDiffLowerThan2Cases")
    void biggestIsLowerThan4OrDiffLowerThan2ShouldShowSetScore(TestCase testCase) {
        SetTranslator setTranslator = new SetTranslator();

        String res = setTranslator.translate(testCase);

        assertEquals(res, testCase.player1WonGames + "-" + testCase.player2WonGames);
    }

}

package tennis;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

public class SetTests {
    
    static Stream<Set> player1DiffOf2WithPlayer2AndGreaterOrEqualTo4Cases() {
        return Stream.of(
                new Set(4,2),
                new Set(10,8),
                new Set(12345, 12343)
        );
    }

    static Stream<Set> player2DiffOf2WithPlayer1AndGreaterOrEqualTo4Cases() {
        return Stream.of(
        new Set(2, 4),
                new Set(8,10),
                new Set(12343, 12345)
        );
    }

    static Stream<Set> gameDiffGreaterThan2AndBiggestGreaterOrEqualTo4Cases() {
        return Stream.of(
                new Set(1, 4),
                new Set(1, 12345),
                new Set(12342, 12345),
                new Set(12342, 67890),
                new Set(4, 1),
                new Set(12345, 1),
                new Set(12345, 12342),
                new Set(67890, 12342)
        );
    }

    static Stream<Set> negativeGameCases() {
        return Stream.of(
                new Set(-1, 4),
                new Set(4, -7),
                new Set(-3, -6)
        );
    }

    static Stream<Set> biggestIsLowerThan4OrDiffLowerThan2Cases() {
        return Stream.of(
            new Set(0, 0),
            new Set(1, 3),
            new Set(6, 7),
            new Set(150, 149)
        );
    }

    @ParameterizedTest
    @MethodSource("player1DiffOf2WithPlayer2AndGreaterOrEqualTo4Cases")
    void player1DiffOf2WithPlayer2AndGreaterOrEqualTo4ShouldTranslateWinPlayer1(Set testCase) {
        SetTranslator setTranslator = new SetTranslator();

        String res = setTranslator.translate(testCase);

        assertEquals(res, "Player 1 wins the match");
    }

    @ParameterizedTest
    @MethodSource("player2DiffOf2WithPlayer1AndGreaterOrEqualTo4Cases")
    void player2DiffOf2WithPlayer1AndGreaterOrEqualTo4ShouldTranslateWinPlayer2(Set testCase) {
        SetTranslator setTranslator = new SetTranslator();

        String res = setTranslator.translate(testCase);

        assertEquals(res, "Player 2 wins the match");
    }

    @ParameterizedTest
    @MethodSource("gameDiffGreaterThan2AndBiggestGreaterOrEqualTo4Cases")
    void gameDiffGreaterThan2AndBiggestGreaterOrEqualTo4ShouldRaiseException(Set testCase) {
        SetTranslator setTranslator = new SetTranslator();

        assertThrows(IllegalArgumentException.class, () -> setTranslator.translate(testCase));
    }

    @ParameterizedTest
    @MethodSource("negativeGameCases")
    void negativeGameShouldRaiseException(Set testCase) {
        SetTranslator setTranslator = new SetTranslator();

        assertThrows(IllegalArgumentException.class, () -> setTranslator.translate(testCase));
    }

    @ParameterizedTest
    @MethodSource("biggestIsLowerThan4OrDiffLowerThan2Cases")
    void biggestIsLowerThan4OrDiffLowerThan2ShouldShowSetScore(Set testCase) {
        SetTranslator setTranslator = new SetTranslator();

        String res = setTranslator.translate(testCase);

        assertEquals(res, testCase.player1Score + "-" + testCase.player2Score);
    }

}

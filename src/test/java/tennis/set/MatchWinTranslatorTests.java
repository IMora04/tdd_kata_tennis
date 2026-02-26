package tennis.set;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MatchWinTranslatorTests {

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

}

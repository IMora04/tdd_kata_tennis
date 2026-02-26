package tennis;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SetWinTranslatorTests {

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

}

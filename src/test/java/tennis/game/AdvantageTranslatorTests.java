package tennis.game;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import tennis.game.Game;
import tennis.game.GameScoreTranslator;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdvantageTranslatorTests {

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

}

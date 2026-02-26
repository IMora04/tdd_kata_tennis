package tennis.game;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeuceTranslatorTests {

    static Stream<Game> sameScoreAndGreaterOrEqualTo3Cases() {
        return Stream.of(
                new Game(3, 3),
                new Game(10, 10),
                new Game(12345, 12345)
        );
    }

    @ParameterizedTest
    @MethodSource("sameScoreAndGreaterOrEqualTo3Cases")
    void sameScoreAndGreaterOrEqualTo3ShouldTranslateDeuce(Game testCase) {
        GameScoreTranslator gameTranslator = new GameScoreTranslator();

        String res = gameTranslator.translate(testCase);

        assertEquals(res, "Deuce");
    }

}

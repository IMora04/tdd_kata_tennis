package tennis;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

public class GameTests {

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

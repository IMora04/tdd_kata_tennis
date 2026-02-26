package tennis.game;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import tennis.game.Game;
import tennis.game.GameScoreTranslator;
import tennis.game.ScoreTranslator;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleScoreTranslatorTests {

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

    @ParameterizedTest
    @MethodSource("differentScoreAndBiggestIsLowerThan3Cases")
    void differentScoreAndBiggestIsLowerThan3ShouldReturnLiteralTranslation(Game testCase) {
        GameScoreTranslator gameTranslator = new GameScoreTranslator();
        ScoreTranslator scoreTranslator = new ScoreTranslator();

        String res = gameTranslator.translate(testCase);

        assertEquals(res, scoreTranslator.translate(testCase.player1Score) + "-" + scoreTranslator.translate(testCase.player2Score));
    }

}

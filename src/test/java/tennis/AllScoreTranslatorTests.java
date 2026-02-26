package tennis;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AllScoreTranslatorTests {

    static Stream<Game> sameScoreAndLowerThan3Cases() {
        return Stream.of(
                new Game(0, 0),
                new Game(1, 1),
                new Game(2, 2)
        );
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

}

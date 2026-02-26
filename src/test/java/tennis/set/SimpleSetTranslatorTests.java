package tennis.set;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleSetTranslatorTests {

    static Stream<Set> biggestIsLowerThan4OrDiffLowerThan2Cases() {
        return Stream.of(
                new Set(0, 0),
                new Set(1, 3),
                new Set(6, 7),
                new Set(150, 149)
        );
    }

    @ParameterizedTest
    @MethodSource("biggestIsLowerThan4OrDiffLowerThan2Cases")
    void biggestIsLowerThan4OrDiffLowerThan2ShouldShowSetScore(Set testCase) {
        SetTranslator setTranslator = new SetTranslator();

        String res = setTranslator.translate(testCase);

        assertEquals(res, testCase.player1Score + "-" + testCase.player2Score);
    }

}

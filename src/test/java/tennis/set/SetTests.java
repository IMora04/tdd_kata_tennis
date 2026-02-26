package tennis.set;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

public class SetTests {

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

}

package tennis;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ScoreTranslatorTests {

    static class TestCase {
        int number;
        String expectedResult;

        TestCase(int number, String expectedResult) {
            this.number = number;
            this.expectedResult = expectedResult;
        }

        @Override
        public String toString() {
            return String.format("%d should return \"%s\"", number, expectedResult);
        }
    }

    static Stream<TestCase> expectedNumbersCases() {
        return Stream.of(
    new TestCase(0, "Love"),
            new TestCase(1, "Fifteen"),
            new TestCase(2, "Thirty"),
            new TestCase(3, "Forty")
        );
    }

    static Stream<Integer> unexpectedNumbersCases() {
        return Stream.of(
    -1, 100, -100
        );
    }

    @ParameterizedTest
    @MethodSource("expectedNumbersCases")
    void expectedNumbersShouldBeTranslated(TestCase testCase) {
        ScoreTranslator scoreTranslator = new ScoreTranslator();

        String res = scoreTranslator.translate(testCase.number);

        assertEquals(res, testCase.expectedResult);
    }

    @ParameterizedTest
    @MethodSource("unexpectedNumbersCases")
    void unexpectedNumbersShouldRaiseException(Integer number) {
        ScoreTranslator scoreTranslator = new ScoreTranslator();

        assertThrows(IllegalArgumentException.class, () -> scoreTranslator.translate(number));
    }

}

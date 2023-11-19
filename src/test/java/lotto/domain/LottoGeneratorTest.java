package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.List;
import org.junit.jupiter.api.Test;

public class LottoGeneratorTest {
    @Test
    void 로또_생성_테스트() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        assertThatCode(() -> new LottoNotRandomGenerator(numbers))
                .doesNotThrowAnyException();
    }
}

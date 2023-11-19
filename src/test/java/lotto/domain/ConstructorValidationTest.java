package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;

public class ConstructorValidationTest {
    @Test
    void 로또_정상_생성_테스트() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        assertThatCode(() -> new Lotto(numbers))
                .doesNotThrowAnyException();
    }

    @Test
    void 로또_생성_예외_테스트_사이즈_다른_경우() {
        List<Integer> numbers = List.of(0, 2, 3, 4, 5, 6, 7);
        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @Test
    void 로또_생성_예외_테스트_중복된_숫자() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 5);
        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @Test
    void 로또_생성_예외_테스트_범위_밖_숫자() {
        List<Integer> numbers = List.of(0, 2, 3, 4, 5, 6);
        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }
}
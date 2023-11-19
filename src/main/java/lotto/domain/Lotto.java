package lotto.domain;

import java.util.List;
import lotto.view.output.dto.LottoDto;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException();
        }
    }

    // TODO: 추가 기능 구현
    public LottoDto toDto() {
        return new LottoDto(numbers);
    }
}

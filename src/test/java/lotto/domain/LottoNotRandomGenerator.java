package lotto.domain;

import java.util.List;

public class LottoNotRandomGenerator implements LottoGenerator {
    private final List<Integer> numbers;

    public LottoNotRandomGenerator(List<Integer> numbers) {
        this.numbers = numbers;
    }

    @Override
    public Lotto generateLotto() {
        return new Lotto(numbers);
    }
}

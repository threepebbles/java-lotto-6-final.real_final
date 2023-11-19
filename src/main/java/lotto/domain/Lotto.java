package lotto.domain;

import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import lotto.ErrorMessage;
import lotto.view.input.dto.WinningLottoDto;
import lotto.view.output.dto.LottoDto;

public class Lotto implements Iterable<Integer> {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validateSize(numbers);
        validateDuplication(numbers);
        numbers.forEach(this::validateInRange);
        this.numbers = numbers.stream()
                .sorted()
                .toList();
    }

    public static Lotto createLotto(WinningLottoDto winningLottoDto) {
        return new Lotto(winningLottoDto.getNumbers());
    }

    public LottoDto toLottoDto() {
        return new LottoDto(numbers);
    }

    public WinningLottoDto toWinningLottoDto() {
        return new WinningLottoDto(numbers);
    }

    private void validateSize(List<Integer> numbers) {
        final int LOTTO_SIZE = 6;
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(ErrorMessage.getErrorMessage("올바른 로또 번호 개수가 아닙니다."));
        }
    }

    private void validateDuplication(List<Integer> numbers) {
        if (numbers.stream()
                .distinct()
                .count() != numbers.size()) {
            throw new IllegalArgumentException(ErrorMessage.getErrorMessage("중복된 번호가 있습니다."));
        }
    }

    private void validateInRange(Integer number) {
        final int LOTTO_NUMBER_MIN = 1;
        final int LOTTO_NUMBER_MAX = 45;
        if (number < LOTTO_NUMBER_MIN || number > LOTTO_NUMBER_MAX) {
            throw new IllegalArgumentException(ErrorMessage.getErrorMessage("올바른 범위의 로또 번호가 아닙니다."));
        }
    }

    public boolean contains(Integer target) {
        return numbers.contains(target);
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    @Override
    public Iterator<Integer> iterator() {
        return numbers.iterator();
    }

    @Override
    public void forEach(Consumer<? super Integer> action) {
        numbers.forEach(action);
    }
}

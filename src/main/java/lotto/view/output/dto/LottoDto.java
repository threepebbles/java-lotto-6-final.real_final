package lotto.view.output.dto;

import java.util.List;

public class LottoDto {
    private List<Integer> lotto;

    public LottoDto(List<Integer> lotto) {
        this.lotto = lotto;
    }

    @Override
    public String toString() {
        return lotto.toString();
    }
}

package lotto.view.output.dto;

import java.util.List;

public class LottosDto {
    public static final String LINE_SEPARATOR = System.lineSeparator();
    private final List<LottoDto> lottos;

    public LottosDto(List<LottoDto> lottos) {
        this.lottos = lottos;
    }

    public int getSize() {
        return lottos.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        lottos.forEach(lotto ->
                sb.append(lotto.toString())
                        .append(LINE_SEPARATOR));
        return sb.toString();
    }
}
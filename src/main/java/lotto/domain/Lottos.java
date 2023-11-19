package lotto.domain;

import java.util.List;
import lotto.view.output.dto.LottosDto;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public LottosDto toDto() {
        return new LottosDto(lottos.stream()
                .map(Lotto::toDto)
                .toList());
    }
}
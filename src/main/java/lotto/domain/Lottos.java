package lotto.domain;

import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import lotto.view.output.dto.LottosDto;

public class Lottos implements Iterable<Lotto> {
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public LottosDto toLottosDto() {
        return new LottosDto(lottos.stream()
                .map(Lotto::toLottoDto)
                .toList());
    }

    public int size() {
        return lottos.size();
    }

    @Override
    public Iterator<Lotto> iterator() {
        return lottos.iterator();
    }

    @Override
    public void forEach(Consumer<? super Lotto> action) {
        lottos.forEach(action);
    }
}
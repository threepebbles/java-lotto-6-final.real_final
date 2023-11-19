package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import lotto.ErrorMessage;

public class LottoMachine {
    public static final int LOTTO_UNIT_PRICE = 1000;
    private LottoGenerator lottoGenerator;

    public LottoMachine(LottoGenerator lottoGenerator) {
        this.lottoGenerator = lottoGenerator;
    }

    public int calculateQuantity(int money) {
        if (money % LOTTO_UNIT_PRICE != 0) {
            throw new IllegalArgumentException(ErrorMessage.getErrorMessage("올바른 금액이 아닙니다."));
        }
        return money / LOTTO_UNIT_PRICE;
    }

    private Lottos generateLottosByQuantity(int quantity) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            lottos.add(lottoGenerator.generateLotto());
        }
        return new Lottos(lottos);
    }

    public Lottos generateLottosByMoney(int money) {
        List<Lotto> lottos = new ArrayList<>();
        int quantity = calculateQuantity(money);
        return generateLottosByQuantity(quantity);
    }
}
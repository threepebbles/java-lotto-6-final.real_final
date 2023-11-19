package lotto.service;

import lotto.domain.LottoMachine;
import lotto.domain.LottoRandomGenerator;
import lotto.domain.Lottos;

public class LottoPurchaseService {
    public Lottos requestPurchaseLottos(int money) {
        LottoMachine lottoMachine = new LottoMachine(new LottoRandomGenerator());
        return lottoMachine.generateLottosByMoney(money);
    }
}
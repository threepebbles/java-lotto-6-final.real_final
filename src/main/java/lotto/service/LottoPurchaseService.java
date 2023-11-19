package lotto.service;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoMachine;
import lotto.domain.LottoRandomGenerator;

public class LottoPurchaseService {
    public List<Lotto> requestPurchaseLottos(int money) {
        LottoMachine lottoMachine = new LottoMachine(new LottoRandomGenerator());
        return lottoMachine.generateLottosByMoney(money);
    }
}
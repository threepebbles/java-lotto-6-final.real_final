package lotto.service;

import java.util.HashMap;
import java.util.Map;
import lotto.domain.Lotto;
import lotto.domain.LottoMachine;
import lotto.domain.LottoPrize;
import lotto.domain.Lottos;
import lotto.domain.WinningNumbers;
import lotto.domain.WinningStatistics;

public class LottoMatcherService {
    public WinningStatistics calculateWinningStatistics(WinningNumbers winningNumbers, Lottos lottos) {
        Map<LottoPrize, Integer> prizeCount = new HashMap<>();
        initPrizeCount(prizeCount);

        long sum = 0;
        for (Lotto lotto : lottos) {
            int matchCount = winningNumbers.calculateMatchCount(lotto);
            boolean isBonusMatched = winningNumbers.isBonusMatched(lotto);
            LottoPrize currentPrize = LottoPrize.valueOf(matchCount, isBonusMatched);
            addPrize(prizeCount, currentPrize);
            sum += currentPrize.getAmount();
        }
        removeNothing(prizeCount);
        Double rateOfReturn = (double) sum / (lottos.size() * LottoMachine.LOTTO_UNIT_PRICE) * 100.0;
        return new WinningStatistics(prizeCount, rateOfReturn);
    }

    private void initPrizeCount(Map<LottoPrize, Integer> prizeCount) {
        for (LottoPrize lottoPrize : LottoPrize.values()) {
            prizeCount.put(lottoPrize, 0);
        }
    }

    private void addPrize(Map<LottoPrize, Integer> prizeCount, LottoPrize currentPrize) {
        if (prizeCount.get(currentPrize) == null) {
            prizeCount.put(currentPrize, 1);
            return;
        }
        prizeCount.put(currentPrize, prizeCount.get(currentPrize) + 1);
    }

    private void removeNothing(Map<LottoPrize, Integer> prizeCount) {
        if (prizeCount.get(LottoPrize.NOTHING) != null) {
            prizeCount.remove(LottoPrize.NOTHING);
        }
    }
}
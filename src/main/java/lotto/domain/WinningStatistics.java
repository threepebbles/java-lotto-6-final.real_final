package lotto.domain;

import java.util.Map;
import lotto.view.output.dto.WinningStatisticsDto;

public class WinningStatistics {
    private final Map<LottoPrize, Integer> prizeCount;

    public WinningStatistics(Map<LottoPrize, Integer> prizeCount) {
        this.prizeCount = prizeCount;
    }

    public WinningStatisticsDto toWinningStatisticsDto() {
        return new WinningStatisticsDto(prizeCount);
    }

    private Long calculateTotalPrizeSum() {
        long sum = 0;
        for (LottoPrize lottoPrize : prizeCount.keySet()) {
            sum += (long) countPrize(lottoPrize) * lottoPrize.getAmount();
        }
        return sum;
    }

    public Double calculateRateOfReturn(Lottos purchased) {
        int totalAmount = purchased.size() * LottoMachine.LOTTO_UNIT_PRICE;
        return (double) calculateTotalPrizeSum() / totalAmount * 100.0;
    }

    public int countPrize(LottoPrize lottoPrize) {
        if (prizeCount.get(lottoPrize) == null) {
            return 0;
        }
        return prizeCount.get(lottoPrize);
    }
}
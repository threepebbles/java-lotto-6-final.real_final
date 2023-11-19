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
            sum += (long) prizeCount.get(lottoPrize) * lottoPrize.getAmount();
        }
        return sum;
    }

    public Double calculateRateOfReturn(Lottos purchased) {
        int totalAmount = purchased.size() * LottoMachine.LOTTO_UNIT_PRICE;
        Double rateOfReturn = (double) calculateTotalPrizeSum() / totalAmount;
        return rateOfReturn;
    }
}
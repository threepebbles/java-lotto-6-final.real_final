package lotto.domain;

import java.util.Map;
import lotto.view.output.dto.PrizeCountDto;
import lotto.view.output.dto.RateOfReturnDto;

public class WinningStatistics {
    private final Map<LottoPrize, Integer> prizeCount;
    private final double rateOfReturn;

    public WinningStatistics(Map<LottoPrize, Integer> prizeCount, Double rateOfReturn) {
        this.prizeCount = prizeCount;
        this.rateOfReturn = rateOfReturn;
    }

    public PrizeCountDto toPrizeCountDto() {
        return new PrizeCountDto(prizeCount);
    }

    public RateOfReturnDto toRateOfReturnDto() {
        return new RateOfReturnDto(rateOfReturn);
    }
}
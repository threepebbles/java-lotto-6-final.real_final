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
}
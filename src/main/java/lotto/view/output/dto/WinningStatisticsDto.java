package lotto.view.output.dto;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import lotto.domain.LottoPrize;

public class WinningStatisticsDto {
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private final Map<LottoPrize, Integer> prizeCount;

    public WinningStatisticsDto(Map<LottoPrize, Integer> prizeCount) {
        this.prizeCount = prizeCount;
    }

    @Override
    public String toString() {
        List<LottoPrize> sortedPrizes = Arrays.stream(LottoPrize.values())
                .sorted(Comparator.comparing(LottoPrize::getAmount))
                .toList();

        StringBuilder sb = new StringBuilder();
        for (LottoPrize lottoPrize : sortedPrizes) {
            if (lottoPrize == LottoPrize.NOTHING) {
                continue;
            }
            sb.append(lottoPrizeToStatisticsString(lottoPrize, prizeCount.get(lottoPrize)))
                    .append(LINE_SEPARATOR);
        }
        return sb.toString();
    }

    private String lottoPrizeToStatisticsString(LottoPrize lottoPrize, int count) {
        return String.format("%d개 일치%s (%,d원) - %,d개",
                lottoPrize.getMatchCount(),
                hasBonus(lottoPrize),
                lottoPrize.getAmount(),
                count);
    }

    private String hasBonus(LottoPrize lottoPrize) {
        if (lottoPrize.doesBonusNumberMatters()) {
            return ", 보너스 볼 일치";
        }
        return "";
    }
}
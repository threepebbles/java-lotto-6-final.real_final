package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class WinningStatisticsTest {
    @Test
    void 수익률_계산_테스트() {
        LottoGenerator lottoGenerator = new LottoRandomGenerator();
        Map<LottoPrize, Integer> prizecount = new HashMap<>();
        prizecount.put(LottoPrize.SECOND, 2);
        prizecount.put(LottoPrize.THIRD, 3);
        WinningStatistics winningStatistics = new WinningStatistics(prizecount);
        Lottos purchased = new Lottos(
                List.of(
                        lottoGenerator.generateLotto(),
                        lottoGenerator.generateLotto(),
                        lottoGenerator.generateLotto(),
                        lottoGenerator.generateLotto(),
                        lottoGenerator.generateLotto()
                )
        );
        long expectedSum = LottoPrize.SECOND.getAmount() * 2L + LottoPrize.THIRD.getAmount() * 3L;
        Double expectedRateOfReturn = (double) expectedSum / 5000.0 * 100.0;
        String expected = String.format("%.1f", expectedRateOfReturn);

        // when
        Double actualRateOfReturn = winningStatistics.calculateRateOfReturn(purchased);
        String actual = String.format("%.1f", actualRateOfReturn);

        // then
        assertThat(actual).isEqualTo(expected);
    }
}
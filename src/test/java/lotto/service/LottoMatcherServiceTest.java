package lotto.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoPrize;
import lotto.domain.Lottos;
import lotto.domain.WinningNumbers;
import lotto.domain.WinningStatistics;
import org.junit.jupiter.api.Test;

public class LottoMatcherServiceTest {
    @Test
    void 당첨_통계_계산_테스트() {
        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        int bonusNumbers = 7;
        WinningNumbers winningNumbers = new WinningNumbers(winningLotto, bonusNumbers);
        Lottos purchased = new Lottos(
                List.of(
                        new Lotto(List.of(1, 2, 3, 4, 5, 6)),   // 1등
                        new Lotto(List.of(1, 2, 3, 4, 5, 7)),   // 2등
                        new Lotto(List.of(1, 2, 3, 4, 5, 16)),   // 3등
                        new Lotto(List.of(1, 2, 3, 4, 15, 16)),  // 4등
                        new Lotto(List.of(1, 2, 3, 4, 15, 16)),  // 4등
                        new Lotto(List.of(11, 12, 13, 14, 15, 16))  // 꽝
                )
        );

        LottoMatcherService lottoMatcherService = new LottoMatcherService();
        WinningStatistics actualWinningStatistics = lottoMatcherService.calculateWinningStatistics(winningNumbers,
                purchased);

        assertThat(actualWinningStatistics.countPrize(LottoPrize.FIRST))
                .isEqualTo(1);
        assertThat(actualWinningStatistics.countPrize(LottoPrize.SECOND))
                .isEqualTo(1);
        assertThat(actualWinningStatistics.countPrize(LottoPrize.THIRD))
                .isEqualTo(1);
        assertThat(actualWinningStatistics.countPrize(LottoPrize.FOURTH))
                .isEqualTo(2);
        assertThat(actualWinningStatistics.countPrize(LottoPrize.FIFTH))
                .isEqualTo(0);
    }
}
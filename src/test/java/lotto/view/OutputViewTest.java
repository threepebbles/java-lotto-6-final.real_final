package lotto.view;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lotto.domain.LottoPrize;
import lotto.view.output.OutputView;
import lotto.view.output.dto.LottoDto;
import lotto.view.output.dto.LottosDto;
import lotto.view.output.dto.PrizeCountDto;
import lotto.view.output.dto.RateOfReturnDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OutputViewTest {

    private final OutputView outputView = new OutputView();
    private ByteArrayOutputStream output;

    @BeforeEach
    void setup() {
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
    }

    @AfterEach
    void restore() {
        System.setOut(System.out);
        output.reset();
    }

    @Test
    void 발행한_로또_수량_및_번호_출력_테스트() {
        LottosDto lottosDto = new LottosDto(
                List.of(
                        new LottoDto(List.of(1, 2, 3, 4, 5, 6)),
                        new LottoDto(List.of(1, 2, 3, 4, 5, 7)),
                        new LottoDto(List.of(1, 2, 3, 4, 5, 8))
                )
        );
        outputView.printPurchasedLottoScreen(lottosDto);

        assertThat(output.toString())
                .contains("3개를 구매했습니다.",
                        "[1, 2, 3, 4, 5, 6]",
                        "[1, 2, 3, 4, 5, 7]",
                        "[1, 2, 3, 4, 5, 8]");
    }

    @Test
    void 당첨_내역_출력_테스트() {
        Map<LottoPrize, Integer> prizeCount = new HashMap<>() {{
            put(LottoPrize.FIFTH, 5);
            put(LottoPrize.FOURTH, 3);
            put(LottoPrize.THIRD, 1);
            put(LottoPrize.SECOND, 1);
            put(LottoPrize.FIRST, 4);
            put(LottoPrize.NOTHING, 10);
        }};
        PrizeCountDto prizeCountDto = new PrizeCountDto(prizeCount);

        outputView.printPrizeCountScreen(prizeCountDto);

        assertThat(output.toString())
                .contains("당첨 통계",
                        "---",
                        "3개 일치 (5,000원) - 5개",
                        "4개 일치 (50,000원) - 3개",
                        "5개 일치 (1,500,000원) - 1개",
                        "5개 일치, 보너스 볼 일치 (30,000,000원) - 1개",
                        "6개 일치 (2,000,000,000원) - 4개"
                );
    }

    @Test
    void 수익률_출력_테스트() {
        Double rateOfReturn = 1231234.5767;
        RateOfReturnDto rateOfReturnDto = new RateOfReturnDto(rateOfReturn);
        outputView.printRateOfReturnScreen(rateOfReturnDto);

        assertThat(output.toString())
                .contains("총 수익률은 1,231,234.6%입니다.");
    }
}
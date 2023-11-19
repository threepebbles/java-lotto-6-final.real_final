package lotto.view.output;

import lotto.view.output.dto.LottosDto;
import lotto.view.output.dto.PrizeCountDto;
import lotto.view.output.dto.RateOfReturnDto;

public class OutputView {
    public static final String LINE_SEPARATOR = System.lineSeparator();
    public static final String PURCHASED_LOTTO_SCREEN = "%,d개를 구매했습니다." + LINE_SEPARATOR
            + "%s" + LINE_SEPARATOR;

    public static final String PRIZE_COUNT_SCREEN = "당첨 통계" + LINE_SEPARATOR
            + "---" + LINE_SEPARATOR
            + "%s";
    private static final String RATE_OF_RETURN_SCREEN = "총 수익률은 %s%%입니다." + LINE_SEPARATOR;

    public void printPurchasedLottoScreen(LottosDto lottosDto) {
        System.out.printf(PURCHASED_LOTTO_SCREEN,
                lottosDto.getSize(), lottosDto.toString());
    }

    public void printPrizeCountScreen(PrizeCountDto prizeCountDto) {
        System.out.print(LINE_SEPARATOR);
        System.out.printf(PRIZE_COUNT_SCREEN,
                prizeCountDto.toString());
    }

    public void printRateOfReturnScreen(RateOfReturnDto rateOfReturnDto) {
        System.out.printf(RATE_OF_RETURN_SCREEN,
                rateOfReturnDto.toString());
    }
}
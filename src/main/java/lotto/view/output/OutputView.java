package lotto.view.output;

import lotto.view.output.dto.LottosDto;
import lotto.view.output.dto.RateOfReturnDto;
import lotto.view.output.dto.WinningStatisticsDto;

public class OutputView {
    public static final String LINE_SEPARATOR = System.lineSeparator();
    public static final String PURCHASED_LOTTO_SCREEN = "%,d개를 구매했습니다." + LINE_SEPARATOR
            + "%s" + LINE_SEPARATOR;

    public static final String WINNING_STATISTICS_SCREEN = "당첨 통계" + LINE_SEPARATOR
            + "---" + LINE_SEPARATOR
            + "%s";
    private static final String RATE_OF_RETURN_SCREEN = "총 수익률은 %s%%입니다." + LINE_SEPARATOR;

    public void printPurchasedLottoScreen(LottosDto lottosDto) {
        System.out.printf(PURCHASED_LOTTO_SCREEN,
                lottosDto.getSize(), lottosDto.toString());
    }

    public void printWinningStatisticsScreen(WinningStatisticsDto winningStatisticsDto) {
        System.out.print(LINE_SEPARATOR);
        System.out.printf(WINNING_STATISTICS_SCREEN,
                winningStatisticsDto.toString());
    }

    public void printRateOfReturnScreen(RateOfReturnDto rateOfReturnDto) {
        System.out.printf(RATE_OF_RETURN_SCREEN,
                rateOfReturnDto.toString());
    }
}
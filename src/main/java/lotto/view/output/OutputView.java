package lotto.view.output;

import lotto.view.output.dto.LottosDto;

public class OutputView {
    public static final String LINE_SEPARATOR = System.lineSeparator();
    public static final String PURCHASED_LOTTO_SCREEN = "%,d개를 구매했습니다." + LINE_SEPARATOR
            + "%s" + LINE_SEPARATOR;

    public void printPurchasedLottoScreen(LottosDto lottosDto) {
        System.out.printf(PURCHASED_LOTTO_SCREEN,
                lottosDto.getSize(), lottosDto.toString());
    }
}
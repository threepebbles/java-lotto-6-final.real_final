package lotto.view.input;

import camp.nextstep.edu.missionutils.Console;
import java.util.function.Supplier;
import lotto.view.input.dto.PurchaseAmountDto;
import lotto.view.input.dto.WinningLottoDto;

public class InputView {
    public static final String LINE_SEPARATOR = System.lineSeparator();
    public static final String ENTER_PURCHASE_AMOUNT = "구입금액을 입력해 주세요.";
    public static final String ENTER_WINNING_LOTTO = "당첨 번호를 입력해 주세요.";

    public PurchaseAmountDto requestPurchaseAmountDto() {
        return (PurchaseAmountDto) retryUntilSuccess(() -> {
            String purchaseAmount = scanPurchaseAmount();
            return PurchaseAmountDto.createPurchaseAmountDto(purchaseAmount);
        });
    }

    private String scanPurchaseAmount() {
        System.out.println(ENTER_PURCHASE_AMOUNT);
        return Console.readLine();
    }

    public WinningLottoDto requestWinningLottoDto() {
        return (WinningLottoDto) retryUntilSuccess(() -> {
            String winningLotto = scanWinningLotto();
            return WinningLottoDto.createWinningLottoDto(winningLotto);
        });
    }

    private String scanWinningLotto() {
        System.out.println(ENTER_WINNING_LOTTO);
        return Console.readLine();
    }

    private <T> Object retryUntilSuccess(Supplier<T> function) {
        while (true) {
            try {
                return function.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
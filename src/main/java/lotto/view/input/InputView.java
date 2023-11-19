package lotto.view.input;

import camp.nextstep.edu.missionutils.Console;
import java.util.function.Supplier;
import lotto.view.input.dto.BonusNumberDto;
import lotto.view.input.dto.PurchaseAmountDto;
import lotto.view.input.dto.WinningLottoDto;

public class InputView {
    public static final String LINE_SEPARATOR = System.lineSeparator();
    public static final String ENTER_PURCHASE_AMOUNT = "구입금액을 입력해 주세요." + LINE_SEPARATOR;
    public static final String ENTER_WINNING_LOTTO = "당첨 번호를 입력해 주세요." + LINE_SEPARATOR;
    private static final String ENTER_BONUS_NUMBER = "보너스 번호를 입력해 주세요." + LINE_SEPARATOR;

    public PurchaseAmountDto requestPurchaseAmountDto() {
        return (PurchaseAmountDto) retryUntilSuccess(() -> {
            System.out.print(ENTER_PURCHASE_AMOUNT);
            String purchaseAmount = Console.readLine();
            return PurchaseAmountDto.createPurchaseAmountDto(purchaseAmount);
        });
    }

    public WinningLottoDto requestWinningLottoDto() {
        return (WinningLottoDto) retryUntilSuccess(() -> {
            System.out.print(ENTER_WINNING_LOTTO);
            String winningLotto = Console.readLine();
            return WinningLottoDto.createWinningLottoDto(winningLotto);
        });
    }

    public BonusNumberDto requestBonusNumberDto() {
        return (BonusNumberDto) retryUntilSuccess(() -> {
            System.out.print(ENTER_BONUS_NUMBER);
            String bonusNumber = Console.readLine();
            return BonusNumberDto.createBonusNumberDto(bonusNumber);
        });
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
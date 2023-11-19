package lotto.controller;

import java.util.function.Supplier;
import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.domain.WinningNumbers;
import lotto.service.LottoPurchaseService;
import lotto.view.input.InputView;
import lotto.view.output.OutputView;

public class MainController {

    private final InputView inputView;
    private final OutputView outputView;
    private final LottoPurchaseService lottoPurchaseService = new LottoPurchaseService();

    public MainController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        int purchaseAmount = requestPurchaseAmount();

        Lottos purchased = lottoPurchaseService.requestPurchaseLottos(purchaseAmount);
        outputView.printPurchasedLottoScreen(purchased.toDto());
        WinningNumbers winningNumbers = requestWinningNumbers();
        
    }

    private WinningNumbers requestWinningNumbers() {
        Lotto winningLotto = requestWinningLotto();
        int bonusNumber = requestBonusNumber();
        return new WinningNumbers(winningLotto, bonusNumber);
    }

    private int requestBonusNumber() {
        return inputView.requestBonusNumberDto()
                .getBonusNumber();
    }

    private Lotto requestWinningLotto() {
        return (Lotto) retryUntilSuccess(() -> Lotto.createDto(inputView.requestWinningLottoDto()));
    }

    private int requestPurchaseAmount() {
        return inputView.requestPurchaseAmountDto()
                .getPurchaseAmount();
    }

    private <T> Object retryUntilSuccess(Supplier<T> function) {
        while (true) {
            try {
                return function.get();
            } catch (IllegalArgumentException e) {
                inputView.printlnMessage(e.getMessage());
            }
        }
    }
}
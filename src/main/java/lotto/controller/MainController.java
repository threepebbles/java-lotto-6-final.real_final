package lotto.controller;

import java.util.function.Supplier;
import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.domain.WinningNumbers;
import lotto.domain.WinningStatistics;
import lotto.service.LottoMatcherService;
import lotto.service.LottoPurchaseService;
import lotto.view.input.InputView;
import lotto.view.output.OutputView;

public class MainController {

    private final InputView inputView;
    private final OutputView outputView;
    private final LottoPurchaseService lottoPurchaseService = new LottoPurchaseService();

    private final LottoMatcherService lottoMatcherService = new LottoMatcherService();

    public MainController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        int purchaseAmount = requestPurchaseAmount();

        Lottos purchased = lottoPurchaseService.requestPurchaseLottos(purchaseAmount);
        outputView.printPurchasedLottoScreen(purchased.toDto());
        WinningNumbers winningNumbers = requestWinningNumbers();

        WinningStatistics winningStatistics = lottoMatcherService.calculateWinningStatistics(winningNumbers, purchased);
        outputView.printWinningStatisticsScreen(winningStatistics.toWinningStatisticsDto());
    }

    private WinningNumbers requestWinningNumbers() {
        Lotto winningLotto = requestWinningLotto();
        int bonusNumber = requestBonusNumber(winningLotto);
        return new WinningNumbers(winningLotto, bonusNumber);
    }

    private int requestPurchaseAmount() {
        return inputView.requestPurchaseAmountDto()
                .getPurchaseAmount();
    }

    private Lotto requestWinningLotto() {
        return (Lotto) retryUntilSuccess(() ->
                Lotto.createLotto(inputView.requestWinningLottoDto()));
    }

    private Integer requestBonusNumber(Lotto winningLotto) {
        return (Integer) retryUntilSuccess(() ->
                inputView.requestBonusNumberDto(winningLotto.toWinningLottoDto())
                        .getBonusNumber());
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
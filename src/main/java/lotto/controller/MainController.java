package lotto.controller;

import lotto.view.input.InputView;
import lotto.view.output.OutputView;

public class MainController {

    private final InputView inputView;
    private final OutputView outputView;

    public MainController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        int purchaseAmount = requestPurchaseAmount();


    }

    private int requestPurchaseAmount() {
        return inputView.requestPurchaseAmountDto()
                .getPurchaseAmount();
    }
}
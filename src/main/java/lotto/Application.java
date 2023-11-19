package lotto;

import lotto.controller.MainController;
import lotto.view.input.InputView;
import lotto.view.output.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        MainController mainController = new MainController(inputView, outputView);
        mainController.run();
    }
}
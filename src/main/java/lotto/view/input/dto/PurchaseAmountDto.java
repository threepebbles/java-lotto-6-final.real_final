package lotto.view.input.dto;

import java.util.Objects;
import lotto.ErrorMessage;

public class PurchaseAmountDto {
    private final int purchaseAmount;

    public PurchaseAmountDto(int purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
    }

    public static PurchaseAmountDto createPurchaseAmountDto(String userInput) {
        validateNull(userInput);
        validateBlank(userInput);
        try {
            int amount = Integer.parseInt(userInput);
            return new PurchaseAmountDto(amount);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.getPurchaseAmountErrorMessage());
        }
    }

    private static void validateBlank(String userInput) {
        if (userInput.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.getPurchaseAmountErrorMessage());
        }
    }

    private static void validateNull(String userInput) {
        if (Objects.isNull(userInput)) {
            throw new IllegalArgumentException(ErrorMessage.getPurchaseAmountErrorMessage());
        }
    }
}
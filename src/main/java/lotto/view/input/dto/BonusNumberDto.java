package lotto.view.input.dto;

import java.util.Objects;
import lotto.ErrorMessage;

public class BonusNumberDto {
    private final int bonusNumber;

    public BonusNumberDto(int bonusNumber) {
        this.bonusNumber = bonusNumber;
    }

    public static BonusNumberDto createBonusNumberDto(String userInput) {
        validate(userInput);

        return new BonusNumberDto(Integer.parseInt(userInput));
    }

    private static void validate(String userInput) {
        validateNull(userInput);
        validateBlank(userInput);
        validateInteger(userInput);
    }

    private static void validateInteger(String number) {
        try {
            Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.getWinningLottoErrorMessage());
        }
    }

    private static void validateNull(String userInput) {
        if (Objects.isNull(userInput)) {
            throw new IllegalArgumentException(ErrorMessage.getBonusNumberErrorMessage());
        }
    }

    private static void validateBlank(String userInput) {
        if (userInput.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.getBonusNumberErrorMessage());
        }
    }

    public int getBonusNumber() {
        return bonusNumber;
    }
}
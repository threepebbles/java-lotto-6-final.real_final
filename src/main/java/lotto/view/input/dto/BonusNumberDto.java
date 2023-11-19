package lotto.view.input.dto;

import java.util.Objects;
import lotto.ErrorMessage;

public class BonusNumberDto {
    private final int bonusNumber;

    public BonusNumberDto(int bonusNumber) {
        this.bonusNumber = bonusNumber;
    }

    public static BonusNumberDto createBonusNumberDto(String userInput, WinningLottoDto winningLottoDto) {
        validateNull(userInput);
        validateBlank(userInput);
        validateInteger(userInput);
        int bonusNumber = Integer.parseInt(userInput);
        validateDuplication(bonusNumber, winningLottoDto);
        return new BonusNumberDto(bonusNumber);
    }

    private static void validateDuplication(int bonusNumber, WinningLottoDto winningLottoDto) {
        if (winningLottoDto.getNumbers().contains(bonusNumber)) {
            throw new IllegalArgumentException(ErrorMessage.getBonusNumberErrorMessage());
        }
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
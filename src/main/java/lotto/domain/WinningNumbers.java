package lotto.domain;

import lotto.ErrorMessage;

public class WinningNumbers {
    private final Lotto lotto;
    private final int bonusNumber;

    public WinningNumbers(Lotto lotto, int bonusNumber) {
        validateBonusNumber(lotto, bonusNumber);
        this.lotto = lotto;
        this.bonusNumber = bonusNumber;
    }

    private void validateBonusNumber(Lotto lotto, int bonusNumber) {
        validateInRange(bonusNumber);
        validateDuplication(lotto, bonusNumber);
    }

    private void validateDuplication(Lotto lotto, int bonusNumber) {
        if (lotto.contains(bonusNumber)) {
            throw new IllegalArgumentException(ErrorMessage.getBonusNumberErrorMessage());
        }
    }

    private void validateInRange(int bonusNumber) {
        final int LOTTO_NUMBER_MIN = 1;
        final int LOTTO_NUMBER_MAX = 45;
        if (bonusNumber < LOTTO_NUMBER_MIN || bonusNumber > LOTTO_NUMBER_MAX) {
            throw new IllegalArgumentException(ErrorMessage.getBonusNumberErrorMessage());
        }
    }
}
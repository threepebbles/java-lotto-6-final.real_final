package lotto.view.input.dto;

import java.util.List;
import java.util.Objects;
import lotto.ErrorMessage;
import lotto.util.Parser;

public class WinningLottoDto {
    public static final int LOTTO_SIZE = 6;
    public static final String COMMA = ",";

    private final List<Integer> numbers;

    public WinningLottoDto(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public static WinningLottoDto createWinningLottoDto(String userInput) {
        validate(userInput);

        List<String> numbers = Parser.parseWithDelimiter(userInput, COMMA);
        return new WinningLottoDto(numbers.stream()
                .mapToInt(Integer::parseInt)
                .boxed()
                .toList());
    }

    private static void validate(String userInput) {
        validateNull(userInput);
        validateBlank(userInput);

        List<String> numbers = Parser.parseWithDelimiter(userInput, COMMA);
        validateSize(numbers.size());
        numbers.forEach(WinningLottoDto::validateInteger);
    }

    private static void validateSize(int size) {
        if (size != LOTTO_SIZE) {
            throw new IllegalArgumentException(ErrorMessage.getWinningLottoErrorMessage());
        }
    }

    private static void validateInteger(String number) {
        try {
            Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.getWinningLottoErrorMessage());
        }
    }

    private static void validateBlank(String userInput) {
        if (userInput.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.getWinningLottoErrorMessage());
        }
    }

    private static void validateNull(String userInput) {
        if (Objects.isNull(userInput)) {
            throw new IllegalArgumentException(ErrorMessage.getWinningLottoErrorMessage());
        }
    }
}
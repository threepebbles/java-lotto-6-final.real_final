package lotto;

public class ErrorMessage {
    public static final String ERROR_HEADER = "[ERROR]";
    public static final String WHITE_SPACE = " ";
    public static final String RETRY = "다시 입력해 주세요.";
    public static final String NOT_PROPER_PURCHASE_AMOUNT = "유효하지 않은 구입 금액입니다.";

    private static String getErrorMessage(String message) {
        return ERROR_HEADER + WHITE_SPACE
                + message + WHITE_SPACE
                + RETRY;
    }

    public static String getPurchaseAmountErrorMessage() {
        return getErrorMessage(NOT_PROPER_PURCHASE_AMOUNT);
    }
}
package lotto.view.output.dto;

public class RateOfReturnDto {
    private final Double rateOfReturn;

    public RateOfReturnDto(Double rateOfReturn) {
        this.rateOfReturn = rateOfReturn;
    }

    @Override
    public String toString() {
        int intPart = (int) Math.floor(rateOfReturn);
        int floatPart = (int) (Math.round((rateOfReturn - intPart) * 10));
        return String.format("%,d.%d", intPart, floatPart);
    }
}
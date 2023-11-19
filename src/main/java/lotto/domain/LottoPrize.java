package lotto.domain;

public enum LottoPrize {
    NOTHING(0, 0, false),
    FIFTH(5_000, 3, false),
    FOURTH(50_000, 4, false),
    THIRD(1_500_000, 5, false),
    SECOND(30_000_000, 5, true),
    FIRST(2_000_000_000, 6, false);
    private final int amount;
    private final int matchCount;
    private final boolean bonusNumberMatters;

    LottoPrize(int amount, int matchCount, boolean bonusNumberMatters) {
        this.amount = amount;
        this.matchCount = matchCount;
        this.bonusNumberMatters = bonusNumberMatters;
    }

    public int getAmount() {
        return amount;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public boolean doesBonusNumberMatters() {
        return bonusNumberMatters;
    }
}
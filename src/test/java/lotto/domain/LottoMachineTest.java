package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class LottoMachineTest {
    @Test
    void 로또_개수_계산_테스트() {
        int money = 8000;
        LottoMachine lottoMachine = new LottoMachine(new LottoRandomGenerator());
        int expectedCount = 8;

        int actualCount = lottoMachine.calculateQuantity(money);

        assertThat(actualCount).isEqualTo(expectedCount);
    }

    @Test
    void 금액에_맞게_로또_생성_하는지_테스트() {
        int money = 8000;
        LottoMachine lottoMachine = new LottoMachine(new LottoRandomGenerator());
        int expectedCount = 8;

        Lottos lottos = lottoMachine.generateLottosByMoney(money);

        assertThat(lottos.size()).isEqualTo(expectedCount);
    }
}
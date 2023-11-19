package lotto.view;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import lotto.view.output.OutputView;
import lotto.view.output.dto.LottoDto;
import lotto.view.output.dto.LottosDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OutputViewTest {

    private final OutputView outputView = new OutputView();
    private ByteArrayOutputStream output;

    @BeforeEach
    void setup() {
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
    }

    @AfterEach
    void restore() {
        System.setOut(System.out);
        output.reset();
    }

    @Test
    void 발행한_로또_수량_및_번호_출력_테스트() {
        LottosDto lottosDto = new LottosDto(
                List.of(
                        new LottoDto(List.of(1, 2, 3, 4, 5, 6)),
                        new LottoDto(List.of(1, 2, 3, 4, 5, 7)),
                        new LottoDto(List.of(1, 2, 3, 4, 5, 8))
                )
        );
        outputView.printPurchasedLottoScreen(lottosDto);

        assertThat(output.toString())
                .contains("3개를 구매했습니다.",
                        "[1, 2, 3, 4, 5, 6]",
                        "[1, 2, 3, 4, 5, 7]",
                        "[1, 2, 3, 4, 5, 8]");
    }
}

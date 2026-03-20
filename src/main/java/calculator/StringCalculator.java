package calculator;

import entity.Expression;

import java.util.List;

/**
 * 진입점. 흐름 조율만 담당한다.
 * 파싱, 계산, 이력 관리를 직접 구현하지 말 것.
 * 각 책임은 별도 클래스에 위임할 것.
 */
public class StringCalculator {

    CalculationHistory history = new CalculationHistory();
    InputParser parser = new InputParser();
    Operate operate = new Operate();

    public double calculate(String input) {

        double result = operate.Operate(parser.parse(input));

        history.save(input, result);
        return result;
    }

    public List<String> getHistory() {
        List<String> allReverse = history.findAllReverse();
        return allReverse;
    }
}

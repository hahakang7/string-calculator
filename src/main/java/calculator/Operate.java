package calculator;

import entity.Expression;

import java.util.Arrays;
import java.util.function.DoubleBinaryOperator;

public class Operate {

    public double Operate(Expression expression) {

        double[] numbers = expression.getNumbers();
        OperationType operator = expression.getOperator();

        //null이 들어가거나 0을 입력하거나 숫자가 하나만 들어간 경우
        if (numbers == null || numbers.length == 0) {
            return 0.0;
        }

        // 숫자 하나인 경우
        if (numbers.length == 1) {
            return numbers[0];
        }

        double result = Arrays.stream(numbers)
                .reduce((a, b) -> calculate(a, b, operator))
                .orElse(0.0);

        //소수점 첫번째 까지만
        return Math.floor(result * 10) / 10.0;

    }

    // 각 연산자 타입에 따른 실제 계산 로직 (람다에서 호출됨)
    private double calculate(double left, double right, OperationType opType) {
        return switch (opType) {
            case PLUS -> left + right;
            case MINUS -> left - right;
            case TIMES -> left * right;
            case DIVIDE -> {
                //0으로 못나누게
                if (right == 0) {
                    throw new IllegalArgumentException("0으로 나눌 수 없습니다.");
                }
                yield left / right;
            }
        };
    }
}

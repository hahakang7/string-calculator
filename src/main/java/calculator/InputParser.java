package calculator;

import entity.Expression;

import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * 힌트: 입력 문자열 파싱을 담당하는 클래스.
 * 구분자 추출, 숫자 토큰 분리, 연산자 추출 등을 여기서 처리할 수 있다.
 * 반드시 이 클래스를 사용할 필요는 없다. 자유롭게 설계할 것.
 */
public class InputParser {

    public Expression parse(String input) {

        //0인경우 and null 검증
        if(input == null || input.isEmpty()) {
            return new Expression(OperationType.PLUS, new double[]{0});
        }


        // 케이스 3 연산자지정
        if (input.startsWith("op=")) {
            String opSymbol = input.substring(3, 4);
            OperationType operator = getOperator(opSymbol);
            String target = input.substring(5); //숫자부분
            return new Expression(operator, splitNumbers(target, ",|:"));
        }

        // 케이스 2 커스텀 구분자 (// 로 시작)
        if (input.startsWith("//")) {
            String customDelimiter = input.substring(2, 3);
            String target = input.substring(4); // "//x\n" 이후
            // 커스텀 구분자 하나만 사용하므로 DEFAULT_DELIMITER는 무시
            return new Expression(OperationType.PLUS, splitNumbers(target, Pattern.quote(customDelimiter)));
        }


        // 케이스 1: 기본 덧셈 바로 리턴하기 때문에 맨 밑에
        return new Expression(OperationType.PLUS, splitNumbers(input, ",|:"));


    }

    private double[] splitNumbers(String target, String delimiter) {
        // Stream과 Lambda를 사용하여 숫자 추출 및 예외 처리
        return Arrays.stream(target.split(delimiter))
                .map(String::trim)
                .filter(token -> !token.isEmpty())
                .mapToDouble(token -> {
                    try {
                        double value = Double.parseDouble(token);
                        // 4. 음수 입력 시 예외 발생
                        if (value < 0) {
                            throw new IllegalArgumentException("음수는 입력할 수 없습니다.");
                        }
                        return value;
                    } catch (NumberFormatException e) {
                        // 4. 숫자가 아닌 값 포함 시 예외 발생
                        throw new IllegalArgumentException("숫자가 아닌 값이 포함되어 있습니다.");
                    }
                })
                .toArray();
    }

    private OperationType getOperator(String operator) {
        switch (operator) {
            case "+" -> {
                return OperationType.PLUS;
            }
            case "-" -> {
                return OperationType.MINUS;
            }
            case "*" -> {
                return OperationType.TIMES;
            }
            case "/" -> {
                return OperationType.DIVIDE;
            }
            default -> throw new IllegalArgumentException("지원하지 않는 연산자입니다");
        }

    }
}

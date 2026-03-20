package entity;

import calculator.OperationType;

public class Expression {

    private final OperationType operator;
    private final double[] numbers;

    public Expression(OperationType operator, double[] numbers) {
        this.numbers = numbers;
        this.operator = operator;
    }

    public OperationType getOperator() {
        return operator;
    }

    public double[] getNumbers() {
        return numbers;
    }
}

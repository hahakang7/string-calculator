package entity;

public class History {
    private final String input;
    private final double result;

    public History(String input, double result) {
        this.input = input;
        this.result = result;
    }

    @Override
    public String toString() {
        return String.format("입력: %s | 결과: %.1f", input, result);
    }
}

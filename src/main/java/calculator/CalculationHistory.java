package calculator;

import entity.History;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 힌트: 연산 이력을 관리하는 클래스.
 * 일급 컬렉션으로 구현해볼 것.
 * 반드시 이 클래스를 사용할 필요는 없다. 자유롭게 설계할 것.
 */
public class CalculationHistory {

    private static final int MAX_HISTORY_SIZE = 10;
    private final List<History> storage = new LinkedList<>();

    public void save(String input, double result) {
        //최대 10개까지 저장, 초과 시 가장 오래된 것 삭제
        if (storage.size() >= MAX_HISTORY_SIZE) {
            storage.remove(0); // 가장 앞(오래된 데이터) 삭제
        }
        storage.add(new History(input, result));
    }

    public List<String> findAllReverse() {
        //가장 최근 연산부터 출력
        // Stream의 인덱스를 역순으로 돌려 새로운 리스트 생성
        return IntStream.range(0, storage.size())
                .map(i -> storage.size() - 1 - i)
                .mapToObj(i -> storage.get(i).toString())
                .collect(Collectors.toList());
    }


}

package sample.cafekiosk.running;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import java.util.Collection;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GuavaLearningTest {

  @DisplayName("주어진 개수만큼 List를 파티셔닝한다.")
  @Test
  void test() {
    //given
    final List<Integer> integers = List.of(1, 2, 3, 4, 5, 6);

    //when
    final List<List<Integer>> partition = Lists.partition(integers, 3);

    //then
    Assertions.assertThat(partition).hasSize(2)
        .isEqualTo(
            List.of(List.of(1, 2, 3), List.of(4, 5, 6))
        );

  }

  @DisplayName("멀티맵 기능 확인")
  @Test
  void tes1t() {
    //given
    Multimap<String, String> multiMap = ArrayListMultimap.create();
    multiMap.put("커피", "아메리카노");
    multiMap.put("커피", "카페라떼");
    multiMap.put("커피", "카푸치노");
    multiMap.put("베이커리", "크루아상");
    multiMap.put("베이커리", "식빵");

    //when
    final Collection<String> strings = multiMap.get("커피");

    Assertions.assertThat(strings).hasSize(3)
        .isEqualTo(List.of("아메리카노", "카페라떼", "카푸치노"));

  }

}

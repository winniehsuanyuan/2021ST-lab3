import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;


import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.PriorityQueue;
import java.util.stream.Stream;

public class PriorityQueueTest {
    private static Stream<Arguments> stringIntAndListProvider(){
        return Stream.of(
                arguments(new int[]{2, 3, 4, 1}, new int[]{1, 2, 3, 4}),
                arguments(new int[]{10, 2, 6, 8}, new int[]{2, 6, 8, 10}),
                arguments(new int[]{5, 8, 9, 3}, new int[]{3, 5, 8, 9}),
                arguments(new int[]{1, 3, 7, 5}, new int[]{1, 3, 5, 7}),
                arguments(new int[]{8, 6, 4, 2}, new int[]{2, 4, 6, 8})
                );
    }

    //parameterization
    @ParameterizedTest(name="#{index} - Test with Argument={0},{1}")
    @MethodSource("stringIntAndListProvider")
    public void PriorityQueue_RunTest(int[] random_array, int[] correct_array){
        PriorityQueue<Integer> test = new PriorityQueue<Integer>();
        int index = 0;
        Integer s;
        int[] result = new int[random_array.length];

        //add elements into priority queue
        for(int e: random_array){
            test.offer(e);
        }

        //take out elements in order
        while((s = test.poll()) != null){
            result[index++] = s;
        }
        //check
        assertArrayEquals(correct_array, result);
    }

    //exception
    @Test
    //initial capacity < 1
    public void initialCapacityTest(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new PriorityQueue(0, null);
        });
    }

    @Test
    //add null element
    public void offerTest(){
        Exception exception = assertThrows(NullPointerException.class, () -> {
            new PriorityQueue().offer(null);
        });
    }

    @Test
    //elements can't be compared
    public void differentElementTypesTest(){
        Exception exception = assertThrows(ClassCastException.class, () -> {
            PriorityQueue test = new PriorityQueue();
            test.offer(0);
            test.offer("0");
        });
    }
}
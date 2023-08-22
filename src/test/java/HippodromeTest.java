import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.isNull;

public class HippodromeTest {

    @ParameterizedTest
    @NullAndEmptySource
    public void constructorTest(List<Horse> horses){
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(horses));
        if (horses==null){assertEquals("Horses cannot be null.", exception.getMessage());}
        else if(horses.isEmpty()){assertEquals("Horses cannot be empty.", exception.getMessage());}
    }

    @Test
    public void getHorsesTest(){
        List<Horse> horses = new ArrayList<>();
        IntStream.range(0,30).forEach(x -> horses.add(new Horse("A" + x,x,x)));
        Hippodrome hippodrome = new Hippodrome(horses);
        assertTrue(horses.equals(hippodrome.getHorses()));
    }

    @Test
    public void getWinnerTest(){
        List<Horse> horses = new ArrayList<>();
        Horse horse1 = new Horse("A", 1, 1);
        Horse horse2 = new Horse("B", 1, 2);
        Horse horse3 = new Horse("C", 1, 3);
        horses.add(horse1);
        horses.add(horse2);
        horses.add(horse3);
        Hippodrome hippodrome = new Hippodrome(horses);
        assertTrue(horse3.equals(hippodrome.getWinner()));
    }
}

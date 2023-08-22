import org.junit.jupiter.api.Test;

import static java.util.Objects.isNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.times;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class HorseTest {

    Horse horseTest = new Horse("A",1,1);

    @ParameterizedTest
    @CsvSource(
            {", 1, 0",
            "'\n',1,0",
            "'\t',1,0",
            "A, -1, 0",
            "A, 1, -1"})
    public void construct(String name, double speed, double distance){
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse(name,speed,distance));
        if (isNull(name)){assertEquals("Name cannot be null.", exception.getMessage());}
        else if (name.isBlank()){assertEquals("Name cannot be blank.", exception.getMessage());}
        if (speed<0){assertEquals("Speed cannot be negative.", exception.getMessage());}
        if (distance<0){assertEquals("Distance cannot be negative.", exception.getMessage());}
    }

    @Test
    public void getNameTest(){
        assertEquals("A",horseTest.getName());
    }

    @Test
    public void getSpeedTest(){
        assertEquals(1,horseTest.getSpeed());
    }

    @Test
    public void getDistanceTest(){
        assertEquals(1,horseTest.getDistance());
        Horse h = new Horse("B",1);
        assertEquals(0,h.getDistance());
    }

    @Test
    public void moveTest(){
       try(MockedStatic<Horse> mockitoHorse = Mockito.mockStatic(Horse.class)){
        horseTest.move();
        mockitoHorse.verify(() -> Horse.getRandomDouble(0.2,0.9));
       }
    }

    @ParameterizedTest
    @CsvSource({
            "6, 5",
            "7, 6",
            "11, 10"
    })
    public void moveCalculationTest(double result, double randomDouble){
        try(MockedStatic<Horse> mockitoHorse = Mockito.mockStatic(Horse.class)){
            mockitoHorse.when(() ->Horse.getRandomDouble(anyDouble(),anyDouble())).thenReturn(randomDouble);
            Horse horseTest2 = new Horse("B",1,1);
            horseTest2.move();
            assertEquals(result, horseTest2.getDistance());
        }
    }
}

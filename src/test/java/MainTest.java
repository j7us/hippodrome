import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

public class MainTest {

    @Test
    @Timeout(value = 22, unit = TimeUnit.SECONDS)
    public void mainTest(){
       try{
           Main.main(null);
       }catch (Exception e){e.printStackTrace();}
    }
}

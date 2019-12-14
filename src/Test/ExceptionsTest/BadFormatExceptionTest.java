package Test.ExceptionsTest;

import Data.DataExceptions.BadFormatException;
import Data.HealthCardID;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BadFormatExceptionTest {


    @Test
    public void exceptionTesting(){
        Throwable exception = assertThrows(BadFormatException.class,
                () -> {
                    throw new BadFormatException("Invalid code format.");
                });
        assertEquals("Invalid code format.", exception.getMessage());
    }

    @Test
    public void BadFormatTest(){
        assertThrows(BadFormatException.class, () -> {new HealthCardID("9132ASDBAS");});
    }

}

package Test.ExceptionsTest;

import Data.DataExceptions.NullObjectException;
import Data.HealthCardID;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NullObjectExceptionTest {
    @Test
    public void exceptionTesting(){
        Throwable exception = assertThrows(NullObjectException.class,
                () -> {
                    throw new NullObjectException("Invalid code format.");
                });
        assertEquals("Invalid code format.", exception.getMessage());
    }

    @Test
    public void NullObjectTest(){
        assertThrows(NullObjectException.class, () -> {new HealthCardID(null);});
    }
}

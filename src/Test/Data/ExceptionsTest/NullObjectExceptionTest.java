package Test.Data.ExceptionsTest;

import Data.Exceptions.NullObjectException;
import Data.HealthCardID;
import Data.PatientContr;
import Data.ProductID;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class NullObjectExceptionTest {

    @Test
    public void HealthCardNullTest(){
        assertThrows(NullObjectException.class, () -> {new HealthCardID(null);});
    }

    @Test
    public void PatientContNullTest(){
        assertThrows(NullObjectException.class, () -> {new PatientContr(null);});
    }

    @Test
    public void ProductIDNullTest(){
        assertThrows(NullObjectException.class, () -> {new ProductID(null);});
    }
}

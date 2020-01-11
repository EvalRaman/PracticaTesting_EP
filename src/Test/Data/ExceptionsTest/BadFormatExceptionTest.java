package Test.Data.ExceptionsTest;

import Data.Exceptions.BadFormatException;
import Data.HealthCardID;
import Data.ProductID;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class BadFormatExceptionTest {


    @Test
    public void LengthTest(){
        assertThrows(BadFormatException.class, () -> {new HealthCardID("");});
    }

    @Test
    public void LettersTest(){ assertThrows(BadFormatException.class, () -> {new HealthCardID("AB/D123456789012");}); }

    @Test
    public void DigitsTest() { assertThrows(BadFormatException.class, () -> {new HealthCardID("ABCD123456A89012");}); }

    @Test
    public void UPCTest() { assertThrows(BadFormatException.class, () -> {new ProductID("");}); }
}

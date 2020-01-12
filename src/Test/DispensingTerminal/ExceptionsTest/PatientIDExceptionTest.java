package Test.DispensingTerminal.ExceptionsTest;

import Data.Exceptions.BadFormatException;
import Data.Exceptions.NullObjectException;
import Data.HealthCardID;
import DispensingTerminal.Exceptions.PatientIDException;
import Pharmacy.Dispensing;
import Pharmacy.Exceptions.DispensingNotAvailableException;
import Pharmacy.ProductSpecification;
import Services.Exceptions.HealthCardException;
import Services.HealthCardReader;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class PatientIDExceptionTest {
    @Test
    public void PatientIdTest() {

        HealthCardReader healthCardReaderDouble = new HealthCardReaderDouble();
        assertThrows(PatientIDException.class , () -> {healthCardReaderDouble.getHealthCardID();});
    }

    public static class HealthCardReaderDouble implements HealthCardReader {

        public HealthCardID getHealthCardID() throws HealthCardException, PatientIDException {
            try {
                return new HealthCardID("125469876321");
            }catch (BadFormatException | NullObjectException e){
                throw new PatientIDException("Not a correctID");
            }
        }

    }
}

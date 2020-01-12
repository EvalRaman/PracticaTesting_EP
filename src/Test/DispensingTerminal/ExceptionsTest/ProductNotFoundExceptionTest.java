package Test.DispensingTerminal.ExceptionsTest;

import Data.Exceptions.BadFormatException;
import Data.Exceptions.NullObjectException;
import Data.HealthCardID;
import Data.PatientContr;
import Data.ProductID;
import DispensingTerminal.Exceptions.PatientIDException;
import DispensingTerminal.Exceptions.ProductNotFoundException;
import Pharmacy.Dispensing;
import Pharmacy.ProductSpecification;
import Services.Exceptions.ConnectException;
import Services.Exceptions.HealthCardException;
import Services.Exceptions.NotValidePrescriptionException;
import Services.Exceptions.ProductIDException;
import Services.HealthCardReader;
import Services.NationalHealthService;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductNotFoundExceptionTest {
    @Test
    public void ProductNotFoundExceptionTest() {

        ProductNotFoundDouble productNotFoundDouble = new ProductNotFoundDouble();
        assertThrows(PatientIDException.class , () -> {productNotFoundDouble.getProductSpecific(new ProductID("659874613259"));});
    }

    public static class ProductNotFoundDouble implements NationalHealthService {

        @Override
        public Dispensing getePrescription(HealthCardID hcID) throws HealthCardException, NotValidePrescriptionException, ConnectException {
            return null;
        }

        @Override
        public PatientContr getPatientContr(HealthCardID hcID) throws ConnectException {
            return null;
        }

        @Override
        public ProductSpecification getProductSpecific(ProductID pID) throws ProductIDException, ConnectException, ProductNotFoundException {
            try {
                if(!pID.equals(new ProductID("123456789012"))){
                    throw new ProductNotFoundException("Product not found");
                }
                return new ProductSpecification(pID, "TestingException", new BigDecimal("1"));
            }catch  (BadFormatException | NullObjectException e){
                return null;
            }
        }

        @Override
        public List<Dispensing> updateePrescription(HealthCardID hcID, Dispensing disp) throws ConnectException {
            return null;
        }

    }
}

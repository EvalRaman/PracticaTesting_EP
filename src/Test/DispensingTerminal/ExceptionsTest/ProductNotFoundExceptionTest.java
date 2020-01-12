package Test.DispensingTerminal.ExceptionsTest;

import Data.Exceptions.BadFormatException;
import Data.Exceptions.NullObjectException;
import Data.HealthCardID;
import Data.PatientContr;
import Data.ProductID;
import DispensingTerminal.Exceptions.ProductNotFoundException;
import Pharmacy.Dispensing;
import Pharmacy.ProductSpecification;
import Services.NationalHealthService;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductNotFoundExceptionTest {
    @Test
    public void ProductNotFoundExceptionTest() {

        ProductNotFoundDouble productNotFoundDouble = new ProductNotFoundDouble();
        assertThrows(ProductNotFoundException.class , () -> {productNotFoundDouble.getProductSpecific(new ProductID("659874613259"));});
    }

    public static class ProductNotFoundDouble implements NationalHealthService {

        @Override
        public Dispensing getePrescription(HealthCardID hcID){
            return null;
        }

        @Override
        public PatientContr getPatientContr(HealthCardID hcID){
            return null;
        }

        @Override
        public ProductSpecification getProductSpecific(ProductID pID) throws ProductNotFoundException {
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
        public List<Dispensing> updateePrescription(HealthCardID hcID, Dispensing disp){
            return null;
        }

    }
}

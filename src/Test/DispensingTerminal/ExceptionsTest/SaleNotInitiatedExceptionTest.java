package Test.DispensingTerminal.ExceptionsTest;


import Data.ProductID;
import DispensingTerminal.DispensingTerminal;
import DispensingTerminal.Exceptions.SaleNotInitiatedException;
import Services.HealthCardReader;
import Services.NationalHealthService;
import org.junit.Test;



import static org.junit.jupiter.api.Assertions.assertThrows;

public class SaleNotInitiatedExceptionTest {
    @Test
    public void SaleNotInitiatedExceptionTest() {
        int saleCode = 0;

        HealthCardReader HCReader = null;
        NationalHealthService SNS = null;

        DispensingTerminal dispensingTerminal = new DispensingTerminal(saleCode,HCReader,SNS);
        assertThrows(SaleNotInitiatedException.class , () -> {dispensingTerminal.enterProduct(new ProductID("123456789012"));});
    }

    @Test
    public void SaleNotInitiatedfinalExceptionTest() {
        int saleCode = 0;

        HealthCardReader HCReader = null;
        NationalHealthService SNS = null;

        DispensingTerminal dispensingTerminal = new DispensingTerminal(saleCode,HCReader,SNS);
        assertThrows(SaleNotInitiatedException.class , () -> {dispensingTerminal.finalizeSale();});
    }

}

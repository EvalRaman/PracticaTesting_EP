package Test.DispensingTerminal.ExceptionsTest;

import Data.Exceptions.BadFormatException;
import Data.Exceptions.NullObjectException;
import Data.HealthCardID;
import Data.PatientContr;
import Data.ProductID;
import DispensingTerminal.DispensingTerminal;
import DispensingTerminal.Exceptions.PatientIDException;
import DispensingTerminal.Exceptions.ProductNotFoundException;
import DispensingTerminal.Exceptions.SaleNotInitiatedException;
import Pharmacy.Dispensing;
import Pharmacy.ProductSpecification;
import Pharmacy.Sale;
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

public class SaleNotInitiatedExceptionTest {
    @Test
    public void SaleNotInitiatedExceptionTest() {
        HealthCardID hCardID = null;
        Dispensing disp;
        Sale sale;
        BigDecimal price;
        int saleCode = 0;

        HealthCardReader HCReader = null;
        NationalHealthService SNS = null;

        DispensingTerminal dispensingTerminal = new DispensingTerminal(saleCode,HCReader,SNS);
        assertThrows(SaleNotInitiatedException.class , () -> {dispensingTerminal.initNewSale();});
    }

    @Test
    public void SaleNotInitiatedfinalExceptionTest() {
        HealthCardID hCardID = null;
        Dispensing disp;
        Sale sale;
        BigDecimal price;
        int saleCode = 0;

        HealthCardReader HCReader = null;
        NationalHealthService SNS = null;

        DispensingTerminal dispensingTerminal = new DispensingTerminal(saleCode,HCReader,SNS);
        assertThrows(SaleNotInitiatedException.class , () -> {dispensingTerminal.finalizeSale();});
    }

}

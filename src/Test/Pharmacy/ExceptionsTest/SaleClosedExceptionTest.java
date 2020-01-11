package Test.Pharmacy.ExceptionsTest;


import Data.Exceptions.BadFormatException;
import Data.Exceptions.NullObjectException;
import Data.PatientContr;
import Data.ProductID;
import Pharmacy.Exceptions.DispensingNotCompletedException;
import Pharmacy.Exceptions.SaleClosedException;
import Pharmacy.Sale;

import java.math.BigDecimal;
import java.util.Date;


import org.junit.Test;



import static org.junit.jupiter.api.Assertions.assertThrows;

public class SaleClosedExceptionTest {


    @Test
    public void closedSaleTest() throws SaleClosedException, BadFormatException, NullObjectException {
        Sale new_sale = new Sale(1, new Date());
        new_sale.addLine(new ProductID("123456789012"), new BigDecimal("2"), new PatientContr(new BigDecimal("20")));
        new_sale.addLine(new ProductID("123456789013"), new BigDecimal("3"), new PatientContr(new BigDecimal("27")));
        new_sale.calculateFinalAmount();
        assertThrows(SaleClosedException.class, () -> {new_sale.calculateFinalAmount();});
    }



}

package Test.Pharmacy;

import Data.Exceptions.BadFormatException;
import Data.Exceptions.NullObjectException;
import Data.ProductID;
import Pharmacy.Dispensing;
import Pharmacy.ProductSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DispensingTest {

    Dispensing dispense;

    @BeforeEach
    public void setUp() {
        byte order = 0x0a;
        dispense = new Dispensing(order, new Date(0), new Date(99999999));
    }

    @Test
    public void getDescription(){
        assertEquals("Bonito, Barato", product.getDescription());
    }

    @Test
    public void getPrice() { assertEquals(new BigDecimal("7.234"), product.getPrice());
    }



}

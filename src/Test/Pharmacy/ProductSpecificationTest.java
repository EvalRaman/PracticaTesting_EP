package Test.Pharmacy;

import Data.Exceptions.BadFormatException;
import Data.Exceptions.NullObjectException;
import Data.ProductID;
import Pharmacy.ProductSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;
import java.math.BigDecimal;

public class ProductSpecificationTest {

    ProductSpecification product;

    @BeforeEach
    public void setUp() throws NullObjectException, BadFormatException {
        product = new ProductSpecification(new ProductID("123456789012"), "Bonito, Barato", new BigDecimal("7.234"));
    }

    @Test
    public void getDescription(){
        assertEquals("Bonito, Barato", product.getDescription());
    }

    @Test
    public void getPrice() { assertEquals(new BigDecimal("7.234"), product.getPrice());
    }



}

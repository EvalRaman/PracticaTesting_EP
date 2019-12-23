package Test;

import Data.ProductID;
import Data.Exceptions.*;
import org.junit.jupiter.api.*;
import static org.junit.Assert.*;

public class ProductIDTest {
    ProductID product;

    @BeforeEach
    public void setUp() throws NullObjectException, BadFormatException {
        product = new ProductID("123456789012");
    }

    @Test
    public void getPersonalIDTest(){
        assertEquals("123456789012", product.getUPC());
    }

    @Test
    public void equalsTest() throws NullObjectException, BadFormatException{
        assertEquals(new ProductID("123456789012"), product);
    }

    @Test
    public void hashCodeTest() throws NullObjectException, BadFormatException{
        assertEquals(new ProductID("123456789012").hashCode(), product.hashCode());
    }

    @Test
    public void toStringTest(){
        assertEquals("ProductID{" + "Universal Product Code='" + "123456789012" + '\'' + '}', product.toString());
    }
}

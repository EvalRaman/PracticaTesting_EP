package Test.Pharmacy;

import Data.Exceptions.BadFormatException;
import Data.Exceptions.NullObjectException;
import Data.ProductID;
import Pharmacy.Dispensing;
import Pharmacy.Exceptions.*;
import Pharmacy.ProductSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DispensingTest {

    Dispensing dispense;
    ProductSpecification product1, product2, product3;

    @BeforeEach
    public void setUp() throws NullObjectException, BadFormatException, ParseException {
        byte order = 0x0a;
        product1 = new ProductSpecification(new ProductID("123456789012"), "Carn", new BigDecimal(3));
        product2 = new ProductSpecification(new ProductID("123456789013"), "Peix", new BigDecimal(6));
        product3 = new ProductSpecification(new ProductID("123456789014"), "Ous", new BigDecimal(4));
        List<ProductSpecification> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        products.add(product3);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date end_date = simpleDateFormat.parse ( "2020-12-31" );
        dispense = new Dispensing(order, new Date(0), end_date, products);
    }

    @Test
    public void dispensingEnabledTest() throws DispensingNotAvailableException {
        assertEquals(true, dispense.dispensingEnabled());
    }

    @Test
    public void setProductAsDispensedTest() throws DispensingNotAvailableException, DispensingNotCompletedException {
        dispense.setProductAsDispensed(product1.UPCcode);
        assertEquals(true, dispense.getAcquired(0));
    }

    @Test
    public void setCompletedTest() throws DispensingNotAvailableException, DispensingNotCompletedException {
        dispense.setProductAsDispensed(product1.UPCcode);
        dispense.setProductAsDispensed(product2.UPCcode);
        dispense.setProductAsDispensed(product3.UPCcode);
        dispense.setCompleted();
        assertEquals(true, dispense.getIsCompleted());
    }

    @Test
    public void getAcquiredTest(){
        assertEquals(false, dispense.getAcquired(0));
    }

    @Test
    public void getIsCompletedTest(){
        assertEquals(false, dispense.getIsCompleted());
    }





}

package Test.Pharmacy.ExceptionsTest;

import Data.Exceptions.BadFormatException;
import Data.Exceptions.NullObjectException;
import Data.ProductID;
import Pharmacy.Dispensing;
import Pharmacy.Exceptions.DispensingNotAvailableException;
import Pharmacy.Exceptions.DispensingNotCompletedException;
import Pharmacy.ProductSpecification;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class DispensingNotCompletedExceptionTest {

    @Test
    public void moreToDispenseTest() throws ParseException, BadFormatException, NullObjectException, DispensingNotAvailableException {
        byte order = 0x0a;
        ProductSpecification product1 = new ProductSpecification(new ProductID("123456789012"), "Carn", new BigDecimal(3));
        ProductSpecification product2 = new ProductSpecification(new ProductID("123456789013"), "Peix", new BigDecimal(6));
        ProductSpecification product3 = new ProductSpecification(new ProductID("123456789014"), "Ous", new BigDecimal(4));
        List<ProductSpecification> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        products.add(product3);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date end_date = simpleDateFormat.parse ( "2020-12-31" );
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        Date init_date = simpleDateFormat2.parse ( "1998-02-23" );
        Dispensing dispense = new Dispensing(order, init_date, end_date, products);

        dispense.setProductAsDispensed(product1.UPCcode);
        assertThrows(DispensingNotCompletedException.class, () -> {dispense.setCompleted();});
    }

}

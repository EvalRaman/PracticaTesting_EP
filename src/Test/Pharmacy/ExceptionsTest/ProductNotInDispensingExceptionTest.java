package Test.Pharmacy.ExceptionsTest;

import Data.Exceptions.BadFormatException;
import Data.Exceptions.NullObjectException;
import Data.ProductID;
import Pharmacy.Dispensing;
import Pharmacy.Exceptions.DispensingNotAvailableException;
import Pharmacy.Exceptions.DispensingNotCompletedException;
import Pharmacy.Exceptions.ProductNotInDispensingException;
import Pharmacy.ProductSpecification;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductNotInDispensingExceptionTest {

    @Test
    public void moreToDispenseTest() throws ParseException, BadFormatException, NullObjectException, DispensingNotAvailableException, ProductNotInDispensingException {
        byte order = 0x0a;
        ProductSpecification product1 = new ProductSpecification(new ProductID("123456789012"), "Carn", new BigDecimal(3));
        List<ProductSpecification> products = new ArrayList<>();
        products.add(product1);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date end_date = simpleDateFormat.parse ( "2020-12-31" );
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        Date init_date = simpleDateFormat2.parse ( "1998-02-23" );
        Dispensing dispense = new Dispensing(order, init_date, end_date, products);
        dispense.setProductAsDispensed(product1.UPCcode);
        assertThrows(ProductNotInDispensingException.class, () -> {dispense.setProductAsDispensed(new ProductID("658974312594"));});
    }
}

package Test.Pharmacy.ExceptionsTest;

import Pharmacy.Dispensing;
import Pharmacy.Exceptions.DispensingNotAvailableException;
import Pharmacy.ProductSpecification;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class DispensingNotAvailableExceptionTest {

    @Test
    public void availabiltyTest() throws ParseException{
        byte order = 0x0a;
        List<ProductSpecification> products = new ArrayList<>();
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        Date end_date = simpleDateFormat1.parse ( "2020-12-31" );
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        Date init_date = simpleDateFormat2.parse ( "1998-02-23" );
        Dispensing dispense = new Dispensing(order, end_date, init_date, products);

        assertThrows(DispensingNotAvailableException.class, () -> {dispense.dispensingEnabled();});
    }

}

package Test.Pharmacy;

import Data.Exceptions.BadFormatException;
import Data.Exceptions.NullObjectException;
import Data.PatientContr;
import Data.ProductID;
import Pharmacy.Exceptions.SaleClosedException;
import Pharmacy.Sale;
import Pharmacy.ProductSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import static org.junit.Assert.assertEquals;

public class SaleTest {
    ProductSpecification product1, product2, product3;
    ProductID id1, id2, id3;
    Sale new_sale;
    PatientContr contr;

    @BeforeEach
    public void setUp() throws NullObjectException, BadFormatException, ParseException {
        id1 = new ProductID("123456789012");
        id2 = new ProductID("123456789013");
        id3 = new ProductID("123456789014");
        product1 = new ProductSpecification(id1, "Carn", new BigDecimal(3));
        product2 = new ProductSpecification(id2, "Peix", new BigDecimal(6));
        product3 = new ProductSpecification(id3, "Ous", new BigDecimal(4));
        contr = new PatientContr(new BigDecimal("0.5"));

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse ( "2020-12-31" );

        new_sale = new Sale(1, date);
    }

    @Test
    public void addLineTest() throws SaleClosedException{
        new_sale.addLine(product1.UPCcode, product1.getPrice(), contr);
        assertEquals(id1, new_sale.getProductID(0));
    }

    @Test
    public void getAmountTest() {
        assertEquals(new BigDecimal("0"), new_sale.getAmount());
    }

    @Test
    public void calculateFinalAmountTest() throws SaleClosedException{
        new_sale.addLine(product1.UPCcode, product1.getPrice(), contr);
        new_sale.addLine(product2.UPCcode, product2.getPrice(), contr);
        new_sale.addLine(product3.UPCcode, product3.getPrice(), contr);
        new_sale.calculateFinalAmount();
        assertEquals(new BigDecimal("13").multiply(new BigDecimal("1.21").multiply(new BigDecimal("0.5"))), new_sale.getAmount());
    }

    @Test
    public void isClosedTest() throws SaleClosedException{
        assertEquals(false, new_sale.isClosed());
        new_sale.calculateFinalAmount();
        assertEquals(true, new_sale.isClosed());
    }

    @Test
    public void getProductID() throws SaleClosedException{
        new_sale.addLine(product1.UPCcode, product1.getPrice(), contr);
        assertEquals(id1, new_sale.getProductID(0));
    }

}

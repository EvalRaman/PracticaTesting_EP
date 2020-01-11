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
    Sale new_sale;
    PatientContr contr;
    @BeforeEach
    public void setUp() throws NullObjectException, BadFormatException, ParseException {
        product1 = new ProductSpecification(new ProductID("123456789012"), "Carn", new BigDecimal(3));
        product2 = new ProductSpecification(new ProductID("123456789013"), "Peix", new BigDecimal(6));
        product3 = new ProductSpecification(new ProductID("123456789014"), "Ous", new BigDecimal(4));
        contr = new PatientContr(new BigDecimal("2"));

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse ( "2020-12-31" );

        new_sale = new Sale(1, date);
    }

    @Test
    public void addLine() throws SaleClosedException{
        new_sale.addLine(product1.UPCcode, product1.getPrice(), contr);
        assertEquals(,new_sale.partial.get(0).);
    }



}

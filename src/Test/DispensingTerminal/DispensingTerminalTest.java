package Test.DispensingTerminal;

import Data.Exceptions.BadFormatException;
import Data.Exceptions.NullObjectException;
import Data.HealthCardID;
import Data.PatientContr;
import Data.ProductID;
import DispensingTerminal.DispensingTerminal;
import DispensingTerminal.Exceptions.PatientIDException;
import DispensingTerminal.Exceptions.ProductNotFoundException;
import DispensingTerminal.Exceptions.SaleNotInitiatedException;
import Pharmacy.Dispensing;
import Pharmacy.Exceptions.DispensingNotAvailableException;
import Pharmacy.Exceptions.ProductNotInDispensingException;
import Pharmacy.Exceptions.SaleClosedException;
import Pharmacy.ProductSpecification;
import Pharmacy.Sale;
import Services.Exceptions.ConnectException;
import Services.Exceptions.HealthCardException;
import Services.Exceptions.NotValidePrescriptionException;
import Services.Exceptions.ProductIDException;
import Services.HealthCardReader;
import Services.NationalHealthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class DispensingTerminalTest {

    HCReaderDoble HCReaderDoble;
    NationalHealthServiceDoble SNSDoble;
    Dispensing dispensing;
    ProductID id1;
    ProductSpecification product1;
    Sale sale;
    PatientContr contr;

    DispensingTerminal dp;

    @BeforeEach
    public void setUp() throws ParseException, NullObjectException, BadFormatException {
        byte order = 0x0a;
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        Date init_date = simpleDateFormat1.parse ( "1998-12-31" );
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        Date end_date = simpleDateFormat2.parse ( "2020-12-31" );
        List<ProductSpecification> products = new ArrayList<>();
        id1 = new ProductID("123456789012");
        product1 = new ProductSpecification(id1, "Carn", new BigDecimal(3));
        ProductSpecification product2 = new ProductSpecification(new ProductID("123456789013"), "Peix", new BigDecimal(6));
        ProductSpecification product3 = new ProductSpecification(new ProductID("123456789014"), "Ous", new BigDecimal(4));
        products.add(product1);
        products.add(product2);
        products.add(product3);
        contr = new PatientContr(new BigDecimal("0.5"));
        dispensing = new Dispensing(order, init_date, end_date, products);
        HCReaderDoble = new HCReaderDoble();
        SNSDoble = new NationalHealthServiceDoble();
        dp = new DispensingTerminal(0, HCReaderDoble, SNSDoble);
        sale = new Sale(0, new Date());
    }

    @Test
    public void getEPrescriptionTest() throws NotValidePrescriptionException, HealthCardException, PatientIDException, Services.Exceptions.ConnectException {
        dp.getePrescription('c');
        assertTrue(dp.disp.equals(dispensing)); //We consider a Dispensing with same order to be equal to another with that order, order is unique
    }

    @Test
    public void initNewSaleTest() throws DispensingNotAvailableException, HealthCardException, PatientIDException, ConnectException, NotValidePrescriptionException {
        dp.getePrescription('c');
        dp.initNewSale();
        assertFalse(dp.sale == null);
    }

    @Test
    public void enterProductTest() throws DispensingNotAvailableException, HealthCardException, PatientIDException, ConnectException, NotValidePrescriptionException, ProductNotFoundException, ProductNotInDispensingException, SaleNotInitiatedException, ProductIDException, SaleClosedException, NullObjectException {
        dp.getePrescription('c');
        dp.initNewSale();
        dp.enterProduct(id1);
        assertEquals(dp.sale.getProductID(0), id1);
    }

    @Test
    public void finalizeSaleTest() throws HealthCardException, PatientIDException, ConnectException, NotValidePrescriptionException, DispensingNotAvailableException, ProductNotFoundException, ProductNotInDispensingException, SaleNotInitiatedException, ProductIDException, SaleClosedException, NullObjectException {
        dp.getePrescription('c');
        dp.initNewSale();
        dp.enterProduct(id1);
        dp.finalizeSale();
        sale.addLine(id1, new BigDecimal("7.234"), new PatientContr(new BigDecimal("0.5")));
        sale.calculateFinalAmount();
        assertEquals(dp.price, sale.getAmount());
    }




    private class HCReaderDoble implements HealthCardReader{

        @Override
        public HealthCardID getHealthCardID() throws HealthCardException, PatientIDException{
            try{
                return new HealthCardID("123456789012");
            }catch(NullObjectException | BadFormatException e){
                return null;
            }
        }
    }

    private class NationalHealthServiceDoble implements NationalHealthService{

        @Override
        public Dispensing getePrescription(HealthCardID hcID) throws HealthCardException, NotValidePrescriptionException, ConnectException {
            try{
                byte order = 0x0a;
                SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
                Date init_date = simpleDateFormat1.parse ( "1998-12-31" );
                SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
                Date end_date = simpleDateFormat2.parse ( "2020-12-31" );
                ProductSpecification product1 = new ProductSpecification(new ProductID("123456789012"), "Carn", new BigDecimal(3));
                ProductSpecification product2 = new ProductSpecification(new ProductID("123456789013"), "Peix", new BigDecimal(6));
                ProductSpecification product3 = new ProductSpecification(new ProductID("123456789014"), "Ous", new BigDecimal(4));
                List<ProductSpecification> products = new ArrayList<>();
                products.add(product1);
                products.add(product2);
                products.add(product3);
                return new Dispensing(order, init_date, end_date, products);
            }catch(NullObjectException | BadFormatException | ParseException e){
                return null;
            }
        }

        @Override
        public PatientContr getPatientContr(HealthCardID hcID) throws ConnectException {
            try{
                return new PatientContr(new BigDecimal("0.5"));
            }catch(NullObjectException e){
                return null;
            }
        }

        @Override
        public ProductSpecification getProductSpecific(ProductID pID) throws ProductIDException, ConnectException, ProductNotFoundException {
            try {
                return new ProductSpecification(new ProductID("123456789012"), "Bonito, Barato", new BigDecimal("7.234"));
            } catch (NullObjectException | BadFormatException e) {
                return null;
            }
        }

        @Override
        public List<Dispensing> updateePrescription(HealthCardID hcID, Dispensing disp) throws ConnectException {
            return null;
        }
    }


}

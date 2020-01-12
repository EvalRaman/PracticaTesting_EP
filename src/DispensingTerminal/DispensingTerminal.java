package DispensingTerminal;

import Data.HealthCardID;
import Data.PatientContr;
import Data.ProductID;
import Pharmacy.*;
import Pharmacy.Exceptions.*;
import Services.Exceptions.HealthCardException;
import Services.Exceptions.NotValidePrescriptionException;
import Services.Exceptions.ProductIDException;
import Services.HealthCardReader;
import Services.NationalHealthService;
import java.math.BigDecimal;
import java.net.ConnectException;
import java.util.Date;


public class DispensingTerminal {

    private HealthCardID hCardID = null;
    private Dispensing disp = null;
    private Sale sale = null;
    BigDecimal price = null;
    private int saleCode;

    private HealthCardReader HCReader;
    private NationalHealthService SNS;

    public void getePrescription(char option) throws ConnectException, NotValidePrescriptionException, HealthCardException, DispensingNotAvailableException {
        try {
            hCardID = HCReader.getHealthCardID();
            disp = SNS.getePrescription(hCardID);
        }
        catch(Services.Exceptions.ConnectException ce) {
            throw new ConnectException("Connection Failed");
        }
        if(!disp.dispensingEnabled()) {
            throw new NotValidePrescriptionException("Prescription is not allowed");
        }
    }

    public void initNewSale() throws DispensingNotAvailableException {
        if(disp.dispensingEnabled()) {
            sale = new Sale(1,new Date());

        }
        else {
            throw new DispensingNotAvailableException("Dispensing is not available");
        }
    }

    public void enterProduct(ProductID pID) throws SaleClosedException, ProductIDException, ConnectException {
        if(sale.isClosed()) {
            throw new SaleClosedException("Sale is already closed");
        }
        ProductSpecification prodSpecification = new ProductSpecification(pID, null, null);

        try {
            PatientContr contr = SNS.getPatientContr(hCardID);
            sale.addLine(pID, prodSpecification.getPrice(), contr);
        }
        catch(Services.Exceptions.ConnectException ce) {
            throw new ConnectException("Connection failure");
        }

    }

    public void finalizeSale() throws SaleClosedException {
        sale.calculateFinalAmount();
        price = sale.getAmount();
        showSale();
    }

    public void showSale() {
       System.out.println("The final price is" + price + "\n");
    }

	/*
	public void realizePayment(BigDecimal quantity) {}
	public void realizePayment() {}
	public void printNextDispensingSheet() {}
	 */
}
package DispensingTerminal;

import Data.HealthCardID;
import Data.PatientContr;
import Data.ProductID;
import DispensingTerminal.Exceptions.PatientIDException;
import DispensingTerminal.Exceptions.ProductNotFoundException;
import DispensingTerminal.Exceptions.SaleNotInitiatedException;
import Pharmacy.Exceptions.ProductNotInDispensingException;
import Pharmacy.*;
import Pharmacy.Exceptions.*;
import Services.HealthCardReader;
import Services.Exceptions.*;
import Services.NationalHealthService;

import java.math.BigDecimal;
import java.util.Date;


public class DispensingTerminal {

    private HealthCardID hCardID;
    private Dispensing disp;
    private Sale sale;
    BigDecimal price;
    private int saleCode;

    private HealthCardReader HCReader;
    private NationalHealthService SNS;

    public DispensingTerminal(int saleCode, HealthCardReader hcReader, NationalHealthService sns) {
        this.saleCode = saleCode;
        HCReader = hcReader;
        SNS = sns;
    }

    public void getePrescription(char option) throws NotValidePrescriptionException, HealthCardException, PatientIDException, Services.Exceptions.ConnectException {
        try {
            hCardID = HCReader.getHealthCardID();
            disp = SNS.getePrescription(hCardID);
        } catch (Services.Exceptions.ConnectException e) {
            throw new Services.Exceptions.ConnectException("Connection Failed");
        } catch ( PatientIDException e){
            throw new PatientIDException("Incorrect PatientID");
        }
    }

    public void initNewSale() throws DispensingNotAvailableException {
        if (disp.dispensingEnabled()) {
            sale = new Sale(saleCode, new Date());
        }
    }

    public void enterProduct(ProductID pID) throws SaleClosedException, ProductIDException, ProductNotFoundException, ProductNotInDispensingException, Services.Exceptions.ConnectException, SaleNotInitiatedException, DispensingNotAvailableException {
        if(sale == null){
            throw new SaleNotInitiatedException("Sale not initializated");
        }
        if (sale.isClosed()) {
            throw new SaleClosedException("Sale is already closed");
        }
        try {
            ProductSpecification prodSpecification = SNS.getProductSpecific(pID);
            PatientContr contr = SNS.getPatientContr(hCardID);
            sale.addLine(pID, prodSpecification.getPrice(), contr);
            disp.setProductAsDispensed(prodSpecification.UPCcode);
        } catch (ProductNotFoundException e) {
            throw new ProductNotFoundException("Product not in catalogue");
        } catch (ConnectException e) {
            throw new Services.Exceptions.ConnectException("Connection Failed");
        } catch (DispensingNotAvailableException d){
            throw new DispensingNotAvailableException("Dispensing not available");
        }

    }

    public void finalizeSale() throws SaleClosedException, SaleNotInitiatedException {
        if(sale == null){
            throw new SaleNotInitiatedException("Sale not initializated");
        }
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
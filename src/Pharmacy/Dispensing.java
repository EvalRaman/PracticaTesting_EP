package Pharmacy;

import Data.ProductID;
import Pharmacy.Exceptions.DispensingNotAvailableException;

import java.util.Date;

public class Dispensing {
    private byte nOrder;

    private Date initDate, finalDate;
    private boolean isCompleted;

    public Dispensing() {

    }

    public boolean dispensingEnabled() throws DispensingNotAvailableException {

        if (initDate.after(new Date()) && finalDate.before(new Date())) {
            return true;
        }
        throw new DispensingNotAvailableException("Out of Date");
    }

    public void setProductAsDispensed(ProductID prodID) {
        prodID.
    }

    public void setCompleted() {

    }
}

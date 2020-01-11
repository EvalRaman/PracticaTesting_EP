package Pharmacy;

import Data.ProductID;
import Pharmacy.Exceptions.DispensingNotAvailableException;

import java.util.Date;

public class Dispensing {
    private final byte nOrder;

    private final Date initDate, finalDate;
    private boolean isCompleted;

    public Dispensing(byte nOrder, Date initDate, Date finalDate) {
        this.nOrder = nOrder;
        this.initDate = initDate;
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

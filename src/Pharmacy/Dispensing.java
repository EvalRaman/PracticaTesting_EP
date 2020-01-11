package Pharmacy;

import Data.ProductID;
import Pharmacy.Exceptions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Dispensing {
    private final byte nOrder;

    private final Date initDate, finalDate;
    private boolean isCompleted;
    public List<MedicineDispensingLine> medicines;


    public Dispensing(byte nOrder, Date initDate, Date finalDate, List<ProductSpecification> medicines) {
        this.nOrder = nOrder;
        this.initDate = initDate;
        this.finalDate = finalDate;
        this.medicines = new ArrayList<>();
        for(ProductSpecification p : medicines){
            this.medicines.add(new MedicineDispensingLine(p));
        }
    }

    public boolean dispensingEnabled() throws DispensingNotAvailableException {
        Date now = new Date();
        if (initDate.after(now) || finalDate.before(now)) {
            throw new DispensingNotAvailableException("Dispensing out of Date\n");
        }
        return initDate.before(now) && finalDate.after(now);
    }

    public void setProductAsDispensed(ProductID prodID) throws DispensingNotAvailableException {
        if(dispensingEnabled()) {
            for (MedicineDispensingLine i : medicines) {
                if (i.product.UPCcode == prodID) {
                    i.acquired = true;
                }
            }
        }
    }

    public void setCompleted() throws DispensingNotCompletedException, DispensingNotAvailableException{
        if(dispensingEnabled()) {
            for (MedicineDispensingLine i : medicines)
                if (!i.acquired) {
                    throw new DispensingNotCompletedException("More to dispense.\n");
                }
            this.isCompleted = true;
        }
    }

    public boolean getAcquired(int index){
        return this.medicines.get(index).acquired;
    }

    public boolean getIsCompleted(){
        return this.isCompleted;
    }


    private static class MedicineDispensingLine {
        ProductSpecification product;
        boolean acquired;

        MedicineDispensingLine(ProductSpecification product){
            this.product = product;
            this.acquired = false;
        }
    }
}

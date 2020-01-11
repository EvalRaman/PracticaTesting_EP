package Pharmacy;

import Data.ProductID;
import Pharmacy.Exceptions.DispensingNotCompletedException;

import java.util.Date;
import java.util.List;


public class Dispensing {
    private final byte nOrder;

    private final Date initDate, finalDate;
    private boolean isCompleted;
    private List<MedicineDispensingLine> medicines;


    public Dispensing(byte nOrder, Date initDate, Date finalDate) {
        this.nOrder = nOrder;
        this.initDate = initDate;
        this.finalDate = finalDate;
    }


    public void addLine(ProductSpecification product){
        MedicineDispensingLine line = new MedicineDispensingLine(product);
        medicines.add(line);
    }

    public boolean dispensingEnabled() throws DispensingNotCompletedException {
        if (initDate.after(new Date()) && finalDate.before(new Date())) {
            return true;
        }
        throw new DispensingNotCompletedException("Out of Date");
    }

    public void setProductAsDispensed(ProductID prodID) throws DispensingNotCompletedException {
        if(dispensingEnabled()) {
            for (MedicineDispensingLine i : medicines) {
                if (i.product.UPCcode == prodID) {
                    i.acquired = true;
                }
            }
        }
    }

    public void setCompleted() throws DispensingNotCompletedException{
        if(dispensingEnabled()) {
            for (MedicineDispensingLine i : medicines)
                if (!i.acquired) {
                    throw new DispensingNotCompletedException("More to dispense.\n");
                }
            this.isCompleted = true;
        }
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

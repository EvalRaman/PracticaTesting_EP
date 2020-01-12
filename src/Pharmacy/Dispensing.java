package Pharmacy;

import Data.ProductID;
import Pharmacy.Exceptions.ProductNotInDispensingException;
import Pharmacy.Exceptions.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;


public class Dispensing {
    private final byte nOrder;

    private final Date initDate, finalDate;
    private boolean isCompleted;
    public HashMap<ProductID,MedicineDispensingLine> medicines;


    public Dispensing(byte nOrder, Date initDate, Date finalDate, List<ProductSpecification> medicines) {
        this.nOrder = nOrder;
        this.initDate = initDate;
        this.finalDate = finalDate;
        this.medicines = new HashMap<>();
        for(ProductSpecification p : medicines){
            this.medicines.put(p.UPCcode,new MedicineDispensingLine(p));
        }
    }

    public boolean dispensingEnabled() throws DispensingNotAvailableException {
        Date now = new Date();
        if (initDate.after(now) || finalDate.before(now)) {
            throw new DispensingNotAvailableException("Dispensing out of Date\n");
        }
        return initDate.before(now) && finalDate.after(now);
    }

    public void setProductAsDispensed(ProductID prodID) throws DispensingNotAvailableException, ProductNotInDispensingException {
        if(dispensingEnabled()) {
            if(this.medicines.get(prodID) == null){
                throw new ProductNotInDispensingException("404 product not found in dispensing");
            }
            for (MedicineDispensingLine i : medicines.values()) {
                if (i.product.UPCcode == prodID) {
                    i.acquired = true;
                }
            }

        }
    }

    public void setCompleted() throws DispensingNotCompletedException, DispensingNotAvailableException{
        if(dispensingEnabled()) {
            for (MedicineDispensingLine i : medicines.values())
                if (!i.acquired) {
                    throw new DispensingNotCompletedException("More to dispense.\n");
                }
            this.isCompleted = true;
        }
    }

    public boolean getAcquired(ProductID key){
        return this.medicines.get(key).acquired;
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
        MedicineDispensingLine(ProductSpecification product, boolean acquired){
            this.product = product;
            this.acquired = acquired;
        }

    }
}

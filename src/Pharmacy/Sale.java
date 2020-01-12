package Pharmacy;

import Data.*;
import Pharmacy.Exceptions.SaleClosedException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Sale {
    private BigDecimal amount;
    private boolean isClosed;
    public List<ProductSaleLine> partial;

    public Sale(int saleCode, Date date) {
        this.amount= new BigDecimal("0");
        this.isClosed=false;
        this.partial = new ArrayList<>();
    }

    public void addLine(ProductID prodID, BigDecimal price, PatientContr contr) throws SaleClosedException {
        if(isClosed) {
            throw new SaleClosedException("The sale is closed");
        }
        ProductSaleLine line = new ProductSaleLine(prodID, price, contr);
        partial.add(line);
    }

    private void calculateAmount() {
        for (ProductSaleLine d : partial)
            amount = amount.add(d.price.multiply(d.contr.getPersonalCont()));
    }

    private void addTaxes() throws SaleClosedException {
        if(isClosed) {
            throw new SaleClosedException("The sale is closed");
        }
        amount = amount.multiply(new BigDecimal("1.21"));
    }

    public void calculateFinalAmount() throws SaleClosedException {
        if(isClosed) {
            throw new SaleClosedException("The sale is closed");
        }
        calculateAmount();
        addTaxes();
        setClosed();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    private void setClosed() {
        isClosed = true;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public ProductID getProductID(int index){ return this.partial.get(index).prodID;}

    private static class ProductSaleLine {
        ProductID prodID;
        BigDecimal price;
        PatientContr contr;

        ProductSaleLine(ProductID prodID, BigDecimal price, PatientContr contr) {
            this.prodID = prodID;
            this.price = price;
            this.contr = contr;
        }
    }
}

package Pharmacy;

import Data.*;
import Pharmacy.Exceptions.SaleClosedException;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

public class Sale {
    private int saleCode;
    private Date date;
    private BigDecimal amount;
    private boolean isClosed;
    private List<Line> partial;

    public Sale(int saleCode, Date date) {
        this.saleCode=saleCode;
        this.date=date;
        this.amount= new BigDecimal("0");
        this.isClosed=false;
    }

    public void addLine(ProductID prodID, BigDecimal price, PatientContr contr) throws SaleClosedException {
        if(isClosed) {
            throw new SaleClosedException("The sale is closed");
        }
        Line line = new Line(prodID, price, contr);
        partial.add(line);
    }

    private void calculateAmount() {
        for (Line d : partial)
            amount = amount.add(d.price);
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

    public static class Line {
        ProductID prodID;
        BigDecimal price;
        PatientContr contr;

        Line(ProductID prodID, BigDecimal price, PatientContr contr) {
            this.prodID = prodID;
            this.price = price;
            this.contr = contr;
        }
    }
}

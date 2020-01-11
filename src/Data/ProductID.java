package Data;

import Data.Exceptions.BadFormatException;
import Data.Exceptions.NullObjectException;

final public class ProductID{
    private final String UPC;

    public ProductID(String code) throws NullObjectException, BadFormatException{
        if(code == null) throw new NullObjectException("UPC code is null.");
        if(!codeIsValid(code)) throw new BadFormatException("UPC code length is less than 12.");
        this.UPC = code;
    }

    private boolean codeIsValid(String code){
        return code.length() == 12;
    }

    public String getUPC() {
        return UPC;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductID productID = (ProductID) o;
        return UPC.equals(productID.UPC);
    }

    @Override
    public int hashCode() {
        return UPC.hashCode();
    }

    @Override
    public String toString() {
        return "ProductID{" + "Universal Product Code='" + UPC + '\'' + '}';
    }
}
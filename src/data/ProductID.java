package data;

final public class ProductID {
    private final String upc;

    public ProductID(String code) {
        this.upc = code;
    }

    public String getupc() {
        return upc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductID productID = (ProductID) o;
        return upc.equals(productID.upc);
    }

    @Override
    public int hashCode() {
        return upc.hashCode();
    }

    @Override
    public String toString() {
        return "ProductID{" + "Universal Product Code='" + upc + '\'' + '}';
    }
}
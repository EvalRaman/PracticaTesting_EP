package Pharmacy;

import Data.ProductID;

import java.math.BigDecimal;

public class ProductSpecification {
    final ProductID UPCcode;
    private final String description;
    private final BigDecimal price;

    public ProductSpecification(ProductID upCcode, String description, BigDecimal price) {
        this.UPCcode = upCcode;
        this.description = description;
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }
}

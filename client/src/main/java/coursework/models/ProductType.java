package coursework.models;

import java.io.Serializable;

public class ProductType implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String productName;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ProductType() { }

    public ProductType(Integer id, String productName) {
        this.id = id;
        this.productName = productName;
    }

    public ProductType(String productName) {
        this.productName = productName;
    }
}
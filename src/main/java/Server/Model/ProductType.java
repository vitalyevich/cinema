package Server.Model;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "product_types", indexes = {
        @Index(name = "UQ__product___2B5A6A5FD05F8CE6", columnList = "product_name", unique = true)
})
@Entity
public class ProductType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "product_name", nullable = false, length = 20)
    private String productName;

    @Column(name = "image_link", nullable = false, length = 30)
    private String imageLink;

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

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

    public ProductType(int id, String productName, String imageLink) {
        this.id = id;
        this.productName = productName;
        this.imageLink = imageLink;
    }

    public ProductType(String productName, String imageLink) {
        this.productName = productName;
        this.imageLink = imageLink;
    }
}
import java.io.Serializable;

public class Product implements Serializable {
    private int productID;
    public String productName;
    public double productPrice;

    public Product(int i, String productName, double productPrice) {
        this.productID = i;
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductDetails() {
        return "Product Name: " + productName + ", Price: " + productPrice;
    }
}

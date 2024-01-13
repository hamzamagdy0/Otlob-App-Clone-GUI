import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class Order implements Serializable {
    private int OrderID;
    private HashMap<Product, Integer> orderProducts;
    private OrderStatus Status;
    private String orderAddress;
    private Date orderDate;
    private double totalPrice;
    private int rate;
    public Object getOrderDetailsAsString;

    public Order(int i, Date date) {
        this.OrderID = i;
        this.orderDate=date;
        this.orderProducts = new HashMap<>();
        this.Status = OrderStatus.PENDING;
        this.rate = -1;
    }

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int orderID) {
        OrderID = orderID;
    }

    public HashMap<Product, Integer> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(HashMap<Product, Integer> orderProducts) {
        this.orderProducts = orderProducts;
    }

    public OrderStatus getStatus() {
        return Status;
    }

    public void setStatus(OrderStatus status) {
        Status = status;
    }

    public String getOrderAddress() {
        return orderAddress;
    }

    public void setOrderAddress(String orderAddress) {
        this.orderAddress = orderAddress;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public void printOrderDetails() {
        System.out.println("Order ID: " + OrderID);
        System.out.println("Order Date: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(orderDate));
        System.out.println("Order Status: " + Status);
        System.out.println("Order Address: " + orderAddress);
        System.out.println("Total Price: $" + totalPrice);
        System.out.println("Rate: " + (rate != -1 ? rate : "Not rated"));

        System.out.println("Order Products:");
        for (HashMap.Entry<Product, Integer> entry : orderProducts.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            System.out.println("   Product: " + product.getProductName() + ", Quantity: " + quantity);
        }
    }

    public String getOrderDetailsAsString() {
        StringBuilder orderDetails = new StringBuilder();
    
        orderDetails.append("Order ID: ").append(OrderID).append("\n");
        orderDetails.append("Order Date: ").append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(orderDate)).append("\n");
        orderDetails.append("Order Status: ").append(Status).append("\n");
        orderDetails.append("Order Address: ").append(orderAddress).append("\n");
        orderDetails.append("Total Price: $").append(totalPrice).append("\n");
        orderDetails.append("Rate: ").append(rate != -1 ? rate : "Not rated").append("\n");
    
        orderDetails.append("Order Products:\n");
        for (HashMap.Entry<Product, Integer> entry : orderProducts.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            orderDetails.append("   Product: ").append(product.getProductName()).append(", Quantity: ").append(quantity).append("\n");
        }
        String ordereto= orderDetails.toString();
        return ordereto;
    }
    
}

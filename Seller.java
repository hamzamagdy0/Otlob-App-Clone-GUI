import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Seller extends User implements Serializable {
    private int sellerID;
    public ArrayList<Product> sellerProducts;
    private ArrayList<Order> sellerOrders;

    public Seller(String userName, String userPassword, int sellerID) {
        super(userName, userPassword);
        this.sellerID = sellerID;
        sellerProducts = new ArrayList<>();
        sellerOrders = new ArrayList<>();
    }

    public int getSellerID() {
        return sellerID;
    }

    public void setSellerID(int sellerID) {
        this.sellerID = sellerID;
    }

    public ArrayList<Product> getSellerProducts() {
        return sellerProducts;
    }

    public void setSellerProducts(ArrayList<Product> sellerProducts) {
        this.sellerProducts = sellerProducts;
    }

    public ArrayList<Order> getSellerOrders() {
        return sellerOrders;
    }

    public void setSellerOrders(ArrayList<Order> sellerOrders) {
        this.sellerOrders = sellerOrders;
    }

    protected void viewOrderDetails(ArrayList<Order> orderList, Order order){
        if (order == null || !orderList.contains(order)) {
            System.out.println("Invalid order. Unable to view details.");
            return;
        }

        System.out.println("Order Details:");
        System.out.println("Order ID: " + order.getOrderID());
        for (HashMap.Entry<Product, Integer> entry : order.getOrderProducts().entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();

            System.out.println("  - " + product.getProductName() +
                    ", Quantity: " + quantity +
                    ", Price per unit: " + product.getProductPrice());
        }

        System.out.println("Order Status: " + order.getStatus());
        System.out.println("Total Amount: " + order.getTotalPrice());
        System.out.println("Order Date: " + order.getOrderDate());
    }

    protected void changeOrderStatus(Order order, OrderStatus status){
        order.setStatus(status);
    }

    protected double calculateAverageRevenue(Date startDate, Date endDate) {
        double totalRevenue = 0;
        int totalQuantity = 0;

        if (!sellerOrders.isEmpty()) {
            for (Order order : sellerOrders) {
                Date orderDate = order.getOrderDate();

                if (orderDate.after(startDate) && orderDate.before(endDate)) {
                    for (HashMap.Entry<Product, Integer> entry : order.getOrderProducts().entrySet()) {
                        Product product = entry.getKey();
                        int quantity = entry.getValue();
                        totalRevenue += (quantity * product.getProductPrice());
                        totalQuantity += quantity;
                    }
                }
            }
        }
        return totalQuantity > 0 ? totalRevenue / totalQuantity : 0.0;
    }

    protected double calculateTotalRevenue() {
        double totalRevenue = 0;
        for (Order order : sellerOrders) {
            totalRevenue += order.getTotalPrice();
        }
        return totalRevenue;
    }

    protected int calculatePiecesSoldOverTime(Date startDate, Date endDate) {
        int totalPiecesSold = 0;

        if (!sellerOrders.isEmpty()){
            for (Order order : sellerOrders) {
                Date orderDate = order.getOrderDate();

                if (orderDate.after(startDate) && orderDate.before(endDate)) {
                    for (int quantity : order.getOrderProducts().values()) {
                        totalPiecesSold += quantity;
                    }
                }
            }
        }

        return totalPiecesSold;
    }

    protected ArrayList<Product> bestSellingProductOverTime(Date startDate, Date endDate){
        HashMap<Product, Integer> productSales = new HashMap<>();
        int maxSales = 0;

        for (Order order : sellerOrders) {
            if (!order.getOrderDate().before(startDate) && !order.getOrderDate().after(endDate)) {
                for (HashMap.Entry<Product, Integer> entry : order.getOrderProducts().entrySet()) {
                    Product product = entry.getKey();
                    int quantity = entry.getValue();
                    int updatedQuantity = productSales.getOrDefault(product, 0) + quantity;
                    productSales.put(product, updatedQuantity);

                    if (updatedQuantity > maxSales) {
                        maxSales = updatedQuantity;
                    }
                }
            }
        }

        ArrayList<Product> bestSellingProducts = new ArrayList<>();
        for (HashMap.Entry<Product, Integer> entry : productSales.entrySet()) {
            if (entry.getValue() == maxSales) {
                bestSellingProducts.add(entry.getKey());
            }
        }

        return bestSellingProducts;
    }

    protected ArrayList<Product> mostRevenueProductOverTime(Date startDate, Date endDate) {
        HashMap<Product, Double> productRevenue = new HashMap<>();
        double maxRevenue = 0.0;

        for (Order order : sellerOrders) {
            if (!order.getOrderDate().before(startDate) && !order.getOrderDate().after(endDate)) {
                for (HashMap.Entry<Product, Integer> entry : order.getOrderProducts().entrySet()) {
                    Product product = entry.getKey();
                    int quantity = entry.getValue();
                    double revenue = quantity * product.getProductPrice();
                    double updatedRevenue = productRevenue.getOrDefault(product, 0.0) + revenue;
                    productRevenue.put(product, updatedRevenue);

                    if (updatedRevenue > maxRevenue) {
                        maxRevenue = updatedRevenue;
                    }
                }
            }
        }

        ArrayList<Product> mostRevenueProducts = new ArrayList<>();
        for (HashMap.Entry<Product, Double> entry : productRevenue.entrySet()) {
            if (entry.getValue() == maxRevenue) {
                mostRevenueProducts.add(entry.getKey());
            }
        }

        return mostRevenueProducts;
    }

    protected HashMap<Product, Double> getProductRevenues(Date startDate, Date endDate){
        HashMap<Product, Double> productRevenues = new HashMap<>();

        for (Order order : sellerOrders) {
            if (!order.getOrderDate().before(startDate) && !order.getOrderDate().after(endDate)) {
                for (HashMap.Entry<Product, Integer> entry : order.getOrderProducts().entrySet()) {
                    Product product = entry.getKey();
                    int quantity = entry.getValue();
                    double revenue = quantity * product.getProductPrice();
                    productRevenues.put(product, productRevenues.getOrDefault(product, 0.0) + revenue);
                }
            }
        }

        return productRevenues;
    }

    protected void addProduct(ArrayList<Product> productList, Product product){
        if (product != null) {
            if (!this.sellerProducts.contains(product)) {
                this.sellerProducts.add(product);
                productList.add(product);
                System.out.println("Product added successfully, with ID: " + product.getProductID());
            } else {
                System.out.println("Product already exists.");
            }

        } else {
            System.out.println("Invalid product. Unable to add.");
        }
    }

    protected void editProductName(Product product, String newName){
        if (sellerProducts.contains(product)){
            product.setProductName(newName);
        }
    }

    protected void editProductPrice(Product product, double newPrice){
        if (sellerProducts.contains(product)){
            product.setProductPrice(newPrice);
        }
    }

    protected void removeProduct(ArrayList<Product> productList, Product product){
        if (productList.contains(product)){
            productList.remove(product);
        }

        if (sellerProducts.contains(product)){
            sellerProducts.remove(product);
        }
    }

    protected void displayProducts(){
        if (sellerProducts.isEmpty()) {
            System.out.println("No products available.");
        } else {
            for (Product product : sellerProducts) {
                System.out.println(product.getProductDetails());
            }
        }
    }

    protected ArrayList<Product> searchProducts(String searchQuery){
        ArrayList<Product> foundProducts = new ArrayList<>();

        for (Product product : sellerProducts) {
            if (product.getProductName().toLowerCase().contains(searchQuery.toLowerCase())) {
                foundProducts.add(product);
            }
        }

        return foundProducts;
    }
}

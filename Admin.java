import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Admin extends User implements Serializable {
    private int adminID;

    public Admin(int adminID) {
        super();
        this.adminID = adminID;
    }

    public Admin(String userName, String userPassword, int adminID) {
        super(userName, userPassword);
        this.adminID = adminID;
    }

    public int getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }

    protected <T extends User> void addUser(ArrayList<T> list, T user){
        if (user != null && !list.contains(user)) {
            list.add(user);
        }
    }

    protected <T extends User> void editUserName(ArrayList<T> list,T user, String newUserName){
        if (list.contains(user)) {
            user.setUserName(newUserName);
        }
        else {
            System.out.println("User doesn't exist in list");
        }
    }

    protected <T extends User> void editUserPassword(ArrayList<T> list, T user, String newUserPassword){
        if (list.contains(user)) {
            user.setUserPassword(newUserPassword);
        }
        else {
            System.out.println("User doesn't exist in list");
        }
    }

    protected <T extends User> void removeUser(ArrayList<T> list,T user){
        if (list.contains(user)){
            list.remove(user);
        }
        else {
            System.out.println("User doesn't exist in list");
        }
    }

    protected ArrayList<User> searchUser(ArrayList<User> list,String searchQuery){
        ArrayList<User> result = new ArrayList<>();

        for (User user : list) {
            if (user.getUserName().contains(searchQuery)) {
                result.add(user);
            }
        }

        return result;
    }

    protected HashMap<Seller, ArrayList<Order>> getOrdersPerSeller(ArrayList<Seller> sellerList){
        HashMap<Seller, ArrayList<Order>> ordersPerSeller = new HashMap<>();

        for (Seller seller : sellerList) {
            ordersPerSeller.put(seller, seller.getSellerOrders());
        }

        return ordersPerSeller;
    }

    protected  ArrayList<Seller> getSellerWithMaxOrders(ArrayList<Seller> sellerList){
        ArrayList<Seller> sellersMaxOrders = new ArrayList<>();

        int maxOrders = Integer.MIN_VALUE;

        for (Seller seller : sellerList){
            int numberOfOrders = seller.getSellerOrders().size();

            if(numberOfOrders > maxOrders){
                sellersMaxOrders.clear();
                maxOrders = numberOfOrders;
                sellersMaxOrders.add(seller);
            }
            else if (numberOfOrders == maxOrders){
                sellersMaxOrders.add(seller);
            }
        }

        return sellersMaxOrders;
    }

    protected  ArrayList<Seller> getSellerWithMaxRevenue(ArrayList<Seller> sellerList){
        ArrayList<Seller> sellersMaxRevenue = new ArrayList<>();
        double maxRevenue = 0;

        for (Seller seller : sellerList) {
            double revenue = seller.calculateTotalRevenue();

            if (revenue > maxRevenue) {
                sellersMaxRevenue.clear();
                maxRevenue = revenue;
                sellersMaxRevenue.add(seller);
            } else if (revenue == maxRevenue) {
                sellersMaxRevenue.add(seller);
            }
        }

        return sellersMaxRevenue;
    }

    protected HashMap<Customer, ArrayList<Order>> getOrdersPerCustomer(ArrayList<Customer> customerList){
        HashMap<Customer, ArrayList<Order>> ordersPerCustomer = new HashMap<>();

        for (Customer customer : customerList) {
            ordersPerCustomer.put(customer, customer.getCustomerOrders());
        }

        return ordersPerCustomer;
    }

    protected  ArrayList<Customer> getCustomerWithMaxOrders(ArrayList<Customer> customerList){
        ArrayList<Customer> customersMaxOrders = new ArrayList<>();

        int maxOrders = Integer.MIN_VALUE;

        for (Customer customer : customerList){
            int numberOfOrders = customer.getCustomerOrders().size();

            if(numberOfOrders > maxOrders){
                customersMaxOrders.clear();
                maxOrders = numberOfOrders;
                customersMaxOrders.add(customer);
            }
            else if (numberOfOrders == maxOrders){
                customersMaxOrders.add(customer);
            }
        }

        return customersMaxOrders;
    }

    protected  ArrayList<Customer> getCustomerWithMaxRevenue(ArrayList<Customer> customerList){
        ArrayList<Customer> customersMaxRevenue = new ArrayList<>();
        double maxRevenue = 0;

        for (Customer customer : customerList) {
            double revenue = customer.calculateTotalRevenue();

            if (revenue > maxRevenue) {
                customersMaxRevenue.clear();
                maxRevenue = revenue;
                customersMaxRevenue.add(customer);
            } else if (revenue == maxRevenue) {
                customersMaxRevenue.add(customer);
            }
        }

        return customersMaxRevenue;
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

    protected double getAverageRevenue(ArrayList<Order> orderList, Date startDate, Date endDate){
        double totalRevenue = 0;
        int count = 0;

        for (Order order : orderList) {
            if (!order.getOrderDate().before(startDate) && !order.getOrderDate().after(endDate)) {
                totalRevenue += order.getTotalPrice();
                count++;
            }
        }

        return count == 0 ? 0 : totalRevenue / count;
    }

    protected double getTotalRevenue(ArrayList<Order> orderList, Date startDate, Date endDate){
        double totalRevenue = 0;

        for (Order order : orderList) {
            if (!order.getOrderDate().before(startDate) && !order.getOrderDate().after(endDate)) {
                totalRevenue += order.getTotalPrice();
            }
        }

        return totalRevenue;
    }
}
package futurewomen;

public class Order {
    private int orderId;
    private int productId;
    private  int quantity;
    private int customerID;

    public Order(int orderId, int productId, int quantity, int customerID) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.customerID = customerID;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
}

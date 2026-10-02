package models;

public class OrderItem {

    private Product product;
    private int quantity;
    private final double subtotal;

    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.subtotal = product.getPrice() * quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSubtotal() {
        return subtotal;
    }

    @Override
    public String toString() {
        return "LineaPedido{" +
                "producto=" + product +
                ", cantidad=" + quantity +
                ", subtotal=" + subtotal +
                '}';
    }
}

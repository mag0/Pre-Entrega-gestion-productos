package models;

import java.util.List;

public class Order {
    private static int idCounter = 1;

    private final int id;
    private List<OrderItem> orderItems;
    
    public Order(List<OrderItem> orderItems) {
        this.id = idCounter++;
        this.orderItems = orderItems;
    }

    public int getId() {
        return id;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public double getTotal() {
        double total = 0;

        for (OrderItem orderItem : orderItems) {
            total += orderItem.getSubtotal();
        }

        return total;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", pedido=" + orderItems +
                ", total=$" + getTotal() +
                '}';
    }
}
package models;

public class Product {

    private static int idCounter = 1;

    private final int id;
    private String name;
    private double price;
    private int stock;

    public Product(String name, double price, int stock) {
        this.id = idCounter++;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "ID: " + id + 
            " | Producto: " + name + 
            " | Precio: $" + price + 
            " | Stock: " + stock;
    }

}

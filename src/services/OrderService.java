package services;

import exceptions.InsufficientStockException;
import exceptions.ProductNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import models.Order;
import models.OrderItem;
import models.Product;
import utils.Validations;

public class OrderService {

    private final List<Order> orders = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);
    private final ProductService productService;

    public OrderService(ProductService productService) {
        this.productService = productService;
    }

    public void addOrder() {
        List<OrderItem> orderItems = new ArrayList<>();
        boolean orderComplete = false;

        while (!orderComplete) {
            try {
                OrderItem orderItem = addOrderItem();
                orderItems.add(orderItem);
                System.out.println("Producto agregado: " + orderItem.getProduct().getName());
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un número válido.");
                continue;
            } catch (ProductNotFoundException | InsufficientStockException | IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
                continue;
            }

            if (!addAnotherProduct()) {
                orderComplete = true;
            }
        }

        if (orderItems.isEmpty()) {
            System.out.println("No se creó el pedido porque no se agregaron productos.");
            return;
        }

        Order order = new Order(orderItems);

        System.out.println("\nPedido:");
        System.out.println(order);
        System.out.println("Total: $" + order.getTotal());

        orderCheck(order);
    }

    private OrderItem addOrderItem() throws ProductNotFoundException, InsufficientStockException {
        System.out.println("\nIngrese id del producto:");
        int id = Validations.validateInteger(scanner.nextLine());

        Product product = productService.findProductById(id);

        System.out.println("Ingrese cantidad:");
        int quantity = Validations.validateInteger(scanner.nextLine());

        if (quantity <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a 0.");
        }

        if (product.getStock() < quantity) {
            throw new InsufficientStockException(
                "Stock insuficiente para el producto: " + product.getName()
            );
        }

        return new OrderItem(product, quantity);
    }

    private boolean addAnotherProduct() {
        System.out.println("\n¿Desea agregar otro producto?");
        System.out.println("1. Sí");
        System.out.println("2. No");

        try {
            int option = Validations.validateInteger(scanner.nextLine());

            if (option == 2) {
                return false;
            }

            if (option != 1) {
                System.out.println("Opción inválida.");
            }

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        return true;
    }

    private void orderCheck(Order order) {
        System.out.println("\n¿Desea confirmar el pedido?");
        System.out.println("1. Sí");
        System.out.println("2. No");

        try {
            int check = Validations.validateInteger(scanner.nextLine());

            if (check == 1) {
                updateStock(order);
                orders.add(order);

                System.out.println("Pedido confirmado.");
                System.out.println("Pedido creado con ID: " + order.getId());
                System.out.println("Total: $" + order.getTotal());

            } else {
                System.out.println("Pedido cancelado.");
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Opción inválida. El pedido no fue confirmado.");
        }
    }

    private void updateStock(Order order) {
        for (OrderItem orderItem : order.getOrderItems()) {
            Product product = orderItem.getProduct();

            product.setStock(
                product.getStock() - orderItem.getQuantity()
            );
        }
    }

    public void getOrders() {
        if (orders.isEmpty()) {
            System.out.println("No hay pedidos registrados.");
            return;
        }

        System.out.println("Lista de pedidos:");
        System.out.println("------------------\n");

        for (Order order : orders) {
            System.out.println(order);
        }
    }
}
package services;

import exceptions.ProductNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import models.Product;
import utils.Validations;

public class ProductService {
    private final List<Product> products = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);

    public ProductService() {
        loadInitialProducts();
    }

    public void addProduct() {
        try {
            System.out.println("Indique nombre del producto:");
            String name = Validations.validateName(scanner.nextLine());

            System.out.println("Indique precio del producto:");
            double price = Validations.validatePrice(scanner.nextLine());

            System.out.println("Indique cantidad del producto:");
            int quantity = Validations.validateStock(scanner.nextLine());

            Product product = new Product(name, price, quantity);
            products.add(product);

            System.out.println("Producto agregado: " + product);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getProducts() {
        System.out.println("Lista de productos:");
        System.out.println("------------------\n");

        if (products.isEmpty()) {
            System.out.println("No hay productos disponibles.");
            return;
        }

        for (Product product : products) {
            System.out.println(product);
        }
    }

    public void updateProduct() {
        try {
            System.out.println("1. Buscar por ID");
            System.out.println("2. Buscar por nombre");

            int option = Validations.validateInteger(scanner.nextLine());
            Product product;

            product = getProductFound(option);

            System.out.println("Producto encontrado: " + product);

            boolean updateComplete = false;
            while (!updateComplete) {
                updateMenu();
                option = Validations.validateInteger(scanner.nextLine());

                // metodo para actualizar el producto a partir de la opcion elegida
                executeUpdateOption(option, product);

                if (option == 4) {
                    updateComplete = true;
                }
            }
        } catch (ProductNotFoundException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

    }

    public void deleteProduct() {
        System.out.println("Ingrese el ID del producto a eliminar:");
        try {
            int id = Validations.validateInteger(scanner.nextLine());
            Product product = findProductById(id);
            products.remove(product);
            System.out.println("Producto eliminado: " + product);
        } catch (ProductNotFoundException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public Product findProductById(int id) throws ProductNotFoundException {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        throw new ProductNotFoundException("Producto con ID " + id + " no encontrado.");
    }

    private Product findProductByName(String name) throws ProductNotFoundException {
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(name.trim())) {
                return product;
            }
        }
        throw new ProductNotFoundException("Producto con nombre " + name + " no encontrado.");
    }

    private void updateMenu() {
        System.out.println("\nSi desea actualizar algún campo, elija la opción correspondiente:");
        System.out.println("1. Nombre");
        System.out.println("2. Precio");
        System.out.println("3. Cantidad");
        System.out.println("4. Salir");
    }

    private Product getProductFound(int option) throws ProductNotFoundException {
        switch (option) {
            case 1 -> {
                System.out.println("Ingrese el ID del producto que desea buscar:");
                int id = Validations.validateInteger(scanner.nextLine());
                return findProductById(id);
            }
            case 2 -> {
                System.out.println("Ingrese el nombre del producto que desea buscar:");
                String name = Validations.validateName(scanner.nextLine());
                return findProductByName(name);
            }
            default -> throw new IllegalArgumentException("Opción inválida. Intente nuevamente.");
        }
    }

    private void executeUpdateOption(int option, Product product) {
        switch (option) {
            case 1 -> {
                System.out.println("Ingrese el nuevo nombre:");
                String newName = Validations.validateName(scanner.nextLine());
                product.setName(newName);
                System.out.println("Producto actualizado: " + product);
            }
            case 2 -> {
                System.out.println("Ingrese el nuevo precio:");
                double newPrice = Validations.validatePrice(scanner.nextLine());
                product.setPrice(newPrice);
                System.out.println("Producto actualizado: " + product);
            }
            case 3 -> {
                System.out.println("Ingrese la nueva cantidad:");
                int newStock = Validations.validateStock(scanner.nextLine());
                product.setStock(newStock);
                System.out.println("Producto actualizado: " + product);
            }
            case 4 -> System.out.println("\nSaliendo del menú de actualización...");
            default -> System.out.println("Opción inválida. Intente nuevamente.");
        }
    }

    private void loadInitialProducts() {
        products.add(new Product("Yerba", 2500, 20));
        products.add(new Product("Azucar", 1200, 15));
        products.add(new Product("Café", 4500, 10));
        products.add(new Product("Leche", 1500, 30));
    }
}

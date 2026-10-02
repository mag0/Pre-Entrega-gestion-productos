import java.util.Scanner;
import services.OrderService;
import services.ProductService;
import utils.Validations;

public class App {
    public static void main(String[] args) throws Exception {
        int option;
        Scanner scanner = new Scanner(System.in);
        ProductService productService = new ProductService();
        OrderService orderService = new OrderService(productService);

        System.out.println("============================================================");
        System.out.println("        SISTEMA DE PRODUCTOS Y PEDIDOS");
        System.out.println("============================================================");

        do {
            showMenu();

            try {
                option = Validations.validateInteger(scanner.nextLine());
                executeOption(option, productService, orderService);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                option = 0;
            }

        } while(option != 7);

        scanner.close();
    }

    private static void showMenu() {
        System.out.println();
        System.out.println("1. Agregar producto");
        System.out.println("2. Listar productos");
        System.out.println("3. Buscar / Actualizar producto");
        System.out.println("4. Eliminar producto");
        System.out.println("5. Crear pedido");
        System.out.println("6. Listar pedidos");
        System.out.println("7. Salir");
        System.out.print("\nSeleccione una opción: ");
    }

    private static void executeOption(int option, ProductService productService, OrderService orderService) {
        System.out.print("\n\n");
        switch(option) {
            case 1 -> productService.addProduct();
            case 2 -> productService.getProducts();
            case 3 -> productService.updateProduct();
            case 4 -> productService.deleteProduct();
            case 5 -> {
                productService.getProducts();
                orderService.addOrder();
            }
            case 6 -> orderService.getOrders();
            case 7 -> System.out.println("Saliendo del programa...");
            default -> System.out.println("Opción inválida. Intente nuevamente.");
        }
        System.out.print("\n\n");
    }
}


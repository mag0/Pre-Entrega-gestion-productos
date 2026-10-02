package utils;

public class Validations {

    public static String validateName(String value) {
        if(value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacio.");
        }
        return value;
    }

    public static double validatePrice(String value) {
        try {
            double price = Double.parseDouble(value);

            if (price < 0) {
                throw new IllegalArgumentException("El precio no puede ser negativo.");
            }

            return price;

        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Debe ingresar un valor numerico valido.");
        }
    }

    public static int validateStock(String value) {
        try {
            int stock = Integer.parseInt(value);

        if (stock < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo.");
        }

        return stock;

        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Debe ingresar un valor numerico valido.");
        }
    }

    public static int validateInteger(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Debe ingresar un valor numerico valido.");
        }
    }
}

# Sistema de Gestión de Productos y Pedidos

Consola interactiva en Java para la administración de inventario de productos y procesamiento de pedidos, desarrollada bajo el paradigma de Programación Orientada a Objetos (POO).

---

## Características

- **CRUD Completo de Productos**:
  - **Crear**: Registro de productos con nombre, precio, stock e identificador único autoincremental.
  - **Listar**: Visualización de todos los productos disponibles en inventario.
  - **Buscar**: Búsqueda flexible por ID o por nombre de producto.
  - **Actualizar**: Modificación individual de nombre, precio o cantidad/stock.
  - **Eliminar**: Baja de productos mediante su ID.
- **Gestión de Pedidos**:
  - Creación de órdenes compuestas por múltiples ítems/productos.
  - Validación de stock en tiempo real con descuento automático tras confirmación.
  - Listado de pedidos registrados con cálculo automático de subtotales y total.
- **Validación y Manejo Robusto de Excepciones**:
  - Control de entradas numéricas no válidas (`NumberFormatException`).
  - Restricción de precios y stock negativos o nombres vacíos.
  - Excepciones personalizadas (`ProductNotFoundException`, `InsufficientStockException`).
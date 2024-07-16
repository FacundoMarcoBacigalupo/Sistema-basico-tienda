public enum MenuUsuario {
    CREAR_PRODUCTO("Crear Producto"),
    AGREGAR_A_CARRITO("Agregar a Carrito"),
    VER_CARRITO("Ver Carrito"),
    COMPRAR_CARRITO("Comprar Carrito"),
    OBTENER_PRODUCTOS_ASCENDENTEMENTE("Obtener Productos Ascendentemente"),
    OBTENER_USUARIOS("Obtener Usuarios"),
    OBTENER_HISTORIAL_DE_COMPRAS("Obtener Historial de Compras"),
    AGREGAR_SALDO_USUARIO("Agregar Saldo Usuario"),
    CERRAR_SESION("Cerrar Sesión");

    private final String descripcion;

    MenuUsuario(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
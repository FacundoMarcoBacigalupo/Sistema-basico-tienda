public enum MenuPrincipal {
    INICIAR_SESION("Iniciar Sesión"),
    REGISTRAR_USUARIO("Registrar Usuario"),
    SALIR("Salir del Sistema");

    private final String descripcion;

    MenuPrincipal(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
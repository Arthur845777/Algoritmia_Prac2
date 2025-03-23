package Fase1.P2.Ejercicio.GestorInventarioGenerico;

class Producto<T extends Number> {
    private String nombre;
    private T precio; //Generico para que el usuario ponga algun tipo numerico
    private int cantidad;

    public Producto(String nombre, T precio, int cantidad) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    //getters y setters
    public String getNombre() { return nombre; }
    public T getPrecio() { return precio; }
    public int getCantidad() { return cantidad; }
    public void setPrecio(T precio) { this.precio = precio; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
}




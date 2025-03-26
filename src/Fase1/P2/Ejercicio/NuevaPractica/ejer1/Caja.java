package Fase1.P2.Ejercicio.NuevaPractica.ejer1;
class Caja<T> {
    private T elemento;

    public Caja(T elemento) {
        this.elemento = elemento;
    }

    public Caja() {}

    public T getElemento() {
        return elemento;
    }

    public void setElemento(T elemento) {
        this.elemento = elemento;
    }

    public static <T> void add(T elemento, Caja<T> caja) {
        caja.setElemento(elemento);
    }

    public static void main(String[] args) {
        Caja<Integer> c = new Caja<>();
        Caja.add(43, c);

        System.out.println("El elemento en la caja es: " + c.getElemento());

        Caja<String> cajaTexto = new Caja<>();
        Caja.add("Hola Mundo", cajaTexto);
        System.out.println("El elemento en la caja de texto es: " + cajaTexto.getElemento());

    }
}

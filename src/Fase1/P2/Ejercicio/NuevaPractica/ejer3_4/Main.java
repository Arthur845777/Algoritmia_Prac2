package Fase1.P2.Ejercicio.NuevaPractica.ejer3_4;

public class Main {
    public static void main(String[] args) {
        Caja<Integer> c = new Caja<>("azul");
        Cajoneria<Caja<?>> cajaGeneral = new Cajoneria<>();

        c.add(43, c);

        Caja<String> cajaTexto = new Caja<>("rojo");
        cajaTexto.add("Hola Mundo", cajaTexto);

        Caja<Double> caja3 = new Caja<>("Verde");
        caja3.add(99.99, caja3);

        cajaGeneral.add(c);
        cajaGeneral.add(cajaTexto);
        cajaGeneral.add(caja3);

        cajaGeneral.mostrarCajas();
        System.out.println(cajaGeneral.delete(caja3));
        cajaGeneral.mostrarCajas();

        System.out.println(cajaGeneral.search(caja3));

        System.out.println(cajaGeneral);


    }
}

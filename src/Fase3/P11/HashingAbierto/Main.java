package Fase3.P11.HashingAbierto;

public class Main {
    public static void main(String[] args) {
        // Crear tabla hash de tamaño 10
        HashO tabla = new HashO(10);
        int[] valores = {34, 3, 7, 30, 11, 8, 7, 23, 41, 16, 34, 55};
        int numeroPruebas = 1;

        // Insertar registros
        for (int v : valores) {
            String str = "R" + numeroPruebas;
            tabla.insert(new Register(v, str));
            numeroPruebas++;
            System.out.println("Tabla después de inserción:");
            tabla.printTable();
        }

        // Eliminar un registro
        tabla.delete(30);
        System.out.println("Tabla después de eliminar clave 30:");
        tabla.printTable();

        // Buscar una clave
        Register encontrado = tabla.search(23);
        if (encontrado != null) {
            System.out.println("Registro encontrado: " + encontrado);
        } else {
            System.out.println("Registro no encontrado.");
        }

        // Intentar buscar clave eliminada
        encontrado = tabla.search(30);
        System.out.println(encontrado != null ? "Encontrado: " + encontrado : "Clave 30 no está en la tabla.");
    }
}


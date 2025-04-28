package Fase2.P5.Ejer.lista;

public class Main {
    public static void main(String[] args) {
        // Crear la primera lista con números
        LinkedList<Integer> listaNumeros = new LinkedList<>();
        System.out.println("--- Creando lista de números ---");
        Ejercicios.insertar(listaNumeros, 10);
        Ejercicios.insertar(listaNumeros, 20);
        Ejercicios.insertar(listaNumeros, 30);
        Ejercicios.insertar(listaNumeros, 40);
        Ejercicios.insertar(listaNumeros, 50);
        
        // Crear la segunda lista con números
        LinkedList<Integer> listaNumeros2 = new LinkedList<>();
        System.out.println("\n--- Creando segunda lista de números ---");
        Ejercicios.insertar(listaNumeros2, 100);
        Ejercicios.insertar(listaNumeros2, 200);
        Ejercicios.insertar(listaNumeros2, 300);
        
        // Crear una lista para comparación
        LinkedList<Integer> listaComparacion = new LinkedList<>();
        System.out.println("\n--- Creando lista para comparación ---");
        Ejercicios.insertar(listaComparacion, 10);
        Ejercicios.insertar(listaComparacion, 20);
        Ejercicios.insertar(listaComparacion, 30);
        Ejercicios.insertar(listaComparacion, 40);
        Ejercicios.insertar(listaComparacion, 50);
        
        // Crear una lista de strings
        LinkedList<String> listaStrings = new LinkedList<>();
        System.out.println("\n--- Creando lista de strings ---");
        Ejercicios.insertar(listaStrings, "Hola");
        Ejercicios.insertar(listaStrings, "Mundo");
        Ejercicios.insertar(listaStrings, "Java");
        
        // Probar el método search
        System.out.println("\n--- Prueba método search ---");
        System.out.println("¿Está el 30 en la lista?: " + Ejercicios.search(listaNumeros, 30));
        System.out.println("¿Está el 60 en la lista?: " + Ejercicios.search(listaNumeros, 60));
        System.out.println("¿Está 'Java' en la lista de strings?: " + Ejercicios.search(listaStrings, "Java"));
        System.out.println("¿Está 'Python' en la lista de strings?: " + Ejercicios.search(listaStrings, "Python"));
        
        // Probar el método contar
        System.out.println("\n--- Prueba método contar ---");
        System.out.println("La lista de números tiene " + Ejercicios.contar(listaNumeros) + " elementos");
        System.out.println("La lista de strings tiene " + Ejercicios.contar(listaStrings) + " elementos");
        
        // Probar el método comparar
        System.out.println("\n--- Prueba método comparar ---");
        System.out.println("¿Son iguales listaNumeros y listaComparacion?: " + 
                          Ejercicios.comparar(listaNumeros, listaComparacion));
        System.out.println("¿Son iguales listaNumeros y listaNumeros2?: " + 
                          Ejercicios.comparar(listaNumeros, listaNumeros2));
        
        // Probar el método unir
        System.out.println("\n--- Prueba método unir ---");
        System.out.println("Uniendo listaNumeros con listaNumeros2");
        Ejercicios.unir(listaNumeros, listaNumeros2);
        
        // Probar el método invertir
        System.out.println("\n--- Prueba método invertir con lista par ---");
        LinkedList<Character> listaParCaracteres = new LinkedList<>();
        Ejercicios.insertar(listaParCaracteres, 'A');
        Ejercicios.insertar(listaParCaracteres, 'B');
        Ejercicios.insertar(listaParCaracteres, 'C');
        Ejercicios.insertar(listaParCaracteres, 'D');
        Ejercicios.invertir(listaParCaracteres);
        
        System.out.println("\n--- Prueba método invertir con lista impar ---");
        LinkedList<Character> listaImparCaracteres = new LinkedList<>();
        Ejercicios.insertar(listaImparCaracteres, 'X');
        Ejercicios.insertar(listaImparCaracteres, 'Y');
        Ejercicios.insertar(listaImparCaracteres, 'Z');
        Ejercicios.invertir(listaImparCaracteres);
        
        
    }
}
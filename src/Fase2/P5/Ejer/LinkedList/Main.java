package Fase2.P5.Ejer.LinkedList;

public class Main {
    public static void main(String[] args) {
        
        // Crear lista vacía
        LinkedList<Integer> list = new LinkedList<>();
        
        // Insertar elementos
        LinkedListFunctions.insertar(list, 5);
        LinkedListFunctions.insertar(list, 10);
        LinkedListFunctions.insertar(list, 15);
        LinkedListFunctions.insertar(list, 20);
        
        // Mostrar lista
        System.out.println("Lista actual: " + LinkedListFunctions.toString(list));
        
        // Buscar elemento
        System.out.println("¿Existe el 10?: " + LinkedListFunctions.search(list, 10));
        System.out.println("¿Existe el 99?: " + LinkedListFunctions.search(list, 99));
        
        // Contar elementos
        System.out.println("Número de elementos en la lista: " + LinkedListFunctions.contar(list));
        
        // Invertir la lista
        LinkedListFunctions.invertir(list);
        
        // Crear otra lista
        LinkedList<Integer> list2 = new LinkedList<>();
        LinkedListFunctions.insertar(list2, 100);
        LinkedListFunctions.insertar(list2, 200);
        LinkedListFunctions.insertar(list2, 300);

        // Mostrar segunda lista
        System.out.println("Segunda lista: " + LinkedListFunctions.toString(list2));
        
        // Comparar listas
        System.out.println("¿Las listas son iguales?: " + LinkedListFunctions.comparar(list, list2));
        
        // Unir listas
        LinkedListFunctions.unir(list, list2);
        
        // Mostrar lista después de unir
        System.out.println("Lista final unida: " + LinkedListFunctions.toString(list));
    }
}

package Fase3.P11.HashingCerrado;

import Fase3.P11.HashingAbierto.Register;

public class HashC {
    private static class Element {
        Register register;
        boolean isAvailable; // true = disponible (vacía o borrada), false = ocupada

        public Element() {
            this.register = null;
            this.isAvailable = true; // vacía inicialmente
        }
    }

    private Element[] table;
    private int size;

    public HashC(int size) {
        int primeSize = obtenerPrimoMasCercano(size);
        this.size = primeSize;
        this.table = new Element[primeSize];
    }

    // Devuelve el primo más cercano
    public static int obtenerPrimoMasCercano(int n) {
        if (n <= 2) return 2;
        if (esPrimo(n)) return n;

        int menor = n;
        int mayor = n;

        while (!esPrimo(menor)) menor--;
        while (!esPrimo(mayor)) mayor++;

        return (n - menor) <= (mayor - n) ? menor : mayor;
    }

    // Verifica si un número es primo
    public static boolean esPrimo(int num) {
        if (num < 2) return false;
        if (num == 2) return true;
        if (num % 2 == 0) return false;

        for (int i = 3; i <= Math.sqrt(num); i += 2) {
            if (num % i == 0) return false;
        }

        return true;
    }

    private int hash(int key) {
        return key % size;
    }

    public void insert(Register reg) {
        int key = reg.getKey();
        int index = hash(key);
        int originalIndex = index;
        
        do {
        	//Es nulo por borrado logico o esta disponible
            if (table[index] == null || table[index].isAvailable) {
                if (table[index] == null) table[index] = new Element();
                table[index].register = reg;
                table[index].isAvailable = false; // ahora está ocupada
                return;
            }
            index = (index + 1) % size;
        } while (index != originalIndex);

        System.out.println("Error: tabla hash llena.");
    }

    public Register search(int key) {
        int index = hash(key);
        int originalIndex = index;

        do {
            if (table[index] == null) return null;
            //Si no esta disponible y es la clave correspondiente y no es nulo
            if (!table[index].isAvailable && table[index].register.getKey() == key && table[index].register != null) {
                return table[index].register;
            }
            index = (index + 1) % size;
        } while (index != originalIndex);

        return null;
    }

    public void delete(int key) {
        int index = hash(key);
        int originalIndex = index;

        do {
            if (table[index] == null) return;
            //Si no esta disponible y es la clave correspondiente y no es nulo
            if (!table[index].isAvailable && table[index].register.getKey() == key && table[index].register != null) {
                table[index].register = null;
                table[index].isAvailable = true; // ahora está disponible
                return;
            }
            index = (index + 1) % size;
        } while (index != originalIndex);
    }

    public void printTable() {
        for (int i = 0; i < size; i++) {
            System.out.print("[" + i + "]: ");
            //Si hay un registro y la tabla no esta disponible y el regitro no es nulo
            if (table[i] != null && !table[i].isAvailable && table[i].register != null) {
                System.out.println(table[i].register);
            } else {
                System.out.println("vacío");
            }
        }
    }
}

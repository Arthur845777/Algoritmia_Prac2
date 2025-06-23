package Fase3.P11.HashingCerrado;

import Fase3.P11.HashingAbierto.Register;

public class HashC<E> {
    private static class Element {
        Register register;
        int state; // 0 = nunca usado, 1 = ocupado, -1 = borrado

        public Element() {
            this.register = null;
            this.state = 0; // nunca usado inicialmente
        }
    }

    private Element[] table;
    private int size;

    public HashC(int size) {
        int primeSize = obtenerPrimoMasCercano(size);
        this.size = primeSize;
        this.table = new Element[primeSize];
    }

    public static int obtenerPrimoMasCercano(int n) {
        if (n <= 2) {
            return 2;
        }
        if (esPrimo(n)) {
            return n;
        }

        int menor = n;
        int mayor = n;

        while (!esPrimo(menor)) {
            menor--;
        }

        while (!esPrimo(mayor)) {
            mayor++;
        }

        return (n - menor) <= (mayor - n) ? menor : mayor;
    }

    public static boolean esPrimo(int num) {
        if (num < 2) {
            return false;
        }

        if (num == 2) {
            return true;
        }

        if (num % 2 == 0) {
            return false;
        }

        for (int i = 3; i <= Math.sqrt(num); i += 2) {
            if (num % i == 0) {
                return false;
            }
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
            if (table[index].state == 0 || table[index].state == -1) {

                table[index] = new Element();

                table[index].register = reg;
                table[index].state = 1;
                return;
            }

            if (table[index].state == 1 && table[index].register.getKey() == key) {
                System.out.println("Elemento ya existe");
                return;
            }

            index = (index + 1) % size; // hash(index + 1)

        } while (index != originalIndex);

        System.out.println("Error: tabla hash llena.");
    }

    public Register search(int key) {
        int index = hash(key);
        int originalIndex = index;

        do {
            if (table[index] == null) {
                return null;
            }

            if (table[index].state == 1 && table[index].register.getKey() == key) {
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
            if (table[index] == null) {
                return;
            }


            if (table[index].state == 1 && table[index].register.getKey() == key) {
                table[index].register = null;
                table[index].state = -1;
                return;
            }

            index = (index + 1) % size;

        } while (index != originalIndex);
    }

    public void printTable() {
        for (int i = 0; i < size; i++) {
            System.out.print("[" + i + "]: ");

            if (table[i] != null && table[i].state == 1) {
                System.out.println(table[i].register);
            } else if (table[i] != null && table[i].state == -1) {
                System.out.println("borrado");
            } else {
                System.out.println("vacío");
            }
        }
    }
}
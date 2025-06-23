package Fase3.P11.HashingAbierto;

import Fase3.P11.LinkedList.LinkedList;

public class HashO {
    private LinkedList<Register>[] table;
    private int size;

    @SuppressWarnings("unchecked")

    public HashO(int size) {
        this.size = obtenerPrimoMasCercano(size);
        this.table = new LinkedList[this.size];

        for (int i = 0; i < this.size; i++) {
            table[i] = new LinkedList<>();
        }
    }

    public static int obtenerPrimoMasCercano(int n) {
        if (n <= 2) {
            return 2;
        }
        if (esPrimo(n)) {
            return n;
        }

        int menor = n - 1;
        int mayor = n + 1;

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
        if (reg == null) {
            return;
        }

        int index = hash(reg.getKey());
        LinkedList<Register> list = table[index];

        if (search(reg.getKey()) == null) {
            return;
        }

        list.insertLast(reg);
    }

    public Register search(int key) {
        int index = hash(key);
        LinkedList<Register> list = table[index];

        for (int i = 0; i < list.length(); i++) {
            Register record = list.getNodeAtIndex(i);

            if (record != null && record.getKey() == key) {
                return record;
            }
        }

        return null;
    }

    public boolean delete(int key) {
        int index = hash(key);
        LinkedList<Register> list = table[index];

        for (int i = 0; i < list.length(); i++) {
            Register record = list.getNodeAtIndex(i);

            if (record != null && record.getKey() == key) {
                return list.removeNode(record);
            }

        }

        return false;
    }

    public void printTable() {
        for (int i = 0; i < size; i++) {
            System.out.print("[" + i + "]: ");
            LinkedList<Register> list = table[i];

            if (list.isEmpty()) {
                System.out.println("vacío");
            } else {
                for (int j = 0; j < list.length(); j++) {
                    Register r = list.getNodeAtIndex(j);
                    System.out.print(r + " -> ");
                }
                System.out.println("null");
            }
        }
    }


    public int getSize() {
        return size;
    }
}
package Fase2.P5.Act.TareaSanti;

import java.util.ArrayList;

public class Ejercicio<T extends Comparable<T>> {

//        public boolean findItem(ArrayList<T> taskComplete, T task) {
//        boolean found = false;
//        for (T t : taskComplete) {
//            if (t.equals(task)) {
//                return true;
//            }
//        }
//        return false;
//    }

    public boolean findItem(ArrayList<T> taskComplete, T task) {
        return taskComplete.contains(task);
    }

    public ArrayList reverseList(ArrayList<T> taskComplete){
        ArrayList<T> auxTask = new ArrayList<>();
        for (int i = taskComplete.size(); i > 0; i--) {
            auxTask.add(taskComplete.get(i - 1));
        }
        taskComplete = auxTask;
        return taskComplete;
    }

    public void AddNodoFinal(Nodo<T> head, T nodo) {
        Nodo<T> newNode = new Nodo<T>(nodo);
        if (head == null) {
            head = newNode;
        } else {
            Nodo<T> nodoCurrent = head;
            while (nodoCurrent.next != null) {
                nodoCurrent = nodoCurrent.next;
            }
            nodoCurrent.next = newNode;
        }
    }

    public static int contarNodo(Nodo<?> head){
        if (head == null) {
            return 0;
        } else {
            Nodo<?> nodoCurrent = head;
            int count = 1;
            while (nodoCurrent.next != null){
                nodoCurrent = nodoCurrent.next;
                count++;
            }
            return count;
        }
    }

    public boolean compararLinkedList(Nodo<T> head1, Nodo<T> head2){
        if(head1 == null && head2 == null){
            return true;
        }

        if(head1 == null || head2 == null){
            return false;
        }

        int head1Count = contarNodo(head1);
        int head2Count = contarNodo(head2);
        if(head1Count != head2Count){
            return false;
        }

        while (head1.next != null && head2.next != null){
            if(!head1.dato.equals(head2.dato)){
                return false;
            }
            head1 = head1.next;
            head2 = head2.next;
        }
        return true;
    }

    public Nodo<T> concatenarLinkedList(Nodo<T> head1, Nodo<T> head2){
        if (head1 == null) {
            return head2;
        }

        if (head2 == null) {
            return head1;
        }

        Nodo<T> nodoCurrent = head1;
        while (nodoCurrent.next != null){
            nodoCurrent = nodoCurrent.next;
        }
        nodoCurrent.next = head2;

        return head1;
    }

    public static void printNode(Nodo<?> head){
        if (head == null) {
            System.out.println("Lista Vacia");
            return;
        } else {
            Nodo<?> nodoCurrent = head;
            do {
                System.out.print(nodoCurrent.dato + "\n");
                nodoCurrent = nodoCurrent.next;
            }while (true);
        }
    }

}

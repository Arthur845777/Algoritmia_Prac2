package Fase2.P6.Ejer.Ejercicio1_3.Test;

import Fase2.P6.Ejer.Ejercicio1_3.PriorityQueue.PriorityQueueLinked;
import Fase2.P6.Ejer.Ejercicio1_3.ExceptionIsEmpty.ExceptionIsEmpty;

public class TestPriorityQueueLinked {
    public static void main(String[] args) {
        PriorityQueueLinked<String, Integer> pq = new PriorityQueueLinked<>(6); // prioridades de 0 a 5

        pq.enqueue("A", 2);
        pq.enqueue("B", 5);
        pq.enqueue("C", 3);
        pq.enqueue("D", 5);
        pq.enqueue("E", 1);

        System.out.println("PriorityQueueLinked:");
        System.out.println(pq);

        try {
            System.out.println("Front: " + pq.front()); 
            System.out.println("Back: " + pq.back());  
            System.out.println("Dequeue: " + pq.dequeue());
            System.out.println("After Dequeue:");
            System.out.println(pq);
        } catch (ExceptionIsEmpty e) {
            System.out.println(e.getMessage());
        }
    }
}

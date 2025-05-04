package Fase2.P6.Test;

import Fase2.P6.PriorityQueue.PriorityQueueLinkSort;
import Fase2.P6.ExceptionIsEmpty.ExceptionIsEmpty;

public class TestPriorityQueueLinkSort {
    public static void main(String[] args) {
        PriorityQueueLinkSort<String, Integer> pq = new PriorityQueueLinkSort<>();

        pq.enqueue("A", 2);
        pq.enqueue("B", 5);
        pq.enqueue("C", 3);
        pq.enqueue("D", 5);
        pq.enqueue("E", 1);

        System.out.println("PriorityQueueLinkSort:");
        System.out.println(pq);

        try {
            System.out.println("Front: " + pq.front()); // B
            System.out.println("Back: " + pq.back());   // E
            System.out.println("Dequeue: " + pq.dequeue()); // B
            System.out.println("After Dequeue:");
            System.out.println(pq);
        } catch (ExceptionIsEmpty e) {
            System.out.println(e.getMessage());
        }
    }
}

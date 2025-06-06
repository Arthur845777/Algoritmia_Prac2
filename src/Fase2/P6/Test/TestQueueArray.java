package Fase2.P6.Test;

import Fase2.P6.Queue.ArrayQueue;
import Fase2.P6.ExceptionIsEmpty.ExceptionIsEmpty;

public class TestQueueArray {
    public static void main(String[] args) {
        ArrayQueue<String> queue = new ArrayQueue<>(4); // capacidad lógica: 4

        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");
        queue.enqueue("D"); // Debería llenar la cola
        queue.enqueue("E"); // No se añade, cola llena

        try {
            System.out.println("Front: " + queue.front()); // A
            System.out.println("Back: " + queue.back());   // D

            System.out.println("Dequeue: " + queue.dequeue()); // A
            System.out.println("Dequeue: " + queue.dequeue()); // B

            queue.enqueue("E"); // Ahora sí entra
            System.out.println("Front: " + queue.front()); // C
            System.out.println("Back: " + queue.back());   // E

        } catch (ExceptionIsEmpty e) {
            System.out.println(e.getMessage());
        }
    }
}

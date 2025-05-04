package Fase2.P6.PriorityQueue;

import Fase2.P6.Queue.LinkedQueue;
import Fase2.P6.ExceptionIsEmpty.ExceptionIsEmpty;

public class PriorityQueueLinked<E, N extends Number> implements PriorityQueue<E, N> {

    private LinkedQueue<E>[] queues;
    private int numPriorities;

    public PriorityQueueLinked(int numPriorities) {
        this.numPriorities = numPriorities;
        this.queues = new LinkedQueue[numPriorities];
        for (int i = 0; i < numPriorities; i++) {
            queues[i] = new LinkedQueue<>();
        }
    }

    public void enqueue(E x, N pr) {
        int priority = pr.intValue();
        if (priority < 0 || priority >= numPriorities) {
            throw new IllegalArgumentException("Invalid priority: " + priority);
        }
        queues[priority].enqueue(x);
    }

    public E dequeue() throws ExceptionIsEmpty {
        for (int i = 0; i < numPriorities; i++) {
            if (!queues[i].isEmpty()) {
                return queues[i].dequeue();
            }
        }
        throw new ExceptionIsEmpty();
    }

    public E front() throws ExceptionIsEmpty {
        for (int i = 0; i < numPriorities; i++) {
            if (!queues[i].isEmpty()) {
                return queues[i].front();
            }
        }
        throw new ExceptionIsEmpty();
    }

    public E back() throws ExceptionIsEmpty {
        for (int i = numPriorities - 1; i >= 0; i--) {
            if (!queues[i].isEmpty()) {
                return queues[i].back();
            }
        }
        throw new ExceptionIsEmpty();
    }

    public boolean isEmpty() {
        for (int i = 0; i < numPriorities; i++) {
            if (!queues[i].isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numPriorities; i++) {
            sb.append("Priority ").append(i).append(": ").append(queues[i].toString()).append("\n");
        }
        return sb.toString();
    }
}

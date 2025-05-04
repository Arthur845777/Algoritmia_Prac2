package PriorityQueue;

import Queue.QueueLink;
import ExceptionIsEmpty.ExceptionIsEmpty;

public class PriorityQueueLinked<E, N extends Number> implements PriorityQueue<E, N> {

    private QueueLink<E>[] queues;
    private int numPriorities;

    public PriorityQueueLinked(int numPriorities) {
        this.numPriorities = numPriorities;
        this.queues = new QueueLink[numPriorities];
        for (int i = 0; i < numPriorities; i++) {
            queues[i] = new QueueLink<>();
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
        for (int i = numPriorities - 1; i >= 0; i--) {
            if (!queues[i].isEmpty()) {
                return queues[i].dequeue();
            }
        }
        throw new ExceptionIsEmpty("Priority queue is empty");
    }

    public E front() throws ExceptionIsEmpty {
        for (int i = numPriorities - 1; i >= 0; i--) {
            if (!queues[i].isEmpty()) {
                return queues[i].front();
            }
        }
        throw new ExceptionIsEmpty("Priority queue is empty");
    }

    public E back() throws ExceptionIsEmpty {
        for (int i = 0; i < numPriorities; i++) {
            if (!queues[i].isEmpty()) {
                return queues[i].back();
            }
        }
        throw new ExceptionIsEmpty("Priority queue is empty");
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
        for (int i = numPriorities - 1; i >= 0; i--) {
            sb.append("Priority ").append(i).append(": ").append(queues[i].toString()).append("\n");
        }
        return sb.toString();
    }
}

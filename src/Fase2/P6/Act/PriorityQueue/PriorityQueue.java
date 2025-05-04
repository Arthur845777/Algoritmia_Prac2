package Fase2.P6.Act.PriorityQueue;

public interface PriorityQueue<E, N>{
    public void enqueue(E e, N n);
    public E dequeue() throws ExceptionIsEmpty;
    public E front() throws ExceptionIsEmpty;
    public E back() throws ExceptionIsEmpty;
    public boolean isEmpty();
}

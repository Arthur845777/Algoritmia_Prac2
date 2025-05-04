package Fase2.P6.Act.QueueLinked;


public interface Queue<E> {
    public void enqueue(E e);
    public E dequeue() throws ExceptionIsEmpty;
    public E front() throws ExceptionIsEmpty;
    public E back() throws ExceptionIsEmpty;
    public boolean isEmpty();
}

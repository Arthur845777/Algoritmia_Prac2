package Fase2.P7.miguel.Arbolito;
import Fase2.P7.miguel.Excepciones.*;

public interface BinarySearchTree<E> {
    void insert(E data) throws ItemDuplicated;
    E search(E data) throws ItemNotFound;
    void delete(E data) throws ExceptionIsEmpty;
    boolean isEmpty();
}

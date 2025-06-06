package Fase2.P7.BSTreeInterface;

import Fase2.P7.Exceptions.ExceptionIsEmpty;
import Fase2.P7.Exceptions.ItemDuplicated;
import Fase2.P7.Exceptions.ItemNotFound;

public interface BinarySearchTree<E> {
    void insert(E element) throws ItemDuplicated;
    E search(E element) throws ItemNotFound;
    void delete(E element) throws ExceptionIsEmpty;
    boolean isEmpty();
}

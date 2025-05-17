package Fase2.P8.BSTreeInterface;

import Fase2.P8.Exceptions.ExceptionIsEmpty;
import Fase2.P8.Exceptions.ItemDuplicated;
import Fase2.P8.Exceptions.ItemNotFound;

public interface BinarySearchTree<E> {
    void insert(E element) throws ItemDuplicated;
    E search(E element) throws ItemNotFound;
    void delete(E element) throws ExceptionIsEmpty;
    boolean isEmpty();
}

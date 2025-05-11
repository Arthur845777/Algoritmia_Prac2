package Fase2.P7.jose.BST;

import Fase2.P7.jose.Exceptions.ExceptionIsEmpty;
import Fase2.P7.jose.Exceptions.ItemDuplicated;
import Fase2.P7.jose.Exceptions.ItemNotFound;

public interface BinarySearchTree<E> {
	void insert(E data) throws ItemDuplicated;
	E search(E data) throws ItemNotFound;
	void delete(E data) throws ExceptionIsEmpty, ItemNotFound;
	boolean isEmpty();
}

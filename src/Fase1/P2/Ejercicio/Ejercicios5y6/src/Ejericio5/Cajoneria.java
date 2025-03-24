package Ejericio5;

import java.util.ArrayList;
import java.util.Iterator;

public class Cajoneria <T> implements Iterable<T>{
	private ArrayList<T> cajoneria = new ArrayList<>();
	
	public void agregar(T item) {
		cajoneria.add(item);
	}
	
	public ArrayList<T> getLista(){
		return cajoneria;
	}
	
	public void borrarContenido() {
		cajoneria.clear();
	}
	
	@Override
	public Iterator<T> iterator() {
		return cajoneria.iterator();
	}
	
}

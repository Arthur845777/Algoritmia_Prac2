package Fase2.P6.ExceptionIsEmpty;

public class ExceptionIsEmpty extends Exception {
	public ExceptionIsEmpty() {
		super("El stack esta vacio");
	}
}
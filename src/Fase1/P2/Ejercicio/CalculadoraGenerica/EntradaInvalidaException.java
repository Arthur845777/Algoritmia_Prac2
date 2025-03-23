package Fase1.P2.Ejercicio.CalculadoraGenerica;

class EntradaInvalidaException extends Exception {
    public EntradaInvalidaException() {
        super("Error: Entrada no válida. Debe ingresar un número.");
    }
}
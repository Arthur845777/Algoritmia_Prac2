package Fase1.P2.Ejercicio.CalculadoraGenerica;

public class Operador<T extends Number>  {
    private T valor1;
    private T valor2;

    public Operador(T valor1, T valor2) {
        this.valor1 = valor1;
        this.valor2 = valor2;
    }

    public T getValor1() {
        return valor1;
    }

    public T getValor2() {
        return valor2;
    }
}

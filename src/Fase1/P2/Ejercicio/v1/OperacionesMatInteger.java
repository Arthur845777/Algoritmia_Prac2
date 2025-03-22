package Fase1.P2.Ejercicio.v1;

public class OperacionesMatInteger implements Operable<Integer> {
    @Override
    public Integer suma(Integer a, Integer b) {
        return a + b;
    }

    @Override
    public Integer resta(Integer a, Integer b) {
        return a - b;
    }

    @Override
    public Integer multiplicacion(Integer a, Integer b) {
        return a * b;
    }

    @Override
    public Integer division(Integer a, Integer b) {
        return a / b;
    }

}

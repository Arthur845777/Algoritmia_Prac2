package Fase1.P2.Ejercicio;

//public interface Operable <E> {
//    E suma(E obje);
//    E resta(E obje);
//    E multiplicacion(E obje);
//    E division(E obje);
//}

public interface Operable<N extends Number> {
    public N suma(N operando1, N operando2);
    public N resta(N operando1, N operando2);
    public N multiplicacion(N operando1, N operando2);
    public N division(N operando1, N operando2);
}

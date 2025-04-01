package Fase1.P3.Actividad;

import java.util.Scanner;

public class Act_Alg_No_Iterativos {

    public static double potencia(double x, double y) {
        double t;
        if (y == 0) {                           // O(1)
            return 1.0;                         // O(1)
        }
        if (y % 2 == 1) {                       // O(1)
            return x * potencia(x, y - 1);   // O(y - 1)
        }
        else {
            t = potencia(x, y / 2);          // O(y / 2)
            return t * t;                       // O(1)
        }
    }

    public static void main(String[] args) {
        System.out.println(potencia(2,3));
    }

}

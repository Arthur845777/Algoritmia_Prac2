package Fase1.P1.Actividad.Teoria;

public class arreglo {

    int sumaFila(int mat[][], int fila) {
        int suma = 0;
        for (int i = 0; i < mat[fila].length; i++) {
            suma += mat[fila][i];
        }
        return suma;
    }

    int sumaFila(int mat[][]) {
        int suma = 0;
        for (int i = 0; i < mat.length; i++) {
            suma += mat[i][0];
        }

        return suma;
    }

//    int sumaMat(int matA[][], int matB[][]){
//        int suma[][] = new int[2][2];
//
//        //IlegalArgumentException
//        int limitFila = Math.max(matA.length, matB.length);
//
//        for (int i = 0; i < matA[0].length; i++) {}
//
//        int limitColumna = sumaMat(matA, matB);
//
//        for (int i = 0; i < (matA.length); i++) {
//            for (int j = 0; j < matA[i].length; j++) {
//                suma[i][j] = matA[i][j] ;
//            }
//        }
//
//
//    }

}
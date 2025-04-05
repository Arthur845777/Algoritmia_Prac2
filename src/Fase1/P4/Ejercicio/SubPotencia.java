package Fase1.P4.Ejercicio;

public class SubPotencia {

    public static boolean PotenRestringido(int size, int[] arreglo, int resultSum) {
        int[] aux = new int[size];
        int t = 0;
        int sum = 0;

        for(int i = 0; i < arreglo.length; i++) {
            if (esPotenciaDeDos(arreglo[i])) {
                aux[t++] = arreglo[i];
            }
        }

        for(int i = 0; i < arreglo.length; i++){
            if((arreglo[i] % 5 == 0) && (i + 1 <= arreglo.length && arreglo[i + 1] % 2 == 0)) {
                aux[t++] = arreglo[i];
            }
        }

        for(int i = 0; i < aux.length; i++){
            sum += aux[i];
        }
        if(sum == resultSum){
            return true;
        }
        return false;
    }

    public static boolean esPotenciaDeDos(int n) {
        if (n <= 0) {
            return false;
        }

        return (n & (n-1)) == 0;
    }

    public static void main(String[] args) {
        int[] numeros = {5,4,8,10,6,5,27};
        boolean resultado = PotenRestringido(numeros.length, numeros, 27);
        System.out.println(resultado);
    }


}
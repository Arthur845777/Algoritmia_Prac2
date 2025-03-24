package Fase1.P2.Ejercicio.ConversorUnidadesGenerico;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String categoria="Nada";
        String cateC;
        int opc=-1;
        Scanner sc= new Scanner(System.in);
        double num1=0;
        Unidad<Double> uniG= new Unidad<Double>(categoria, num1);
        do {
            do {
                System.out.println("Elija una conversion");
                System.out.println("1. Convertir longitud (metros, pies, pulgadas)\r\n" + //
                        "2. Convertir masa (kilogramos, libras, onzas).\r\n" + //
                        "3. Convertir temperatura (Celsius, Fahrenheit, Kelvin).\r\n" + //
                        "4. Convertir tiempo o (segundos, minutos, horas).\r\n" + //
                        "5. Salir del Programa.");
                opc=sc.nextInt();
                sc.nextLine(); // Limpia el buffer antes de leer cadenas
            } while (opc<0 || opc>5);

            do {
                System.out.println("Ingrese la unidad inicial");
                categoria=sc.nextLine();
                uniG.setTipo(categoria);
                System.out.println("Ingrese el valor de la unidad");
                num1= sc.nextDouble();
                uniG.setValor(num1);
                sc.nextLine();
                System.out.println("Ingrese la unidad a la quese desea convertir");
                cateC=sc.nextLine();
            } while (!UnidadesC(uniG,cateC));

            boolean compo = Conversor.EsIgual(uniG, cateC);
            if (!compo) {
                switch (opc) {
                    case 1:
                        System.out.println("El resultado de convertir " + uniG.getTipo() + " a "+ cateC + "es\n"
                                    + Resultados("Longitud",uniG,cateC));
                        break;

                    case 2:
                        System.out.println("El resultado de convertir " + uniG.getTipo() + " a "+ cateC + "es\n"
                                    + Resultados("Masa",uniG,cateC));
                        break;

                    case 3:
                        System.out.println("El resultado de convertir " + uniG.getTipo() + " a "+ cateC + "es\n"
                                    + Resultados("Temperatura",uniG,cateC));
                        break;

                    case 4:
                        System.out.println("El resultado de convertir " + uniG.getTipo() + " a "+ cateC + "es\n"
                                    + Resultados("Tiempo",uniG,cateC));
                        break;

                    default:
                        break;
                }
            }

        } while (opc!=5);
    }

    public static <T extends Unidad<? extends Number>> boolean UnidadesC(T uni, String type) {
        ArrayList<String> Longitud = new ArrayList<>(Arrays.asList("metros", "pies", "pulgadas"));
        ArrayList<String> Masa = new ArrayList<>(Arrays.asList("kilogramos", "libras", "onzas"));
        ArrayList<String> Temperatura = new ArrayList<>(Arrays.asList("Celsius", "Fahrenheit", "Kelvin"));
        ArrayList<String> Tiempo = new ArrayList<>(Arrays.asList("segundos", "minutos", "horas"));

        String tipoUnidad = uni.getTipo().trim().toLowerCase();
        String tipoChange = type.trim().toLowerCase();

        // Verificar si tipoUnidad y type pertenecen a la misma lista
        if (Longitud.contains(tipoUnidad) && Longitud.contains(tipoChange)) return true;
        if (Masa.contains(tipoUnidad) && Masa.contains(tipoChange)) return true;
        if (Temperatura.contains(tipoUnidad) && Temperatura.contains(tipoChange)) return true;
        if (Tiempo.contains(tipoUnidad) && Tiempo.contains(tipoChange)) return true;
        System.out.println("Por favor ingrese unidades que en las que sea posible");
        return false;  // Si no están en la misma lista, retorna false
    }

    public static <T extends Unidad<? extends Number>> double Resultados(String Cate,T uni, String type){

        if(Cate.equals("Longitud")){
            return  Conversor.Longitud(uni, type);
        }
        else if(Cate.equals("Masa")){
            return Conversor.Masa(uni, type);
        }else if(Cate.equals("Temperatura")){
            return Conversor.Temperatura(uni, type);
        }else if(Cate.equals("Tiempo")){
            return Conversor.Tiempo(uni, type);
        }
        return 0;
    }
}
package Fase1.P2.Clase;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<BolsaNumeros<? extends Number>> bolsnum= new ArrayList<>();
        ArrayList<SBolsa> bolsP= new ArrayList<>();

        BolsaNumeros<Integer> bolsaNum1 = new BolsaNumeros<>();
        bolsaNum1.add(64);
        bolsaNum1.add(23);
        bolsaNum1.add(54);

        BolsaNumeros<Double> bolsaNum2 = new BolsaNumeros<>();
        bolsaNum2.add(44.9);
        bolsaNum2.add(3.6);
        bolsaNum2.add(9.2);

        bolsnum.add(bolsaNum1);
        bolsnum.add(bolsaNum2);

        for(BolsaNumeros bolsa:bolsnum){
            bolsa.ordenar();
        }

        for(BolsaNumeros bolsa:bolsnum){
            bolsa.mostrar();
        }
        System.out.println("\n");

        SBolsa<String> bolsa3 = new SBolsa<>();
        bolsa3.add("hola");
        bolsa3.add("dddddd");
        bolsa3.add("jjjjjjjjjj");

        SBolsa<Integer> bolsa32 = new SBolsa<>();
        bolsa32.add(464563);
        bolsa32.add(756);
        bolsa32.add(89);

        bolsP.add(bolsa3);
        bolsP.add(bolsa32);


        for(SBolsa i:bolsP){
            i.mostrar();
        }

    }
}


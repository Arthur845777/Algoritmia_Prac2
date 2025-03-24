package Fase1.P2.Actividad.Bolsa;

import java.util.ArrayList;
import java.util.List;

public class Principal {
    public static void main(String[] args) {

        List<Bolsa> listaBolsas = new ArrayList<>();

        Bolsa <Chocolatina> bolsaCho = new Bolsa < Chocolatina > ();
        Bolsa <Golosina> bolsaGolo = new Bolsa<Golosina>();

        listaBolsas.add(bolsaCho.add(new Chocolatina("milka")));
        listaBolsas.add(bolsaGolo.add(new Golosina("picaras",1.2)));
        listaBolsas.add(bolsaCho.add(new Chocolatina("ferrero")));
        listaBolsas.add(bolsaGolo.add(new Golosina("candy",2.1)));
        listaBolsas.add(bolsaCho.add(new Chocolatina("iberica")));
        listaBolsas.add(bolsaGolo.add(new Golosina("soda",2.3)));


        for (Chocolatina chocolatina: bolsaCho) {
            System.out.println(chocolatina.getMarca());
        }

        for (Golosina golo: bolsaGolo) {
            System.out.println(golo);
        }
    }
}
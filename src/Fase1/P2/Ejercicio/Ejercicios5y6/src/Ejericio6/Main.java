package Ejericio6;

import java.util.Scanner;

import Ejericio5.GestorCajoneria;
import Ejericio5.Golosina;

public class Main {
	public static void main(String[] args) {
		GestorCajoneriaChocolatina gc = new GestorCajoneriaChocolatina();
		Scanner sc = new Scanner(System.in);
		Chocolatina c1 = new Chocolatina("C1", 1.0);
		Chocolatina c2 = new Chocolatina("C2", 2.0);
		Chocolatina c3 = new Chocolatina("C3", 3.0);
		Chocolatina c4 = new Chocolatina("C4", 4.0);
		Chocolatina c5 = new Chocolatina("C5", 5.0);
		gc.agregarItem(c1);
		gc.agregarItem(c2);
		gc.agregarItem(c3);
		gc.agregarItem(c4);
		gc.agregarItem(c5);
		gc.buscarItem("C1");
	}
}

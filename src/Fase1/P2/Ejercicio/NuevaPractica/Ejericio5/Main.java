package Fase1.P2.Ejercicio.NuevaPractica.Ejericio5;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		GestorCajoneria gc = new GestorCajoneria();
		Scanner sc = new Scanner(System.in);
		Golosina g1 = new Golosina("Golosina1", 1.0);
		Golosina g2 = new Golosina("Golosina2", 2.0);
		Golosina g3 = new Golosina("Golosina3", 3.0);
		Golosina g4 = new Golosina("Golosina4", 4.0);
		Golosina g5 = new Golosina("Golosina5", 5.0);
		gc.agregarItem(g1);
		gc.agregarItem(g2);
		gc.agregarItem(g3);
		gc.agregarItem(g4);
		gc.agregarItem(g5);
		gc.buscarItem("Golosina1");
		gc.limpiarCajoneria();
	}
}

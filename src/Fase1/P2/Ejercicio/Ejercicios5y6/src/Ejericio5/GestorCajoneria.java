package Ejericio5;

import java.util.Scanner;

public class GestorCajoneria {
	Cajoneria<Golosina> cj = new Cajoneria();
	Scanner sc = new Scanner(System.in);
	
	public void agregarItem() {
		String nombre = sc.nextLine();
		double peso = Double.parseDouble(sc.nextLine());
		Golosina g = new Golosina(nombre, peso);
		cj.agregar(g);
	}
	
	public void agregarItem(Golosina g) {
		cj.agregar(g);
	}
	
	public void buscarItem(String nombre) {
		for (Golosina g :cj.getLista() ) {
			if (g.getNombre().equals(nombre)) {
				System.out.println(g);
			}
		}
	}
	public void limpiarCajoneria() {
		cj.borrarContenido();
	}
}

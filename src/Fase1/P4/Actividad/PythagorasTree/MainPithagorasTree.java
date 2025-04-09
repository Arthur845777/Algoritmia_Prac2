package PythagorasTree;

import javax.swing.JFrame;

public class MainPithagorasTree {
	public static void main(String[] args) {
		crearVentana(6);
		crearVentana(8);
		crearVentana(10);
	}

	private static void crearVentana(int niveles) {
		JFrame ventana = new JFrame("Árbol de Pitágoras - Niveles: " + niveles);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.add(new PythagorasTree(niveles));
		ventana.pack();
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
	}
}

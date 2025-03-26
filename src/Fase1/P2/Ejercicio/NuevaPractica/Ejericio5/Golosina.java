package Fase1.P2.Ejercicio.NuevaPractica.Ejericio5;

public class Golosina {
	String nombre;
	double peso;
	
	public Golosina(String nombre, double peso) {
		this.nombre = nombre;
		this.peso = peso;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}

	@Override
	public String toString() {
		return "Golosina [nombre=" + nombre + ", peso=" + peso + "]";
	}
}

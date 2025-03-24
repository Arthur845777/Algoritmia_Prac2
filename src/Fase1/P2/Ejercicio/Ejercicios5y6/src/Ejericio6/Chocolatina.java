package Ejericio6;

public class Chocolatina {
	String nombre;
	double Peso;

	public Chocolatina(String nombre, double peso) {
		this.nombre = nombre;
		Peso = peso;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getPeso() {
		return Peso;
	}
	public void setPeso(double peso) {
		Peso = peso;
	}
	@Override
	public String toString() {
		return "Chocolatina [nombre=" + nombre + ", Peso=" + Peso + "]";
	}
	
}

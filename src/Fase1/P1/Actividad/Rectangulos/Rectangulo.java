package Fase1.P1.Actividad.Rectangulos;

// Explicacion 4.2
public class Rectangulo {
    private Coordenada esquina1, esquina2;

    // Constructor (4.2)
    public Rectangulo(Coordenada c1, Coordenada c2) {
    	 ordenar(c1,c2);
    }

    // Ordenar (4.2)
    public void ordenar(Coordenada c1, Coordenada c2) {
        double xMin = Math.min(c1.getX(), c2.getX());
        double yMin = Math.min(c1.getY(), c2.getY());
        double xMax = Math.max(c1.getX(), c2.getX());
        double yMax = Math.max(c1.getY(), c2.getY());

        this.esquina1 = new Coordenada(xMin, yMin);
        this.esquina2 = new Coordenada(xMax, yMax);
    }

    public Coordenada getEsquina1() {
        return esquina1;
    }
    public Coordenada getEsquina2() {
        return esquina2;
    }

    public void setEsquina1(Coordenada esquina1) {
        this.esquina1 = esquina1;
    }
    public void setEsquina2(Coordenada esquina2) {
        this.esquina2 = esquina2;
    }

    public double area() {
        return Math.abs((esquina2.getX() - esquina1.getX()) *
                (esquina2.getY() - esquina1.getY()));
    }

    // Distancia Euclidiana (4.2)
    public double DistanciaE(){
        return Math.sqrt(Math.pow(esquina1.getX() - esquina2.getX(), 2) +
        Math.pow(esquina1.getY() - esquina2.getY(), 2));
    }

    @Override
    public String toString() {
        return "Rectangulo {esquina1: " + esquina1 + "; esquina2: " + esquina2 + '}';
    }
}

package Fase1.P1.Actividad.Rectangulos;

public class ContainerRect {
	Rectangulo[] rectangulos;
	double[] distanciasEcleudianas;
	double[] areas;

	static int numRect = 0;
	
	public ContainerRect () {
		this.rectangulos = new Rectangulo[9];
		this.distanciasEcleudianas = new double[9];
		this.areas = new double[9];
	}

	// addRectagulo (4.4.2)
	public void addRectagulo(Rectangulo re){
		if (numRect <= 9){
			this.rectangulos[numRect] = re;
			this.distanciasEcleudianas[numRect] = re.DistanciaE();
			this.areas[numRect] = re.area();
			numRect += 1;
		} else {
			System.out.println("Container lleno");
		}
	}

	// Funciones Generales (4.4.3)
	public void showrectangulos(){
		for (int i = 0; i < numRect; i++){
			System.out.println(i + " ->  " + rectangulos[i]);
		}
	}

	public Rectangulo getRectangulo(int i){
		return rectangulos[i];
	}
	public int getNumrec(){
		return numRect;
	}

	// ToString(4.4.4)
	@Override
	public String toString() {
    	StringBuilder sb = new StringBuilder();
    
    	sb.append(String.format("%-10s %-15s %-15s %-10s %-10s%n", 
                            "Index", "Esquina M", "Esquina N", "Distancia", "Área"));
    	sb.append("------------------------------------------------------------\n");

    	for (int i = 0; i < numRect; i++) {
        	sb.append(String.format("%-10d %-15s %-15s %-10.2f %-10.2f%n", 
            i, 
            rectangulos[i].getEsquina2().toString(),
            rectangulos[i].getEsquina1().toString(),
            distanciasEcleudianas[i], 
            areas[i]));
    	}
   	 	return sb.toString();
	}

}

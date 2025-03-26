package Fase1.P2.Ejercicio.NuevaPractica.ejer2;
public class Main {
    public static void main(String[] args) {
        Bolsa < Chocolatina > bolsaCho = new Bolsa < Chocolatina > ();
        Bolsa<Golosina> bolsaGolo = new Bolsa<Golosina>();
        Chocolatina c = new Chocolatina("milka");
        Chocolatina c1 = new Chocolatina("milka");
        Chocolatina c2 = new Chocolatina("ferrero");
        bolsaCho.add(c);
        bolsaCho.add(c1);
        bolsaCho.add(c2);
        for (Chocolatina chocolatina: bolsaCho) {
            System.out.println(chocolatina.getMarca());
        }

        Golosina g1 = new Golosina("Caramelo", 10.5);
        Golosina g2 = new Golosina("Chicle", 5.2);
        Golosina g3 = new Golosina("Gomita", 12.3);

        bolsaGolo.add(g1);
        bolsaGolo.add(g2);
        bolsaGolo.add(g3);

        for (Golosina golosina : bolsaGolo) {
            System.out.println(golosina);
        }
    }
        
}

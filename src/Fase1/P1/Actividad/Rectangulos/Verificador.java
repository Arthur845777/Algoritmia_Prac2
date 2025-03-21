package Fase1.P1.Actividad.Rectangulos;

class Verificador {
    // EsSobrePos (4.3.2)
    public static boolean esSobrePos(Rectangulo r1, Rectangulo r2) {

        double r1x2 = r1.getEsquina2().getX();
        double r1x1 = r1.getEsquina1().getX();
        double r1y2 = r1.getEsquina2().getY();
        double r1y1 = r1.getEsquina1().getY();

        double r2x1 = r2.getEsquina1().getX();
        double r2x2 = r2.getEsquina2().getX();
        double r2y1 = r2.getEsquina1().getY();
        double r2y2 = r2.getEsquina2().getY();

        if (r1x2 > r2x1 && r1x1 < r2x2 && r1y2 > r2y1 && r1y1 < r2y2) {
            return true;
        }
        return false;
    }

    // esJunto (4.3.2)
    public static boolean esJunto(Rectangulo r1, Rectangulo r2) {
        if(esSobrePos(r1,r2)){
            return false;
        }
        double r1x2 = r1.getEsquina2().getX();
        double r1x1 = r1.getEsquina1().getX();
        double r1y2 = r1.getEsquina2().getY();
        double r1y1 = r1.getEsquina1().getY();

        double r2x2 = r2.getEsquina2().getX();
        double r2x1 = r2.getEsquina1().getX();
        double r2y2 = r2.getEsquina2().getY();
        double r2y1 = r2.getEsquina1().getY();

        boolean tocaX = (r1x2 == r2x1 || r1x1 == r2x2);
        boolean tocaY = (r1y2 == r2y1 || r1y1 == r2y2);

        return (tocaX && (r1y2 >= r2y1 && r1y1 <= r2y2)) ||
                (tocaY && (r1x2 >= r2x1 && r1x1 <= r2x2));
    }

    // esDisunto (4.3.4)
    public static boolean esDisjunto(Rectangulo r1, Rectangulo r2) {
        return !(esSobrePos(r1, r2) || esJunto(r1, r2));
    }

    // Mostrar_R(4.3.5)
    public static void mostrarR(Rectangulo re1, Rectangulo re2) {
        Rectangulo nre = rectanguloSobre(re1, re2);

        System.out.println("\nRectángulo 1: " + re1);
        System.out.println("Rectángulo 2: " + re2);
        System.out.println("Relación entre los rectángulos:");

        boolean esSobrePos = esSobrePos(re1, re2);
        boolean esJunto = esJunto(re1, re2);

        System.out.println("\nEstán sobrepuestos: " + esSobrePos);
        System.out.println("Estan juntos (se tocan sin sobreponerse): " + esJunto);
        System.out.println("Son disjuntos : " + esDisjunto(re1, re2));

        if (esSobrePos) {
            System.out.println("\nEl rectángulo de intersección es: \n" + nre);
            System.out.println("\nEl área de la intersección es: \n" + nre.area());
        }
    }

    // RectanguloSobre(4.3.6)
    public static Rectangulo rectanguloSobre(Rectangulo r1, Rectangulo r2) {
        double interseccionXMin = Math.max(r1.getEsquina1().getX(), r2.getEsquina1().getX());
        double interseccionYMin = Math.max(r1.getEsquina1().getY(), r2.getEsquina1().getY());
        double interseccionXMax = Math.min(r1.getEsquina2().getX(), r2.getEsquina2().getX());
        double interseccionYMax = Math.min(r1.getEsquina2().getY(), r2.getEsquina2().getY());

        return new Rectangulo(
                new Coordenada(interseccionXMin, interseccionYMin),
                new Coordenada(interseccionXMax, interseccionYMax)
        );
    }
}
package angel;

import java.util.Random;

class Persona{
    public String nombre;
    public Persona interes;

    public Persona(String x, Persona y){
        this.nombre = x;
        this.interes = y;
    }

    public void setInteres(Persona interes) {
        this.interes = interes;
    }


    public String getNombre() {
        return nombre;
    }

    public boolean feelings(Persona persona) {
        return persona != null && persona.equals(interes);
    }

}

public class descanso {

    Persona chico;
    Persona chica;


    public descanso(Persona x, Persona y) {
        chico = x;
        chica = y;

        chico.setInteres(chica);
        chica.setInteres(chico);
    }

    public boolean amor() {
        return amorCorrespondido(0);
    }

    public boolean amorCorrespondido(int intentos) {
        if (intentos >= 10) {
            System.out.println("Se agotaron los intentos (" + intentos + "). El amor no floreció...");
            romperTodo();
            return false;
        }

        if (chico.feelings(chica) && chica.feelings(chico)) {
            if (intentos > 0) {
                System.out.println("¡Después de " + intentos + " intentos, el amor floreció!");
            }
            return true;
        }

        if (chico.feelings(chica) || chica.feelings(chico)) {
            System.out.println("Intento " + (intentos + 1) + ": Alguien siente amor pero no es correspondido...");

            cambiarSentimientosAleatoriamente();

            return amorCorrespondido(intentos + 1);
        }

        romperTodo();
        return false;
    }

    public void cambiarSentimientosAleatoriamente() {
        Random random = new Random();

        boolean cambioChico = random.nextBoolean();
        boolean cambioChica = random.nextBoolean();

        if (cambioChico) {
            if (chico.interes == null) {
                chico.setInteres(chica);
                System.out.println("  -> " + chico.nombre + " empezó a sentir amor por " + chica.nombre);
            } else {
                chico.setInteres(null);
                System.out.println("  -> " + chico.nombre + " dejó de sentir amor por " + chica.nombre);
            }
        }

        if (cambioChica) {
            if (chica.interes == null) {
                chica.setInteres(chico);
                System.out.println("  -> " + chica.nombre + " empezó a sentir amor por " + chico.nombre);
            } else {
                chica.setInteres(null);
                System.out.println("  -> " + chica.nombre + " dejó de sentir amor por " + chico.nombre);
            }
        }

        if (!cambioChico && !cambioChica) {
            System.out.println("  -> Nadie cambió de opinión esta vez...");
        }
    }

    public void romperTodo() {
        System.out.println(chico.nombre + " siente amor por " + chico.interes.nombre);
        System.out.println("Ya fue todo, ya no se siente el amor");
    }

    public void cambiarSentimientos(boolean chicoAmaChica, boolean chicaAmaChico) {
        if (chicoAmaChica) {
            chico.setInteres(chica);
        } else {
            chico.setInteres(null);
        }

        if (chicaAmaChico) {
            chica.setInteres(chico);
        } else {
            chica.setInteres(null);
        }
    }

    public static void main(String[] args) {

        Persona trampa = new Persona("X", null);
        Persona chica = new Persona("Mujer", trampa);
        Persona chico = new Persona("Yhosfer", chica);

        descanso d = new descanso(chico, chica);

        System.out.println("=== ESCENARIO 1: Amor correspondido desde el inicio ===");
        System.out.println("¿Hay amor correspondido? " + d.amor());

        System.out.println("\n=== ESCENARIO 2: Solo uno ama (recursión infinita) ===");
        d.cambiarSentimientos(true, false); // Solo diosfer ama

         System.out.println("¿Hay amor correspondido? " + d.amor());

        System.out.println("\n=== ESCENARIO 3: Nadie ama a nadie (se rompe todo) ===");
        d.cambiarSentimientos(false, false); // Nadie ama
        System.out.println("¿Hay amor correspondido? " + d.amor());
    }

}



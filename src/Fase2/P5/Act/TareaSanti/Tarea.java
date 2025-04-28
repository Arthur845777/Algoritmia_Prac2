package Fase2.P5.Act.TareaSanti;

public class Tarea implements Comparable<Tarea>{
    private String titulo;
    private int prioridad;
    private String descripcion;

    public Tarea(String titulo, int prioridad, String descripcion) {
        this.titulo = titulo;
        this.prioridad = prioridad;
        this.descripcion = descripcion;
    }

    public String getTitulo() {
        return titulo;
    }
    public int getPrioridad() {
        return prioridad;
    }
    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {
        return "Tarea{" +
                "titulo='" + titulo + '\'' +
                ", prioridad=" + prioridad +
                '}';
    }

//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) return true;
//        if (obj == null || getClass() != obj.getClass()) return false;
//
//        Tarea tarea = (Tarea) obj;
//        return prioridad == tarea.prioridad &&
//                (titulo != null ? titulo.equals(tarea.titulo) : tarea.titulo == null);
//    }

    @Override
    public int compareTo(Tarea o) {
        return Integer.compare(this.prioridad, o.prioridad);
    }
}


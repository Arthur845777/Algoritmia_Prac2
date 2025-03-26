package Fase1.P2.Ejercicio.NuevaPractica.ejer2;

    class Caja<T> {
        private T elemento;
        private String color;
    
        public Caja(T elemento, String color) {
            this.elemento = elemento;
            this.color = color;
        }
    
        public Caja(String color) { 
            this.color = color;
        }
    
        public T getElemento() {
            return elemento;
        }
    
        public void setElemento(T elemento) {
            this.elemento = elemento;
        }
    
        public String getColor() {
            return color;
        }
    
        public static <T> void add(T elemento, Caja<T> caja) {
            caja.setElemento(elemento);
        }
    
        @Override
        public String toString() {
            return "Caja de color " + color + " con elemento: " + (elemento != null ? elemento.toString() : "vacía");
        }
    }
    
package Fase2.P5.Ejer.lista;

class Ejercicios<T extends Comparable<T>> {

    public static <T extends Comparable<T>> boolean search(LinkedList<T> List, T elemento){
        Nodo<T> nodocurrent = List.head;
        while(nodocurrent!= null){
            if(nodocurrent.dato.compareTo(elemento)==0){
                return true;
            }
            nodocurrent=nodocurrent.next;
        }
        return false;
    }

    public static <T extends Comparable<T>> void invertir(LinkedList<T> List){
        Nodo<T> inicial = List.head;
        Nodo<T> fin = List.tail;
        int pivot = List.length()/2;
        System.out.println("La lista inical es " + toString(List));
        for(int i=0; i<pivot; i++){
            T temp=inicial.dato;
            inicial.dato = fin.dato;
            fin.dato = temp;
            inicial=inicial.next;
            fin=fin.before;
        }
        System.out.println("La lista invertida es " + toString(List));
    }

    public static <T extends Comparable<T>> void insertar(LinkedList<T> List, T elemento){
        Nodo<T> node = new Nodo<T>(elemento);
        if (List.isEmphy()) {
            List.head = node;
            List.tail = node;
            return;
        }
        List.tail.next = node;
        node.before =  List.tail;
        List.tail = node;
        System.out.println("La lista sera " + toString(List));
    }

    public static <T extends Comparable<T>> int contar(LinkedList<T> List){
        Nodo<T> nodocurrent = List.head;
        int contador = 0;
        while (nodocurrent != null) {
            contador++;
            nodocurrent = nodocurrent.next;
        }
        return contador;
    }

    public static <T extends Comparable<T>> boolean comparar(LinkedList<T> List1, LinkedList<T> List2){
        //compara si los elementos son iguales y estan en el mismo orden
        if(List1.length() != List2.length()){
            return false;
        }
        Nodo<T> nodocurrent1 =List1.head;
        Nodo<T> nodocurrent2 =List2.head;
        boolean continuar=true;
        while(nodocurrent1 != null && nodocurrent2 != null){
            if(nodocurrent1.dato.compareTo(nodocurrent2.dato)==0){
                continuar = true;
            }else{
                continuar=false;
                break;
            }
            nodocurrent1=nodocurrent1.next;
            nodocurrent2=nodocurrent2.next;
        }
        return continuar;
    }

    public static <T extends Comparable<T>> void unir(LinkedList<T> List1, LinkedList<T> List2){
        List1.tail.next=List2.head;
        List2.head.before=List1.tail;
        System.out.println("La nueva lista seria " + toString(List1));
    }

    public static <T extends Comparable<T>> String toString(LinkedList<T> List){
        String lista="";
        Nodo<T> nodocurrent =List.head;
        while (nodocurrent!=null) {
            lista+=nodocurrent.dato +" ";
            nodocurrent = nodocurrent.next;  
        }
        return lista;
    }

}

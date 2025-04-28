package Fase2.P5;

public class ListCircle <E>{
    private Nodo<E> first;

    public ListCircle(){
        first = null;
    }

    public void inser(E x){
        if (first == null) {
            first = new Nodo<E>(x);
            first.setNext(first);
        } else {
            first.setNext(new Nodo<E>(x, first.getNext()));
        }
    }

    public String toString(){
        String str = "";
        if(first == null){
            return null;
        } else {
            Nodo<E> aux = first;
            do {
                str += aux.getDato() + " ";
                aux = aux.getNext();
            } while (aux != first);
        }

        return str;
    }

//    public static void main(String [] args){
//        ListCircle <String> circle = new ListCircle<String>();
//        circle.inser("A");
//        circle.inser("B");
//        circle.inser("C");
//        circle.inser("D");
//
//        System.out.println(circle);
//    }

}

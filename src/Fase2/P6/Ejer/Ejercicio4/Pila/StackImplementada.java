package Fase2.P6.Ejer.Ejercicio4.Pila;
import Fase2.P6.Ejer.Ejercicio4.Excepciones.ExceptionIsEmpty;

public class StackImplementada<E extends Comparable<E>> implements Stack<E> {
    private Object[] array;  // Usar Object[] en lugar de E[]
    private int tope;

    public StackImplementada(int n){
        this.array = new Object[n];  // Sin cast
        this.tope = -1;
    }

    public void push(E x){
        if(tope <array.length-1){
            tope++;
            array[tope]=x; 
        }else{
            full();
        }
    }

    public E pop() throws ExceptionIsEmpty{
        if(isEmpty()){
            throw new ExceptionIsEmpty();
        }
        E temp= (E) array[tope];
        array[tope]=null;
        tope--;
        return temp;
    }

    public E top() throws ExceptionIsEmpty{
        if(isEmpty()){
            throw new ExceptionIsEmpty();
        }
        return (E) array[tope];
    }

    public boolean isEmpty(){
        return tope == -1;
    }

    public void full(){
        if(tope == array.length-1){  
            System.out.println("Pila llena ya no se pueden recibir mas valores ");
        }
    }
}

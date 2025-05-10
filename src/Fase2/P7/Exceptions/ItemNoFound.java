package Fase2.P7.Exceptions;

public class ItemNoFound extends Exception{
    public ItemNoFound(String msg){
        super(msg);
    }

    public ItemNoFound(){
        super("El item no existe");
    }
}

package Fase2.P6.Ejer.Ejercicio4.Pila;
import Fase2.P6.Ejer.Ejercicio4.Excepciones.ExceptionIsEmpty;

public class Corchetes {
    
    public static boolean symbolBalancing(String S) {
        // Crear una pila con capacidad igual a la longitud de la cadena
        StackImplementada<String> stack = new StackImplementada<>(S.length());
        
        // Recorrer cada caracter de la cadena
        for (int i = 0; i < S.length(); i++) {
            String current = String.valueOf(S.charAt(i));
            
            // Si es un corchete de apertura, lo añadimos a la pila
            if (current.equals("(") || current.equals("[") || current.equals("{")) {
                stack.push(current);
            } 
            // Si es un corchete de cierre
            else if (current.equals(")") || current.equals("]") || current.equals("}")) {
                try {
                    // Verificar si la pila está vacía (no hay corchete de apertura correspondiente)
                    if (stack.isEmpty()) {
                        return false;
                    }
                    
                    // Obtener el último corchete de apertura de la pila
                    String top = stack.pop();
                    
                    // Verificar si coinciden los corchetes de apertura y cierre
                    if ((current.equals(")") && !top.equals("(")) || 
                        (current.equals("]") && !top.equals("[")) || 
                        (current.equals("}") && !top.equals("{"))) {
                        return false;
                    }
                } catch (ExceptionIsEmpty e) {
                    // Si la pila está vacía, entonces hay un corchete de cierre sin su apertura
                    return false;
                }
            }
        }
        
        // Al final, la pila debe estar vacía para que todos los corchetes estén balanceados
        return stack.isEmpty();
    }
    
    // Ejemplo de uso
    public static void main(String[] args) {
        String[] examples = {"()()()[()]()","((()))[]]","([])[](","([{)]}", "[","[][][]{{{}}}"};
        
        for (String i : examples) {
            boolean Correcto = symbolBalancing(i);
            System.out.println("La cadena \"" + i + "\" está " + 
                              (Correcto ? "correctamente anidada" : "incorrectamente anidada"));
        }
    }
}
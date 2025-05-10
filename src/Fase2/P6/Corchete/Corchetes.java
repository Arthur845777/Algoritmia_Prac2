package Fase2.P6.Corchete;
import Fase2.P6.ExceptionIsEmpty.ExceptionIsEmpty;
import Fase2.P6.Stack.LinkedStack;

public class Corchetes {

    public static boolean symbolBalancing(String S) {
        // Crear una pila utilizando nuestra implementación de StackLink
        LinkedStack<Character> stack = new LinkedStack<>();

        // Recorrer cada caracter de la cadena
        for (int i = 0; i < S.length(); i++) {
            char current = S.charAt(i);

            // Si es un corchete de apertura, lo añadimos a la pila
            if (current == '(' || current == '[' || current == '{') {
                stack.push(current);
            }
            // Si es un corchete de cierre
            else if (current == ')' || current == ']' || current == '}') {
                try {
                    char top = stack.pop();

                    // Verificar si coinciden los corchetes de apertura y cierre
                    if ((current == ')' && top != '(') || (current == ']' && top != '[') || (current == '}' && top != '{')) {
                        return false;
                    }
                } catch (ExceptionIsEmpty e) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String[] examples = {"()()()[()]()","((()))[]]","([])[](","([{)]}", "[","[][][]{{{}}}"};

        for (String i : examples) {
            boolean correcto = symbolBalancing(i);
            System.out.println("La cadena \"" + i + "\" está " +
                              (correcto ? "correctamente anidada" : "incorrectamente anidada"));
        }
    }
}
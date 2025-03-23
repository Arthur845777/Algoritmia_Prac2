package Fase1.P2.Ejercicio.CalculadoraGenerica;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        while (opcion != 8) {
            System.out.println("\nMenú de Operaciones Clases Genéricas:");
            System.out.println("1. Suma | 2. Resta | 3. Producto | 4. División | 5. Potencia");
            System.out.println("6. Raíz Cuadrada | 7. Raíz Cúbica | 8. Salir");
            System.out.print("Seleccione una opción: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Error: Ingrese un número válido.");
                scanner.next();
            }
            opcion = scanner.nextInt();

            if (opcion >= 1 && opcion <= 5) {
                Number num1 = null, num2 = null;
                try {
                    System.out.print("Ingrese el primer número: ");
                    num1 = leerNumero(scanner);

                    while (true) {
                        System.out.print("Ingrese otro número del mismo tipo: ");
                        num2 = leerNumero(scanner);

                        if (num1.getClass().equals(num2.getClass())) {
                            break;
                        } else {
                            throw new TipoDatoInvalidoException("El segundo número debe ser del mismo tipo que el primero.");
                        }
                    }

                    switch (opcion) {
                        case 1 -> System.out.println("Resultado: " + suma(num1, num2));
                        case 2 -> System.out.println("Resultado: " + resta(num1, num2));
                        case 3 -> System.out.println("Resultado: " + producto(num1, num2));
                        case 4 -> System.out.println("Resultado: " + division(num1, num2));
                        case 5 -> System.out.println("Resultado: " + potencia(num1, num2));
                    }

                } catch (EntradaInvalidaException | TipoDatoInvalidoException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            } else if (opcion == 6 || opcion == 7) {
                try {
                    System.out.print("Ingrese un número: ");
                    Number num = leerNumero(scanner);
                    System.out.println("Resultado: " + (opcion == 6 ? raizCuadrada(num) : raizCubica(num)));
                } catch (EntradaInvalidaException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            } else if (opcion != 8) {
                System.out.println("Opción inválida. Intente de nuevo.");
            }
        }

        System.out.println("Saliendo del programa...");
        scanner.close();
    }

    private static Number leerNumero(Scanner scanner) throws EntradaInvalidaException {
        if (scanner.hasNextInt()) return scanner.nextInt();
        if (scanner.hasNextDouble()) return scanner.nextDouble();
        scanner.next();
        throw new EntradaInvalidaException();
    }

    private static <T extends Number> double suma(T a, T b) {
        return a.doubleValue() + b.doubleValue();
    }

    private static <T extends Number> double resta(T a, T b) {
        return a.doubleValue() - b.doubleValue();
    }

    private static <T extends Number> double producto(T a, T b) {
        return a.doubleValue() * b.doubleValue();
    }

    private static <T extends Number> double division(T a, T b) {
        if (b.doubleValue() == 0) throw new ArithmeticException("División por cero.");
        return a.doubleValue() / b.doubleValue();
    }

    private static <T extends Number> double potencia(T a, T b) {
        return Math.pow(a.doubleValue(), b.doubleValue());
    }

    private static <T extends Number> double raizCuadrada(T num) {
        return Math.sqrt(num.doubleValue());
    }

    private static <T extends Number> double raizCubica(T num) {
        return Math.cbrt(num.doubleValue());
    }
}

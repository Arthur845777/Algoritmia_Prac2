package Fase2.P8.Test;

import Fase2.P7.Exceptions.ExceptionIsEmpty;
import Fase2.P8.AVL.AVLTree;
import Fase2.P7.Exceptions.ItemDuplicated;
import Fase2.P7.Exceptions.ItemNotFound;
import Fase2.P8.GUI.AVLTreeVisualizer;

import java.util.Scanner;

public class MainAVL {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AVLTree<Integer> avlTree = new AVLTree<>();

        // Menú interactivo
        showMenu(scanner, avlTree);
    }

    private static void showMenu(Scanner scanner, AVLTree<Integer> avlTree) {
        int option;
        boolean exit = false;

        while (!exit) {
            System.out.println("\n╔══════════════════════════════════════════╗");
            System.out.println("║            MENÚ ÁRBOL AVL                ║");
            System.out.println("╠══════════════════════════════════════════╣");
            System.out.println("║ 1. Insertar elemento                     ║");
            System.out.println("║ 2. Eliminar elemento                     ║");
            System.out.println("║ 3. Buscar elemento                       ║");
            System.out.println("║ 4. Mostrar árbol (Representación)        ║");
            System.out.println("║ 5. Mostrar árbol por niveles             ║");
            System.out.println("║ 6. Mostrar altura del árbol              ║");
            System.out.println("║ 7. Mostrar altura de un nodo             ║");
            System.out.println("║ 8. Mostrar factor de balance de la raíz  ║");
            System.out.println("║ 9. Visualizar árbol gráficamente         ║");
            System.out.println("║ 10. Salir                                ║");
            System.out.println("╚══════════════════════════════════════════╝");
            System.out.print("Ingrese su opción: ");

            try {
                option = Integer.parseInt(scanner.nextLine());

                switch (option) {
                    case 1:
                        insertElement(scanner, avlTree);
                        break;
                    case 2:
                        deleteElement(scanner, avlTree);
                        break;
                    case 3:
                        searchElement(scanner, avlTree);
                        break;
                    case 4:
                        showTree(avlTree);
                        break;
                    case 5:
                        showTreeByLevels(avlTree);
                        break;
                    case 6:
                        showTreeHeight(avlTree);
                        break;
                    case 7:
                        showNodeHeight(scanner, avlTree);
                        break;
                    case 8:
                        showBalanceFactor(avlTree);
                        break;
                    case 9:
                        visualizeTree(avlTree);
                        break;
                    case 10:
                        exit = true;
                        System.out.println("¡Hasta luego!");
                        break;
                    default:
                        System.out.println("Opción no válida. Intente nuevamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número válido.");
            }
        }
    }

    /**
     * Método para visualizar gráficamente el árbol
     */
    private static void visualizeTree(AVLTree<Integer> avlTree) {
        try {
            if (avlTree.isEmpty()) {
                throw new ExceptionIsEmpty("El árbol está vacío.");
            }
            AVLTreeVisualizer.showTree(avlTree);
        } catch (ExceptionIsEmpty e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void insertElement(Scanner scanner, AVLTree<Integer> avlTree) {
        System.out.print("Ingrese el elemento a insertar: ");
        try {
            int element = Integer.parseInt(scanner.nextLine());
            avlTree.insert(element);
            System.out.println("Elemento " + element + " insertado correctamente.");
        } catch (NumberFormatException e) {
            System.out.println("Por favor, ingrese un número válido.");
        } catch (ItemDuplicated e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void deleteElement(Scanner scanner, AVLTree<Integer> avlTree) {
        System.out.print("Ingrese el elemento a eliminar: ");
        try {
            int element = Integer.parseInt(scanner.nextLine());
            avlTree.delete(element);
            System.out.println("Elemento " + element + " eliminado correctamente.");
        } catch (NumberFormatException e) {
            System.out.println("Por favor, ingrese un número válido.");
        } catch (ExceptionIsEmpty e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void searchElement(Scanner scanner, AVLTree<Integer> avlTree) {
        System.out.print("Ingrese el elemento a buscar: ");
        try {
            int element = Integer.parseInt(scanner.nextLine());
            Integer result = avlTree.search(element);
            System.out.println("Elemento " + result + " encontrado en el árbol.");
        } catch (NumberFormatException e) {
            System.out.println("Por favor, ingrese un número válido.");
        } catch (ItemNotFound e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void showTree(AVLTree<Integer> avlTree) {
        try {
            if (avlTree.isEmpty()) {
                throw new ExceptionIsEmpty("El árbol está vacío.");
            }
            System.out.println("Representación parentizada del árbol:");
            System.out.println(avlTree.parenthesize());

            System.out.println("\nRecorrido pre-orden:");
            avlTree.RecorridoPreOrder();
            System.out.println();
        } catch (ExceptionIsEmpty e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void showTreeByLevels(AVLTree<Integer> avlTree) {
        try {
            System.out.println("Árbol por niveles:");
            avlTree.printByLevels();
        } catch (ExceptionIsEmpty e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void showTreeHeight(AVLTree<Integer> avlTree) {
        try {
            if (avlTree.isEmpty()) {
                throw new ExceptionIsEmpty("El árbol está vacío.");
            }
            System.out.println("Altura del árbol: " + avlTree.height(avlTree.getNode()));
        } catch (ExceptionIsEmpty e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void showNodeHeight(Scanner scanner, AVLTree<Integer> avlTree) {
        System.out.print("Ingrese el nodo para obtener su altura: ");
        try {
            int element = Integer.parseInt(scanner.nextLine());
            int height = avlTree.heightof(element);
            if (height >= 0) {
                System.out.println("Altura del nodo " + element + ": " + height);
            } else {
                System.out.println("El nodo no existe en el árbol.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Por favor, ingrese un número válido.");
        } catch (ExceptionIsEmpty e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void showBalanceFactor(AVLTree<Integer> avlTree) {
        try {
            if (avlTree.isEmpty()) {
                throw new ExceptionIsEmpty("El árbol está vacío.");
            }
            System.out.println("Factor de balance de la raíz: " + avlTree.balanceFactor(avlTree.getNode()));
        } catch (ExceptionIsEmpty e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
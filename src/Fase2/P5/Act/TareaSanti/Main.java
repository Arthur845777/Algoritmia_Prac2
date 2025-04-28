package Fase2.P5.Act.TareaSanti;

public class Main {
    public static void main(String[] args) {
        // Paso 1: Crear una instancia de GestorDeTareas (TaskManager)
        System.out.println("1. Creando gestor de tareas...");
        TaskManager<Tarea> gestorTareas = new TaskManager<>();

        // Paso 2: Agregar tareas
        System.out.println("\n2. Agregando tareas...");
        Tarea tarea1 = new Tarea("Completar informe", 3, "sa");
        Tarea tarea2 = new Tarea("Preparar presentación", 1, "sa");
        Tarea tarea3 = new Tarea("Enviar correo", 2, "sa");
        Tarea tarea4 = new Tarea("Reunión con cliente", 5, "sa");

        gestorTareas.AddTask(tarea1);
        gestorTareas.AddTask(tarea2);
        gestorTareas.AddTask(tarea3);
        gestorTareas.AddTask(tarea4);

        // Imprimir tareas actuales
        System.out.println("Tareas agregadas:");
        gestorTareas.PrintTaskInProgress();
        System.out.println("Total de tareas: " + gestorTareas.CountTaskInProgress());

        // Paso 3: Eliminar alguna tarea
        System.out.println("\n3. Eliminando una tarea...");
        if (gestorTareas.RemoveTask(tarea2)) {
            System.out.println("Tarea 'Preparar presentación' eliminada con éxito");
        } else {
            System.out.println("No se pudo eliminar la tarea");
        }

        // Paso 4: Imprimir todas las tareas actuales
        System.out.println("\n4. Tareas actuales después de eliminar:");
        gestorTareas.PrintTaskInProgress();
        System.out.println("Total de tareas: " + gestorTareas.CountTaskInProgress());

        // Paso 5: Verificar si cierta tarea existe
        System.out.println("\n5. Verificando existencia de tareas...");
        System.out.println("¿Existe 'Completar informe'? " + gestorTareas.ContainTask(tarea1));
        System.out.println("¿Existe 'Preparar presentación'? " + gestorTareas.ContainTask(tarea2));

        // Obtener tarea más prioritaria
        System.out.println("\nTarea más prioritaria: " + gestorTareas.MaxElement());

        // Paso 6: Invertir la lista
        System.out.println("\n6. Invirtiendo la lista de tareas...");
        gestorTareas.ReverseLinkedList();
        System.out.println("Tareas después de invertir:");
        gestorTareas.PrintTaskInProgress();

        // Paso 7: Transferir una tarea a "tareas completadas"
        System.out.println("\n7. Transfiriendo una tarea a 'completadas'...");
        gestorTareas.completeTask(tarea1);


        // Paso 8: Mostrar ambas listas
        System.out.println("\n8. Estado final de las listas:");
        System.out.println("Tareas en progreso:");
        gestorTareas.PrintTaskInProgress();

        // Indicamos qué tarea se completó:
        System.out.println("\nTareas completadas:");
        gestorTareas.PrintCompletedTasks();

//        TaskManager<Tarea> gestorTareas2 = new TaskManager<>();
//        gestorTareas2.AddTask(tarea2);
////         9 Probanod las nuevas funcionalidades
//        gestorTareas.concatenarLinkedList(gestorTareas.getNodo(), gestorTareas2.getNodo());
    }
}

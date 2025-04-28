package Fase2.P5.Act.TareaSanti;

import java.util.ArrayList;

public class TaskManager<T extends Comparable<T>> {
    private LinkedList<T> taskComplete;
    private LinkedList<T> taskInProgress;

    public TaskManager() {
        taskComplete = new LinkedList();
        taskInProgress = new LinkedList();
    }

    public void AddTask(T task) {
        taskInProgress.insertLast(task);
    }

    public boolean RemoveTask(T task) {
        return taskInProgress.removeNode(task);
    }

    public boolean ContainTask(T task) {
        if (taskInProgress.search(task) > -1) {
            return true;
        }
        return false;
    }

    public void PrintTaskInProgress(){
        printNode(getNodo());
    }

    public int CountTaskInProgress(){
        return taskInProgress.countNode();
    }

    public T MaxElement(){
        return maxPriorityTask(getNodo());
    }

    public void ReverseLinkedList(){
        taskInProgress.reverseLinkedList();
    }

    public void completeTask(T task) {
        System.out.println("Tarea 'Completar informe' marcada como completada");
        if (RemoveTask(task)) {
            taskComplete.sortLinkedList(task);
        } else {
            System.out.println("Tarea 'Completar informe' marcada como no existente");
        }
    }

    public void PrintCompletedTasks(){
        printNode(getNodo());
    }

    public Nodo<T> getNodo() {
        return taskInProgress.getNodoCurrent();
    }

    public T maxPriorityTask(Nodo<T> head){
        if (head == null) {
            return null;
        }
        Nodo<T> nodoCurrent = head;
        T maxElement = head.dato;

        while (nodoCurrent != null) {
            if(nodoCurrent.dato.compareTo(maxElement) > 0) {
                maxElement = nodoCurrent.dato;
            }
            nodoCurrent = nodoCurrent.next;
        }
        return maxElement;
    }

    public void printNode(Nodo<T> head){
        if (head == null) {
            System.out.println("Lista Vacia");
            return;
        } else {
            Nodo<T> nodoCurrent = head;
            do {
                System.out.print(nodoCurrent.dato + "\n");
                nodoCurrent = nodoCurrent.next;
            }while (nodoCurrent != null);
        }
    }

}



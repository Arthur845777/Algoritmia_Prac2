package Fase2.P5.Act.TareaSanti;

import java.util.ArrayList;

public class TaskManager<T extends Comparable<T>> {
    private ArrayList<T> taskComplete;
    private LinkedList<T> taskInProgress;

    public TaskManager() {
        taskComplete = new ArrayList();
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
        taskInProgress.printNode();
    }

    public int CountTaskInProgress(){
        return taskInProgress.countNode();
    }

    public T MaxElement(){
        return taskInProgress.maxPriorityTask();
    }

    public void ReverseList(){
        taskInProgress.reverseLinkedList();
    }

    public void completeTask(T task) {
        System.out.println("Tarea 'Completar informe' marcada como completada");
        if (RemoveTask(task)) {
            taskComplete.add(task);
        }
    }

    public void PrintCompletedTasks() {
        for (T task : taskComplete) {
            System.out.println(task);
        }
    }
}



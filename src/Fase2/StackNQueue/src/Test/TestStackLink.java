package Test;

import Stack.StackLink;
import ExceptionIsEmpty.ExceptionIsEmpty;

public class TestStackLink {
    public static void main(String[] args) {
        StackLink<String> stack = new StackLink<>();

        stack.push("One");
        stack.push("Two");
        stack.push("Three");

        try {
            System.out.println("Top: " + stack.top()); // Three
            System.out.println("Pop: " + stack.pop()); // Three
            System.out.println("Pop: " + stack.pop()); // Two
            System.out.println("Top: " + stack.top()); // One
            System.out.println("Pop: " + stack.pop()); // One

            // stack.pop(); // Descomenta para lanzar excepción

        } catch (ExceptionIsEmpty e) {
            System.out.println(e.getMessage());
        }
    }
}

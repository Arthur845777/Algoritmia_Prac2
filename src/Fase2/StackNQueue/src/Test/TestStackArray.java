package Test;

import Stack.ExceptionIsEmpty;
import Stack.StackArray;

public class TestStackArray {
	public static void main(String[] args) throws ExceptionIsEmpty {
		StackArray<Integer> stckArr = new StackArray<>(3);
		//stckArr.pop();
		stckArr.push(10);
		stckArr.push(20);
		stckArr.push(30);
		System.out.println(stckArr.top());
		System.out.println(stckArr.pop());
		System.out.println(stckArr.top());
		stckArr.push(30);
		stckArr.push(40);		
	}
}

import java.util.ArrayList;
import java.util.Scanner;




public class GenericStack extends ArrayList {
	
	private int stackSize;
	private Object topElement;
	private static Scanner scan;
	
	public GenericStack() {
		setSize();
		setTop();
	}
	
	public int getSize() {
		return stackSize;
	}
	
	public Object peek() {
		return topElement;
	}
	
	public void push(Object item) {
		super.add(item);
		setTop();
		setSize();
	}
	
	public Object pop() {
		if(stackSize != 0) {
			super.remove(stackSize -1);
		}
		setTop();
		setSize();
		return topElement;
	}
	
	public boolean isEmpty() {
		return super.isEmpty();
	}
	
	public void setSize() {
		stackSize = super.size();
	}
	
	public void setTop() {
		if(stackSize ==0) {
			topElement = null;
		} else {
			topElement = super.get(stackSize-1);
		}
	}
	
	public void reverseOrder() {
		for(int i = getSize() - 1; i>= 0; i--) {
				System.out.println(super.get(i));
		}
	}
	public static void main(String args[]){
		GenericStack gs = new GenericStack();
		scan = new Scanner(System.in);
		System.out.println("Running GenericStack.java.....");
		System.out.println("Enter 5 Strings:");
		String var1 = scan.nextLine();
		gs.push(var1);
		String var2 = scan.nextLine();
		gs.push(var2);
		String var3 = scan.nextLine();
		gs.push(var3);
		String var4 = scan.nextLine();
		gs.push(var4);
		String var5 = scan.nextLine();
		gs.push(var5);
		gs.reverseOrder();
	}
	
}
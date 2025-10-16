package LamdaExpExample;

import java.util.ArrayList;

public class PracticeFuntionalInterface {
	public static void main(String[] args) {
		
		
		//1. Filter even numbers 
		//👉 Input: List<Integer> → Output: only even numbers.
		ArrayList<Integer> list = new ArrayList<>();
		list.add(11);
		list.add(22);
		list.add(17);
		list.add(34);
		list.add(88);
		list.add(25);
		System.out.println("All the value of list = " + list);
		
		list.stream().filter(a -> a % 2 == 0).forEach(a -> System.out.print(a + " "));
		
		
	}
}

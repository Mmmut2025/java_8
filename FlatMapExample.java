package com.image.project2Bank;

import java.util.Arrays;
import java.util.List;

record Student2(String name ,  List<String> subject) {
}

public class FlatMapExample {
	public static void main(String[] args) {
		 List<Student2> students = Arrays.asList(
		            new Student2("Harish", Arrays.asList("Math", "Science")),
		            new Student2("Riya", Arrays.asList("English", "History")),
		            new Student2("Amit", Arrays.asList("Math", "Hindi"))
		        );
		 
	List<String> list = students.stream().flatMap(stu -> stu.subject().stream()).distinct().toList();
	System.out.println(list);
	
		//convert string to stream() and find occurrence of spl char in string
		String str = "madam maya kumari";
		long count = str.chars().filter(c -> c == 'a').count();
		System.out.println(count);
		
		
		//convert stream to array (Object array)
		Object[] array = Arrays.asList(1,2,3,4,5).stream().toArray();
		
		//convert stream to array (Integer(obj) array)
		Integer[] array2 = Arrays.asList(1,2,3,4,5).stream().toArray(Integer[]::new);
		
		
		//convert stream to array (int(primitive) array)
		int[] array3 = Arrays.asList(1,2,3,4,5).stream().mapToInt(s -> s.intValue()).toArray();
		
		Arrays.asList(1,2,3,4,5,6,7,8).parallelStream().forEach(k -> System.out.print(k + " "));
		
		System.out.println("\n\nUsing forEachOdered()");  //maintain order
		Arrays.asList(1,2,3,4,5,6,7,8).parallelStream().forEachOrdered(k -> System.out.print(k + " "));
		
		
		 
	}

}

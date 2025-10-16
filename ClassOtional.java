package com.image.optionalclass;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ClassOtional {

	public static void main(String[] args) {
		//findUpper(1);  //without optional class
		
		findUpperWithOptional(1);  //Using optional
		
		var a = 10;
		System.out.println(a);
		System.out.println(((Object)a).getClass().getName());
		
		List<String> names = Arrays.asList("Harish", "Ravi");

        names.forEach((@Deprecated var name) -> {
            System.out.println(name);
        });
        
        String multiLine = "Java\nPython\nC++";

        multiLine.lines()
                 .forEach(System.out::println);

	}
	
//	public static void findUpper(int id) {
////		String name = getName(1);
////		System.out.println(name.toUpperCase());  
//		//Here NullPointerException throw becuase name are null so we can handle by using two waya
//		
//		//first ways use of if block (check name is null or not)
////		if(name != null) {  
////			System.out.println(name.toUpperCase());
////		}
//		//But this is not good , because someone can forgot to handle this case who have no idea of nullpointer exception
//	}
	
	static void findUpperWithOptional(int id) {
		//second ways :- using Optional class
		
		Optional<String> name = getName(id);  //here string is optional
		if(name.isPresent()) {
			System.out.println(name.get().toUpperCase());
		}
		
		//name.ifPresent(s -> System.out.println(s.toUpperCase()));
		

		
		
	}
	
	public static Optional<String> getName(int id) {
		String name = "Ram" ;  //let's Ram is taken from the database after execute Query
		//name = null;  //Let's DB have not any record with given id then DB query give null 
		//return name;
		return Optional.ofNullable(name);  //Handle the null pointer exception
	}

}

package com.comparator.comparable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class Student2 {
	private String name;
	private int age;
	private double salary;
	
	public Student2(String name , int age , double salary) {
		this.name= name;
		this.age = age;
		this.salary = salary;
	}
	
	public String getName() {
		return name;
	}
	
	public int getAge() {
		return age;
	}
	
	public double getSalary() {
		return salary;
	}
	
	public String toString() {
		return "Student[ Name = " + name + " , Age = " + age + " , Salary = " + salary+ "]";
	}
}

class Test implements Comparator<Student2>{
	public int compare(Student2 s1, Student2 s2) {
		return (int) (s1.getSalary() - s2.getSalary());
	}
}

class Test2 implements Comparator<Student2>{
	public int compare(Student2 s1 , Student2 s2) {
		return s1.getName().length()  - s2.getName().length();
	}
}

class Test3 implements Comparator<Student2>{
	public int compare(Student2 s1 , Student2 s2) {
		return s1.getName().compareTo(s2.getName());
	}
}


public class BasicOfComparator {

	public static void main(String[] args) {

		ArrayList<Student2> students = new ArrayList<>();
		students.add(new Student2("Harish",22,28000));
		students.add(new Student2("Vik" , 26 , 22000));
		students.add(new Student2("Nitishhhh",27,19000));
		students.add(new Student2("Deepa" , 20 , 12000));
		students.add(new Student2("Pk",18,14000));
		students.add(new Student2("Sanjay" , 32 , 40000));
		System.out.println("before sorting:");
		students.forEach(System.out::println);
		
		
		
		//explicitly
		//Collections.sort(students , new Test());
		
		//by lambda
		//Collections.sort(students , (s1,s2) -> (int)(s1.getSalary() - s2.getSalary())); //compare return the integer value so need to cast into integer of res
		//Collections.sort(students , (s1,s2) -> Double.compare(s1.getSalary() , s2.getSalary()));
		//students.sort((s1,s2) -> Double.compare(s1.getSalary() , s2.getSalary()));
		
		//by method ref
		Collections.sort(students , Comparator.comparing(Student2::getSalary));
		//students.sort(Comparator.comparing(Student2::getSalary));

		
		System.out.println("\n\nAfter sorting based on salary " );
		students.forEach(System.out::println);
		

		
		
		
		//explicitly
		//Collections.sort(students , new Test2());
		
		//by lambda
		//Collections.sort(students,(s1,s2) -> (s1.getName().length() - s2.getName().length())); 
		//Collections.sort(students,(s1,s2) -> (Integer.compare(s1.getName().length(), s2.getName().length())));
		
		//by method ref
		//Collections.sort(students, Comparator.comparing(s -> s.getName().length()));
		students.sort(Comparator.comparing(StudentUtils::getNameLength));
		
		System.out.println("\n\nAfter sorting based on length of name " );
		students.forEach(System.out::println);
		
		
		
		
		//explicitly
		//Collections.sort(students , new Test3());
		
		//by lambda
		//Collections.sort(students , (s1,s2) -> (s1.getName().compareTo(s2.getName())));
		
		//by method ref
		Collections.sort(students, Comparator.comparing(Student2::getName));
		
		System.out.println("\n\nAfter sorting based on alphabatically " );
		students.forEach(System.out::println);
	}
	
	public class StudentUtils {
	    public static Integer getNameLength(Student2 s) {
	        return s.getName().length();
	    }
	}

}

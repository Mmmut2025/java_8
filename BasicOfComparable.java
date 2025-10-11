package com.comparator.comparable;

import java.util.ArrayList;
import java.util.Collections;

class Student implements Comparable<Student> {
	private String name;
	private int age;
	private double salary;
	
	public Student(String name , int age , double salary) {
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
	
	public int compareTo(Student other) {
		//for ascending order
		return this.age - other.age;
//		if(this.age > other.age) {
//			return 1;
//		}
//		else if(this.age < other.age) {
//			return -1;
//		}
//		else {
//			return 0;
//		}
		
		
		//descending order
		//return other.age - this.age;
//		if(this.age > other.age) {
//			return -1;
//		}
//		else if(this.age < other.age) {
//			return 1;
//		}
//		else {
//			return 0;
//		}
	}
	
}

public class BasicOfComparable {
	public static void main(String[] args) {
		
//		ArrayList<Integer> list = new ArrayList<>();
//		list.add(22);
//		list.add(44);
//		list.add(11);
//		list.add(114);
//		
//		System.out.println(list);
//		Collections.sort(list);  //it sort int list because it is in natural order
//		System.out.println(list);
		
		
		
		ArrayList<Student> students = new ArrayList<>();
		students.add(new Student("Harish",22,28000));
		students.add(new Student("Vikas" , 26 , 22000));
		students.add(new Student("Nitish",27,19000));
		students.add(new Student("Deepak" , 20 , 12000));
		students.add(new Student("Prince",18,14000));
		students.add(new Student("Sanjay" , 32 , 40000));
		
		
		System.out.println("before :");
		students.forEach(System.out::println);
		//Collections.sort(students);
		Collections.sort(null);
		System.out.println("After " + students);

	}

}

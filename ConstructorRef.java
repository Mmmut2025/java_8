package com.method.ref;

@FunctionalInterface
interface Provider{
	public abstract Student getStudent();
}

class Student{
	public void display() {
		System.out.println("this is student");
	}
}

public class ConstructorRef {
	public static void main(String[] args) {
		//by lambda expression
		Provider d1 = () -> new Student();
		Student s1 = d1.getStudent();
		s1.display();
		
		//by constructor reference
		Provider d2 = Student::new;
		Student s2 = d2.getStudent();
		s2.display();
	}
}

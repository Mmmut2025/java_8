package com.method.ref;

@FunctionalInterface
interface intertask{
	public abstract void doTask();
}

class Stuff{
	static void doStuff() {
		System.out.println("print using do stuff2");
		System.out.println("print using do stuff2");
		System.out.println("print using do stuff2");
		System.out.println("print using do stuff2");
		System.out.println("print using do stuff2");
		System.out.println("print using do stuff2");
		System.out.println("print using do stuff2");
		System.out.println("print using do stuff2");
	}
	
	public int runTask() {
		int count = 0;
		for(int i = 1 ; i <= 5 ; i++) {
			try {
				count++;
				System.out.println("value of iteration : " + i);
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return count;
	}
	
	
}

public class RefDemoWithCustomFuntionalInterface {

	public static void main(String[] args) {
		//FIRST WAY WITHOUT METHOD REFERENCE
		intertask i = () -> {
			System.out.println("task using lambda exp1");
			System.out.println("task using lambda exp1");
			System.out.println("task using lambda exp1");
			System.out.println("task using lambda exp1");
			System.out.println("task using lambda exp1");
			System.out.println("task using lambda exp1");
			System.out.println("task using lambda exp1");
			System.out.println("task using lambda exp1");
		};
		i.doTask();
		
		System.out.println("\n\n------------------------------------------");
		
		
		//FIRST WAY WITHOUT METHOD REFERENCE
		intertask ii = () -> {
			System.out.println("task using lambda exp1");
			System.out.println("task using lambda exp1");
			System.out.println("task using lambda exp1");
			System.out.println("task using lambda exp1");
			System.out.println("task using lambda exp1");
			System.out.println("task using lambda exp1");
			System.out.println("task using lambda exp1");
			System.out.println("task using lambda exp1");
		};
		ii.doTask();
		
		//if we create same lines of code multiple time for multiple method we need to write the same code agian and again for diff-2 interface ref
		//but if we put the same lines of code inside the one method then we can use any number of time for interface ref 
		//and we need to change only method implementation 
		
		
		System.out.println("\n\n------------------------------------------");
		
		
		//refering static method
		//className::methodName
		intertask i2 = Stuff::doStuff;
		i2.doTask();
		
		System.out.println("\n\n------------------------------------------");
		//refering static method
		//className::methodName
		intertask ii2 = Stuff::doStuff;
		i2.doTask();
		
		
		
		
		
		
		
		System.out.println("\n\n------------------------------------------");
		//refering instance method / non-static method
		//obj::methodName
		Stuff s1 = new Stuff();
		intertask i3 = s1::runTask;
		i3.doTask();
		
		
		//No of arguments in functional_interface_method and referenced_method must be same;
//		for(Student stu : stuList) {
//		Predicate<Student> p = (stu) -> (stu.marks > 60);
//		if(p.test(stu)) {
//			System.out.println(stu);
//		}
//		
//	}
		
	}
}

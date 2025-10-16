package com.method.ref;

@FunctionalInterface
interface MyInterface2{
	void interfaceMethod2(int a);
}

@FunctionalInterface
interface MyInterface1{
	void interfaceMethod1(int a,int b);
}

@FunctionalInterface
interface MyInterface3{
	void interfaceMethod3();
}

class Helper{
	public static void printMethod(int a , int b) {
		System.out.println(a + "  " + b);
	}
	
	public void printMethod() {
		System.out.println("print using method reference1");
	}
}



public class EntryPoint {
	public static void main(String[] args) {
		
		MyInterface2 m2 = System.out::println;
		m2.interfaceMethod2(10);
		
		//MyInterface1 m1 = (a,b) -> Helper.printMethod(a, b);
		MyInterface1 m1 = Helper::printMethod;
		m1.interfaceMethod1(10,20);
		
		Helper h1 = new Helper();
		MyInterface3 m3 = h1::printMethod;
		m3.interfaceMethod3();
		
		
	}
}

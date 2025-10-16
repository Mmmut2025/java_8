package com.method.ref;

@FunctionalInterface
interface Calculator{
	String sqRoot(int x);
}

public class CalculatorByMethodRef {
	public static void main(String[] args) {
		Calculator c1 = CalculatorByMethodRef::printSqroot;
		String res = c1.sqRoot(9);
		System.out.println(res);
	}
	
	public static String printSqroot(int x) {
		return ("the value is " + x*x);
	}
	
	public static String sqRoot(int x) {
		return ("the value is " + x+x);
	}

}

package com.java_8;

//first of all createt he functional interface
@FunctionalInterface
interface Drawable{
	//abstract method
	double area(int l , int b);
}

class Rectangle implements Drawable{
	@Override
	public double area(int l, int b) {
		return l * b;
	}
}

public class LambdaExp {	
	public static void main(String[] args) {
		//wihout lambda
		Drawable d = new Rectangle();
		System.out.println(d.area(3, 22));
		
		//using lambda
		Drawable d1 = (l,b) -> l*b ;
		System.out.println(d1.area(22,2));
		
	}
}

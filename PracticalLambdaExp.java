package LamdaExpExample;

@FunctionalInterface
interface MathCalculator{
	int operation(int a , int b);
}

@FunctionalInterface
interface Shape{
	double area(int a , int b, int c);
}

public class PracticalLambdaExp {
	public static void main(String[] args) {
		//implements functional interface using lambda
		MathCalculator c1 = (first , second) -> first + second;
		System.out.println("sum = " + c1.operation(4, 11));
		
		MathCalculator c2 = (first , second) -> first * second;
		System.out.println("multiply = " + c2.operation(4, 11));
		
		
		//calculate are of different different shape using lambda expression
		Shape rectangle = (len , breath , unused) -> len * breath;
		System.out.println("The are of rectangle : " + rectangle.area(3, 6, 0));
		
		Shape circle = (radius , unused1 , unused2) -> Math.PI * radius * radius;
		System.out.println("The are of Circle : " + circle.area(3, 0, 0));
		
		Shape cuboid = (len , breath , height) -> len * breath * height;
		System.out.println("The are of Cuboid : " + cuboid.area(3, 3, 5));
	}
}

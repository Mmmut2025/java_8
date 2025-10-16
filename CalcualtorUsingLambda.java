package LamdaExpExample;

@FunctionalInterface
interface MathOperator{
	//an interface have only one abstract method is called functional interface
	int operate(int a , int b);
}

//first way to implement the functional interface -> generic
//class HelperToImplemetsInterface implements MathOperator{
//	public int operate(int a , int b) {
//		return a+b;
//	}
//}

//first way to implement the functional interface -> generic
//class HelperToImplemetsInterface2 implements MathOperator{
//	public int operate(int a , int b) {
//		return a-b;
//	}
//}

public class CalcualtorUsingLambda {

	public static void main(String[] args) {
		//by generic class
//		HelperToImplemetsInterface  h1 = new HelperToImplemetsInterface();
//		System.out.println(h1.operate(15, 2));
//		
//		HelperToImplemetsInterface2  h2 = new HelperToImplemetsInterface2();
//		System.out.println(h2.operate(15, 2));
		
		
		//by anonymous inner class
//		MathOperator m1 = new MathOperator() {
//			public int operate(int a , int b) {
//				return a+b;
//			}
//			
//		};
//		System.out.println(m1.operate(12, 10));
//		
//		MathOperator m2 = new MathOperator() {
//			public int operate(int a , int b) {
//				return a-b;
//			}
//		};
//		System.out.println(m2.operate(12, 10));
		
		
		//Lambda exp
		MathOperator m3 = (a ,b) -> a+b;
		System.out.println(m3.operate(12, 90));
		
		MathOperator m4 = (a ,b) -> a-b;
		System.out.println(m4.operate(12, 90));
		
		MathOperator m5 = (a ,b) -> a*b;
		System.out.println(m5.operate(12, 90));
		
	}

}

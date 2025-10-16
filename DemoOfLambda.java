package LamdaExpExample;

interface Calculator{
	void sum();
}

class Abc implements Calculator{
	public void sum() {
		System.out.println("Sum in abc class");
	}
}

public class DemoOfLambda {
	public static void main(String[] args) {
		Abc a = new Abc();
		a.sum();
		
		Calculator cc = new Abc();
		cc.sum();
		
		//cc = new Calculator();
		
		//Anonymous class ->it is a inner class without name
		//that used to implements interface and abstract class
		
		Calculator cc1 = new Calculator() {
			public void sum() {
				System.out.println("addtiion inside anonymous class");
			}
		};
		cc1.sum();
		
		
		//Using Lambda function
		Calculator cc2 = ()->{System.out.println("implements interface using lambda");};
		cc2.sum();
		
		Calculator cc3 = ()-> System.out.println("implements interface using lambda2");
		cc3.sum();
	}
}

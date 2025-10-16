package LamdaExpExample;
import java.util.function.*;

public class BuiltInFunctionalInterface {

	public static void main(String[] args) {
		//supplier -> take nothing , return something
		//consumer -> take something , return nothing
		//predicate -> take something , return true
		//function -> take something , return something
		System.out.println("\nSupplier Interface");
		
		
		//supplier -> take nothing , return one value
		Supplier<Double> ranNum = () -> Math.random();
		System.out.println(ranNum.get());
		
		Supplier<String> myName = () -> "Harish kumar";
		System.out.println(myName.get());
		
		Supplier<Integer> maxNum = () -> Integer.MAX_VALUE;
		System.out.println(maxNum.get());
		System.out.println("\nConsumer Interface");
		
		
		//Consumer -> take one value , return something
		Consumer<String> takeString = (name) -> System.out.println(name);
		takeString.accept("My Name is Harish Kumar");
		
		Consumer<Integer> takeAge = (age) -> System.out.println(age);
		takeAge.accept(23);
		System.out.println("\nPredicate");
		
		
		//Predicate -> take one input and return true
		Predicate<Integer> checkAge = (age) -> (age >= 18);
		System.out.println(checkAge.test(12));
		System.out.println(checkAge.test(33));
		
		Predicate<Integer> checkSalary = (salary) -> (salary < 25000);
		System.out.println("\n"+checkSalary.test(18000));
		System.out.println(checkSalary.test(28000));
		System.out.println("\nFunction interface");
		
		
		//Function interface -> take one , return one
		Function<Integer,Integer> increment = (age) -> (age+6);
		System.out.println(increment.apply(33));
		
		Function<String,Integer> findLength = (name) -> (name.length());
		System.out.println(findLength.apply("Harish"));
		System.out.println("\nBiFunction");
		
		
		//BiFunction -> take two input and return one output
		BiFunction<Integer,Integer ,Integer> addTwo = (first , second) -> (first+second);
		System.out.println(addTwo.apply(22,44));
		
	}
}

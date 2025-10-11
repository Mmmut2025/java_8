package com.image.newcollectionmethod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.UnaryOperator;

record Student(int roll , String name , String dep , double marks){
	
}

public class NewCollectionMethod {

	public static void main(String[] args) {
		List<Student> students = new ArrayList<>();
		students.add(new Student(101,"harish","CSE",87));
		students.add(new Student(102,"shivam","IT",77));
		students.add(new Student(102,"ashu","IT",72));
		students.add(new Student(104,"yug","CSE",81));
		
		List<Integer> list = new ArrayList<>();         //Arrays.asList(12,26,13,16,34,36,23,78,45);
		list.add(4);
		list.add(6);
		list.add(52);
		list.add(1);
		list.add(17);
		list.add(8);
		list.add(2);
		list.add(3);
		list.add(10);
		list.add(15);
		
		Set<Integer> set = new HashSet<>();  //set store only unique value ignore duplicates
		set.add(2);
		set.add(55);
		set.add(14);
		set.add(32);
		set.add(25);
		set.add(31);
		
		
		Map<String,Integer> map = new HashMap<>();;
		// inserting values using put()
		map.put("Harish", 22);
		map.put("Shivam", 20);
		map.put("Prince", 17);
		map.put("Deepak", 19);
		
		
		
		
		//New collection method
		// 1. forEach(consumer<?>) -> To traverse the collection and return nothing
		System.out.println("-------------New collection method in java 8-------------------");
		
		System.out.println("------------Print Using forEach() method for list --------------");
		list.forEach((s) -> System.out.print(s + " "));    //generally it used to print and perform the task inside forEach
		
		System.out.println("\n------------Print Using forEach() method for set --------------");
		set.forEach((s) -> System.out.print(s + " "));
		
		System.out.println("\n------------Print Using forEach() method for map --------------");
		map.forEach((k,v) -> System.out.print(k + " : " + v + "\n"));
		
		
		
		//2. removeIf(Predicate<?>) :- if collection contain the spc ele than remove it.
		//list.removeIf((x) -> x%2 == 0);
		list.removeIf((x) -> x >= 10);
		System.out.println("\n\n\n------------remove even number using removeIf in list--------------");
		list.forEach((s) -> System.out.print(s + " "));    //generally it used to print and perform the task inside forEach
		
		
		set.removeIf((x) -> x % 2 == 0);
		System.out.println("\n------------remove even number using removeIf in set--------------");
		set.forEach((s) -> System.out.print(s + " "));
		
		
		//A map doesn't have removeIf() method but we can apply the removeIf() on entrySet() , keySet() and values()
		//map.keySet().removeIf((key) -> key.equalsIgnoreCase("Deepak"));
		//map.values().removeIf((value) -> value < 18);
		//map.entrySet().removeIf((entry) -> entry.getValue() < 18);
		map.entrySet().removeIf((entry) -> entry.getKey().equalsIgnoreCase("Harish"));
		System.out.println("\n------------remove person who age are smaller than 18--------------");
		map.forEach((k,v) -> System.out.print(k + " : " + v + "\n"));
		
		
		
		//3. spliterator() :- 
		/**
			Spliterator = Split + Iterator
					It was introduced in Java 8 in the java.util package.
					Its main purpose: support parallelism in Streams
					For parallel processing, we want to split a collection into multiple parts and process them simultaneously (e.g., in a parallel stream). Spliterator is designed for that.
		 */
		/**
		 * Example:
				👉 If you have a list of 1 million numbers:
				Iterator → One thread processes all.
				Spliterator → It can split the list into chunks (say 4 chunks of 250k) and each thread can process its own chunk → huge performance boost.
		 */
		
		/**
		 * Key Methods of Spliterator
				boolean tryAdvance(Consumer<? super T> action)
						Like Iterator.next() → processes the next element.
						Returns false when no elements remain.
						
				Spliterator<T> trySplit()
						Splits this spliterator into 2 parts (one for current thread, one for another thread).
						Returns another spliterator covering some portion of the elements.
				long estimateSize()
						Returns the estimated number of remaining elements.
				int characteristics()
						Returns properties (constants) about the collection, like:
							ORDERED (elements have defined order, e.g., List)
							SORTED
							SIZED (size known in advance)
							SUBSIZED
							IMMUTABLE
							NONNULL, etc.
		 */
		
		
		System.out.println("\n\n-------------------------------------New list method in java 8----------------------------------------");
		System.out.println("\n------------------sort(Comparator)------------------------------");
		list.sort(null);  //sort based on integer directly
		System.out.println(list);;
		
		//students.sort(null);  //jvm confuse what bases sort the students list so throw exception because list contain obj
		students.sort(Comparator.comparing(Student::marks));
		System.out.println(students);
		
		
		/**
		 * What is UnaryOperator? - function that takes an element and returns an element of the same type.
				It is a functional interface in java.util.function package.
				It represents an operation on a single operand that returns a result of the same type as its operand
					UnaryOperator<Integer> square = x -> x * x;
        			System.out.println(square.apply(5));  // 25
        			System.out.println(square.apply(10)); // 100
		 */
		System.out.println("\n--------UnaryOperator<T> example-------------");
		UnaryOperator<Integer> o1 = (x) -> x+5;
		System.out.println(o1.apply(12));
		System.out.println(o1.apply(20));
		
		
		
		System.out.println("\n--------------replaceAll()----------------------");
		//replaces each element of the list with the result of applying a given UnaryOperator.
		/**
		 * map() (Stream API) → transforms elements into a new stream/list (doesn’t modify the original list).
		   replaceAll() → updates the elements in-place (modifies the original list).
		 */
		//if you have normal class with setter method
//		students.replaceAll(stu -> {
//		    stu.setMarks(stu.getMarks() + 5);
//		    return stu;
//		});
		System.out.println("\nAfter replace name wih upper case name");
		students.replaceAll((stu) -> new Student(stu.roll(),stu.name().toUpperCase(),stu.dep(),stu.marks()));
		System.out.println(students);  //original list change here
		
		System.out.println("\nAfter replace all value of list with new number ");
		list.replaceAll((x) -> x+5);   //original list change here
		System.out.println(list);  
		
		
		
		
		
		System.out.println("\n\n--------------New map method in java 8---------------------------");
		
		System.out.println("\n--------getOrDefault(key , defaultVaue)-----------------");
		//Returns value if exists, else default.
		/*
        Returns the value to which the specified key is mapped,or defaultValue if this map contains no mapping for the key.
		 *  
		 */
		System.out.println(map.get("Yug"));  //give null if specified key is not exist in map 
		//if we want , if specified value is not exist in map then return a default value then we can use this method.
		System.out.println(map.getOrDefault("Prince", 30)); //17  
		// prince key exist then return the mapped value
		System.out.println(map.getOrDefault("Yug", 30)); //30  
		//Yug key is not exist then return default value 30
		
		
		
		System.out.println("\n--------putIfAbsent(key , value)-----------------");
		//Inserts only if key is missing.
		/*
		 * If the specified key is not already associated with a value (or is mappedto null) associates 
		 * it with the given value and returns null,
		 *  else returns the current value (optional operation).
		 */
		System.out.println(map.putIfAbsent("Yug", 12)); //return null  
		//Yug is not in map then create yug with 12 in map
		System.out.println(map.putIfAbsent("Prince", 55)); //return 17   
		//prince alreay exist in map there is no modification on the value of "Prince"
		System.out.println(map);
		
		
		
		System.out.println("\n--------remove(key , value)-----------------");
		//Removes only if key maps to value.
		/*
		 * Removes the entry for the specified key only if it is currently mapped to the specified value (optional operation).
		 */
		System.out.println(map.remove("Prince",22));  //return false  because entry doesn't match Prince:22
		System.out.println(map.remove("Prince",17));  //return true and remove entry because it match in map
		System.out.println(map);  
		
		
		System.out.println("\n--------replace(key , oldValue , newValue)-----------------");
		//Replaces value if key exists.
		System.out.println(map.replace("Deepak" , 17));  //Here no need to tell old value ,  just write new value
		//if key present in map then return old value and set the new value , if key not present in map than return null
		System.out.println(map);
		
		//java - 8 method
		System.out.println("\n"+map.replace("Shivam", 22, 24)); // here we need to tell key as well as old value for replacement
		//if replace than return true otherwise return false
		System.out.println(map);
		
		System.out.println("\n"+map.replace("Shivam", 20, 24)); // here we need to tell key as well as old value for replacement
		//if replace than return true otherwise return false
		System.out.println(map);

		
		System.out.println("\n----replaceAll(BiFunction<? super K, ? super V, ? extends V> function)-------");
		//Replaces all values with function result.
		map.replaceAll((k,v) -> v + 3);  //return nothing 
		//increase the value of each key by 3
		System.out.println(map);
		
		
		System.out.println("\n--computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction)-------");
		//Updates the value only if the key exists AND value is not null. (Key must exist and value != null)
		//Purpose: Compute a new value based on the old value.
		/*
		 *		 👉 So, use replace() when you just want to substitute.
				 👉 Use computeIfPresent() when you need to compute the new value from the old one.
				 
				 	// Using replace (only substitution)
					salary.replace("Harish", 5500); // must know exact new value

					// Using computeIfPresent (logic-based)
					salary.computeIfPresent("Vishal", (k, v) -> v + 1000); // increment by 1000
		 */
		map.computeIfPresent("Shivam", ((k,v) -> v + 5));
		System.out.println(map);
		
		
		System.out.println("\n--computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction)-------");
		//When to use: Only if the key is absent or value is null.
		//Purpose: Compute a value for a key if it doesn’t already exist.
		map.computeIfAbsent("Harish", (v) -> 24);  //Add key with value if key not present
		System.out.println(map);
		map.computeIfAbsent("Harish", (v) -> 21);  //Nothing change anywhere if key present
		System.out.println(map);
		
		
		
		
		
		System.out.println("\n--compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction)-------");
		//When to use: Always computes a value regardless of whether key exists or not.
		//Purpose: Full control to compute value using old value (if present) or null (if absent).
		/*
		 * Always apply function (whether key exists or not).
		 * if key exist then update its value 
		 * if key not exist then add it with value in map
		 */
		
		map.compute("Shivam", (k,v) -> v == null ? 30 : v - 10);
		System.out.println(map);
		map.compute("Naresh", (k,v) -> v == null ? 30 : v - 10);
		System.out.println(map);
		
		//if key is not present then value is null;  (value will be null means key not present)
		/*
		 * Why v == null instead of k == null?
			k is never null here (because you passed "Shivam").
			v can be null if "Shivam" is not already in the map.
			You need to check v == null to handle the "key absent" case.
		 */
		
		
		
		System.out.println("\n--merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction)--");
		/*
		 * Purpose: Combines a new value with an existing value for a key.
		   Behavior:
				If the key does not exist, it simply adds the key with the given value.
				If the key exists, it applies the remappingFunction on the old value and the new value, and stores the result as the new value.
				If the result of remappingFunction is null, the key is removed.
		 */
		map.merge("Harish", (50), (old,newv) -> old + newv);
		System.out.println(map);
		map.merge("kiran", (50), (old,newv) -> old + newv);
		System.out.println(map);
		
		/*
		 * why use merge() when we have compute():-
		 * because compute() need the extra checking condition inside compute() for handling the null condition
		 * but merge dont't require any check condition it will automatically handle all the case (key is null or not)
		 */
		
		
		/*
		 * why use compute() when we have merge():-
		 * merge is specialized, compute is general
		 		* merge is designed for the specific use case: “I have a new value to combine with an existing value for a key.”
		 		* Automatically handles missing keys (no null check needed).
		 		* You provide a combining function (oldVal, newVal) -> ....
		 		* Great for sums, counters, appending strings.
		 * compute is more general-purpose:
		 		* It gives access to both key and current value (even if null).
		 		* You can define any custom logic, including deleting the key (by returning null) or completely transforming the value based on the key.
		 		* You are not restricted to just combining old and new value.
		 		* Complex updates, conditional logic based on key and current value
		 */
		
		Map<Integer, String> map2 = new HashMap<>();
		map2.put(1, "Harish");

		// Example: value depends on key
		map2.compute(1, (k, v) -> v + " is key " + k);  
		map2.compute(2, (k, v) -> v == null ? "Added key " + k : v);  

		System.out.println(map2);
		// Output: {1=Harish is key 1, 2=Added key 2}
		
		//We used key k in the computation.
		//merge cannot directly use the key for computation — it only takes (oldVal, newVal).

	}
}

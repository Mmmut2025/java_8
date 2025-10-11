package com.image.project2Bank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

record Student(int id , String name , String dep , double marks){
	  
}

public class StudenManagementUsingStream {

	public static void main(String[] args) {
		List<Student> students = new ArrayList<>();
		students.add(new Student(101,"harish","CSE",76));
		students.add(new Student(102,"vishal","ECE",45));
		students.add(new Student(103,"nitish","CSE",87));
		students.add(new Student(104,"deepak pal","CSE",58));
		students.add(new Student(105,"harish","IT",95));
		students.add(new Student(106,"shivam","ECE",47));
		students.add(new Student(107,"dolly","CSE",73));
		students.add(new Student(108,"lalit","IT",51));
		students.add(new Student(109,"naresh","CSE",84));
		students.add(new Student(110,"prince","ECE",63));
		
		students.forEach(System.out::println);
		
		//------------------------Filter---------------------------------------------
		System.out.println("\nStudent who got the marks greather than 70 BY FILTER");
		List<Student> stu1 = students.stream().filter((student) -> student.marks() > 60).collect(Collectors.toList());
		stu1.forEach(System.out::println);
		
		
		//-----------------------Map and Distinct-------------------------------------
		System.out.println("\nPrint list of all department have in college BY MAP AND DINTINCT");
		List<String> allDep = students.stream().map(Student::dep).distinct().collect(Collectors.toList());
		allDep.forEach(System.out::println);
		
		
		//----------------------sorted and limit----------------------------------------
		System.out.println("\nPrint Top 3 student of college BY LIMIT AND SORTED");
		List<Student> topFiveStu = students.stream().sorted((s1,s2) -> Double.compare(s2.marks(), s1.marks())).limit(3).toList();
		topFiveStu.forEach(System.out::println);
		
		
		//----------------------filter and sorted----------------------------------------
		System.out.println("\nPrint All student of cse");
		List<Student> top3StuCSE = students.stream().filter((stu) -> stu.dep().equalsIgnoreCase("CSE")).sorted((s1,s2) -> Double.compare(s2.marks(), s1.marks())).toList();
		top3StuCSE.forEach(System.out::println);
		
		
		//---------------------filter - sorted - limit------------------------------------
		System.out.println("\nPrint Top 3 student of cse BY LIMIT AND SORTED");
		List<Student> allStuCSE = students.stream().filter((stu) -> stu.dep().equalsIgnoreCase("CSE")).sorted((s1,s2) -> Double.compare(s2.marks(), s1.marks())).limit(3).toList();
		allStuCSE.forEach(System.out::println);
		
		
		//----------------------filter - count -----------------------------------
		System.out.println("\nPrint Bottom 2 student of cse BY skip AND SORTED");
		//first find the total student of cse dep
		long total = students.stream().filter((stu) -> stu.dep().equalsIgnoreCase("CSE")).count();
		long skipEle = total - 2;
		
		//--------------------------filter - sorted and skip---------------------
		List<Student> bottom2StuCSE = students.stream().filter((stu) -> stu.dep().equalsIgnoreCase("CSE")).sorted((s1,s2) -> Double.compare(s2.marks(), s1.marks())).skip(skipEle).toList();
		bottom2StuCSE.forEach(System.out::println);
		
		
		//check if we perform any operation in that changes the original stream or not
		System.out.println("\n\n--------peak for testing------------------");
		students.stream().peek((stu) -> idIncrementBy50(stu.id())).forEach(System.out::println);
		
		
		
		//min/max/sum/average
		System.out.println("\n\nFind the min marks in cse department");
		Student stu2 = students.stream().filter((stu) -> stu.dep().equalsIgnoreCase("CSE")).min((s1,s2) -> (int)(s1.marks()-s2.marks())).orElse(null);
		if(stu2 != null) {
			System.out.println(stu2);
		}
		
		System.out.println("\nFind the max marks in cse department");
		//Student stu3 = students.stream().filter((stu) -> stu.dep().equalsIgnoreCase("CSE")).min((s1,s2) -> (int)(s2.marks()-s1.marks())).orElse(null);
		Student stu3 = students.stream().filter((stu) -> stu.dep().equalsIgnoreCase("CSE")).max((s1,s2) -> (int)(s1.marks()-s2.marks())).orElse(null);
		if(stu3 != null) {
			System.out.println(stu3);
		}
		
		
		System.out.println("\nFind the sum of marks of cse department");
		double sum = students.stream().filter((stu) -> stu.dep().equalsIgnoreCase("CSE")).mapToDouble(Student::marks).sum();
		System.out.println(sum);
		
		
		System.out.println("\nFind the Avg of marks of cse department");
		OptionalDouble avg = students.stream().filter((stu) -> stu.dep().equalsIgnoreCase("CSE")).mapToDouble(Student::marks).average();
		if(avg.isPresent()) {
			System.out.println("Avg : " +  avg.getAsDouble());
		}
//		Double avg = students.stream().filter((stu) -> stu.dep().equalsIgnoreCase("CSE")).mapToDouble(Student::marks).average().orElse(0.0);
//		System.out.println(avg);
		
		//sum() and avg perform on primitive stream
		
		
		/***
		 * when we have min() , max() , sum() , average() then why use reduce()
		 * Answer -> min() , max() , sum() and average() only used for one specialized calculation for exm sum only use for add all the value
		 * But reduce() is general function so we can perform the all calculation lie  min , max , sum and avg by using reduce() only
		 * As well as it is used for some other purpose like multiple all the number there is no function for multiply all the number 
		 * And perform other calculation that is not possible by direct stream method 
		 */
		
		
		//reduce() combine the all value of stream into one value based on the condition
		System.out.println("--------------------redude()-----------------------------------");  
		System.out.println("\nAdding all the marks using reduce()");
		Optional<Double> sum2 = students.stream().filter((stu) -> stu.dep().equalsIgnoreCase("CSE")).map(Student::marks).reduce((first,second) -> first + second);
		System.out.println(sum2.get());    //redude(Binary operator) without initial value arg return the Optional<Double/Integer> depend on the type of prop on which reduce apply
		
		
		System.out.println("\nMultiply last digit of all the marks of cse department using reduce()");   //there is no function for multiply all number so we can use reduce()
		double mul = students.stream().filter((stu) -> stu.dep().equalsIgnoreCase("CSE")).map((stu) -> stu.marks()%10).reduce(1.0 , (first,second) -> (first) * (second));
		System.out.println(mul);     //redude(initialValue, BinaryOperator) that have the one initial value declared at run time // return primitive value
		
		
		
		System.out.println("\n\n--------------------anyMatch(Predicate) , allMatch(Predicate) , NoneMatch(Predicate)-----------------------------------");
		//All method take the predicate as an arguments and return only boolean value(true/false)
		System.out.println("\nCheck whether the any student with id 101 is present in database");
		int id = 116;
		boolean isFind = students.stream().anyMatch((student) -> student.id() == id);
		//yadi koi bhi student esa h jiski id 116 h database me then return true otherwise return false
		//koi ek match kar gya to return true
		if(isFind)
			System.out.println("The student already present in database with id "+ id);
		else {
			System.out.println("No student present with this id so you can add ");
		}
		
		System.out.println("\nCheck whether the all student marks greater than 40 or not? ");
		boolean isGreaterThan40 = students.stream().allMatch((student) -> student.marks() > 50);
		//yadi all student marks greater than 50 then return true otherwise return false
		//sare element match karenge to rutun true
		if(isGreaterThan40)
			System.out.println("Yes all student's marks greater than 50");
		else {
			System.out.println("sorry all student haven't marks greater than 50");
		}
		
		System.out.println("\nCheck whether the all student marks greater than 40 or not? ");
		boolean isDepCivil = students.stream().noneMatch((student) -> student.dep().equalsIgnoreCase("civil"));
		//koi bhi condition true nhi ho rhi h to return true, yadhi ek bhi bar predicate ne ture return kar diya to it will return false
		if(isDepCivil)
			System.out.println("Yes there is not student of civil department");
		else {
			System.out.println("Yes the student of civil department exist in database");
		}
		
		
		System.out.println("\n\n--------------------findFirst()-----------------------------------");
		//findFirst() is terminal and no arg method , can return optional value (obj)
//		Optional<Student> s = students.stream().filter((stu) -> stu.dep().equalsIgnoreCase("it")).findFirst();
//		System.out.println(s.get());
		Student s = students.stream().filter((stu) -> stu.dep().equalsIgnoreCase("it")).findFirst().orElse(null);
		if(s==null) {
			System.out.println("no student of it department");
		}
		else {
			System.out.println(s);
		}
		
		
		
		//------------------flatMap------------------
		System.err.println("------flatMap()-------------");
		//it is used to process 2-d list and convert into 1-d list
		//transformation + flattern
		
		List<List<Integer>> doubleList= Arrays.asList(
					Arrays.asList(1,2,3),
					Arrays.asList(4,5,6)
				); 
		
		List<Integer> list = doubleList.stream().flatMap(inner -> inner.stream()).map(g -> g*g).toList();
		System.out.println(list);
		
		//doubleList.stream(x -> x.stream().map(p -> p*p).toList()).toList();
		
		
		List<String> strList = Arrays.asList("Java is popular language" , "Python is modern language");
		List<String> list2 = strList.stream().flatMap(P -> Arrays.stream(P.split(" ")).map(H->H.toUpperCase())).toList();
		System.out.println(list2);
		
		
		
		System.out.println("\n\n--------------------------------------Collectors------------------------------");
		//---------------- toList() ----------------------------------------------------------------------------
		System.out.println("Q1. Convert a stream of students to a List<Student>");
		List<Student> collect = students.stream().collect(Collectors.toList());
		collect.forEach(System.out::println);
		
		
		//-------------mapping() and toSet()--------------------------------------------------------------------
		System.out.println("\nGet a Set<String> of unique department names from the students list.");
		Set<String> collect2 = students.stream().collect(Collectors.mapping(s1 -> s1.dep(), Collectors.toSet()));
		System.out.println(collect2);
		
		//--------------------------toCollection()--------------------------------------------------------------
		System.out.println("\nCollect students into a LinkedList<Student>.");
		LinkedList<Student> collect3 = students.stream().collect(Collectors.toCollection(LinkedList::new));
		collect3.forEach(System.out::println);
		
		//------------------toMap()-----------------------------------------------------------------------------
		System.out.println("\nCreate a Map<Integer, String> mapping student IDs to names.");
		Map<Integer, String> collect4 = students.stream().collect(Collectors.toMap(Student::id , Student::name));
		collect4.forEach((k,v) -> System.out.println(k + " : " + v));
		
		System.out.println("\n.Create a LinkedHashMap<String, Double> where key = name and value = marks. Handle duplicate names by keeping higher marks. and use LinkedHashMap");
		Map<String, Double> collect5 = students.stream().collect(Collectors.toMap(Student::name, Student::marks , (oldValue,newValue) -> oldValue , LinkedHashMap::new));
		collect5.forEach((k,v) -> System.out.println(k + " : " + v));
		
		System.out.println("\nCollect a stream into an unmodifiable map of ID to Student object.");
		//Jab aap kisi list ko unmodifiableList() bana dete ho, to aap us list mein kuch add, remove, ya update nahi kar sakte.
		//Agar aap aisa try karte ho, to program UnsupportedOperationException throw karega.
		//why use:- Jab aap chahte ho ke koi list read-only ho — sirf data ko access kiya ja sake, par change na kiya ja sake.
		Map<Integer, Student> collect6 = students.stream().collect(Collectors.toUnmodifiableMap(Student::id, stu -> stu));
		collect6.forEach((k,v) -> System.out.println(k + " : " + v));
		
		
		//----------------------summarizingDouble()--------------------------------------------------------------
		System.out.println("\nGet a DoubleSummaryStatistics for student marks (count, sum, min, max, average).s");
		DoubleSummaryStatistics collect7 = students.stream().collect(Collectors.summarizingDouble(Student::marks));
		System.out.println("Total number of student : " + collect7.getCount());
		System.out.println("Sum of marks of all student : " + collect7.getSum());
		System.out.println("Avg of marks of all student : " + collect7.getAverage());
		System.out.println("Max marks in all student : " + collect7.getMax());
		
		
		//----------------------counting()-----------------------------------------------------------------------
		System.out.println("\nCount total number of students using Collectors.counting().");
		Long collect8 = students.stream().collect(Collectors.counting());
		System.out.println("Total student : " + collect8);
		
		//-------------------averagingDouble() ------------------------------------------------------
		System.out.println("\nFind the average marks of all students.");
		Double collect9 = students.stream().collect(Collectors.averagingDouble(Student::marks));
		System.out.println("Average : " +collect9);
		
		//------------summingDouble()/summingInt()/summintLong()-----------------------------------------------------------------
		System.out.println("\nFind the total marks of all students.");
		Double collect10 = students.stream().collect(Collectors.summingDouble(Student::marks));
		System.out.println("Sum : " +collect10);
		
		
		//------------minBy(Comparator)-----------------------------------------------------------------
		System.out.println("\nFind the student with highest marks overall.");
		Optional<Student> collect11 = students.stream().collect(Collectors.maxBy((s1,s2)-> Double.compare(s1.marks(), s2.marks())));
		System.out.println("max marks student : " +collect11.get());
		
		
		//--------------joining() and mapping()-----------------------------------------------------------------------
		System.out.println("\nJoin all student names into a single comma-separated string.");
		String collect12 = students.stream().collect(Collectors.mapping(Student::name, Collectors.joining(",")));
		System.out.println(collect12);
		
		
		//--------------filtering() and mapping() and joining()-----------------------------------------------------------------------
		System.out.println("\nJoin student names from a department with a delimiter \" | \" and prefix/suffix.");
		String collect13 = students.stream().collect(Collectors.filtering(sw -> sw.dep().equalsIgnoreCase("CSE"), Collectors.mapping(Student::name, Collectors.joining(" | ", "[", "]"))));
		System.out.println(collect13);
		
		
		//------------------------------mapping()------------------------------
		System.out.println("\nGet all uppercase names of students as a list.");  //for distinct name use toSet()
		List<String> collect14 = students.stream().collect(Collectors.mapping(stu -> stu.name().toUpperCase(), Collectors.toList()));
		System.out.println(collect14);
		
	
		//-------------------------groupingby() + mapping()
		System.out.println("\nGroup students by department and collect only their names (List<String>).");
		Map<String, List<String>> collect15 = students.stream().collect(Collectors.groupingBy(stu ->stu.dep(),Collectors.mapping(Student::name, Collectors.toList())));
		collect15.forEach((k,v) -> System.out.println(k + " : " + v));
		
		
		System.out.println("\nGet a list of students sorted by marks and then return only the top 3 scorers");
		
		
		
		
		//--------------------------------filtering()------------------------------------
		System.out.println("\nGroup students by department and keep only those with marks > 60 in each group.");
		Map<String, List<String>> collect16 = students.stream().
collect(Collectors.groupingBy(Student::dep , Collectors.filtering(sS -> sS.marks() > 60, Collectors.mapping(Student::name, Collectors.toList()))));
		collect16.forEach((k,v) -> System.out.println(k + " : " + v));
		
		
		
		//--------------------------------flatMapping()--------------------------------------
		System.out.println("\nGroup students by department, and for each department collect all unique characters from the students' names.");
		//students.stream().collect(Collectors.groupingBy(Student::dep,));

		Map<String, Set<Character>> result = students.stream()
	            .collect(Collectors.groupingBy(
	                Student::dep, // Group by department
	                Collectors.collectingAndThen(
	                    Collectors.mapping(Student::name, Collectors.toList()),
	                    names -> names.stream()
	                        .flatMap(name -> name.chars().mapToObj(c -> (char) c))
	                        .collect(Collectors.toSet())
	                )
	            ));

	        // Print the result
	        result.forEach((dep, chars) -> {
	            System.out.println(dep + " => " + chars);
	        });

		
		
	        
	      
		//-------------------------------reducing()-----------------------------------------------------------------------------
		//Use Collectors.reducing() to find the total marks of all students.
  		Optional<Double> collect17 = students.stream().collect(Collectors.mapping(Student::marks, Collectors.reducing((m1,m2) -> m1+m2)));
		System.out.println("\nUse Collectors.reducing() to find the total marks of all students.\n"+collect17.get());
		
		
		
		
		//Use Collectors.reducing() to get the student name with the longest length.
		Optional<Student> collect19 = students.stream().collect(Collectors.reducing((s1, s2) -> s1.name().length() >= s2.name().length() ? s1 : s2));
		System.out.println("\nUse Collectors.reducing() to get the student name with the longest length\n" + collect19.get().name());
		
		
		
		
		//Group students by department and use reducing() to get the total marks per department.
		System.out.println("\n\nGroup students by department and use reducing() to get the total marks per department.");
		Map<String, Optional<Double>> collect18 = students.stream().collect(Collectors.groupingBy(Student::dep,Collectors.mapping(Student::marks, Collectors.reducing((s1,s2) -> s2+s2))));
		collect18.forEach((k,v) -> System.out.println(k + " : " + v.get()));

		
		
		
		
		//----------------------groupingBy() / partitioningBy(------------------------------------------)
		//Group students by department into a Map<String, List<Student>>.
		System.out.println("\nGroup student by department");
		Map<String, List<Student>> collect27 = students.stream().collect(Collectors.groupingBy(Student::dep));
		collect27.forEach((k,v) -> System.out.println(k + " : " + v));
		
		
		//Count how many students are in each department.
		System.out.println("\nCount no of student in each department");
		Map<String,Long> collect28 = students.stream().collect(Collectors.groupingBy(Student::dep,Collectors.counting()));
		collect28.forEach((k,v) -> System.out.println(k + " : " + v));

		
		//Group students by department and collect names as a List<String> in each group.
		System.out.println("\nGroup students by department and collect names as a List<String> in each group.");
		Map<String, List<String>> collect30 = students.stream().collect(Collectors.groupingBy(Student::dep , Collectors.mapping(Student::name, Collectors.toList())));
		collect30.forEach((k,v) -> System.out.println(k + " : " + v));

		
		//Partition students into two groups: those who passed (marks ≥ 50) and failed.
		System.out.println("\nPartition students into two groups: those who passed (marks ≥ 50) and failed.");  
		Map<Boolean, List<String>> collect20 = students.stream().collect(Collectors.partitioningBy(stu -> stu.marks() >= 50 , Collectors.mapping(Student::name, Collectors.toList())));
		collect20.forEach((k,v) -> System.out.println(k + " : " + v));

		
		//Group students by department and find the student with max marks in each department
		System.out.println("\nGroup students by department and find the student with max marks in each department");
		Map<String, Optional<Student>> collect21 = students.stream().collect(Collectors.groupingBy(Student::dep , Collectors.maxBy((s1,s2) -> Double.compare(s1.marks(), s2.marks()))));
		collect21.forEach((k,v) -> System.out.println(k + " : " + v.get()));
	}
	
	public static Integer idIncrementBy50(int id) {
		System.out.println("Id : " + (id + 50));
		return id+50;
	}
}

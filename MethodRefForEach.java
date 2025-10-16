package com.method.ref;

import java.util.ArrayList;

public class MethodRefForEach {
	public  void printNum(Integer num) {
		System.out.println(num);
	}
	
	public static void main(String[] args) {
		MethodRefForEach obj = new MethodRefForEach();
		ArrayList<Integer> list = new ArrayList<>();
		list.add(10);
		list.add(20);
		list.add(30);
		
		//print using mannual forEach loop
		System.out.println("print using mannual foreach loop");
		for(Integer i : list) {
			//MethodRefForEach.printNum(i);
			obj.printNum(i);
		}
		
		System.out.println("\nprint using forEach lambda exp");
		//list.forEach(e -> MethodRefForEach.printNum(e));
		list.forEach(e -> obj.printNum(e));
		
		System.out.println("\nprint using forEach method Reference");
//		list.forEach(MethodRefForEach::printNum);
		list.forEach(obj::printNum);
	}
}

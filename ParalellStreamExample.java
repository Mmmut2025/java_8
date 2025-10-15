package com.image.paralellstream;

import java.util.ArrayList;

public class ParalellStreamExample {
	public static void main(String[] args) {
			ArrayList<String> list = new ArrayList<>();
			
			for(int i = 1 ; i <= 1000000 ; i++) {
				list.add("test" + i);
			}
			
			long startTime = System.currentTimeMillis();
			list.stream().forEach(d -> d.toLowerCase().toUpperCase().toLowerCase());
			long endTime = System.currentTimeMillis();
			System.out.println("Total time : " + (endTime-startTime));
			
			startTime = System.currentTimeMillis();
			list.parallelStream().forEach(d -> d.toLowerCase().toUpperCase().toLowerCase());
			endTime = System.currentTimeMillis();
			System.out.println("Total time : " + (endTime-startTime));
	}

}

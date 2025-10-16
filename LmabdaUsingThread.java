package com.java_8;

class Tester implements Runnable{
	@Override
	public void run() {
		System.out.println("without lambda exp");
	}
}

public class LmabdaUsingThread {
	public static void main(String[] args) {
		Tester t = new Tester();
		Thread th = new Thread(t);
		th.start();
		
		Runnable r1 = () -> System.out.println("Using Lambda Expression");
		Thread th2 = new Thread(r1);
		th2.start();
	}
}

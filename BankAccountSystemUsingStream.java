package com.image.project2Bank;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

enum AccountType{
	SAVING,CURRENT,SALARY;
}

enum TransactionType{
	WITHDRAWAL,DEPOSIT;
}

class Customer{
	int id;
	String name;
    int age;
    List<BankAccount> accountList = new ArrayList<>();  //Customer who have any account doesn't have any account details
    
    public Customer(int id , String name , int age) {
    	this.id = id;
    	this.name=name;
    	this.age=age;
    }
    public void addAccount(BankAccount bankAccount) {
    	accountList.add(bankAccount);
    }
    
    
    public String toString() {
    	return "Customer [ id : " + id + " , Name : " + name + " , Age : " + age + " ]";
    }
}

class BankAccount{
	int accountNumber;
	Customer customer;
	double balance;
	AccountType accountType;	
	
	public BankAccount(int accountNumber, Customer customer , double balance ,AccountType accountType) {
		this.accountNumber = accountNumber;
		this.customer = customer;
		this.balance = balance;
		this.accountType = accountType;
		customer.addAccount(this);     //link bankAccount to customer
	}
	
	public int getAccountNumber() {
		return accountNumber;
	}

	public Customer getCustomer() {
		return customer;
	}

	public double getBalance() {
		return balance;
	}

	public AccountType getAccountType() {
		return accountType;
	}
	
	public void deposit(double amount) {
	   balance += amount;
	}

	public void withdraw(double amount) {
	   if (balance < amount) {
	       //throw new IllegalArgumentException("Insufficient balance in account " + accountNumber);
		   System.out.println("Insufficient balance in account " + accountNumber);
	   }
	   balance -= amount;
	}


	public String toString() {
		return "Bank [ Account Number : " + accountNumber + " , Customer : " + customer + " , balance : " + balance + " , accountType : "+ accountType + " ]";
	}
}

class Transaction{
	int transactionId;
	BankAccount bankAccount;
	double amount;
	TransactionType transactionType;
	
	
	public Transaction(int transactionId , BankAccount bankAccount , double amount,TransactionType transactionType) {
		this.transactionId = transactionId;
		this.bankAccount = bankAccount;
		this.amount = amount;
		this.transactionType = transactionType;
		
		if(transactionType == TransactionType.WITHDRAWAL) {
			 bankAccount.withdraw(amount);
		}
		else {
			 bankAccount.deposit(amount);
		}
	}
	
	public String toString() {
		return "Transaction [ TransactionId : " + transactionId + " , BankAccount : " + bankAccount + " , Amount : " + amount + " , transactionType : " + transactionType + " ]";
	}
}

public class BankAccountSystemUsingStream {

	public static void main(String[] args) {
		//create 5 customer who are simple not any account
		
		Customer c1 = new Customer(101 , "Harish" , 22);
		Customer c2 = new Customer(102 , "Vikas" , 19);
		Customer c3 = new Customer(103 , "Aarav kjan" , 26);
		Customer c4 = new Customer(104 , "Shiva" , 42);
		Customer c5 = new Customer(105 , "Bhanu prakash" , 32);
		Customer c6 = new Customer(106 , "Nitish" , 25);
		Customer c7 = new Customer(107 , "Deepak kumar" , 18);
		Customer c8 = new Customer(108 , "Neha" , 21);
		
		
		//create the account for these customer
		List<BankAccount> bankAccounts = new ArrayList<>();
		BankAccount b1 = new BankAccount(10001 , c1, 42000,AccountType.SAVING);
		bankAccounts.add(b1);
		
		BankAccount b2 = new BankAccount(10002 , c2, 67000,AccountType.CURRENT);
		bankAccounts.add(b2);
		
		BankAccount b3 = new BankAccount(10003 , c3, 53000,AccountType.CURRENT);
		bankAccounts.add(b3);
		
		BankAccount b4 = new BankAccount(10004 , c4, 0 ,AccountType.SAVING);
		bankAccounts.add(b4);
		
		BankAccount b5 = new BankAccount(10005 , c5, 42000,AccountType.SAVING);
		bankAccounts.add(b5);
		
		BankAccount b6 = new BankAccount(10006 , c6, 67000,AccountType.SAVING);
		bankAccounts.add(b6);
		
		BankAccount b7 = new BankAccount(10007 , c7, 53000,AccountType.CURRENT);
		bankAccounts.add(b7);
		
		BankAccount b8 = new BankAccount(10008 , c8, 40000 ,AccountType.SAVING);
		bankAccounts.add(b8);
		
		BankAccount b9 = new BankAccount(10009 , c1, 42000,AccountType.SALARY);
		bankAccounts.add(b9);

		
		
		//Before the transaction the account like this
		System.out.println("\n\nBefore performing the any transaction , All the account like this----------");
		bankAccounts.forEach((bankAccount) -> {
			System.out.println(bankAccount);
		});
		
		
		//Transaction list
		List<Transaction> transactions = new ArrayList<>();
		transactions.add(new Transaction(10000001 , b1 , 12000.00 , TransactionType.DEPOSIT));
		transactions.add(new Transaction(10000002 , b2 , 6000.00 , TransactionType.WITHDRAWAL));
		transactions.add(new Transaction(10000003 , b2 , 14000.00 ,TransactionType.WITHDRAWAL));
		transactions.add(new Transaction(10000004 , b3 , 20000.00 , TransactionType.DEPOSIT));
		transactions.add(new Transaction(10000005 , b2, 5000.00 , TransactionType.DEPOSIT));
		transactions.add(new Transaction(10000006 , b1 , 32000.00 , TransactionType.WITHDRAWAL));
		transactions.add(new Transaction(10000007 , b3, 9000.00 , TransactionType.DEPOSIT));
		transactions.add(new Transaction(10000008 , b4, 60000.00 , TransactionType.WITHDRAWAL));
		
		
		//Before the transaction the account like this
		System.out.println("\n\nAfter performing the any transaction , All the account like this----------");
		bankAccounts.forEach((bankAccount) -> {
			System.out.println(bankAccount);
		});
		
		
		//First Assignment -> 3 Question
		//filter the bank customer by high transaction
		
		
		
		
		//filter the bank customer by minimum balance
		System.out.println("\n\nfilter the bank customer by minimum balance");
		BankAccount minBalanceAccount= bankAccounts.stream().sorted(Comparator.comparing(BankAccount::getBalance)).findFirst().get();
		System.out.println(minBalanceAccount);
		
		
		
		//filter the bank customer by top 5 high bank balance
		System.out.println("\n\nfilter the bank customer by top 5 high bank balance");
		bankAccounts.stream().sorted(Comparator.comparingDouble(BankAccount::getBalance).reversed()).limit(5).forEach((bankAccount) -> System.out.println(bankAccount));
				
		
		
		System.out.println("-------------------------filter-----------------------------------");
		//find the print all customers with balance greater than 50,000
		System.out.println("\n\nCustomer with balance greater than 50000");
		bankAccounts.stream().filter((bankAccount) -> bankAccount.balance > 50000).forEach((bank) -> System.out.println(bank));
		
		
		
		//find and print all customer whose name start with "A"
		System.out.println("\n\ncustomer whose name start with A ");
		bankAccounts.stream().filter((bankAccount) -> bankAccount.customer.name.startsWith("A")).forEach((bankAccount) -> System.out.println(bankAccount.customer));
		
		//find and print all customers who have an account number ending with an even digit;
		System.out.println("\n\nAll customers who have an account number ending with an even digit");
		bankAccounts.stream().filter((bankAccount) -> bankAccount.accountNumber % 2 == 0).forEach(System.out::println);
		
		//find and print all customer who have a zero balance
		System.out.println("\n\nCustomer Who have 0 zero balnance");
		bankAccounts.stream().filter((bankAccount) -> bankAccount.balance == 0).forEach((bankAccount) -> System.out.println(bankAccount.customer));
		
		
		//find and print all transaction with an amount greater than 10,000
		System.out.println("\n\nAll Transaction with an amount greater than 15000");
		transactions.stream().filter((transation) -> transation.amount > 15000).forEach((transaction) -> System.out.println(transaction));
	
		
		//find and print all transaction of type WITHDRAWAL
		System.out.println("\n\nAll Transaction with transation Type WITHDRAWAL");
		transactions.stream().filter((transaction) -> transaction.transactionType.equals("WITHDRAWAL")).forEach(System.out::println);
		
		
		//find and print all transactions of type DEPOSIT
		System.out.println("\n\nAll Transaction with transation Type DEPOSIT");
		transactions.stream().filter((transaction) -> transaction.transactionType.equals("DEPOSIT")).forEach(System.out::println);
				
		//find and print all transactions where the amount is divisible by 5000
		System.out.println("\n\nAll transactions where the amount is divisible by 5000");
		transactions.stream().filter((transaction) -> transaction.amount % 5000 == 0).forEach(System.out::println);
				
		
		//find and print all customer whose name length is greater than 5 character
		System.out.println("\n\nAall customer whose name length is greater than 5 character");
		bankAccounts.stream().filter((bankAccount) -> bankAccount.customer.name.length() > 5).forEach(System.out::println);
		
		
		//find and print all customer who have made at least one withdrawal greater than 20,000
		System.out.println("\n\nAll customer who have made at least one withdrawal greater than 20,000");
		transactions.stream().filter((transaction) -> transaction.transactionType == TransactionType.WITHDRAWAL).filter((transaction) -> transaction.amount > 20000).forEach(System.out::println);
				
		
		
		System.out.println("\n\n-------------------------forEach()-----------------------------------");
		//Print details of all customer
		System.out.println("\nPrint details of all customer");
		bankAccounts.stream().forEach((bankAccount) -> System.out.println(bankAccount.customer));
		
		//Print All Account Number of customer
		System.out.println("\nPrint All Account Number of customer");
		bankAccounts.stream().forEach((bankAccount) -> System.out.println(bankAccount.accountNumber));
		
		System.out.println("\nprint Transaction ID's from all accounts");
		transactions.stream().forEach((transaction) -> System.out.println(transaction.transactionId));
		
		
		
		System.out.println("-------------------------Map-----------------------------------");
		System.out.println("\nGet a list of customer names in cutomer");
		List<String> nameInUpperCase = bankAccounts.stream().map((bankAccount) -> bankAccount.customer.name.toUpperCase()).toList();
		System.out.println(nameInUpperCase);
		
		System.out.println("\nGet a list of account balance");
		List<Double> allAccountBalance = bankAccounts.stream().map((bankAccount) -> bankAccount.balance).toList();
		System.out.println(allAccountBalance);
		
		System.out.println("\nConvert all transactions into their **amounts only**");
		
		
		
		System.out.println("\n\n-------------------------Peek-----------------------------------");
		System.out.println("\nPrint each customer while building a stream pipeline (debugging)");
		bankAccounts.stream().peek(System.out::println).toList();
		
		System.out.println("\nPeek into transactions while filtering only those above 10,000");
		transactions.stream().peek(System.out::println).filter((transaction) -> transaction.amount > 10000).toList();
		
		
		System.out.println("\n\n-------------------------skip/limit-----------------------------------");
		System.out.println("\nSkip the first 2 customers and print the rest");
		bankAccounts.stream().skip(2).forEach(System.out::println);
		
		System.out.println("\nprint only the first 3 customers");
		bankAccounts.stream().limit(3).forEach(System.out::println);
		
		System.out.println("\nPrint the top 2 transactions of of each customer");
		transactions.stream().limit(2).forEach(System.out::println);
		
		
		
		System.out.println("\n\n-------------------------sort(sorted)-----------------------------------");
		System.out.println("Sort customers by their name alphabatically");
		//sort according to name and print all accounts
		bankAccounts.stream().sorted((s1,s2) -> s1.customer.name.compareTo(s2.customer.name)).forEach(System.out::println);
	
		
		System.out.println("\nSort customers by account balance (descending)");
		bankAccounts.stream().sorted((s1,s2) -> Double.compare(s2.balance,s1.balance)).forEach(System.out::println);
	
		System.out.println("\nSort transaction by amount(ascending)");
		transactions.stream().sorted((s1,s2) -> Double.compare(s1.amount, s2.amount)).forEach(System.out::println);
		
		
		System.out.println("\n\n-------------------------collect-----------------------------------");
		System.out.println("\nCollect all customer names into a List<String>");
		//here convert the list<BankAccount> to list<String> => one type to another type then use map
		List<String> customerName = bankAccounts.stream().map((bankAccount) -> bankAccount.customer.name).collect(Collectors.toList());
		System.out.println(customerName);
		
		System.out.println("\nCollect all account numbers into a Set<Integer>");
		//Set<Integer> customerNumber = bankAccounts.stream().map((bankAccount) -> bankAccount.accountNumber).collect(Collectors.toSet());
		Set<Integer> customerNumber = bankAccounts.stream().map(BankAccount::getAccountNumber).collect(Collectors.toSet());
		System.out.println(customerNumber);
		
		
		System.out.println("\nCollect transaction above 20,000 into a List<Transaction");
	    List<Transaction> transactionAbove20000 = transactions.stream().filter((transaction) -> transaction.amount > 20000).collect(Collectors.toList());
	    System.out.println(transactionAbove20000);
	    
	    
	    System.out.println("\n\n-------------------------findFirst-----------------------------------");
	    System.out.println("Find the first customer whose balance is above 60,000");
	    BankAccount firstCustomer = bankAccounts.stream().filter((bankAccount) -> bankAccount.getBalance() > 60000).findFirst().orElse(null);
	    System.out.println(firstCustomer);
	    
	    System.out.println("\nFind the first transaction of type WITHDRAWAL");
	    Transaction tran = transactions.stream().filter((transaction) -> transaction.transactionType == TransactionType.WITHDRAWAL).findFirst().orElse(null);
	    if(tran == null) {
	    	System.out.println("There is not transaction with transaction type WITHDRAWAL");
	    }
	    else {
	    	System.out.println(tran);
	    }
	    
	    
	    //-------------------------------------------------------------------------
	    System.out.println("\n\n------------------------Some practical-------------------------------");
	    System.out.println("All account of customer id 101");
	    int cusId = 101;
	    BankAccount ba = bankAccounts.stream().filter((bankAccount) -> bankAccount.customer.id == cusId).findFirst().orElse(null);
	    Customer cus = ba.customer;
	    cus.accountList.forEach(System.out::println);
	}
}

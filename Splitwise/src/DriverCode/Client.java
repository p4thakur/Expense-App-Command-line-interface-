package DriverCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Manager.ExpenseManger;
import Model.EqualSplit;
import Model.ExactSplit;
import Model.ExpenseType;
import Model.PercentageSplit;
import Model.Split;
import Model.User;

public class Client {

	public static void main(String[] args) {//args will be u1 4 u2 u3 u4 u5 amount EXACT a1 b1 c1
		// TODO Auto-generated method stub
		
      ExpenseManger manager=new ExpenseManger();
      
      //add few users initially
      
      manager.addUser(new User("u1", "Prateek", "abcs@gmail.com", "7415519467"));
      manager.addUser(new User("u2", "Prashant", "abcs@gmail.com", "7415519467"));
      manager.addUser(new User("u3", "Pragya", "abcs@gmail.com", "7415519467"));
      manager.addUser(new User("u4", "Priyanka", "abcs@gmail.com", "7415519467"));
      
      //Once the user is added(reisterd with the app,now lets use command line agument to see what we need)
      //EXPENSEu1 NumberOfpeopelInSplit u2 u3 u4 u5 Amount EXACT a1 a2 a3 a4 or percntage 20 20 20 40 or EQUAL
      
      //ShowBalalce
      //arg0=type, arg1 user, agrg2=number of user, (arg2+number of user+1) type ofxpense,than EXACt a1 a2 a3,,again number of user
      while(true)
      {
    	  Scanner in = new Scanner(System.in);
          String s = in. nextLine();
          
          String s1[]=s.split(" ");
          args=s1;
          
          String operation=args[0];
          String paidBy=args[1];
          List<Split> split=new ArrayList<Split>();
          
          if(operation.equals("EXPENSE"))
          {//before adding expenses all split user 
        	  
        	  double amount=Double.parseDouble(args[2]);
        	  int numberOfSplit=Integer.parseInt(args[3]);
        	  String typeSplit=args[3+numberOfSplit+1];
        	  int index1=4;
        	  int indexofSplittype=5+numberOfSplit;
        	  if(typeSplit.equals("EQUAL"))
        	  {
        		
        		 // for(int i=0;i<numberOfSplit;i++)//number of split
            	  //{
            		  //create split type
        			  split.add(new EqualSplit(manager.getPayBy().get(args[index1])));
        			  
            	  //} 
            	  
            	  manager.addExpense(ExpenseType.EQUAL, amount, paidBy, split);
        	  } 
        	  if(typeSplit.equals("EXACT"))
        	  {
        		
        		  for(int i=0;i<numberOfSplit;i++)//number of split
            	  {
            		  split.add(new ExactSplit(manager.getPayBy().get(args[index1+i]), Double.parseDouble(args[indexofSplittype+i])));
            	  } 
            	  
            	  manager.addExpense(ExpenseType.EXACT, amount, paidBy, split);
        	  } 
        	  if(typeSplit=="PERCENT")
        	  {
        		
        		  for(int i=0;i<numberOfSplit;i++)//number of split
            	  {
            		 split.add(new PercentageSplit(manager.getPayBy().get(args[index1+i]), Double.parseDouble(args[indexofSplittype+i])));

        			  
            	  } 
            	  
            	  manager.addExpense(ExpenseType.PERCENTAGE, amount, paidBy, split);
        	  } 
        	  
        	  
        	  //EXPENSE u1 1000 4 u1 u2 u3 u4 EQUAL
          } 
          
         if(operation.equalsIgnoreCase("SHOWBALANCE"))
          {
        	  
        	  manager.showBalance(paidBy);
          } 
           
      }
      
     
	}

}

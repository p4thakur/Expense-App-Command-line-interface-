package Manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Model.User;
import Service.ExpenseService;
import Model.Expense;
import Model.ExpenseType;
import Model.Split;

public class ExpenseManger {
	//list of expenses done by one user
	//keepp list of user regist in spliwise
	//balance s
	
	private Map<String , User>  payBy;
	
	//list of expenses
	List<Expense>  expenses;
    //balnce sheet... user1---> store to which own it own and How much
	Map<String, Map<String ,Double>> balance;

    //Initalize memory for all these three at tim eof creatinG ExpenseManger obkject
	  public ExpenseManger()
	  {
		  setPayBy(new HashMap<String, User>());
		  
		  expenses=new ArrayList<Expense>();
		  
		  balance=new HashMap<String ,Map<String, Double>>();
	  }
	  
	//Now operation that this manger wil perform is ...add users in app/add expense/Showbalance/ShowBalance of One user  
	 public void addUser(User user)
	 {
		 getPayBy().put(user.getId(), user);
		 balance.put(user.getId(), new HashMap<String, Double>());
	 }
	 
	 //add expenses and update balance sheet of both user who paid it
	 public void addExpense(ExpenseType type, Double amount,String paidBy, List<Split> splits)
	 {
		 //CREATE EXPENSE SERVICE   THAT CREATE expnese for me  will add it here,.
		  Expense expense=ExpenseService.createExpence(type,amount,getPayBy().get(paidBy),splits);
		  
		  expenses.add(expense);
		  
		  //update balalce shheet
		  for(Split split:splits)
		  {
			 //find the user to which i paid the amount
			  String paidTo=split.getUser().getId();
			  
			  //now check if this guy is present in the user1 expense book
			  Map<String, Double> bal=balance.get(paidBy);
			  
			  //check if any past payment than paidto need to do
			  if(!bal.containsKey(paidTo))
			  {
				  bal.put(paidTo, 0.0);
			  }
			  bal.put(paidTo, bal.get(paidTo)+split.getAmount());
			  balance.put(paidBy, bal);
			  
			  //also update balanse sheeof paid to
			  bal=balance.get(paidTo);
			  
			  if(!bal.containsKey(paidBy))
			  {
				  bal.put(paidBy, 0.0);
			  } 
			  bal.put(paidBy, bal.get(paidBy) - split.getAmount());
			  
			  balance.put(paidTo, bal);
		  	}  
	 }
	 
	 //show balance based on user 
	 public void showBalance(String  userid)
	 {
		 Map<String, Double> userbalance=balance.get(userid);
		 
		 if(userbalance!=null)
		 {	 
		 for(Map.Entry<String ,Double> entry: userbalance.entrySet())
		 {
			//key is user paid to, and valu is how muc
			 printBalance(userid, entry.getKey(), entry.getValue());
		 } 
		 
		 }
		 
	 }
	 
	 public void printBalance(String paidBy,String paidTo, Double amount)
	 {
		 String paidByName=getPayBy().get(paidBy).getName();
		 String paidToName=getPayBy().get(paidTo).getName();
		 
		 if(amount>0)
		 {
			 System.out.println(paidToName + "OWE amount "+ amount +"TO "+paidByName);
		 } 
		 if(amount<0)
		 {
			 System.out.println(paidByName + "OWE amount "+ Math.abs(amount) +"TO "+paidToName);
		 }
	 }

	public Map<String , User> getPayBy() {
		return payBy;
	}

	public void setPayBy(Map<String , User> payBy) {
		this.payBy = payBy;
	}
}

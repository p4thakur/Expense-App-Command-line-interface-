package Service;

import java.util.List;

import Model.Expense;
import Model.ExpenseType;
import Model.Split;
import Model.User;
import Model.PercentageSplit;

public class ExpenseService {

	public static Expense createExpence(ExpenseType type, Double amount, User user, List<Split> splits) {
		// TODO Auto-generated method stub
		//based on expense type create your expense object .
		//vased on type plit the amount among the split users
		if(type==ExpenseType.EXACT)
		{
			//create the expns....in this case i gues aamount is already added
			return new Expense(amount, user,splits);
		}
		if(type==ExpenseType.EQUAL)
		{
			//divide number in equal number of split...
			int numberOfSplit=splits.size();
			Double splitAmount=(double) Math.round(amount/numberOfSplit);
			for(Split split: splits)
			{
				split.setAmount(splitAmount);
			}
			
			return new Expense(amount, user,splits);	
		}	
		
		if(type==ExpenseType.PERCENTAGE)
		{
			//divide number in equal number of split...
			int numberOfSplit=splits.size();
			
			Double splitAmount=(double) Math.round((amount/100));
			for(Split split: splits)
			{
				//based on percentage of each user divide the amount
				PercentageSplit percSplit=(PercentageSplit)split;
				double perc=percSplit.getPercent();
				split.setAmount((splitAmount/100)*perc);
			}
			
			return new Expense(amount, user,splits);	
		}
		return null;
		
	}

}

package Model;

import java.util.List;

public class Expense {

	double amount;
	User user;
	List<Split> split;
	
	public Expense(double amount, User user, List<Split> split) {
		super();
		this.amount = amount;
		this.user = user;
		this.split = split;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<Split> getSplit() {
		return split;
	}
	public void setSplit(List<Split> split) {
		this.split = split;
	}
	
//	public abstract Boolean validate();
	
}

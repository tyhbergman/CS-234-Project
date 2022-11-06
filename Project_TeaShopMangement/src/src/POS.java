package src;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class POS {
	private double cost;
	private double cash;
	private double change;
	private Map<String,ArrayList<String>> sale;
	
	POS(){
		this.sale = new TreeMap<>();
	}
	
	public void addItem(String product) {
		// Needs to pull data from product class
	}
	
	public void removeItem(String product) {
		sale.remove(product);
	}
	
	public double totalCost() {
		// calculates total in cart
		return cost;
	}
	
	public void moneyGiven(double cash) {
		this.cash = cash;
	}
	
	public double change() {
		change = cost - cash;
		return change;
	}
}

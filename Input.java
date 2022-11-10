/**
Project Vendor Superclass
*/

import java.util.HashMap;
import java.util.Map;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Collection;

public class Input {

	private int lineItemID;
	private String name;
	private String category;
	private String input;
	private double cost;
	private double taxRate;
	private double discount;
	private double quantity;
		
	public Input (int lineItemID, String name, String category, String input, double cost, double quantity, double discount, double taxRate) {
	
		this.lineItemID = lineItemID;
		this.name = name;
		this.category = category;
		this.input = input;
		this.cost = cost;
		this.taxRate = taxRate;
		this.discount = discount;
		this.quantity = quantity;
		getGrossCost(cost, quantity, discount);
		getTaxAmount(taxRate, cost, quantity, discount);
		getNetCost(taxRate, cost, quantity, discount);

	}
	
	public void setDiscount(double discount) {
	
		this.discount = discount;
		
	}
	
	public double getDiscount() {
	
		return discount;
	
	}
	
	public void setTaxRate(double taxRate) {
	
		this.taxRate = taxRate;
	
	}
	
	public double getTaxRate() {
	
		return taxRate;
	
	}
	
	public void setInput(String input) {
	
		this.input = input;
	
	}
	
	public String getInput() {
	
		return input;
	
	}

	public void setQuantity(double quantity) {

		if (quantity > 0.0)
		{
		
			this.quantity = quantity;
		
		}
		else 
		{
		
			System.out.println("Quantity must be greater than zero.");
		
		}
	
	}
	
	public double getQuantity() {
	
		return quantity;
	
	}
	
	public void setCost(double cost) {
	
		if (cost >= 0.0)
		{
		
			this.cost = cost;
		
		}
		else
		{
		
			System.out.println("Cost must be greater than or equal to zero.");
		
		}
	
	}
	
	public double getCost() {
	
		return cost;
	
	}
	
	public double getGrossCost(double cost, double quantity, double discount) {
	
		double grossCost = (cost * (1 - discount) * quantity);
		return grossCost;
	
	}
	
	public double getTaxAmount(double taxRate, double cost, double quantity, double discount) {
	
		double taxAmount = taxRate * getGrossCost(cost, quantity, discount);
		return taxAmount;
	
	}
	
	public double getNetCost(double taxRate, double cost, double quantity, double discount) {
	
		double netCost = getGrossCost(cost, quantity, discount) + getTaxAmount(taxRate, cost, quantity, discount);
		return netCost;
	
	}
	
	public void setLineItemID(int lineItemID) {
	
		this.lineItemID = lineItemID;
		
	}
	
	public int getLineItemID() {
	
		return lineItemID;
	
	}
	
	public void setName(String name) {
	
		this.name = name;
	
	}

	public String getName() {
	
		return name;
	
	}
	
	public void setCategory(String category) {
	
		this.category = category;
		
	}
	
	public String getCategory() {
	
		return category;
	
	}
	
	public String toString() {
	
		return lineItemID + " " + name + " " + category + " " + input + " $" + cost + " " + quantity + " " + (double) discount * 100 + "% " + (double) taxRate * 100 + "% $" + getGrossCost(cost, quantity, discount) + " $" + getTaxAmount(taxRate, cost, quantity, discount) + " $" + getNetCost(taxRate, cost, quantity, discount); 
	
	}
	/*
	public double toDouble() {
	
		return getGrossCost(cost, quantity, discount);
		return getTaxAmount(taxRate, cost, quantity, discount);
		return getNetCost(taxRate, cost, quantity, discount);
		
	}
	*/
}

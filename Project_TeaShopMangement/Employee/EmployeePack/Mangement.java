package EmployeePack;

public class Mangement extends Employees {
	
	 private int inventory;
	 private String categories;
	 private double timeframe;
	 private double revenue;
	 private double expenses;
	 private boolean order;
	 
	 Mangement(){
		 
	 }
	 
	 public void setInventory(int invt) {
		 this.inventory = invt;
	 }
	 
	 public int getInventory() {
		 return inventory;
	 }
	 
	 public void setExpenses(double exp) {
		 this.expenses = exp;
	 }
	 
	 public double getExpenses() {
		 return expenses;
	 }
	 
	 public void setRevenue(double rev) {
		 this.revenue = rev;
	 }
	 
	 public double getRevenue() {
		 return revenue;
	 }
	 
	 public void setOrder(boolean ord) {
		 this.order = ord;
	 }
	 
	 public boolean getOrder() {
		 return order;
	 }
	 
	 public double getMargin() {
		 double margin = revenue - expenses;
		 return margin;
	 }
	 

	
}

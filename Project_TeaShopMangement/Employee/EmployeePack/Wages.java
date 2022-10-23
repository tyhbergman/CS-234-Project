package EmployeePack;

public class Wages extends Employees {

	private double tax;
	private double grossPay;
	
	public void setGrossPay(double hours, double wages) {
		double grossPay = hours*wages;
		this.grossPay = grossPay;
	}
	
	public double getGrossPay() {
		return grossPay;
	}
	
	public double getNetPay() {
		double netPay = grossPay*tax;
		return netPay;
	}
	
}

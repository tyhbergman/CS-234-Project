/**
Project Input Superclass
*/

public class Input {

	private int lineItemID;
	private String vendorName;
	private String vendorCategory;
	private String input;
	private double cost;
	private double taxRate;
	private double discount;
	private int quantity;

		
	public Input (int lineItemID, String vendorName, String vendorCategory, String input, double cost, int quantity, double discount, double taxRate) {
	
		this.lineItemID = lineItemID;
		this.vendorName = vendorName;
		this.vendorCategory = vendorCategory;
		this.input = input;
		this.cost = cost;
		this.taxRate = taxRate;
		this.discount = discount;
		this.quantity = quantity;
		getDiscountAmount(cost, quantity, discount);
		getGrossCost(cost, quantity);
		getTaxAmount(taxRate, cost, quantity);
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
	
	public void setQuantity(int quantity) {

		if (quantity >= 0)
		{
		
			this.quantity = quantity;
		
		}
		else 
		{
		
			System.out.println("Quantity must be at least zero.");
		
		}
	
	}
	
	public int getQuantity() {
	
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
	
	public double getGrossCost(double cost, int quantity) {

		double grossCost = 0.0;
		if (quantity <= 0)
		{
		
			grossCost = 0.0;
		
		}
		else 
		{
			
			grossCost = cost * quantity;
		
		}
		
		return grossCost;
	
	}
	
	public double getTaxAmount(double taxRate, double cost, int quantity) {
	
		double taxAmount = taxRate * getGrossCost(cost, quantity);
		return taxAmount;
	
	}
	
	public double getDiscountAmount(double cost, int quantity, double discount) {
	
		double discountAmount = 0.0;
		if (discount <= 0.0)
		{
			
			discountAmount = 0.0;
		
		}
		else
		{
		
			discountAmount = getGrossCost(cost, quantity) * discount;
		
		}
		
		return discountAmount;
	
	}
	
	public double getNetCost(double taxRate, double cost, int quantity, double discount) {
	
		double netCost = 0.0;
		if (discount <= 0.0)
		{
		
			netCost = getGrossCost(cost, quantity) + getTaxAmount(taxRate, cost, quantity);
		
		}
		else
		{
			
			netCost = (getGrossCost(cost, quantity) - getDiscountAmount(cost, quantity, discount)) + getTaxAmount(taxRate, cost, quantity);
			
		}

		return netCost;
	
	}
	
	public void setLineItemID(int lineItemID) {
	
		this.lineItemID = lineItemID;
		
	}
	
	public int getLineItemID() {
	
		return lineItemID;
	
	}
	
	public void setVendorName(String vendorName) {
	
		this.vendorName = vendorName;
	
	}

	public String getVendorName() {
	
		return vendorName;
	
	}
	
	public void setVendorCategory(String vendorCategory) {
	
		this.vendorCategory = vendorCategory;
		
	}
	
	public String getVendorCategory() {
	
		return vendorCategory;
	
	}

	public String toString() {
	
		return lineItemID + " | " + vendorName + "| " + vendorCategory + "| " + input + 
		"| $" + cost + "| " + quantity + "| " + (double) discount * 100 + "%| " + 
		(double) taxRate * 100 + "% | $" + getDiscountAmount(cost, quantity, discount) + " | $" + 
		getGrossCost(cost, quantity) + "| $" + getTaxAmount(taxRate, cost, quantity) + 
		"| $" + getNetCost(taxRate, cost, quantity, discount); 
	
	}

}

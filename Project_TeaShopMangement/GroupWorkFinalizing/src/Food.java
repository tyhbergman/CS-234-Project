/* Tea Shop Software
 * CS 234 - TB LG NA
 */ 

public class Food extends Product {
	
	private boolean glutenFree;
	private boolean sugarFree;
	
	//Express Constructor -- Crucial information only, description left blank
	public Food(Register register, String name, double price, int quantity, String vendor) {
		
		super(register, name, price, quantity, vendor);
		//Automatically assign 'f' to productType for food
		super.setProductType('f');
		super.setVendorPrice(price*0.87);	//default 0.87 in order to get about a 15% mark-up from
											//vendor price based on original given price
		
		//If not specified, set gluten and sugarFree to false
		setGlutenFree(false);
		setSugarFree(false);
	}
	
	//Full Constructor -- For full control on product information
	public Food(Register register, String name, boolean gluten, boolean sugarFree, String productDesc, double price, double discount, double taxRate, int quantity, String vendor, double vendorPrice) {
		
		super(register, name, productDesc, price, discount, taxRate, quantity, vendor);
		//Automatically assign 'f' to productType for food
		super.setProductType('f');
		
		setGlutenFree(gluten);
		setSugarFree(sugarFree);
		setVendorPrice(vendorPrice);
	}
	
	/*
	 * Getters and Setters
	 */
	
	public boolean isGlutenFree() {
		return glutenFree;
	}

	public void setGlutenFree(boolean gluten) {
		this.glutenFree = gluten;
	}

	public boolean isSugarFree() {
		return sugarFree;
	}

	public void setSugarFree(boolean sugarFree) {
		this.sugarFree = sugarFree;
	}
}

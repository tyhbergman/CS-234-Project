/* Tea Shop Software
 * CS 234 - TB LG NA
 */ 

public class Food extends Product {
	
	private boolean gluten;
	private boolean sugarFree;
	
	//Express Constructor -- Crucial information only, description left blank
	public Food(String name, double price, int quantity, String vendor) {
		
		super(name, price, quantity, vendor);
		//Automatically assign 'f' to productType for food
		super.setProductType('f');
		
		//If not specified, set gluten and sugarFree to false
		setGluten(false);
		setSugarFree(false);
	}
	
	//Full Constructor -- For full control on product information
	public Food(String name, boolean gluten, boolean sugarFree, String productDesc, double price, double discount, double taxRate, int quantity, String vendor) {
		
		super(name, productDesc, price, discount, taxRate, quantity, vendor);
		//Automatically assign 'f' to productType for food
		super.setProductType('f');
		
		setGluten(gluten);
		setSugarFree(sugarFree);
	}
	
	/*
	 * Getters and Setters
	 */
	
	public boolean isGluten() {
		return gluten;
	}

	public void setGluten(boolean gluten) {
		this.gluten = gluten;
	}

	public boolean isSugarFree() {
		return sugarFree;
	}

	public void setSugarFree(boolean sugarFree) {
		this.sugarFree = sugarFree;
	}
}

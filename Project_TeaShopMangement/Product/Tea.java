/* Tea Shop Software
 * CS 234 - TB LG NA
 */ 

public class Tea extends Product {

	private boolean caffeine;
	private boolean sugarFree;
	private boolean seasonal;
	
	//Express Constructor -- Crucial information only, description left blank
	public Tea(String name, boolean caffeine, boolean sugarFree, boolean seasonal, double price, int quantity) {
		
		super(name, price, quantity);
		//Automatically assign 't' to productType for tea
		super.setProductType('t');
		
		setCaffeine(caffeine);
		setSugarFree(sugarFree);
		setSeasonal(seasonal);
	}
	
	//Full Constructor -- For full control on product information
	public Tea(String name, boolean caffeine, boolean sugarFree, boolean seasonal, String productDesc, double price, double discount, double taxRate, int quantity) {
		
		super(name, productDesc, price, discount, taxRate, quantity);
		//Automatically assign 't' to productType for tea
		super.setProductType('t');
		
		setCaffeine(caffeine);
		setSugarFree(sugarFree);
		setSeasonal(seasonal);
		
	}
	
	/*
	 * Getters and Setters
	 */

	public boolean isCaffeine() {
		return caffeine;
	}

	public void setCaffeine(boolean caffeine) {
		this.caffeine = caffeine;
	}

	public boolean isSugarFree() {
		return sugarFree;
	}

	public void setSugarFree(boolean sugarFree) {
		this.sugarFree = sugarFree;
	}

	public boolean isSeasonal() {
		return seasonal;
	}

	public void setSeasonal(boolean seasonal) {
		this.seasonal = seasonal;
	}
	
}

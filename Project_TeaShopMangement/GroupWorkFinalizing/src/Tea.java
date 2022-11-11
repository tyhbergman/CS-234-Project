/* Tea Shop Software
 * CS 234 - TB LG NA
 */ 

public class Tea extends Product {

	private boolean caffeine;
	private boolean sugarFree;
	private boolean seasonal;
	
	//Express Constructor -- Crucial information only, description left blank
	public Tea(Register register, String name, double price, int quantity, String vendor) {
		
		super(register, name, price, quantity, vendor);
		//Automatically assign 't' to productType for tea
		super.setProductType('t');
		super.setVendorPrice(price*0.87);	//default 0.87 in order to get about a 15% mark-up from
											//vendor price based on original given price
		
		//by default:
		setCaffeine(false);
		setSugarFree(false);
		setSeasonal(false);
	}
	
	//Full Constructor -- For full control on product information
	public Tea(Register register, String name, boolean caffeine, boolean sugarFree, boolean seasonal, String productDesc, double price, double discount, double taxRate, int quantity, String vendor, double vendorPrice) {
		
		super(register, name, productDesc, price, discount, taxRate, quantity, vendor);
		//Automatically assign 't' to productType for tea
		super.setProductType('t');
		
		setCaffeine(caffeine);
		setSugarFree(sugarFree);
		setSeasonal(seasonal);
		setVendorPrice(vendorPrice);
		
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
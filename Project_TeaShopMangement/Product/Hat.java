/* Tea Shop Software
 * CS 234 - TB LG NA
 */ 

public class Hat extends Apparel {
	
	private String hatType;
	
	//Express Constructor -- Crucial information only, description left blank
	public Hat(Register register, String name, String hatType, String color, double price, int quantity, String vendor) {
		
		super(register, name, color, price, quantity, vendor);
		
		//Set apparelType to 'h' for hat
		super.setApparelType('h');
		super.setVendorPrice(price*0.87);	//default 0.87 in order to get about a 15% mark-up from
											//vendor price based on original given price
		
		setHatType(hatType);
	}
	
	//Full Constructor -- For full control on product information
	public Hat(Register register, String name, String hatType, String color, String productDesc, double price, double discount, double taxRate, int quantity, String vendor, double vendorPrice) {
		
		super(register, name, color, productDesc, price, discount, taxRate, quantity, vendor, vendorPrice);
		
		//Set apparelType to 'h' for hat
		super.setApparelType('h');
		
		setHatType(hatType);
	}
	
	/*
	 * Getters and Setters
	 */
	
	public String getHatType() {
		return hatType;
	}

	public void setHatType(String hatType) {
		this.hatType = hatType;
	}
	
}

/* Tea Shop Software
 * CS 234 - TB LG NA
 */ 

public class Hat extends Apparel {
	
	private String hatType;
	
	//Express Constructor -- Crucial information only, description left blank
	public Hat(String name, String hatType, String color, double price, int quantity) {
		
		super(name, color, price, quantity);
		
		//Set apparelType to 'h' for hat
		super.setApparelType('h');
		
		setHatType(hatType);
	}
	
	//Full Constructor -- For full control on product information
	public Hat(String name, String hatType, String color, String productDesc, double price, double discount, double taxRate, int quantity) {
		
		super(name, color, productDesc, price, discount, taxRate, quantity);
		
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

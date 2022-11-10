/* Tea Shop Software
 * CS 234 - TB LG NA
 */ 

public class Apparel extends Product {
	
	private char apparelType;
	private String color;
	
	//Express Constructor -- Crucial information only, description left blank
	public Apparel(Register register, String name, String color, double price, int quantity, String vendor) {
		
		super(register, name, price, quantity, vendor);
		//Automatically assign 'a' to productType for apparel
		super.setProductType('a');
		super.setVendorPrice(price*0.87);	//default 0.87 in order to get about a 15% mark-up from
											//vendor price based on original given price
		
		//Automatically set apparelType to 'x' since no type is specified
		setApparelType('x');
		setColor(color);
	}
	
	//Full Constructor -- For full control on product information
	public Apparel(Register register, String name, String color, String productDesc, double price, double discount, double taxRate, int quantity, String vendor, double vendorPrice) {
		
		super(register, name, productDesc, price, discount, taxRate, quantity, vendor);
		//Automatically assign 'a' to productType for apparel
		super.setProductType('a');
		setVendorPrice(vendorPrice);
		
		//Automatically set apparelType to 'x' since no type is specified
		setApparelType('x');
		setColor(color);
	}
	
	//Empty Constructor to avoid compile errors
	public Apparel() {}
	
	/*
	 * Getters and Setters
	 */
	
	public char getApparelType() {
		return apparelType;
	}

	public void setApparelType(char apparelType) {
		this.apparelType = apparelType;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
}

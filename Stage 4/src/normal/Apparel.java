/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package normal;
public class Apparel extends Product {
	
	private char apparelType;
	private String color;
	
	//Express Constructor -- Crucial information only, description left blank
	public Apparel(Register register, String name, double price, int quantity) {
		
		super(register, name, price, quantity);
		//Automatically assign 'a' to productType for apparel
		super.setProductType('a');	
		
		//Automatically set apparelType to 'x' since no type is specified
		setApparelType('x');
		setColor(color);
	}
	
	//Full Constructor -- For full control on product information
	public Apparel(Register register, String name, String color, String productDesc, double price, double discount, double taxRate, int quantity) {
		
		super(register, name, productDesc, price, discount, taxRate, quantity);
		//Automatically assign 'a' to productType for apparel
		super.setProductType('a');
		
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package normal;
/* Tea Shop Software
 * CS 234 - TB LG NA
 */ 

public class Hat extends Apparel {
	
	private String hatType;
	
	//Express Constructor -- Crucial information only, description left blank
	public Hat(Register register, String name, String hatType, String color, double price, int quantity) {
		
		super(register, name, price, quantity);
		
		//Set apparelType to 'h' for hat
		super.setApparelType('h');
		
		setHatType(hatType);
	}
	
	//Full Constructor -- For full control on product information
	public Hat(Register register, String name, String hatType, String color, String productDesc, double price, double discount, double taxRate, int quantity) {
		
		super(register, name, color, productDesc, price, discount, taxRate, quantity);
		
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package normal;
/* Tea Shop Software
 * CS 234 - TB LG NA
 */ 

public class Food extends Product {
	
	private boolean glutenFree;
	private boolean sugarFree;
	
	//Express Constructor -- Crucial information only, description left blank
	public Food(Register register, String name, double price, int quantity) {
		
		super(register, name, price, quantity);
		//Automatically assign 'f' to productType for food
		super.setProductType('f');
		
		//If not specified, set gluten and sugarFree to false
		setGlutenFree(false);
		setSugarFree(false);
	}
	
	//Full Constructor -- For full control on product information
	public Food(Register register, String name, boolean gluten, boolean sugarFree, String productDesc, double price, double discount, double taxRate, int quantity) {
		
		super(register, name, productDesc, price, discount, taxRate, quantity);
		//Automatically assign 'f' to productType for food
		super.setProductType('f');
		
		setGlutenFree(gluten);
		setSugarFree(sugarFree);
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

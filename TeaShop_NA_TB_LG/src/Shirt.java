/* Tea Shop Software
 * CS 234 - TB LG NA
 */ 

public class Shirt extends Apparel {
	
	private int sQuantity;
	private int mQuantity;
	private int lQuantity;
	private int xLQuantity;
	
	private boolean longSleeve;
	private boolean hoodie;
	
	//Express Constructor -- Crucial information only, description left blank
	public Shirt(Register register, String name, String color, boolean longSleeve, boolean hoodie, double price, int sQuantity, int mQuantity, int lQuantity, int xLQuantity) {
		
		super(register, name, color, price, (sQuantity+mQuantity+lQuantity+xLQuantity));
		
		//Set apparelType to 's' for shirt
		super.setApparelType('s');
		
		setSQuantity(sQuantity);
		setMQuantity(mQuantity);
		setLQuantity(lQuantity);
		setXLQuantity(xLQuantity);
		
		setLongSleeve(longSleeve);
		setHoodie(hoodie);
		
	}
	
	//Full Constructor -- For full control on product information
	public Shirt(Register register, String name, String color, boolean longSleeve, boolean hoodie, String productDesc, double price, double discount, double taxRate, int sQuantity, int mQuantity, int lQuantity, int xLQuantity) {
		
		super(register, name, color, productDesc, price, discount, taxRate, (sQuantity+mQuantity+lQuantity+xLQuantity));
		
		//Set apparelType to 's' for shirt
		super.setApparelType('s');
		
		setSQuantity(sQuantity);
		setMQuantity(mQuantity);
		setLQuantity(lQuantity);
		setXLQuantity(xLQuantity);

		setColor(color);
		setLongSleeve(longSleeve);
		setHoodie(hoodie);
		
	}
	
	/*
	 * Getters and Setters
	 */
	
	//Override getQuantity
	public int getQuantity() {
		return sQuantity+mQuantity+lQuantity+xLQuantity;
	}
	public int getSQuantity() {
		return sQuantity;
	}

	public void setSQuantity(int sQuantity) {
		this.sQuantity = sQuantity;
	}

	public int getMQuantity() {
		return mQuantity;
	}

	public void setMQuantity(int mQuantity) {
		this.mQuantity = mQuantity;
	}

	public int getLQuantity() {
		return lQuantity;
	}

	public void setLQuantity(int lQuantity) {
		this.lQuantity = lQuantity;
	}

	public int getXLQuantity() {
		return xLQuantity;
	}

	public void setXLQuantity(int xLQuantity) {
		this.xLQuantity = xLQuantity;
	}

	public boolean getLongSleeve() {
		return longSleeve;
	}

	public void setLongSleeve(boolean isLongSleeve) {
		this.longSleeve = isLongSleeve;
	}

	public boolean getHoodie() {
		return hoodie;
	}

	public void setHoodie(boolean isHoodie) {
		this.hoodie = isHoodie;
	}

}
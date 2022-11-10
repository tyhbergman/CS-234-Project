import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Register {
	private ArrayList<List> cart = new ArrayList<List>();
	private ArrayList<Object> cartItems = new ArrayList<Object>();
	private double tempSubTotal;
	private int saleQuantity;
	private double tempTotal;
	private double subTotal = 0;
	private double total = 0;
	private int totalQuantity = 0;
	private int transactions = 1;
	
	private int productIndex=0;
	private Map<Integer,Product> productList = new HashMap<Integer,Product>();
	
	public void addToCart(Product n, int saleQuantity) {
		this.saleQuantity = saleQuantity;
		if(saleQuantity <= 0) {
			System.out.println("Quantity error, could not add to cart.");
		} 
		else if(saleQuantity > n.getQuantity()) {
			System.out.println("Insufficient quantity for " + n.getName() + ", could not add to cart.");	
		} 
		
		else {
			
			
			cartItems.add(n.getName());
			cartItems.add(saleQuantity);
			cartItems.add(n.getPrice());
			cartItems.add(n.getTaxRate());
			
			//Adjust quantity in product object
			n.setQuantity(n.getQuantity()-saleQuantity);
			
			tempSubTotal = n.getPrice()*saleQuantity;
			tempTotal = tempSubTotal*n.getTaxRate();
			
			cartItems.add(tempSubTotal);
			cartItems.add(tempTotal);

			cartItems.add(n);
			
			cart.add(new ArrayList(cartItems));
			
			
			cartItems.clear();
			
			}
	}
	
	public void sale() {

		totalQuantity = 0;
		subTotal = 0;
		total = 0;
		
		if(cart.isEmpty()) {
			System.out.println("Cart is empty!");
		} 
		else 
		{
			for (int i = 0; i < cart.size(); i++) {
				subTotal = (double)(cart.get(i).get(4)) + subTotal;
				total = (double)(cart.get(i).get(5)) + total;
				totalQuantity = (int)(cart.get(i).get(1)) + totalQuantity;
				
				
			}
			
			System.out.println("Transaction #" + transactions + " succesful.");
			System.out.println("\tTotal quantity of items sold: " + totalQuantity);
			System.out.println("\tThe subtotal is $" + subTotal);
			System.out.println("\tThe tax due is $" + (total-subTotal));
			System.out.println("\tThe grand total is $" + total);
		
			/*
			 * Write sale information to sale logs and vendor logs here
			 */
			

			transactions++;
		}
		
		
		
		cart.removeAll(cart);
		System.out.println();
	}
	
	public void clearCart() {
		//This monstrosity of a for loop simply adds back saleQuantity to the products available quantity
		int tempSaleQuantity;
		for (int i = 0; i < cart.size(); i++) {
			tempSaleQuantity = (int) cart.get(i).get(1);
			System.out.println("Testing: " + cart.get(i));
			((Product) cart.get(i).get(6)).setQuantity(   ((Product) cart.get(i).get(6)).getQuantity() + tempSaleQuantity   );
		}
		
		//Now actually clear the cart
		cart.removeAll(cart);
		System.out.println();
	}

	public Map<Integer,Product> getProductList() {
		return productList;
	}

	public void setProductList(Map<Integer,Product> productList) {
		this.productList = productList;
	}
	
	public void printProductList() {
		System.out.println(productList);
	}

	public int getAndIncrementProductIndex() {
		productIndex++;
		return productIndex-1;
		
	}

	public void setProductIndex(int productIndex) {
		this.productIndex = productIndex;
	}
	
	
}


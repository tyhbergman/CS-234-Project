import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Register {
	private ArrayList<List> cart = new ArrayList<List>();
	private ArrayList<Object> itemDetails = new ArrayList<Object>();
	private ArrayList<List> allTransactions = new ArrayList<List>();
	private ArrayList<Object> transactionDetails = new ArrayList<Object>();
	private ArrayList<List> allInputs = new ArrayList<List>();
	private ArrayList<Object> inputDetails = new ArrayList<Object>();
	private double tempSubTotal;
	private int saleQuantity;
	private double tempTotal;
	private double subTotal = 0;
	private double total = 0;
	private int totalQuantity = 0;
	private int transactions = 1;
	
	private int productIndex=0;
	private Map<Integer,Product> productList = new HashMap<Integer,Product>();
	
	/* 
	 * 
	 * The cart system uses ArrayList 'cart' to store ArrayLists of information
	 * from each object 'itemDetails'. This information is crucial information
	 * needed at time of sale (ID, name, price, tax rate, etc.)
	 * 
	 * These details are indexed in each itemDetails ArrayList:
	 * 
	 * index  |  information
	 * ------------------------------------------------------------
	 * 0      |  Name
	 * 1	  |  Sale Quantity (quantity customer wants to purchase)
	 * 2	  |  Price
	 * 3      |  Tax Rate (Some products are taxed at different rates)
	 * 4      |  Sub-total cost of that specific item at that quantity
	 * 5      |  Total cost of that specific item at that quantity
	 * 6      |  Product itself (can use methods belonging to that class)
	 * 
	 * 
	 * addToCart is the method that alters the quantity of the Product
	 * object. Clearing the cart is the only way to restore the quantity. 
	 */
	
	
	public void addToCart(Product n, int saleQuantity) {
		this.saleQuantity = saleQuantity;
		if(saleQuantity <= 0) {
			System.out.println("Quantity error, could not add to cart.");
		} 
		else if(saleQuantity > n.getQuantity()) {
			System.out.println("Insufficient quantity for " + n.getName() + ", could not add to cart.");	
		} 
		
		else {
			
			
			itemDetails.add(n.getName());
			itemDetails.add(saleQuantity);
			itemDetails.add(n.getPrice());
			itemDetails.add(n.getTaxRate());
			
			//Adjust quantity in product object
			n.setQuantity(n.getQuantity()-saleQuantity);
			
			tempSubTotal = n.getPrice()*saleQuantity;
			tempTotal = tempSubTotal*n.getTaxRate();
			
			itemDetails.add(tempSubTotal);
			itemDetails.add(tempTotal);

			itemDetails.add(n);
			
			cart.add(new ArrayList(itemDetails));
			
			
			itemDetails.clear();
			
			}
	}
	
	/* 
	 * The sale system uses ArrayList 'allTransactions' to store ArrayLists of information
	 * from each ArrayList 'transactionDetails'. This information is crucial information
	 * from time of sale (Transaction#, total cost, sub total, tax rate, etc.)
	 * Used for sales reports
	 * 
	 * These details are indexed in each itemDetails ArrayList:
	 * 
	 * index  |  information
	 * ------------------------------------------------------------
	 * 0      |  Transaction #
	 * 1	  |  Total cost of transaction
	 * 2	  |  Sub-total cost
	 * 3      |  Tax cost
	 * 4      |  Total Quantity of items purchased
	 * 
	 * 
	 */
	
	public void sale() {

		totalQuantity = 0;
		subTotal = 0;
		total = 0;
		
		if(cart.isEmpty()) {
			System.out.println("Cart is empty!");
		} 
		else 
		{
			
			//Go through 'cart' and at each 'itemDetails' element
			//gather specific information for the sale.
			//Refer back to chart at top of page for 
			//legend of index values of itemDetails
			
			for (int i = 0; i < cart.size(); i++) {
				subTotal = (double)(cart.get(i).get(4)) + subTotal;
				total = (double)(cart.get(i).get(5)) + total;
				totalQuantity = (int)(cart.get(i).get(1)) + totalQuantity;
				
				
			}
			
			//Print the 'receipt'
			
			System.out.println("-----------------------------------------------");
			System.out.println("Transaction #" + transactions + " succesful.");
			System.out.println("\tTotal items sold: \t" + totalQuantity);
			System.out.printf("\tThe subtotal is \t$%5.2f", subTotal);
			System.out.println();
			System.out.printf("\tThe tax due is \t\t$%5.2f", (total-subTotal));
			System.out.println();
			System.out.printf("\tThe grand total is \t$%5.2f", total);
			System.out.println();
			System.out.println("-----------------------------------------------");
			
			//String trans = file.addTrans(subTotal, totalQuantity, total);
			//try {
			//	file.write(trans);
			//} catch (IOException e) {
				// TODO Auto-generated catch block
			//	e.printStackTrace();
			//}
			
			//After each sale:
			//Adding arrayList of transaction details as an 
			//element of the ArrayList 'allTransactions'
			transactionDetails.add(transactions);
			transactionDetails.add(total);
			transactionDetails.add(subTotal);
			transactionDetails.add(total-subTotal);
			transactionDetails.add(totalQuantity);
			
			allTransactions.add(new ArrayList(transactionDetails));
			
			transactionDetails.clear();
			
			
			/*
			 * 
			 * 
			 * Write sale information to  vendor logs here
			 *
			 *
			 *
			 */
			
			

			transactions++;
		}
		
		
		//Empty the cart after the sale
		cart.removeAll(cart);
		System.out.println();
	}
	
	public void clearCart() {
		//This monstrosity of a for loop simply adds back saleQuantity to the products available quantity
		//Refer back to chart at top of page for 
		//legend of index values of itemDetails
		
		int tempSaleQuantity;
		for (int i = 0; i < cart.size(); i++) {
			tempSaleQuantity = (int) cart.get(i).get(1);
			((Product) cart.get(i).get(6)).setQuantity(   ((Product) cart.get(i).get(6)).getQuantity() + tempSaleQuantity   );
		}
		
		//Now actually clear the cart
		cart.removeAll(cart);
		System.out.println();
	}
	
	public void viewCart() {
		System.out.println("Contents of the cart:");
		//Cycle through each item in the cart
		for (int i = 0; i < cart.size(); i++) {
			//Print the name
			System.out.print("Item: " + ((Product) cart.get(i).get(6)).getName());
			//Print the quantity the customer ordered
			System.out.println(" | Quantity: " + cart.get(i).get(1));
		}
		System.out.println();
	}
	
	/*
	 * Getters and Setters
	 */
	
	
	public Map<Integer,Product> getProductList() {
		return productList;
	}

	public int getAndIncrementProductIndex() {
		productIndex++;
		return (productIndex-1);
		
	}
	
	public int getProductIndex() {
		return productIndex;
		
	}

	public void setProductIndex(int productIndex) {
		this.productIndex = productIndex;
	}
	
	public ArrayList<List> getAllTransactions() {
		return allTransactions;
	}

	public ArrayList<List> getAllInputs() {
		return allInputs;
	}

	public void setAllInputs(ArrayList<List> allInputs) {
		this.allInputs = allInputs;
	}

	public ArrayList<Object> getInputDetails() {
		return inputDetails;
	}

	public void setInputDetails(ArrayList<Object> inputDetails) {
		this.inputDetails = inputDetails;
		
	}
	
	public void addInputDetails(ArrayList<Object> inputDetails) {
		
		
		this.getAllInputs().add(new ArrayList<Object>(inputDetails));
		
		}
}



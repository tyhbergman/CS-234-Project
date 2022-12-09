/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package normal;

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
	private double tempSubTotal;
	private int saleQuantity;
	private double tempTotal;
	private double subTotal = 0;
	private double total = 0;
	private int totalQuantity = 0;
	private int transactions = 101;
	private int productIndex=1;
        
	private Map<Integer,Product> productList = new HashMap<Integer,Product>();
	
        public Register(){
            
        }
        
	/* 
	 * addToCart():
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
		if(!getProductList().containsKey(n.getId())){
			System.out.println("Item is not in system!");
			System.out.println("Try again.");
			
		}
		
		else if(saleQuantity <= 0) {
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
	 * sale()
	 * The sale system uses ArrayList 'allTransactions' to store ArrayLists of information
	 * from each ArrayList 'transactionDetails'. This information is crucial information
	 * from time of sale (Transaction#, total cost, sub total, tax rate, etc.)
	 * Used for sales reports
	 * 
	 * These details are indexed in each transactionDetails ArrayList:
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
	 *    *This is the main sale method. The sale method at the bottom of the file
	 *     is purely for dummy info purposes (won't print receipts like the
	 *     following method does)
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
		
		/*This for loop adds back the saleQuantity of each item in the cart to that products 
		 * quantity value
		 * 
		 * Refer to this legend of index values of itemDetails
		 * 
		 * * These details are indexed in each itemDetails ArrayList:
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
		 */
		
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
	
	public void salesRecords(Register register) {
		double completeTotal = 0;
		double completeSubTotal = 0;
		double completeTax = 0;
		int completeQuantitySold = 0;
		
		/*
		 * 
		 * 
		 * This prints a nicely formatted table of all transactions to date with important details
		 *
		 * These details are indexed in each transactionDetails ArrayList:
		 * 
		 * index  |  information
		 * ------------------------------------------------------------
		 * 0      |  Transaction #
		 * 1	  |  Total cost of transaction
		 * 2	  |  Sub-total cost
		 * 3      |  Tax cost
		 * 4      |  Total Quantity of items purchased
		 * 
		 * example: register.getAllTransactions().get(i).get(1) is the total cost of the
		 * i'th transaction in allTransactions.
		 */
		 
		
		System.out.println("\t\t| Sales Records (to date) |");
		System.out.println();
		System.out.println("Transaction ID\tTotal\t\tSub-Total\tTax\t\tQuantity of Items Purchased");
		System.out.println("--------------------------------------------------------------------------------------------------");
		for (int i = 0; i < register.getAllTransactions().size(); i++) {
			
			
			System.out.printf("%03d\t\t$%5.2f\t\t$%5.2f\t\t$%5.2f\t\t%d",
					register.getAllTransactions().get(i).get(0), register.getAllTransactions().get(i).get(1),
					register.getAllTransactions().get(i).get(2), register.getAllTransactions().get(i).get(3),
					register.getAllTransactions().get(i).get(4));
			System.out.println();
			
			completeTotal = (double)register.getAllTransactions().get(i).get(1) + completeTotal;
			completeSubTotal = (double)register.getAllTransactions().get(i).get(2) + completeSubTotal;
			completeTax = (double)register.getAllTransactions().get(i).get(3) + completeTax;
			completeQuantitySold = (int)register.getAllTransactions().get(i).get(4) + completeQuantitySold;
			
			
		}
		
		//Print all combined data summed in the above for loop
		
		System.out.println();
		System.out.println();
		System.out.println("Sum of Totals\t\tSum of Sub-totals\tSum of Tax\t\tSum of Product Sold");
		System.out.println("--------------------------------------------------------------------------------------------------");
		System.out.printf("$%7.2f\t\t$%7.2f\t\t$%7.2f\t\t%d\n",completeTotal,completeSubTotal,completeTax,completeQuantitySold);
		System.out.println("--------------------------------------------------------------------------------------------------");
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
	
	public void sale(boolean x) {
		/*
		 * 
		 * METHOD IS PURELY FOR DUMMY INFO ONLY
		 * easiest way to not print the receipts 
		 * initially.
		 * 
		 * normally sale() method is called without parameters,
		 * but dummy information will call sale(true)
		 * which calls this method instead via method
		 * overloading. This method will not print receipts
		 * but stores transaction information the same.
		 * 
		 * 
		 * 
		 */
		
		
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
			
//			System.out.println("-----------------------------------------------");
//			System.out.println("Transaction #" + transactions + " succesful.");
//			System.out.println("\tTotal items sold: \t" + totalQuantity);
//			System.out.printf("\tThe subtotal is \t$%5.2f", subTotal);
//			System.out.println();
//			System.out.printf("\tThe tax due is \t\t$%5.2f", (total-subTotal));
//			System.out.println();
//			System.out.printf("\tThe grand total is \t$%5.2f", total);
//			System.out.println();
//			System.out.println("-----------------------------------------------");
//			
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
	}
	
        public ArrayList<List> getCart() {
        return cart;
        }
        
        //Evaluates what is currently in the cart and returns sub total as a STRING
        public String getCurrentSubTotal(Register register){
            int tempSize = register.getCart().size();
            double tempSubTotal = 0;
            
            for(int i=0; i<tempSize; i++){
                //refer to legend in addToCart() for finding correct index value
                tempSubTotal = (double)register.getCart().get(i).get(4)+tempSubTotal;
            }
            //create string with 2 decimal place format based on tempSubTotal
            String stringSubTotal = String.format("$%.2f", tempSubTotal);
            return stringSubTotal;
        }
        
        //Evaluates what is currently in the cart and returns tax amount as a STRING
        public String getCurrentTax(Register register){
            int tempSize = register.getCart().size();
            double tempTaxTotal = 0;
            double tempTotal, tempSubTotal;
            
            for(int i=0; i<tempSize; i++){
                //refer to legend in addToCart() for finding correct index value
                tempTotal = (double)register.getCart().get(i).get(5);
                tempSubTotal = (double)register.getCart().get(i).get(4);
                
                tempTaxTotal = (tempTotal-tempSubTotal)+tempTaxTotal;
            }
            //create string with 2 decimal place format based on tempTaxTotal
            String stringTaxTotal = String.format("$%.2f", tempTaxTotal);
            return stringTaxTotal;
        }
        
        //Evaluates what is currently in the cart and returns total as a STRING
        public String getCurrentTotal(Register register){
            int tempSize = register.getCart().size();
            double tempTotal = 0;
            
            for(int i=0; i<tempSize; i++){
                //refer to legend in addToCart() for finding correct index value
                tempTotal = (double)register.getCart().get(i).get(5)+tempTotal;
            }
            //create string with 2 decimal place format based on tempTotal
            String stringTotal = String.format("$%.2f", tempTotal);
            return stringTotal;
        }
        
        public int getTransactions() {
        return transactions;
    }
        
}

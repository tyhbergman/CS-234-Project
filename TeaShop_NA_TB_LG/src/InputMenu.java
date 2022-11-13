
/**
Input Menu
*/

import java.util.HashMap;
import java.util.Map;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Collection;
import java.util.Scanner;
import java.util.Iterator;

/**
	This class provides a menu to do operations on all of the inputs that the tea shop 
	purchases. This menu should only be accessible by the manager of the tea shop, since
	these options pertain to sensitive cost information.
*/

public class InputMenu {

	private double totalGrossCost;
	private double totalTaxAmount;
	private double totalDiscountAmount;
	private double totalNetCost;
	
	/**
		Constructs an input menu with the total gross cost of all of the inputs purchased,
		the total tax amount owed, the total discount amount deducted from the gross cost,
		and the net total cost that the tea shop will pay for all inputs purchased.
	*/
	
	public InputMenu() {
	
		this.totalGrossCost = 0.0;
		this.totalTaxAmount = 0.0;
		this.totalDiscountAmount = 0.0;
		this.totalNetCost = 0.0;

	}
	
		public void inputMenu() {
		
		//Declares an empty constructor to display the line item headers
		Input header = new Input(); 
		//Declares a linked list of the inputs to display each line item
		List<Input> inputDescription = new LinkedList<Input>();
		Scanner c = new Scanner(System.in); //Used for numerical values
		Scanner s = new Scanner(System.in); //Used for string or char values
		
		//Initializing parameter variables
		int lineItemID = 0; //Sets the line item ID to zero initially
		String name = "Generic Vendor"; //Initial vendor name
		String category = "Generic Category"; //Initial vendor category, such as food products
		String input = "Generic Input"; //Initial input, such as green tea formula
		double inputCost = 0.0; //Initial input unit cost in $
		int inputQuantity = 0; //Initial quantity of units purchased
		double inputDiscount = 0.00; //Initial discount rate applied on the number of inputs purchased
		double taxRate = 0.00; //Initial tax rate applied on the number of inputs purchased
		double gc = 0.0; //Initial gross cost total on the quantity of inputs purchased
		double ta = 0.0; //Initial tax amount total on the quantity of inputs purchased
		double da = 0.0; //Initial discount amount total derived from the quantity of inputs purchased
		double nc = 0.0; //Initial net cost total on the quantity of inputs purchased
		//Constructs a new input, initially populated with the info above
		Input lineItem = new Input(lineItemID, name, category, input, inputCost, inputQuantity, inputDiscount, taxRate);
		inputDescription.add(lineItem); //Adds the line item to the linked list
		//End initialization
		
		//Dummy information that is used to display the initial cost report
		Input lineItem1 = new Input(1, "Jimmy's", "Tea Products", "Coconut Tea Supplies", 4.00, 60, 0.0, 0.05);
		inputDescription.add(lineItem1);
		double gc1 = lineItem1.getGrossCost(4.00, 60);
		double ta1 = lineItem1.getTaxAmount(0.05, 4.00, 60);
		double da1 = lineItem1.getDiscountAmount(4.00, 60, 0.0);
		double nc1 = lineItem1.getNetCost(0.05, 4.00, 60, 0.0);
		Input lineItem2 = new Input(2, "Harry's", "Tea Products", "Matcha Tea Supplies", 4.50, 0, 0.0, 0.05);
		inputDescription.add(lineItem2);
		double gc2 = lineItem2.getGrossCost(4.50, 0);
		double ta2 = lineItem2.getTaxAmount(0.05, 4.50, 0);
		double da2 = lineItem2.getDiscountAmount(4.50, 0, 0.0);
		double nc2 = lineItem2.getNetCost(0.05, 4.50, 0, 0.0);
		Input lineItem3 = new Input(3, "Jimmy's", "Tea Products", "Lemon Tea Supplies", 3.00, 80, 0.0, 0.05);
		inputDescription.add(lineItem3);
		double gc3 = lineItem3.getGrossCost(3.00, 80);
		double ta3 = lineItem3.getTaxAmount(0.05, 3.00, 80);
		double da3 = lineItem3.getDiscountAmount(3.00, 80, 0.0);
		double nc3 = lineItem3.getNetCost(0.05, 3.00, 80, 0.0);
		Input lineItem4 = new Input(4, "Anne's", "Food Products", "Cheesecake Supplies", 4.00, 10, 0.0, 0.05);
		inputDescription.add(lineItem4);
		double gc4 = lineItem4.getGrossCost(4.00, 10);
		double ta4 = lineItem4.getTaxAmount(0.05, 4.00, 10);
		double da4 = lineItem4.getDiscountAmount(4.00, 10, 0.0);
		double nc4 = lineItem4.getNetCost(0.05, 4.00, 10, 0.0);
		Input lineItem5 = new Input(5, "Banana Republic", "Apparel Products", "Shirts", 4.00, 50, 0.0, 0.05);
		inputDescription.add(lineItem5);
		double gc5 = lineItem5.getGrossCost(4.00, 50);
		double ta5 = lineItem5.getTaxAmount(0.05, 4.00, 50);
		double da5 = lineItem5.getDiscountAmount(4.00, 50, 0.0);
		double nc5 = lineItem5.getNetCost(0.05, 4.00, 50, 0.0);
		Input lineItem6 = new Input(6, "Harry's", "Tea Products", "Passionfruit Tea Supplies", 4.50, 0, 0.0, 0.05);
		inputDescription.add(lineItem6);
		double gc6 = lineItem6.getGrossCost(4.50, 0);
		double ta6 = lineItem6.getTaxAmount(0.05, 4.50, 0);
		double da6 = lineItem6.getDiscountAmount(4.50, 0, 0.0);
		double nc6 = lineItem6.getNetCost(0.05, 4.50, 0, 0.0);
		Input lineItem7 = new Input(7, "Banana Republic", "Apparel Products", "Hats", 2.80, 80, 0.0, 0.05);
		inputDescription.add(lineItem7);
		double gc7 = lineItem7.getGrossCost(2.80, 80);
		double ta7 = lineItem7.getTaxAmount(0.05, 2.80, 80);
		double da7 = lineItem7.getDiscountAmount(2.80, 80, 0.0);
		double nc7 = lineItem7.getNetCost(0.05, 2.80, 80, 0.0);
		Input lineItem8 = new Input(8, "Anne's", "Food Products", "Butter Biscuits", 1.50, 20, 0.0, 0.05);
		inputDescription.add(lineItem8);
		double gc8 = lineItem8.getGrossCost(1.50, 20);
		double ta8 = lineItem8.getTaxAmount(0.05, 1.50, 20);
		double da8 = lineItem8.getDiscountAmount(1.50, 20, 0.0);
		double nc8 = lineItem8.getNetCost(0.05, 1.50, 20, 0.0);
		//End Dummy information
		
		//Map to capture the gross costs of each line item
		Map<Integer, Double> grossCostTotal = new HashMap<>(); //Declaring map for Total Gross Cost
		grossCostTotal.put(lineItemID, gc); //Initializing map 
		//Total Gross Cost calculation with the dummy information from above
		grossCostTotal.put(1, gc1);
		grossCostTotal.put(2, gc2);
		grossCostTotal.put(3, gc3);
		grossCostTotal.put(4, gc4);
		grossCostTotal.put(5, gc5);
		grossCostTotal.put(6, gc6);
		grossCostTotal.put(7, gc7);
		grossCostTotal.put(8, gc8);
		
		//Map to capture the tax amount of each line item
		Map<Integer, Double> taxAmountTotal = new HashMap<>(); //Declaring map for Total Tax Amount
		taxAmountTotal.put(lineItemID, ta); //Initializing map
		//Total Tax Amount calculation with the dummy information from above
		taxAmountTotal.put(1, ta1);
		taxAmountTotal.put(2, ta2);
		taxAmountTotal.put(3, ta3);
		taxAmountTotal.put(4, ta4);
		taxAmountTotal.put(5, ta5);
		taxAmountTotal.put(6, ta6);
		taxAmountTotal.put(7, ta7);
		taxAmountTotal.put(8, ta8);
		
		//Map to capture the discount amount of each line item
		Map<Integer, Double> discountAmountTotal = new HashMap<>(); //Declaring map for Total Discount Amount
		discountAmountTotal.put(lineItemID, da); //Initializing map
		//Total Discount Amount calculation with the dummy information from above
		discountAmountTotal.put(1, da1);
		discountAmountTotal.put(2, da2);
		discountAmountTotal.put(3, da3);
		discountAmountTotal.put(4, da4);
		discountAmountTotal.put(5, da5);
		discountAmountTotal.put(6, da6);
		discountAmountTotal.put(7, da7);
		discountAmountTotal.put(8, da8);
		
		//Map to capture the net cost amount of each line item
		Map<Integer, Double> netCostTotal = new HashMap<>(); //Declaring map for Total Net Cost Amount
		netCostTotal.put(lineItemID, nc); //Initializing map
		//Total Net Cost calculation with the dummy information from above
		netCostTotal.put(1, nc1);
		netCostTotal.put(2, nc2);
		netCostTotal.put(3, nc3);
		netCostTotal.put(4, nc4);
		netCostTotal.put(5, nc5);
		netCostTotal.put(6, nc6);
		netCostTotal.put(7, nc7);
		netCostTotal.put(8, nc8);
		
		int choice; //Used for the menu entering options (ie, 1,2,3,etc.)
		do 
		{
			
			//Input Menu Display
			System.out.println("----------------------------");
			System.out.println("|       Inputs Menu        |");
			System.out.println("----------------------------");
			System.out.println("Select your option (only enter the below listed integers, ie, 1,2,3,4,5,or 6)");
			System.out.println("1. Add an input");
			System.out.println("2. Display each line item");
			System.out.println("3. Search for a line item");
			System.out.println("4. Delete a line item");
			System.out.println("5. Update a line item");
			System.out.println("6. Return to Main Menu");
			System.out.println("----------------------------");
			choice = c.nextInt();
		
			switch (choice) //Goes through each option the user enters until quit, ie, when 6 is entered.
			{
			
				case 1: //Adds a line item of all the information about an input
					System.out.print("Enter the line item ID for this input (as a nonnegative integer, ie, 0,1,2,18, etc.): ");
					lineItemID = c.nextInt();
					boolean exists = false;
					while (!exists) //Checks if the line item is already existing
					{
						
						if (lineItemID == lineItem.getLineItemID() || lineItemID == lineItem1.getLineItemID() || 
						lineItemID == lineItem2.getLineItemID() || lineItemID == lineItem3.getLineItemID() ||
						lineItemID == lineItem4.getLineItemID() || lineItemID == lineItem5.getLineItemID() ||
						lineItemID == lineItem6.getLineItemID() || lineItemID == lineItem7.getLineItemID() ||
						lineItemID == lineItem8.getLineItemID())
						{
						
							System.out.println("Line item already exists. Please try again.");
							System.out.print("Enter the line item ID for this input: ");
							lineItemID = c.nextInt();
						
						}
						else 
						{
							
							exists = true;
					
						}
						
					}
					System.out.print("Enter the name of the vendor receiving the input from: ");
					name = s.nextLine();
					System.out.print("Enter the category of goods/services the vendor is selling, ie, food products, tea products, etc.: ");
					category = s.nextLine();
					System.out.print("Enter the particular input that the vendor is providing, ie, green tea formula, black tea formula, etc.: ");
					input = s.nextLine();
					System.out.print("Enter the unit cost of the input (as a nonnegative number, ie, 1.5, 30.0, etc.): $");
					inputCost = c.nextDouble();
					System.out.print("Enter the quantity of the input (as a nonnegative integer, ie, 0, 10, 100, etc.): ");
					inputQuantity = c.nextInt();
					System.out.print("Enter the discount percentage on this input (write as a positive decimal less than 1, ie, 0.05, 0.1, etc.): ");
					inputDiscount = c.nextDouble();
					System.out.print("Enter the tax rate applied to this input (write as a positive decimal less than 1, ie, 0.05, 0.1, etc.): ");
					taxRate = c.nextDouble();
					//Creates a new line item once all the parameters have been entered
					lineItem = new Input(lineItemID, name, category, input, inputCost, inputQuantity, inputDiscount, taxRate);
					//Adds the line itme to linked list for displaying
					inputDescription.add(lineItem);
					
					//Calculates the gross cost for the quantity of inputs purchased
					gc = lineItem.getGrossCost(inputCost, inputQuantity); 
					grossCostTotal.put(lineItemID, gc); //Adds to the gross cost map
					//Calculates the tax amount applied for the quantity of inputs purchased
					ta = lineItem.getTaxAmount(taxRate, inputCost, inputQuantity); 
					taxAmountTotal.put(lineItemID, ta); //Adds to the tax amount map
					//Calculates the discount amount applied for the quantity of inputs purchased
					da = lineItem.getDiscountAmount(inputCost, inputQuantity, inputDiscount);
					discountAmountTotal.put(lineItemID, da); //Adds to the discount amount map
					//Calculates the net cost for the quantity of inputs purchased
					nc = lineItem.getNetCost(taxRate, inputCost, inputQuantity, inputDiscount);
					netCostTotal.put(lineItemID, nc); //Adds to the net cost map
				break;
				case 2:
					//Input Report Header
					System.out.println("-------------------------------------------------");
					System.out.println("|                Inputs Report                  |");
					System.out.println("-------------------------------------------------");
					System.out.print("-------------------------------------------------");
					System.out.print("-------------------------------------------------");;
					System.out.print("-------------------------------------------------");
					System.out.print("-------------------------------------------------\n");
					header.displayHeaders();
					System.out.print("-------------------------------------------------");
					System.out.print("-------------------------------------------------");;
					System.out.print("-------------------------------------------------");
					System.out.print("-------------------------------------------------\n");
					
					//Creates an iterator to go through the linked list to display all the line items
					Iterator<Input> i = inputDescription.iterator();
					while(i.hasNext()) 
					{
					
						Input v = i.next();
						System.out.println(v);
					
					}
					System.out.println("-------------------------------------------------");
					//Sums up all the values in the gross cost map to get the total gross cost
					//for all inputs purchased
					Set<Integer> keySet1 = grossCostTotal.keySet();
					double grossCostSum = 0.0;
					for (int key : keySet1)
					{
		
						double value = grossCostTotal.get(key);
						grossCostSum += value;
		
					}
					this.totalGrossCost = grossCostSum;
					System.out.printf("Total Gross Cost: $%.2f%n", grossCostSum);
					//Sums up all the values in the discount amount map to get the total
					//discount amount for all inputs purchased
					Set<Integer> keySet2 = discountAmountTotal.keySet();
					double discountAmountSum = 0.0;
					for (int key : keySet2)
					{
		
						double value = discountAmountTotal.get(key);
						discountAmountSum += value;
		
					}
					this.totalDiscountAmount = discountAmountSum;
					System.out.printf("Total Discount Amount: $%.2f%n", discountAmountSum);
					//Sums up all the values in the tax amount map to get the total discount
					//amount for all inputs purchased
					Set<Integer> keySet3 = taxAmountTotal.keySet();
					double taxAmountSum = 0.0;
					for (int key : keySet3)
					{
		
						double value = taxAmountTotal.get(key);
						taxAmountSum += value;
		
					}
					this.totalTaxAmount = taxAmountSum;
					System.out.printf("Total Tax Amount: $%.2f%n", taxAmountSum);
					//Sums up all the values in the net cost amount map to get the total 
					//net cost amount for all inputs purchased
					Set<Integer> keySet4 = netCostTotal.keySet();
					double netCostSum = 0.0;
					for (int key : keySet4)
					{
		
						double value = netCostTotal.get(key);
						netCostSum += value;
		
					}
					this.totalNetCost = netCostSum;
					//Asks the user if they want to export the total costs as a report
					System.out.printf("Total Net Cost: $%.2f%n", netCostSum);
					System.out.print("Would you like to export the cost report and exit the input menu? (Y)es | (N)o: ");
					char export = s.next().charAt(0);
					s.nextLine();
					boolean done = false;
					while (!done)
					{
					
						if (export == 'Y' || export == 'y')
						{
					
							displayCostTotals();
							return;
					
						}
						else if (export == 'N' || export == 'n')
						{
					
							System.out.println("Please continue navigating the menu.");
							done = true; 
					
						}
						else
						{
					
							System.out.println("Not a valid option. Please try again.");
							System.out.println("Would you like to export the cost report? (Y)es | (N)o: ");
							export = s.next().charAt(0);
							s.nextLine();
					
						}
						
					}			
				break;
				case 3: 
					//Search for a particular existing line item
					boolean found = false;
					System.out.print("Enter the line item ID of the line item to search for: ");
					int id = c.nextInt();
					System.out.print("-------------------------------------------------");
					System.out.print("-------------------------------------------------");;
					System.out.print("-------------------------------------------------");
					System.out.print("-------------------------------------------------\n");
					header.displayHeaders();
					System.out.print("-------------------------------------------------");
					System.out.print("-------------------------------------------------");;
					System.out.print("-------------------------------------------------");
					System.out.print("-------------------------------------------------\n");
				    i = inputDescription.iterator();
				    //Checks if the line item is in the linked list
					while(i.hasNext()) 
					{
					
						Input v = i.next();
						if (v.getLineItemID() == id)
						{
							
							System.out.println(v);
							found = true;
						
						}
					
					}
					if (!found) 
					{
					
						System.out.println("Line item not found");
					
					}
					System.out.println("-------------------------------------------------");
				break;
				case 4: 
					//Deletes a line item and updates the total cost amounts
					found = false;
					System.out.print("Enter the line item ID of the line item to search for: ");
					id = c.nextInt();
				    i = inputDescription.iterator();
					while(i.hasNext()) 
					{
					
						Input v = i.next();
						if (v.getLineItemID() == id)
						{
							
							this.totalGrossCost = totalGrossCost - grossCostTotal.get(v.getLineItemID());
							this.totalTaxAmount = totalTaxAmount - taxAmountTotal.get(v.getLineItemID());
							this.totalDiscountAmount = totalDiscountAmount - discountAmountTotal.get(v.getLineItemID());
							this.totalNetCost = totalNetCost - netCostTotal.get(v.getLineItemID());
							
							i.remove();
							grossCostTotal.remove(v.getLineItemID());
							taxAmountTotal.remove(v.getLineItemID());
							discountAmountTotal.remove(v.getLineItemID());
							netCostTotal.remove(v.getLineItemID());
							found = true;
						
						}
					
					}
					if (!found) 
					{
						
						System.out.println("-------------------------------------------------");
						System.out.println("Line item not found");
						System.out.println("-------------------------------------------------");
				
					}
					else
					{
					
						System.out.println("-------------------------------------------------");
						System.out.println("Line item deleted successfully");
						System.out.println("-------------------------------------------------");
					
					}
				break;
				case 5: 
					//Updates the line item that the user wants to change
					found = false;
					System.out.print("Enter the line item ID of the line item to update: ");
					id = c.nextInt();
				    ListIterator<Input> descriptionIter = inputDescription.listIterator();
					while(descriptionIter.hasNext()) 
					{
					
						Input v = descriptionIter.next();
						if (v.getLineItemID() == id)
						{
							
							System.out.print("Enter the new name of the vendor ");
							System.out.print("(enter the same name if no change): ");
							name = s.nextLine();
							System.out.print("Enter the new category of goods/services the vendor is selling, ie, food products, tea products, etc. ");
							System.out.print("(enter the same category if no change): ");
							category = s.nextLine();
							System.out.print("Enter the new input that the vendor is selling, ie, green tea formula, black tea formula, etc.");
							System.out.print("(enter the same input if no change): ");
						    input = s.nextLine();
							System.out.print("Enter the updated cost (as a nonnegative number, ie, 1.5, 30.0, etc.) of the input ");
							System.out.print("(enter the same cost if no change): $");
							inputCost = c.nextDouble();
							System.out.print("Enter the updated quantity (as a nonnegative integer, ie, 0, 10, 100, etc.) of the input ");
							System.out.print("(enter the same quantity if no change): ");
							inputQuantity = c.nextInt();
							System.out.print("Enter the discount percentage (as a positive decimal less than 1, ie, 0.05, 0.1, etc.) on this input ");
							System.out.print("(enter the same discount if no change): ");
							inputDiscount = c.nextDouble();
							System.out.print("Enter the tax rate (as a positive decimal less than 1, ie, 0.05, 0.1, etc.) applied to this input ");
							System.out.print("(enter the same tax rate if no change): ");
							taxRate = c.nextDouble();
							lineItem = new Input(id, name, category, input, inputCost, inputQuantity, inputDiscount, taxRate);
							descriptionIter.set(lineItem);
							
							gc = lineItem.getGrossCost(inputCost, inputQuantity);
							ta = lineItem.getTaxAmount(taxRate, inputCost, inputQuantity);
							da = lineItem.getDiscountAmount(inputCost, inputQuantity, inputDiscount);
							nc = lineItem.getNetCost(taxRate, inputCost, inputQuantity, inputDiscount);
							grossCostTotal.replace(v.getLineItemID(), gc);
							taxAmountTotal.replace(v.getLineItemID(), ta);
							discountAmountTotal.replace(v.getLineItemID(), da);
							netCostTotal.replace(v.getLineItemID(), nc);
							//
							this.totalGrossCost = gc;
							this.totalTaxAmount = ta;
							this.totalDiscountAmount = da;
							this.totalNetCost = nc;
							//
							found = true;
							
						}
						
					}
					
					if (!found) 
					{
					
						System.out.println("-------------------------------------------------");
						System.out.println("Line item not found");
						System.out.println("-------------------------------------------------");
					
					}
					else
					{
						
						System.out.println("-------------------------------------------------");
						System.out.println("Line item updated successfully");
						System.out.println("-------------------------------------------------");
						
					}
				break;
				default:
					if (choice != 6) //Return to main menu
					{
						
						System.out.println("Not a valid choice. Please try again.");
						
					}
				break;
				
			}
		
		}while (choice != 6); return;
	
	}

	/**
		Report to display the total cost totals when the user wants to export the report.
		@param none
		@return non
	*/

	public void displayCostTotals() {
		
		System.out.println("-----------------------------");
		System.out.println("Cost Totals");
		System.out.println("-----------------------------");
		System.out.printf("Total Gross Cost: $%.2f%n", totalGrossCost);
		System.out.printf("Total Discount Amount: $%.2f%n", totalDiscountAmount);
		System.out.printf("Total Tax Amount: $%.2f%n", totalTaxAmount);
		System.out.printf("Total Net Cost: $%.2f%n", totalNetCost);
		System.out.println("-----------------------------");

	}
	
	/**
		Gets the total gross cost of all the inputs purchased
		@return the total gross cost
	*/
	
	
	public double getTotalGrossCost() {
	
		return totalGrossCost;
	
	}
	
	/**	
		Gets the total tax amount of all the inputs purchased
		@return the total tax amount
	*/
	
	public double getTotalTaxAmount() {
	
		return totalTaxAmount;
	
	}
	
	/**
		Gets the total discount amount applied to all of the inputs purchased
		@return the total discount amount
	*/
	
	public double getTotalDiscountAmount() {
	
		return totalDiscountAmount;
	
	}
	
	/**
		Gets the total net cost of all the inputs purchased
		@return the total net cost
	*/
	
	public double getTotalNetCost() {
	
		return totalNetCost;
	
	}

}

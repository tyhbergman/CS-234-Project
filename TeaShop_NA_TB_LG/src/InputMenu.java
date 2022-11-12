import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class InputMenu {
	
	InputMenu(){
		
	}

	public static void inputMenu() {
		
		List<Input> inputDescription = new LinkedList<Input>();
		Scanner c = new Scanner(System.in);
		Scanner s = new Scanner(System.in);
		int lineItemID = 0;
		String name = "Generic Vendor";
		String category = "Generic Product Category";
		String input = "Generic Input";
		double inputCost = 0.00;
		int inputQuantity = 0;
		double inputDiscount = 0.00;
		double taxRate = 0.00;
		Input lineItem = new Input(lineItemID, name, category, input, inputCost, inputQuantity, inputDiscount, taxRate);
		inputDescription.add(lineItem);
		
		double gc = 0.0;
		double ta = 0.0;
		double da = 0.0;
		double nc = 0.0;
		Map<Integer, Double> grossCostTotal = new HashMap<>();
		grossCostTotal.put(lineItemID, gc);
		Map<Integer, Double> taxAmountTotal = new HashMap<>();
		taxAmountTotal.put(lineItemID, ta);
		Map<Integer, Double> discountAmountTotal = new HashMap<>();
		discountAmountTotal.put(lineItemID, da);
		Map<Integer, Double> netCostTotal = new HashMap<>();
		netCostTotal.put(lineItemID, nc);

		int choice;
		do 
		{
		
			System.out.println("----------------------------");
			System.out.println("|       Inputs Menu        |");
			System.out.println("----------------------------");
			System.out.println("Select your option");
			System.out.println("1. Add an input");
			System.out.println("2. Display each line item");
			System.out.println("3. Search for a line item");
			System.out.println("4. Delete a line item");
			System.out.println("5. Update a line item");
			System.out.println("0. Exit");
			System.out.println("----------------------------");
			choice = c.nextInt();
		
			switch (choice)
			{
			
				case 1: 
					System.out.print("Enter the line item ID for this input (as a nonnegative integer, ie, 0,1,2, 18, etc.): ");
					lineItemID = c.nextInt();
					if (lineItemID == lineItem.getLineItemID())
					{
						
						System.out.println("Line item already exists. Please try again.");
						System.out.print("Enter the line item ID for this input: ");
						lineItemID = c.nextInt();
						
					}
					System.out.print("Enter the name of the vendor receiving the input from: ");
					name = s.nextLine();
					System.out.print("Enter the category of goods/services the vendor is selling: ");
					category = s.nextLine();
					System.out.print("Enter the particular input that the vendor is providing: ");
					input = s.nextLine();
					System.out.print("Enter the cost of the input: $");
					inputCost = c.nextDouble();
					System.out.print("Enter the quantity of the input: ");
					inputQuantity = c.nextInt();
					System.out.print("Enter the discount percentage on this input (write as a decimal, ie, 0.05, 0.1, etc.): ");
					inputDiscount = c.nextDouble();
					System.out.print("Enter the tax rate applied to this input (write as a decimal, ie, 0.05, 0.1, etc.): ");
					taxRate = c.nextDouble();
					lineItem = new Input(lineItemID, name, category, input, inputCost, inputQuantity, inputDiscount, taxRate);
					inputDescription.add(lineItem);
					
					gc = lineItem.getGrossCost(inputCost, inputQuantity, inputDiscount);
					grossCostTotal.put(lineItemID, gc);
					ta = lineItem.getTaxAmount(taxRate, inputCost, inputQuantity, inputDiscount);
					taxAmountTotal.put(lineItemID, ta);
					da = lineItem.getDiscountAmount(inputCost, inputDiscount);
					discountAmountTotal.put(lineItemID, da);
					nc = lineItem.getNetCost(taxRate, inputCost, inputQuantity, inputDiscount);
					netCostTotal.put(lineItemID, nc);
				break;
				case 2:
					System.out.println("-------------------------------------------------");
					System.out.println("|                Inputs Report                  |");
					System.out.println("-------------------------------------------------");
					System.out.println("-------------------------------------------------");
					System.out.print("ID # | ");
					System.out.print("Vendor Name \t|");
					System.out.print("Vendor Category \t|");
					System.out.print("Input \t|");
					System.out.print("Cost ($) \t|");
					System.out.print("Quantity \t|");
					System.out.print("Discount (%) \t|");
					System.out.print("Tax Rate (%) \t|");
					System.out.print("Discount Amount ($) \t|");
					System.out.print("Gross Cost ($) \t|");
					System.out.print("Tax Amount ($) \t|");
					System.out.print("Net Cost ($) \n");
					System.out.println("-------------------------------------------------");
					
					Iterator<Input> i = inputDescription.iterator();
					while(i.hasNext()) 
					{
					
						Input v = i.next();
						System.out.println(v);
					
					}
					System.out.println("-------------------------------------------------");

					Set<Integer> keySet1 = grossCostTotal.keySet();
					double grossCostSum = 0.0;
					for (int key : keySet1)
					{
		
						double value = grossCostTotal.get(key);
						grossCostSum += value;
		
					}
					System.out.printf("Total Gross Cost: $%.2f%n", grossCostSum);
					
					Set<Integer> keySet2 = discountAmountTotal.keySet();
					double discountAmountSum = 0.0;
					for (int key : keySet2)
					{
		
						double value = discountAmountTotal.get(key);
						discountAmountSum += value;
		
					}
					System.out.printf("Total Discount Amount: $%.2f%n", discountAmountSum);
					
					Set<Integer> keySet3 = taxAmountTotal.keySet();
					double taxAmountSum = 0.0;
					for (int key : keySet3)
					{
		
						double value = taxAmountTotal.get(key);
						taxAmountSum += value;
		
					}
					System.out.printf("Total Tax Amount: $%.2f%n", taxAmountSum);
					
					Set<Integer> keySet4 = netCostTotal.keySet();
					double netCostSum = 0.0;
					for (int key : keySet4)
					{
		
						double value = netCostTotal.get(key);
						netCostSum += value;
		
					}
					System.out.printf("Total Net Cost: $%.2f%n", netCostSum);
				break;
				case 3: 
					boolean found = false;
					System.out.print("Enter the line item ID of the line item to search for: ");
					int id = c.nextInt();
					System.out.println("-------------------------------------------------");
					System.out.print("ID # | ");
					System.out.print("Vendor Name \t|");
					System.out.print("Vendor Category \t|");
					System.out.print("Input \t|");
					System.out.print("Cost ($) \t|");
					System.out.print("Quantity \t|");
					System.out.print("Discount (%) \t|");
					System.out.print("Tax Rate (%) \t|");
					System.out.print("Discount Amount ($) \t|");
					System.out.print("Gross Cost ($) \t|");
					System.out.print("Tax Amount ($) \t|");
					System.out.print("Net Cost ($) \n");
					System.out.println("-------------------------------------------------");
				    i = inputDescription.iterator();
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
					found = false;
					System.out.print("Enter the line item ID of the line item to search for: ");
					id = c.nextInt();
				    i = inputDescription.iterator();
					while(i.hasNext()) 
					{
					
						Input v = i.next();
						if (v.getLineItemID() == id)
						{
							
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
							System.out.print("Enter the new category of goods/services the vendor is selling ");
							System.out.print("(enter the same category if no change): ");
							category = s.nextLine();
							System.out.print("Enter the new input that the vendor is selling ");
							System.out.print("(enter the same input if no change): ");
						    input = s.nextLine();
							System.out.print("Enter the updated cost of the input ");
							System.out.print("(enter the same cost if no change): $");
							inputCost = c.nextDouble();
							System.out.print("Enter the updated quantity of the input ");
							System.out.print("(enter the same quantity if no change): ");
							inputQuantity = c.nextInt();
							System.out.print("Enter the discount percentage (as a decimal) on this input ");
							System.out.print("(enter the same discount if no change): ");
							inputDiscount = c.nextDouble();
							System.out.print("Enter the tax rate (as a decimal) applied to this input ");
							System.out.print("(enter the same tax rate if no change): ");
							taxRate = c.nextDouble();
							lineItem = new Input(id, name, category, input, inputCost, inputQuantity, inputDiscount, taxRate);
							descriptionIter.set(lineItem);
							
							gc = lineItem.getGrossCost(inputCost, inputQuantity, inputDiscount);
							ta = lineItem.getTaxAmount(taxRate, inputCost, inputQuantity, inputDiscount);
							da = lineItem.getDiscountAmount(inputCost, inputDiscount);
							nc = lineItem.getNetCost(taxRate, inputCost, inputQuantity, inputDiscount);
							grossCostTotal.replace(v.getLineItemID(), gc);
							taxAmountTotal.replace(v.getLineItemID(), ta);
							discountAmountTotal.replace(v.getLineItemID(), da);
							netCostTotal.replace(v.getLineItemID(), nc);
							found = true;
						
						}
						
					}
					
					if (!found) 
					{
					
						System.out.println("Line item not found");
					
					}
					else
					{
					
						System.out.println("Line item updated successfully");
					
					}
				break;
				
			}
		
		}while (choice != 0);
	
	}
}

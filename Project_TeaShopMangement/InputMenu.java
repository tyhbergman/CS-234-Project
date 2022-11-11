/*
Input Tester
**/

import java.util.*;

public class InputMenu {

	public InputMenu() {}
	
		
		public void inputMenu() {
		
		List<Input> inputDescription = new LinkedList<Input>();
		Scanner c = new Scanner(System.in);
		Scanner s = new Scanner(System.in);
		int lineItemID = 0;
		String name = "Generic Vendor";
		String category = "Generic Product Category";
		String input = "Generic Input";
		String inputType = "T";
		double inputCost = 0.00;
		int inputQuantity = 0;
		double inputDiscount = 0.00;
		double taxRate = 0.00;
		Input lineItem = new Input(lineItemID, name, category, input, inputType, inputCost, inputQuantity, inputDiscount, taxRate);
		inputDescription.add(lineItem);
		double gc = 0.0;
		double ta = 0.0;
		double nc = 0.0;
		double da = 0.0;
		List<Double> grossCostList = new LinkedList<Double>();
		grossCostList.add(gc);
		List<Double> taxAmountList = new LinkedList<Double>();
		taxAmountList.add(ta);
		List<Double> netCostList = new LinkedList<Double>();
		netCostList.add(nc);
		List<Double> discountAmountList = new LinkedList<Double>();
		discountAmountList.add(da);
		double grossCostTotal = 0.0;
		double taxAmountTotal = 0.0;
		double netCostTotal = 0.0;
		double discountAmountTotal = 0.0;
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
					System.out.print("For the line item ID, enter 1 for the first line item and for further line items add 1 to the previous line item ID number entered.\n");
					System.out.print("Enter the line item ID for this input: ");
					lineItemID = c.nextInt();
					System.out.print("Enter the name of the vendor receiving the input from: ");
					name = s.nextLine();
					System.out.print("Enter the category of goods/services the vendor is selling: ");
					category = s.nextLine();
					System.out.print("Enter the particular input that the vendor is providing: ");
					input = s.nextLine();
					System.out.print("Enter the product type (T, F, A, or X): ");
					inputType = s.nextLine();
					System.out.print("Enter the cost of the input: $");
					inputCost = c.nextDouble();
					System.out.print("Enter the quantity of the input: ");
					inputQuantity = c.nextInt();
					System.out.print("Enter the discount percentage on this input (write as a decimal): ");
					inputDiscount = c.nextDouble();
					System.out.print("Enter the tax rate applied to this input (write as a decimal): ");
					taxRate = c.nextDouble();
					lineItem = new Input(lineItemID, name, category, input, inputType, inputCost, inputQuantity, inputDiscount, taxRate);
					inputDescription.add(lineItem);
					gc = lineItem.getGrossCost(inputCost, inputQuantity, inputDiscount);
					grossCostList.add(gc);
					ta = lineItem.getTaxAmount(taxRate, inputCost, inputQuantity, inputDiscount);
					taxAmountList.add(ta);
					nc = lineItem.getNetCost(taxRate, inputCost, inputQuantity, inputDiscount);
					netCostList.add(nc);
					da = lineItem.getDiscountAmount(inputCost, inputDiscount);
					discountAmountList.add(da);
					
					double temp = 0.0;
					for (int count = 0; count < grossCostList.size(); count++)
					{
		
						temp += grossCostList.get(count);
		
					}
				    grossCostTotal = temp;
		
					double temp1 = 0.0;
					for (int count = 0; count < taxAmountList.size(); count++)
					{
		
						temp1 += taxAmountList.get(count);
		
					}
					taxAmountTotal = temp1;
	
					double temp2 = 0.0;
					for (int count = 0; count < netCostList.size(); count++)
					{
		
						temp2 += netCostList.get(count);
						
					}
					netCostTotal = temp2;
					
					double temp3 = 0.0;
					for (int count = 0; count < discountAmountList.size(); count++)
					{
					
						temp3 += discountAmountList.get(count);

					}
					discountAmountTotal = temp3;
				break;
				case 2:
					System.out.println("-------------------------------------------------");
					System.out.println("|                Inputs Report                  |");
					System.out.println("-------------------------------------------------");
					System.out.println("-------------------------------------------------");
					System.out.print("ID # \t| ");
					System.out.print("\tVendor Name \t| ");
					System.out.print("\tVendor Category \t| ");
					System.out.print("\tInput \t| ");
					System.out.print("\tInput Type \t| ");
					System.out.print("\tCost ($) \t| ");
					System.out.print("\tQuantity \t| ");
					System.out.print("\tDiscount (%) \t| ");
					System.out.print("\tTax Rate (%) \t| ");
					System.out.print("\tDiscount Amount ($) \t| ");
					System.out.print("\tGross Cost ($) \t| ");
					System.out.print("\tTax Amount ($) \t| ");
					System.out.print("\tNet Cost ($) \n");
					System.out.println("-------------------------------------------------");
					Iterator<Input> i = inputDescription.iterator();
					while(i.hasNext()) 
					{
					
						Input v = i.next();
						System.out.println(v);
					
					}
					System.out.println("-------------------------------------------------");
					
					System.out.println("Total Discout Amount: $" + discountAmountTotal);
					System.out.println("Total Gross Cost: $" + grossCostTotal);
					System.out.println("Total Tax Amount: $" + taxAmountTotal);
					System.out.println("Total Net Cost: $" + netCostTotal);
					System.out.println();
					System.out.println(discountAmountList);
					System.out.println(grossCostList);
					System.out.println(taxAmountList);
					System.out.println(netCostList);
				break;
				case 3: 
					boolean found = false;
					System.out.print("Enter the line item ID of the line item to search for: ");
					int id = c.nextInt();
					System.out.println("-------------------------------------------------");
					System.out.print("ID # \t| ");
					System.out.print("\tVendor Name \t| ");
					System.out.print("\tVendor Category \t| ");
					System.out.print("\tInput \t| ");
					System.out.print("\tInput Type \t| ");
					System.out.print("\tCost ($) \t| ");
					System.out.print("\tQuantity \t| ");
					System.out.print("\tDiscount (%) \t| ");
					System.out.print("\tTax Rate (%) \t| ");
					System.out.print("\tDiscount Amount ($) \t| ");
					System.out.print("\tGross Cost ($) \t| ");
					System.out.print("\tTax Amount ($) \t| ");
					System.out.print("\tNet Cost ($) \n");
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
							found = true;
						
						}
					
					}
					if (!found) 
					{
					
						System.out.println("Line item not found");
					
					}
					else
					{
					
						System.out.println("Line item deleted successfully");
					
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
						    System.out.print("Enter the new input type (T, F, A, or X): ");
						    System.out.print("(enter the same input type if no change): ");
							inputType = s.nextLine();
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
							lineItem = new Input(lineItemID, name, category, input, inputType, inputCost, inputQuantity, inputDiscount, taxRate);
							descriptionIter.set(lineItem);
							//descriptionIter.set(new Input(id, name, category, input, inputCost, inputQuantity, inputDiscount, taxRate));
						
							gc = lineItem.getGrossCost(inputCost, inputQuantity, inputDiscount);
							grossCostList.add(gc);
							ta = lineItem.getTaxAmount(taxRate, inputCost, inputQuantity, inputDiscount);
							taxAmountList.add(ta);
							nc = lineItem.getNetCost(taxRate, inputCost, inputQuantity, inputDiscount);
							netCostList.add(nc);
					
							double uc = 0.0;
							for (int count = 0; count < grossCostList.size(); count++)
							{
					
								if (count == id)
								{
						
									uc = grossCostList.set(count, gc);
									//grossCostList.add(uc);
						
								}
		
							}
							grossCostList.add(uc);
							
							double ut = 0.0;
							for (int count = 0; count < taxAmountList.size(); count++)
							{
	
								if (count == id)
								{
						
									ut = taxAmountList.set(count, ta);
									//taxAmountList.add(ut);
						
								}
			
							}
							taxAmountList.add(ut);
							
							double un = 0.0;
							for (int count = 0; count < netCostList.size(); count++)
							{
					
								if (count == id)
								{
									
									un = netCostList.set(count, nc);
									//netCostList.add(un);
							
								}
						
							}
							netCostList.add(un);
							
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
	
	/**
	public static void main(String[] args) {
	
		Menu();
	
	
	}
	*/
	

}

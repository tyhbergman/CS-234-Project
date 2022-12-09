/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package normal;

import java.util.Scanner;
import java.util.Set;
import java.util.Map.Entry;

public class ProductMenu {
	
	public void productMenu(Register register) {
		
		int x; //counter variable
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("\tWelcome to the Product Management Menu!");
		System.out.println();
		
		System.out.println("1.) Add Product");
		System.out.println("2.) Get Product Info");
		System.out.println("3.) Modify Product");
		System.out.println("4.) Delete Product");
		System.out.println("5.) Get Product Log");
		System.out.println("6.) Quit PMM");
		
		System.out.println();
		
		System.out.println("Select an option: ");
		
		x = scan.nextInt();
		
		switch (x) {
			case 1:
				chooseProductType(register);
				break;
			case 2:
				getProduct(register);
				break;
			case 3:
				modifyProduct(register);
				break;
			case 4:
				delete(register);
				break;
			case 5:
				getProductLog(register);
				//Don't call productMenu() in body of getProductLog()
				//so that getProductLog() can be called from elsewhere
				//Instead call productMenu() after as below
				productMenu(register);
				break;
			case 6:
				System.out.println("Exiting PMM.");
				System.out.println();
				break;
				
			default:
				break;
		}
	}
	
	public void getProductLog(Register register) {
		System.out.println("ID\tProduct Type\tProduct Name\t\tPrice\tQuantity");
		System.out.println("-------------------------------------------------------------------------------------------------------------");
		Set<Entry<Integer,Product>> entrySet = register.getProductList().entrySet();
		for (Entry<Integer, Product> entry : entrySet) {
			
			System.out.printf("%03d\t%s\t\t%.15s\t\t$%5.2f\t\t%d",
					entry.getKey(), entry.getValue().getProductType(), entry.getValue().getName(), 
					entry.getValue().getPrice(), entry.getValue().getQuantity());
			System.out.println();
		}
		System.out.println();
	}
	
	public void modifyProduct(Register register) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("What is the ID of the product you want to modify?");
		int x = scan.nextInt();
		
		if(register.getProductList().containsKey(x)) {
			System.out.println("Item to be changed: ");
			System.out.println("ID\tProduct Type\tProduct Name\t\tPrice\tQuantity");
			System.out.println("-------------------------------------------------------------------------------------------------------------");
			System.out.printf("%03d\t%s\t\t%.15s\t\t$%5.2f\t\t%d",
					register.getProductList().get(x).getId(), register.getProductList().get(x).getProductType(),
					register.getProductList().get(x).getName(), register.getProductList().get(x).getPrice(),
					register.getProductList().get(x).getQuantity());
			
			System.out.println();
			
			System.out.println("What needs to be modified?");
			System.out.println("1.) Name");
			System.out.println("2.) Quantity");
			System.out.println("3.) Price");
			System.out.println("4.) Tax Rate");
			System.out.println("5.) Discount");
			
			System.out.println("Option: ");
			int y = scan.nextInt();
			
			switch (y) {
			case 1:
				modifyName(register, x);
				break;
			case 2:
				modifyQuantity(register, x);
				break;
			case 3:
				modifyPrice(register, x);
				break;
			case 4:
				modifyTaxRate(register, x);
				break;
			case 5:
				modifyDiscount(register, x);
				break;
			default:
				System.out.println("Input error. Starting over.");
				modifyProduct(register);
				break;
				
			}
			
		} else {
			System.out.println("Error. Product ID not in system. Review product log to retrieve ID values.");
		}
		
		
		System.out.println();
		
		productMenu(register);
	}	

	private void modifyDiscount(Register register, int x) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Current discount: " + register.getProductList().get(x).getDiscount());
		System.out.println("Enter the new discount: ");
		double temp = scan.nextDouble();
		
		System.out.println("Is " + temp + "correct?");
		System.out.println("(Y)es | (N)o");
		scan.nextLine();
		char y = scan.next().charAt(0);
		scan.nextLine();
		
		switch (y) {
		case 'Y':
		case 'y':
			register.getProductList().get(x).setDiscount(temp);
			System.out.println("Discount successfully changed to " + register.getProductList().get(x).getDiscount());
			break;
		case 'N':
		case 'n':
			System.out.println("Starting over.");
			modifyDiscount(register, x);
			break;
		default:
			System.out.println("Input error. Starting over.");
			System.out.println();
			modifyDiscount(register, x);
			break;
		}
		
	}

	private void modifyTaxRate(Register register, int x) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Current tax rate is: " + register.getProductList().get(x).getTaxRate());
		System.out.println("Enter the new tax rate: ");
		double temp = scan.nextDouble();
		
		System.out.println("Is " + temp + "correct?");
		System.out.println("(Y)es | (N)o");
		scan.nextLine();
		char y = scan.next().charAt(0);
		scan.nextLine();
		
		switch (y) {
		case 'Y':
		case 'y':
			register.getProductList().get(x).setTaxRate(temp);
			System.out.println("Tax rate successfully changed to " + register.getProductList().get(x).getTaxRate());
			break;
		case 'N':
		case 'n':
			System.out.println("Starting over.");
			modifyTaxRate(register, x);
			break;
		default:
			System.out.println("Input error. Starting over.");
			System.out.println();
			modifyTaxRate(register, x);
			break;
		}
		
	}
	
	private void modifyPrice(Register register, int x) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Current price is: " + register.getProductList().get(x).getPrice());
		System.out.println("Enter the new price: ");
		double temp = scan.nextDouble();
		
		System.out.println("Is $" + temp + "correct?");
		System.out.println("(Y)es | (N)o");
		scan.nextLine();
		char y = scan.next().charAt(0);
		scan.nextLine();
		
		switch (y) {
		case 'Y':
		case 'y':
			register.getProductList().get(x).setPrice(temp);
			System.out.println("Price successfully changed to " + register.getProductList().get(x).getPrice());
			break;
		case 'N':
		case 'n':
			System.out.println("Starting over.");
			modifyPrice(register, x);
			break;
		default:
			System.out.println("Input error. Starting over.");
			System.out.println();
			modifyPrice(register, x);
			break;
		}
		
	}

	private void modifyQuantity(Register register, int x) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Current quantity is: " + register.getProductList().get(x).getQuantity());
		System.out.println("Enter the new quantity: ");
		int temp = scan.nextInt();
		
		System.out.println("Is " + temp + "correct?");
		System.out.println("(Y)es | (N)o");
		scan.nextLine();
		char y = scan.next().charAt(0);
		scan.nextLine();
		
		switch (y) {
		case 'Y':
		case 'y':
			register.getProductList().get(x).setQuantity(temp);
			System.out.println("Quantity successfully changed to " + register.getProductList().get(x).getQuantity());
			break;
		case 'N':
		case 'n':
			System.out.println("Starting over.");
			modifyQuantity(register, x);
			break;
		default:
			System.out.println("Input error. Starting over.");
			System.out.println();
			modifyQuantity(register, x);
			break;
		}
		
	}

	private void modifyName(Register register, int x) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Current name is: " + register.getProductList().get(x).getName());
		System.out.println("Enter the new name: ");
		String temp = scan.nextLine();
		
		System.out.println("Is " + temp + "correct?");
		System.out.println("(Y)es | (N)o");
		scan.nextLine();
		char y = scan.next().charAt(0);
		scan.nextLine();
		
		switch (y) {
		case 'Y':
		case 'y':
			register.getProductList().get(x).setName(temp);
			System.out.println("Name successfully changed to " + register.getProductList().get(x).getName());
			break;
		case 'N':
		case 'n':
			System.out.println("Starting over.");
			modifyName(register, x);
			break;
		default:
			System.out.println("Input error. Starting over.");
			System.out.println();
			modifyName(register, x);
			break;
		}
	}

	public void chooseProductType(Register register) {
		Scanner scan = new Scanner(System.in);
		char c;
		
		System.out.println("\tYou've chosen to add a product.");
		System.out.println();
		
		System.out.println("\tWhat is the type of product?");
		System.out.println("(T)ea | (F)ood | (A)pparel | (M)isc");
		
		c = scan.next().charAt(0);
		scan.nextLine();
		System.out.println();
		
		switch (c) {
			case 'T':
			case 't':
				System.out.println("\tYou've selected to add a tea product.");
				System.out.println();
				addTea(register);
				break;
				
			case 'F':
			case 'f':
				System.out.println("\tYou've selected to add a food product.");
				System.out.println();
				addFood(register);
				break;
			
			case 'A':
			case 'a':
				System.out.println("\tYou've selected to add an apparel product.");
				System.out.println();
				chooseApparelType(register);
				break;
			
			case 'M':
			case 'm':
				System.out.println("\tYou've selected to add a miscellaneous product.");
				System.out.println();
				addProduct(register);
				break;
			
			default:
				System.out.println("Input error, try again.");
				System.out.println();
				chooseProductType(register);
				break;
				
		}
		
	}
	
	public void addProduct(Register register) {
		Scanner scan = new Scanner(System.in);
		
		String name;
		int quantity;
		double price;
		
		char c; char d; char e;//switch variables
		
		System.out.println("Name of product: ");
		name = scan.nextLine();
		
		System.out.println("Quantity available: ");
		quantity = scan.nextInt();
		
		System.out.println("Price of product: ");
		price = scan.nextDouble();
		
		System.out.println();
		
		
		System.out.println("Is the following correct?");
		System.out.println("Name: " + name + " | Quantity: " + quantity + " | Price: $" + price);
		System.out.println();
		
		System.out.println("(Y)es | (N)o");
		c = scan.next().charAt(0);
		scan.nextLine();
		
		switch (c) {
		case 'Y':
		case 'y':
			
			System.out.println();
			System.out.println("Would you like to enter additional product information?");
			System.out.println("(Choosing \"No\" will give the product default values.)");
			System.out.println("(Y)es | (N)o");
			
			d = scan.next().charAt(0);
			scan.nextLine();
			
			switch (d) {
				case 'Y':
				case 'y':
					
					String productDesc;
					double discount;
					double taxRate;
					
					System.out.println("Enter the product description: ");
					productDesc = scan.nextLine();
					scan.nextLine();
					
					System.out.println("Enter the discount value for future sales (default discount is 0.15): ");
					discount = scan.nextDouble();
					
					System.out.println("Enter the tax rate (default tax rate is 1.08): ");
					taxRate = scan.nextDouble();
					
					Product tempProductLong = new Product(register, name, productDesc, price, discount, taxRate, quantity);
					
					System.out.println();
					System.out.println("Product created!");
					System.out.println();
					
					productMenu(register);
					break;
			
					
				case 'N':
				case 'n':
					
					Product tempProduct = new Product(register, name, price, quantity);
					
					System.out.println();
					System.out.println("Product created!");
					System.out.println();
					
					productMenu(register);
					break;
					
				default:
					System.out.println("Input error. Starting over.");
					System.out.println();
					addProduct(register);
					break;
				}
			break;
			
		case 'N':
		case 'n':
			System.out.println("Starting over.");
			System.out.println();
			chooseProductType(register);
			break;
		default:
			System.out.println("Input error. Starting over.");
			System.out.println();
			addProduct(register);
			break;
		}
	}
	
	public void getProduct(Register register) {
		Scanner scan = new Scanner(System.in);
		System.out.println("What is the ID of the product you want to review?");
		int x = scan.nextInt();
		
		if(register.getProductList().containsKey(x)) {
			System.out.println("ID\tProduct Type\tProduct Name\t\tPrice\t\tQuantity");
			System.out.println("-------------------------------------------------------------------------------------------------------------");
			System.out.printf("%03d\t%s\t\t%.15s\t\t$%5.2f\t\t%d",
					register.getProductList().get(x).getId(), register.getProductList().get(x).getProductType(),
					register.getProductList().get(x).getName(), register.getProductList().get(x).getPrice(),
					register.getProductList().get(x).getQuantity());
			
			System.out.println();
		} else {
			System.out.println("Error. Product ID not in system. Review product log to retrieve ID values.");
		}
		
		
		System.out.println();
		
		productMenu(register);
	}
	
	public void delete(Register register) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("What is the ID of the product you want to delete?");
		int x = scan.nextInt();
		scan.nextLine();
		
		if(register.getProductList().containsKey(x)) {
			
			System.out.println("Are you sure you really want to remove product " + register.getProductList().get(x).getName() + "?");
			System.out.println("(Y)es | (N)o");
			char c = scan.next().charAt(0);
			scan.nextLine();
			
			switch (c) {
			case 'Y':
			case 'y':
				System.out.println("Product " + register.getProductList().get(x).getName() + " succesfuly removed from system.");
				register.getProductList().remove(x);
				break;
			case 'N':
			case 'n':
				System.out.println("Deletion terminated.");
				break;
			default:
				System.out.println("Input error. Starting over.");
				delete(register);
				break;
			}
			
		} else {
			System.out.println("Error. Product ID not in system. Review product log to retrieve ID values.");
		}
		
		System.out.println();
		
		productMenu(register);
		
	}

	public void addTea(Register register) {
		Scanner scan = new Scanner(System.in);
		
		String name;
		int quantity;
		double price;
		
		char c; char d; char e;//switch variables
		
		System.out.println("Name of tea product: ");
		name = scan.nextLine();
		
		System.out.println("Quantity available: ");
		quantity = scan.nextInt();
		
		System.out.println("Price of tea product: ");
		price = scan.nextDouble();
		
		System.out.println();
		
		
		System.out.println("Is the following correct?");
		System.out.println("Name: " + name + " | Quantity: " + quantity + " | Price: $" + price);
		System.out.println();
		
		System.out.println("(Y)es | (N)o");
		
		c = scan.next().charAt(0);
		scan.nextLine();
		
		switch (c) {
		case 'Y':
		case 'y':
			
			System.out.println();
			System.out.println("Would you like to enter additional product information?");
			System.out.println("(Choosing \"No\" will give the product default values.)");
			System.out.println("(Y)es | (N)o");
			
			d = scan.next().charAt(0);
			scan.nextLine();
			
			switch (d) {
				case 'Y':
				case 'y':
					
					boolean caffeine = true;
					boolean sugarFree = false;
					boolean seasonal = false;
					String productDesc;
					double discount;
					double taxRate;
					
					System.out.println("Tea contains caffeine? (Y)es | (N)o");
					e = scan.next().charAt(0);
					scan.nextLine();
					switch(e) {
					case 'Y':
					case 'y':
							caffeine = true;
							break;
					case 'N':
					case 'n':
							caffeine = false;
							break;
					default:
						System.out.println("Input error. Starting over.");
						System.out.println();
						addTea(register);
						break;
					}
					
					
					System.out.println("Tea contains sugar? (Y)es | (N)o");
					e = scan.next().charAt(0);
					scan.nextLine();
					switch(e) {
						case 'Y':
						case 'y':
								sugarFree = false;
								break;
						case 'N':
						case 'n':
								sugarFree = true;
								break;
						default:
							System.out.println("Input error. Starting over.");
							System.out.println();
							addTea(register);
							break;
					}
					
					System.out.println("Tea is seasonal? (Y)es | (N)o");
					e = scan.next().charAt(0);
					scan.nextLine();
					switch(e) {
					case 'Y':
					case 'y':
							seasonal = true;
							break;
					case 'N':
					case 'n':
							seasonal = false;
							break;
					default:
						System.out.println("Input error. Starting over.");
						System.out.println();
						addTea(register);
						break;
						
						
					}
					
					System.out.println("Enter the product description: ");
					productDesc = scan.nextLine();
					
					System.out.println("Enter the discount value for future sales (default discount is 0.15): ");
					discount = scan.nextDouble();
					
					System.out.println("Enter the tax rate (default tax rate is 1.08): ");
					taxRate = scan.nextDouble();
					
					Tea tempTeaLong = new Tea(register, name, caffeine, sugarFree, seasonal, productDesc, price, discount, taxRate, quantity);
					
					System.out.println();
					System.out.println("Tea product created!");
					System.out.println();
					
					productMenu(register);
					
					break;
			
					
				case 'N':
				case 'n':
					
					Tea tempTea = new Tea(register, name, price, quantity);
					
					System.out.println();
					System.out.println("Tea product created!");
					System.out.println();
					
					productMenu(register);
					break;
					
				default:
					System.out.println("Input error. Starting over.");
					System.out.println();
					addTea(register);
					break;
				}
			break;
			
		case 'N':
		case 'n':
			System.out.println("Starting over.");
			System.out.println();
			chooseProductType(register);
			break;
		default:
			System.out.println("Input error. Starting over.");
			System.out.println();
			addTea(register);
			break;
		}
		
	}

	public void addFood(Register register) {
		Scanner scan = new Scanner(System.in);
		
		String name;
		int quantity;
		double price;
		
		char c; char d; char e; //switch variables
		
		System.out.println("Name of food product: ");
		name = scan.nextLine();
		
		System.out.println("Quantity available: ");
		quantity = scan.nextInt();
		
		System.out.println("Price of food product: ");
		price = scan.nextDouble();
		
		System.out.println("Is the following correct?");
		System.out.println("Name: " + name + " | Quantity: " + quantity + " | Price: $" + price);
		System.out.println();
		
		System.out.println("(Y)es | (N)o");
		c = scan.next().charAt(0);
		scan.nextLine();
		
		switch (c) {
		case 'Y':
		case 'y':
			System.out.println("Would you like to enter additional product information?");
			System.out.println("(Choosing \"No\" will give the product default values.)");
			System.out.println("(Y)es | (N)o");
			d = scan.next().charAt(0);
			scan.nextLine();
				switch (d) {
				case 'Y':
				case 'y':
					
					boolean gluten = true;
					boolean sugarFree = false;
					String productDesc;
					double discount;
					double taxRate;
					
					System.out.println("Food contains gluten? (Y)es | (N)o");
					e = scan.next().charAt(0);
					scan.nextLine();
					switch(e) {
					case 'Y':
					case 'y':
							gluten = true;
							break;
					case 'N':
					case 'n':
							gluten = false;
							break;
					default:
						System.out.println("Input error. Starting over.");
						System.out.println();
						addFood(register);
						break;
					}
					
					System.out.println("Food is sugar free? (Y)es | (N)o");
					e = scan.next().charAt(0);
					scan.nextLine();
					switch(e) {
					case 'Y':
					case 'y':
							sugarFree = true;
							break;
					case 'N':
					case 'n':
							sugarFree = false;
							break;
					default:
						System.out.println("Input error. Starting over.");
						System.out.println();
						addFood(register);
						break;
					}
					
					System.out.println("Enter the product description: ");
					productDesc = scan.nextLine();
					scan.nextLine();
					
					System.out.println("Enter the discount value for future sales (default discount is 0.15): ");
					discount = scan.nextDouble();
					
					System.out.println("Enter the tax rate (default tax rate is 1.08): ");
					taxRate = scan.nextDouble();
					
					Food tempFoodLong = new Food(register, name, gluten, sugarFree, productDesc, price, discount, taxRate, quantity);
					
					System.out.println();
					System.out.println("Food product created!");
					System.out.println();
					
					productMenu(register);
					break;
					
				case 'N':
				case 'n':
					
					Food tempFood = new Food(register, name, price, quantity);
					
					System.out.println();
					System.out.println("Food product created!");
					System.out.println();
					
					productMenu(register);
					break;
					
				default:
					System.out.println("Input error. Starting over.");
					System.out.println();
					addFood(register);
					break;
				}
			break;
			
		case 'N':
		case 'n':
			System.out.println("Starting over.");
			System.out.println();
			chooseProductType(register);
			break;
		default:
			System.out.println("Input error. Starting over.");
			System.out.println();
			addFood(register);
			break;
		}
		
	}

	public void chooseApparelType(Register register) {
		Scanner scan = new Scanner(System.in);
		System.out.println("What is the type of apparel?");
		System.out.println("(S)hirt or hoodie | (H)at | (M)isc apparel");
		char e = scan.next().charAt(0);
		scan.nextLine();
		
		switch (e) {
		case 'S':
		case 's':
			addShirt(register);
			break;
		
		case 'H':
		case 'h':
			addHat(register);
			break;
		
		case 'M':
		case 'm':
			addApparel(register);
			break;
		
		default:
			System.out.println("Input error. Starting over.");
			System.out.println();
			chooseApparelType(register);
			break;
		}
		
	}

	public void addApparel(Register register) {
		Scanner scan = new Scanner(System.in);
		
		String name;
		String color;
		int quantity;
		double price;
		
		char c; char d; //switch variables
		
		System.out.println("Name of apparel product: ");
		name = scan.nextLine();
		
		System.out.println("Enter the general color of apparel: ");
		color = scan.nextLine();
	
		System.out.println("Quantity available: ");
		quantity = scan.nextInt();
		
		System.out.println("Price of apparel product: ");
		price = scan.nextDouble();
		
		System.out.println("Is the following correct?");
		
		System.out.println("Name: " + name + " | Color: " + color + " | Quantity: " + quantity + " | Price: $" + price);
		System.out.println();
		
		System.out.println("(Y)es | (N)o");
		c = scan.next().charAt(0);
		scan.nextLine();
		
		switch (c) {
		case 'Y':
		case 'y':
			System.out.println("Would you like to enter additional product information?");
			System.out.println("(Choosing \"No\" will give the product default values.)");
			System.out.println("(Y)es | (N)o");
			d = scan.next().charAt(0);
			scan.nextLine();
				switch (d) {
				case 'Y':
				case 'y':
					
					String productDesc;
					double discount;
					double taxRate;
					
					System.out.println("Enter the product description: ");
					productDesc = scan.nextLine();
					scan.nextLine();
					
					System.out.println("Enter the discount value for future sales (default discount is 0.15): ");
					discount = scan.nextDouble();
					
					System.out.println("Enter the tax rate (default tax rate is 1.08): ");
					taxRate = scan.nextDouble();
					
					Apparel tempApparelLong = new Apparel(register, name, color, productDesc, price, discount, taxRate, quantity);
					
					System.out.println();
					System.out.println("Apparel product created!");
					System.out.println();
					
					productMenu(register);
					break;
					
				case 'N':
				case 'n':
					
					Apparel tempApparel = new Apparel(register, name, price, quantity);
					
					System.out.println();
					System.out.println("Apparel product created!");
					System.out.println();
					
					productMenu(register);
					break;
					
				default:
					System.out.println("Input error. Starting over.");
					System.out.println();
					addApparel(register);
					break;
				}
			break;
			
		case 'N':
		case 'n':
			System.out.println("Starting over.");
			System.out.println();
			chooseProductType(register);
			break;
		default:
			System.out.println("Input error. Starting over.");
			System.out.println();
			addApparel(register);
			break;
		}
		
		
		
	}
	
	public void addShirt(Register register) {
		Scanner scan = new Scanner(System.in);
		
		String name;
		String color;
		boolean longSleeve = false;
		boolean hoodie = false;
		int sQuantity;
		int mQuantity;
		int lQuantity;
		int xLQuantity;
		int totalQuantity;
		double price;
		
		char c; char d; char e; //switch variables
		
		
		
		System.out.println("Name of shirt product: ");
		name = scan.nextLine();
		
		System.out.println("Enter the general color of shirt: ");
		color = scan.nextLine();
		
		System.out.println("Shirt is long sleeve? (Y)es | (N)o");
		e = scan.next().charAt(0);
		scan.nextLine();
		switch(e) {
		case 'Y':
		case 'y':
				longSleeve = true;
				break;
		case 'N':
		case 'n':
				longSleeve = false;
				break;
		default:
			System.out.println("Input error. Starting over.");
			System.out.println();
			addShirt(register);
			break;
		}
		
		System.out.println("Shirt is a hoodie? (Y)es | (N)o");
		e = scan.next().charAt(0);
		scan.nextLine();
		switch(e) {
		case 'Y':
		case 'y':
				hoodie = true;
				break;
		case 'N':
		case 'n':
				hoodie = false;
				break;
		default:
			System.out.println("Input error. Starting over.");
			System.out.println();
			addShirt(register);
			break;
		}
	
		System.out.println("Quantity of size small available: ");
		sQuantity = scan.nextInt();
		
		System.out.println("Quantity of size medium available: ");
		mQuantity = scan.nextInt();
		
		System.out.println("Quantity of size large available: ");
		lQuantity = scan.nextInt();
		
		System.out.println("Quantity of size extra-large available: ");
		xLQuantity = scan.nextInt();
		
		totalQuantity = sQuantity + mQuantity + lQuantity + xLQuantity;
		
		System.out.println("Price of apparel product: ");
		price = scan.nextDouble();
		
		System.out.println("Is the following correct?");
		
		System.out.println("Name: " + name + " | Color: " + color + " | Total Quantity: " + totalQuantity + " | Price: $" + price);
		System.out.println();
		
		System.out.println("(Y)es | (N)o");
		c = scan.next().charAt(0);
		scan.nextLine();
		
		switch (c) {
		case 'Y':
		case 'y':
			System.out.println("Would you like to enter additional product information?");
			System.out.println("(Choosing \"No\" will give the product default values.)");
			System.out.println("(Y)es | (N)o");
			d = scan.next().charAt(0);
			scan.nextLine();
				switch (d) {
				case 'Y':
				case 'y':
					
					String productDesc;
					double discount;
					double taxRate;
					
					System.out.println("Enter the shirt description: ");
					productDesc = scan.nextLine();
					scan.nextLine();
					
					System.out.println("Enter the discount value for future sales (default discount is 0.15): ");
					discount = scan.nextDouble();
					
					System.out.println("Enter the tax rate (default tax rate is 1.08): ");
					taxRate = scan.nextDouble();
					
					Shirt tempShirtLong = new Shirt(register, name, color, longSleeve, hoodie, productDesc, price, discount, taxRate, sQuantity, mQuantity, lQuantity, xLQuantity);
					
					System.out.println();
					System.out.println("Food product created!");
					System.out.println();
					
					productMenu(register);
					break;
					
				case 'N':
				case 'n':
					
					Shirt tempShirt = new Shirt(register, name, color, longSleeve, hoodie, price, sQuantity, mQuantity, lQuantity, xLQuantity);
					
					System.out.println();
					System.out.println("Food product created!");
					System.out.println();
					
					productMenu(register);
					break;
					
				default:
					System.out.println("Input error. Starting over.");
					System.out.println();
					addShirt(register);
					break;
				}
			break;
			
		case 'N':
		case 'n':
			System.out.println("Starting over.");
			System.out.println();
			chooseProductType(register);
			break;
		default:
			System.out.println("Input error. Starting over.");
			System.out.println();
			addShirt(register);
			break;
		}
		
		
		
	}

	public void addHat(Register register) {
		Scanner scan = new Scanner(System.in);
		
		String name;
		String hatType;
		String color;
		int quantity;
		double price;
		
		char c; char d; char e; //switch variables
		
		
		
		System.out.println("Name of hat product: ");
		name = scan.nextLine();
		
		System.out.println("What is the type of hat? (Trucker, beanie, dad cap, etc.): ");
		hatType = scan.nextLine();
		
		System.out.println("Enter the general color of hat: ");
		color = scan.nextLine();
		
		System.out.println("Quantity available: ");
		quantity = scan.nextInt();
		
		System.out.println("Price of hat: ");
		price = scan.nextDouble();
		
		System.out.println("Is the following correct?");
		
		System.out.println("Name: " + name + " | Color: " + color + " | Quantity: " + quantity + " | Price: $" + price);
		System.out.println();
		
		System.out.println("(Y)es | (N)o");
		c = scan.next().charAt(0);
		scan.nextLine();
		
		switch (c) {
		case 'Y':
		case 'y':
			System.out.println("Would you like to enter additional product information?");
			System.out.println("(Choosing \"No\" will give the product default values.)");
			System.out.println("(Y)es | (N)o");
			d = scan.next().charAt(0);
			scan.nextLine();
				switch (d) {
				case 'Y':
				case 'y':
					
					String productDesc;
					double discount;
					double taxRate;
					
					System.out.println("Enter the hat description: ");
					productDesc = scan.nextLine();
					scan.nextLine();
					
					System.out.println("Enter the discount value for future sales (default discount is 0.15): ");
					discount = scan.nextDouble();
					
					System.out.println("Enter the tax rate (default tax rate is 1.08): ");
					taxRate = scan.nextDouble();
					
					Hat tempHatLong = new Hat(register, name, hatType, color, productDesc, price, discount, taxRate, quantity);
					
					System.out.println();
					System.out.println("Hat product created!");
					System.out.println();
					
					productMenu(register);
					break;
					
				case 'N':
				case 'n':
					
					Hat tempHat = new Hat(register, name, hatType, color, price, quantity);
					
					System.out.println();
					System.out.println("Hat product created!");
					System.out.println();
					
					productMenu(register);
					break;
					
				default:
					System.out.println("Input error. Starting over.");
					System.out.println();
					addHat(register);
					break;
				}
			break;
			
		case 'N':
		case 'n':
			System.out.println("Starting over.");
			System.out.println();
			chooseProductType(register);
			break;
		default:
			System.out.println("Input error. Starting over.");
			System.out.println();
			addHat(register);
			break;
		}
		
		
		
	}

}

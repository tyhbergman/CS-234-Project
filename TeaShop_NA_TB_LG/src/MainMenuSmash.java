import java.util.Map.Entry;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class MainMenuSmash {
	public static void main(String[] args) {
		Register register = new Register();
		
		mainMenuSmash(register);

	}
	
	public static void mainMenuSmash(Register register) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("\tMain Menu");
		System.out.println();
		
		System.out.println("1.) Sale Menu");
		System.out.println("2.) Product Management");
		System.out.println("3.) Input Records");
		System.out.println("4.) Sales Records");
		System.out.println("5.) Employee Management");
		System.out.println("6.) Quit Tea Shop");
		
		System.out.println();
		
		System.out.println("Select an option: ");
		
		int x = scan.nextInt();
		
		switch (x) {
			case 1:
				saleMenu(register);
				break;
			case 2:
				productMenu(register);
				break;
			case 3:
				vendorRecords(register);
				break;
			case 4:
				salesRecords(register);
				break;
			case 5:
				employeeMenu(register);
				break;
			case 6:
				System.out.println();
				System.out.println("Exiting.");
				break;
			default:
				System.out.println("Input error. Starting over.");
				System.out.println();
				mainMenuSmash(register);
				break;
		}
		
	
	}
	
	public static void saleMenu(Register register) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("\tSale Menu");
		System.out.println();
		
		System.out.println("1.) Add Item to Cart");
		System.out.println("2.) Execute Sale");
		System.out.println("3.) View Cart");
		System.out.println("4.) Clear Cart");
		System.out.println("5.) Product Menu");
		System.out.println("6.) Quit Sale Menu");
		
		System.out.println();
		
		System.out.println("Select an option: ");
		
		int x = scan.nextInt();
		
		switch (x) {
			case 1:
				System.out.print("Enter the ID of item: ");
				int temp = scan.nextInt();
				System.out.print("Enter the desired quantity: ");
				int tempQ = scan.nextInt();
				register.addToCart(register.getProductList().get(temp), tempQ);
				saleMenu(register);
				break;
			case 2:
				register.sale();
				saleMenu(register);
				break;
			case 3:
				register.viewCart();
				saleMenu(register);
				break;
			case 4:
				register.clearCart();
				saleMenu(register);
				break;
			case 5:
				getProductMenu(register);
				saleMenu(register);
				break;
			case 6:
				System.out.println();
				System.out.println("Exiting.");
				mainMenuSmash(register);
				break;
			default:
				System.out.println("Input error. Starting over.");
				System.out.println();
				saleMenu(register);
				break;
		}
	}
	
	public static void salesRecords(Register register) {
		System.out.println("Transaction ID\tTotal\t\tSub-Total\tTax\t\tQuantity of Items Purchased");
		System.out.println("-------------------------------------------------------------------------------------------------------------");
		for (int i = 0; i < register.getAllTransactions().size(); i++) {
			
			
			System.out.printf("%03d\t\t$%5.2f\t\t$%5.2f\t\t$%5.2f\t\t%d",
					register.getAllTransactions().get(i).get(0), register.getAllTransactions().get(i).get(1),
					register.getAllTransactions().get(i).get(2), register.getAllTransactions().get(i).get(3),
					register.getAllTransactions().get(i).get(4));
			System.out.println();
		}
		
		System.out.println();
		mainMenuSmash(register);
		
		
		
		
	}
	
	public static void vendorRecords(Register register) {
		inputMenu(register);
		//System.out.println("Input ID\tVendor\t\tName\t\t\tVendor Price\tPrice\t\tQuantity\tTax Rate\tDiscount");
		//System.out.println("-------------------------------------------------------------------------------------------------------------");
		//for (int i = 0; i < register.getAllInputs().size(); i++) {
			//System.out.printf("%03d\t\t%.7s\t\t%.15s\t\t$%5.2f\t\t$%5.2f\t\t%d\t\t%2.2f",
					//register.getAllInputs().get(i).get(0), register.getAllInputs().get(i).get(1),
					//register.getAllInputs().get(i).get(2), register.getAllInputs().get(i).get(3),
					//register.getAllInputs().get(i).get(4), register.getAllInputs().get(i).get(5),
					//register.getAllInputs().get(i).get(6), register.getAllInputs().get(i).get(7));
			//System.out.println();
		//}
		//^will work when i learn how to add to inputDetails
		
		//System.out.println();
		mainMenuSmash(register);
	}
	
	public static void productMenu(Register register) {
		
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
				break;
			case 4:
				delete(register);
				break;
			case 5:
				getProductLog(register);
				break;
			case 6:
				System.out.println("Exiting PMM.");
				System.out.println();
				mainMenuSmash(register);
				break;
				
			default:
		}
	}
	
	public static void getProductLog(Register register) {
		
		
		System.out.println("ID\tProduct Type\tProduct Name\t\tPrice\t\tVendor Price\tQuantity\tVendor");
		System.out.println("-------------------------------------------------------------------------------------------------------------");
		Set<Entry<Integer,Product>> entrySet = register.getProductList().entrySet();
		for (Entry<Integer, Product> entry : entrySet) {
			
			System.out.printf("%03d\t%s\t\t%.15s\t\t$%5.2f\t\t$%5.2f\t\t%d\t\t%.15s",
					entry.getKey(), entry.getValue().getProductType(), entry.getValue().getName(), entry.getValue().getPrice(), 
					entry.getValue().getVendorPrice(), entry.getValue().getQuantity(), entry.getValue().getVendor());
			System.out.println();
		}
		System.out.println();
		productMenu(register);
		
		
		
		
		
		
		
		
	}
	
	public static void chooseProductType(Register register) {
		Scanner scan = new Scanner(System.in);
		char c;
		
		System.out.println("\tYou've chosen to add a product.");
		System.out.println();
		
		System.out.println("\tWhat is the type of product?");
		System.out.println("(T)ea | (F)ood | (A)pparel | (M)isc");
		
		c = scan.nextLine().charAt(0);
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
	
	public static void addProduct(Register register) {
		Scanner scan = new Scanner(System.in);
		
		String name;
		int quantity;
		double price;
		String vendor;
		
		char c; char d; char e;//switch variables
		
		System.out.println("Name of product: ");
		name = scan.nextLine();
		
		System.out.println("Quantity available: ");
		quantity = scan.nextInt();
		
		System.out.println("Price of product: ");
		price = scan.nextDouble();
		
		System.out.println("Name of vendor: ");
		scan.nextLine();
		vendor = scan.nextLine();
		
		System.out.println();
		
		
		System.out.println("Is the following correct?");
		System.out.println("Name: " + name + " | Quantity: " + quantity + " | Price: $" + price + " | Vendor: " + vendor);
		System.out.println();
		
		System.out.println("(Y)es | (N)o");
		c = scan.nextLine().charAt(0);
		
		switch (c) {
		case 'Y':
		case 'y':
			
			System.out.println();
			System.out.println("Would you like to enter additional product information?");
			System.out.println("(Choosing \"No\" will give the product default values.)");
			System.out.println("(Y)es | (N)o");
			
			d = scan.nextLine().charAt(0);
			
			switch (d) {
				case 'Y':
				case 'y':
					
					String productDesc;
					double discount;
					double taxRate;
					double vendorPrice;
					
					System.out.println("Enter the vendor price: ");
					vendorPrice = scan.nextDouble();
					while (vendorPrice>price) {
						System.out.println("Error. The vendor price cannot exceed the sale price ($" + price + ").");
						System.out.println("Enter the vendor price: ");
						vendorPrice = scan.nextDouble();
					}
					
					System.out.println("Enter the product description: ");
					productDesc = scan.nextLine();
					scan.nextLine();
					
					System.out.println("Enter the discount value for future sales (default discount is 0.15): ");
					discount = scan.nextDouble();
					
					System.out.println("Enter the tax rate (default tax rate is 1.08): ");
					taxRate = scan.nextDouble();
					
					Product tempProductLong = new Product(register, name, productDesc, price, discount, taxRate, quantity, vendor);
					
					System.out.println();
					System.out.println("Product created!");
					System.out.println("Its memory location is: " + tempProductLong);
					System.out.println();
					
					productMenu(register);
					break;
			
					
				case 'N':
				case 'n':
					
					Product tempProduct = new Product(register, name, price, quantity, vendor);
					
					System.out.println();
					System.out.println("Product created!");
					System.out.println("Its memory location is: " + tempProduct);
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
	
	public static void addTea(Register register) {
		Scanner scan = new Scanner(System.in);
		
		String name;
		int quantity;
		double price;
		String vendor;
		
		char c; char d; char e;//switch variables
		
		System.out.println("Name of tea product: ");
		name = scan.nextLine();
		
		System.out.println("Quantity available: ");
		quantity = scan.nextInt();
		
		System.out.println("Price of tea product: ");
		price = scan.nextDouble();
		
		System.out.println("Name of vendor: ");
		scan.nextLine();
		vendor = scan.nextLine();
		
		System.out.println();
		
		
		System.out.println("Is the following correct?");
		System.out.println("Name: " + name + " | Quantity: " + quantity + " | Price: $" + price + " | Vendor: " + vendor);
		System.out.println();
		
		System.out.println("(Y)es | (N)o");
		c = scan.nextLine().charAt(0);
		
		switch (c) {
		case 'Y':
		case 'y':
			
			System.out.println();
			System.out.println("Would you like to enter additional product information?");
			System.out.println("(Choosing \"No\" will give the product default values.)");
			System.out.println("(Y)es | (N)o");
			
			d = scan.nextLine().charAt(0);
			
			switch (d) {
				case 'Y':
				case 'y':
					
					boolean caffeine = true;
					boolean sugarFree = false;
					boolean seasonal = false;
					String productDesc;
					double discount;
					double taxRate;double vendorPrice;
					
					System.out.println("Enter the vendor price: ");
					vendorPrice = scan.nextDouble();
					while (vendorPrice>price) {
						System.out.println("Error. The vendor price cannot exceed the sale price ($" + price + ").");
						System.out.println("Enter the vendor price: ");
						vendorPrice = scan.nextDouble();
					}
					
					System.out.println("Tea contains caffeine? (Y)es | (N)o");
					scan.nextLine();
					e = scan.nextLine().charAt(0);
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
					e = scan.nextLine().charAt(0);
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
					e = scan.nextLine().charAt(0);
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
					scan.nextLine();
					
					System.out.println("Enter the discount value for future sales (default discount is 0.15): ");
					discount = scan.nextDouble();
					
					System.out.println("Enter the tax rate (default tax rate is 1.08): ");
					taxRate = scan.nextDouble();
					
					Tea tempTeaLong = new Tea(register, name, caffeine, sugarFree, seasonal, productDesc, price, discount, taxRate, quantity, vendor, vendorPrice);
					
					System.out.println();
					System.out.println("Tea product created!");
					System.out.println("Its memory location is: " + tempTeaLong);
					System.out.println();
					
					productMenu(register);
					break;
			
					
				case 'N':
				case 'n':
					
					Tea tempTea = new Tea(register, name, price, quantity, vendor);
					
					System.out.println();
					System.out.println("Tea product created!");
					System.out.println("Its memory location is: " + tempTea);
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
	
	public static void addFood(Register register) {
		Scanner scan = new Scanner(System.in);
		
		String name;
		int quantity;
		double price;
		String vendor;
		
		char c; char d; char e; //switch variables
		
		System.out.println("Name of food product: ");
		name = scan.nextLine();
		
		System.out.println("Quantity available: ");
		quantity = scan.nextInt();
		
		System.out.println("Price of food product: ");
		price = scan.nextDouble();
		
		System.out.println("Name of vendor: ");
		scan.nextLine();
		vendor = scan.nextLine();
		System.out.println();
		
		
		System.out.println("Is the following correct?");
		System.out.println("Name: " + name + " | Quantity: " + quantity + " | Price: $" + price + " | Vendor: " + vendor);
		System.out.println();
		
		System.out.println("(Y)es | (N)o");
		c = scan.nextLine().charAt(0);
		
		switch (c) {
		case 'Y':
		case 'y':
			System.out.println("Would you like to enter additional product information?");
			System.out.println("(Choosing \"No\" will give the product default values.)");
			System.out.println("(Y)es | (N)o");
			d = scan.nextLine().charAt(0);
				switch (d) {
				case 'Y':
				case 'y':
					
					boolean gluten = true;
					boolean sugarFree = false;
					String productDesc;
					double discount;
					double taxRate;
					double vendorPrice;
					
					System.out.println("Enter the vendor price: ");
					vendorPrice = scan.nextDouble();
					while (vendorPrice>price) {
						System.out.println("Error. The vendor price cannot exceed the sale price ($" + price + ").");
						System.out.println("Enter the vendor price: ");
						vendorPrice = scan.nextDouble();
					}
					
					System.out.println("Food contains gluten? (Y)es | (N)o");
					e = scan.nextLine().charAt(0);
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
					e = scan.nextLine().charAt(0);
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
					
					Food tempFoodLong = new Food(register, name, gluten, sugarFree, productDesc, price, discount, taxRate, quantity, vendor, vendorPrice);
					
					System.out.println();
					System.out.println("Food product created!");
					System.out.println("Its memory location is: " + tempFoodLong);
					System.out.println();
					
					productMenu(register);
					break;
					
				case 'N':
				case 'n':
					
					Food tempFood = new Food(register, name, price, quantity, vendor);
					
					System.out.println();
					System.out.println("Food product created!");
					System.out.println("Its memory location is: " + tempFood);
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
	
	public static void chooseApparelType(Register register) {
		Scanner scan = new Scanner(System.in);
		System.out.println("What is the type of apparel?");
		System.out.println("(S)hirt or hoodie | (H)at | (M)isc apparel");
		char e = scan.next().charAt(0);
		
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
			addApparel(register);
			break;
		}
		
	}
	
	public static void addApparel(Register register) {
		Scanner scan = new Scanner(System.in);
		
		String name;
		String color;
		int quantity;
		double price;
		String vendor;
		
		char c; char d; //switch variables
		
		System.out.println("Name of apparel product: ");
		name = scan.nextLine();
		
		System.out.println("Enter the general color of apparel: ");
		color = scan.nextLine();
	
		System.out.println("Quantity available: ");
		quantity = scan.nextInt();
		
		System.out.println("Price of apparel product: ");
		price = scan.nextDouble();
		
		System.out.println("Name of vendor: ");
		scan.nextLine();
		vendor = scan.nextLine();
		System.out.println();
		
		
		System.out.println("Is the following correct?");
		
		System.out.println("Name: " + name + " | Color: " + color + " | Quantity: " + quantity + " | Price: $" + price + " | Vendor: " + vendor);
		System.out.println();
		
		System.out.println("(Y)es | (N)o");
		c = scan.next().charAt(0);
		
		switch (c) {
		case 'Y':
		case 'y':
			System.out.println("Would you like to enter additional product information?");
			System.out.println("(Choosing \"No\" will give the product default values.)");
			System.out.println("(Y)es | (N)o");
			d = scan.next().charAt(0);
				switch (d) {
				case 'Y':
				case 'y':
					
					String productDesc;
					double discount;
					double taxRate;
					double vendorPrice;
					
					System.out.println("Enter the vendor price: ");
					vendorPrice = scan.nextDouble();
					while (vendorPrice>price) {
						System.out.println("Error. The vendor price cannot exceed the sale price ($" + price + ").");
						System.out.println("Enter the vendor price: ");
						vendorPrice = scan.nextDouble();
					}
					
					System.out.println("Enter the product description: ");
					productDesc = scan.nextLine();
					scan.nextLine();
					
					System.out.println("Enter the discount value for future sales (default discount is 0.15): ");
					discount = scan.nextDouble();
					
					System.out.println("Enter the tax rate (default tax rate is 1.08): ");
					taxRate = scan.nextDouble();
					
					Apparel tempApparelLong = new Apparel(register, name, color, productDesc, price, discount, taxRate, quantity, vendor, vendorPrice);
					
					System.out.println();
					System.out.println("Food product created!");
					System.out.println("Its memory location is: " + tempApparelLong);
					System.out.println();
					
					productMenu(register);
					break;
					
				case 'N':
				case 'n':
					
					Apparel tempApparel = new Apparel(register, name, color, price, quantity, vendor);
					
					System.out.println();
					System.out.println("Food product created!");
					System.out.println("Its memory location is: " + tempApparel);
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
	
	public static void addShirt(Register register) {
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
		String vendor;
		
		char c; char d; char e; //switch variables
		
		
		
		System.out.println("Name of shirt product: ");
		name = scan.nextLine();
		
		System.out.println("Enter the general color of shirt: ");
		color = scan.nextLine();
		
		System.out.println("Shirt is long sleeve? (Y)es | (N)o");
		e = scan.nextLine().charAt(0);
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
		e = scan.nextLine().charAt(0);
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
		
		System.out.println("Name of vendor: ");
		scan.nextLine();
		vendor = scan.nextLine();
		System.out.println();
		
		
		System.out.println("Is the following correct?");
		
		System.out.println("Name: " + name + " | Color: " + color + " | Total Quantity: " + totalQuantity + " | Price: $" + price + " | Vendor: " + vendor);
		System.out.println();
		
		System.out.println("(Y)es | (N)o");
		c = scan.nextLine().charAt(0);
		
		switch (c) {
		case 'Y':
		case 'y':
			System.out.println("Would you like to enter additional product information?");
			System.out.println("(Choosing \"No\" will give the product default values.)");
			System.out.println("(Y)es | (N)o");
			d = scan.nextLine().charAt(0);
				switch (d) {
				case 'Y':
				case 'y':
					
					String productDesc;
					double discount;
					double taxRate;
					double vendorPrice;
					
					System.out.println("Enter the vendor price: ");
					vendorPrice = scan.nextDouble();
					while (vendorPrice>price) {
						System.out.println("Error. The vendor price cannot exceed the sale price ($" + price + ").");
						System.out.println("Enter the vendor price: ");
						vendorPrice = scan.nextDouble();
					}
					
					System.out.println("Enter the shirt description: ");
					productDesc = scan.nextLine();
					scan.nextLine();
					
					System.out.println("Enter the discount value for future sales (default discount is 0.15): ");
					discount = scan.nextDouble();
					
					System.out.println("Enter the tax rate (default tax rate is 1.08): ");
					taxRate = scan.nextDouble();
					
					Shirt tempShirtLong = new Shirt(register, name, color, longSleeve, hoodie, productDesc, price, discount, taxRate, sQuantity, mQuantity, lQuantity, xLQuantity, vendor, vendorPrice);
					
					System.out.println();
					System.out.println("Food product created!");
					System.out.println("Its memory location is: " + tempShirtLong);
					System.out.println();
					
					productMenu(register);
					break;
					
				case 'N':
				case 'n':
					
					Shirt tempShirt = new Shirt(register, name, color, longSleeve, hoodie, price, sQuantity, mQuantity, lQuantity, xLQuantity, vendor);
					
					System.out.println();
					System.out.println("Food product created!");
					System.out.println("Its memory location is: " + tempShirt);
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
	
	public static void addHat(Register register) {
		Scanner scan = new Scanner(System.in);
		
		String name;
		String hatType;
		String color;
		int quantity;
		double price;
		String vendor;
		
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
		
		System.out.println("Name of vendor: ");
		scan.nextLine();
		vendor = scan.nextLine();
		System.out.println();
		
		
		System.out.println("Is the following correct?");
		
		System.out.println("Name: " + name + " | Color: " + color + " | Quantity: " + quantity + " | Price: $" + price + " | Vendor: " + vendor);
		System.out.println();
		
		System.out.println("(Y)es | (N)o");
		c = scan.nextLine().charAt(0);
		
		switch (c) {
		case 'Y':
		case 'y':
			System.out.println("Would you like to enter additional product information?");
			System.out.println("(Choosing \"No\" will give the product default values.)");
			System.out.println("(Y)es | (N)o");
			d = scan.nextLine().charAt(0);
				switch (d) {
				case 'Y':
				case 'y':
					
					String productDesc;
					double discount;
					double taxRate;
					double vendorPrice;
					
					System.out.println("Enter the vendor price: ");
					vendorPrice = scan.nextDouble();
					while (vendorPrice>price) {
						System.out.println("Error. The vendor price cannot exceed the sale price ($" + price + ").");
						System.out.println("Enter the vendor price: ");
						vendorPrice = scan.nextDouble();
					}
					
					System.out.println("Enter the hat description: ");
					productDesc = scan.nextLine();
					scan.nextLine();
					
					System.out.println("Enter the discount value for future sales (default discount is 0.15): ");
					discount = scan.nextDouble();
					
					System.out.println("Enter the tax rate (default tax rate is 1.08): ");
					taxRate = scan.nextDouble();
					
					Hat tempHatLong = new Hat(register, name, hatType, color, productDesc, price, discount, taxRate, quantity, vendor, vendorPrice);
					
					System.out.println();
					System.out.println("Food product created!");
					System.out.println("Its memory location is: " + tempHatLong);
					System.out.println();
					
					productMenu(register);
					break;
					
				case 'N':
				case 'n':
					
					Hat tempHat = new Hat(register, name, hatType, color, price, quantity, vendor);
					
					System.out.println();
					System.out.println("Hat product created!");
					System.out.println("Its memory location is: " + tempHat);
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

	public static void delete(Register register) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("What is the ID of the product you want to delete?");
		int x = scan.nextInt();
		scan.nextLine();
		
		if(register.getProductList().containsKey(x)) {
			
			System.out.println("Are you sure you really want to remove product " + register.getProductList().get(x).getName() + "?");
			System.out.println("(Y)es | (N)o");
			char c = scan.nextLine().charAt(0);
			
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
	
	public static void getProduct(Register register) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("What is the ID of the product you want to review?");
		int x = scan.nextInt();
		
		if(register.getProductList().containsKey(x)) {
			System.out.println("!" + register.getProductList().get(x).getId());
			System.out.println("ID\tProduct Type\tProduct Name\t\tPrice\t\tVendor Price\tQuantity\tVendor");
			System.out.println("-------------------------------------------------------------------------------------------------------------");
			System.out.printf("%03d\t%s\t\t%.15s\t\t$%5.2f\t\t$%5.2f\t\t%d\t\t%.15s",
					register.getProductList().get(x).getId(), register.getProductList().get(x).getProductType(),
					register.getProductList().get(x).getName(), register.getProductList().get(x).getPrice(),
					register.getProductList().get(x).getVendorPrice(), register.getProductList().get(x).getQuantity(),
					register.getProductList().get(x).getVendor());
			
			System.out.println();
		} else {
			System.out.println("Error. Product ID not in system. Review product log to retrieve ID values.");
		}
		
		
		System.out.println();
		
		productMenu(register);
	}

	public static void getProductMenu(Register register) {
		System.out.println("ID\tProduct Name\t\tPrice\t\tQuantity");
		System.out.println("-------------------------------------------------------------------------------------");
		Set<Entry<Integer,Product>> entrySet = register.getProductList().entrySet();
		for (Entry<Integer, Product> entry : entrySet) {
			
			System.out.printf("%03d\t%.15s\t\t$%5.2f\t\t%d",
					entry.getKey(),entry.getValue().getName(), entry.getValue().getPrice(),entry.getValue().getQuantity());
			System.out.println();
		}
		System.out.println();
	}
	
	public static void employeeMenu(Register Register) {
		System.out.println("Employee Menu - please log in");
		System.out.println("  Default manager passcode - 1234");
		
		// Sets up a default manager
		Employees employees = new Employees();
		Employees schedule = new Employees();
		employees.addEmployee("manager", "12345", "Default Manager Account", "0.00", "1234", "0", "yes");
		
		// Keeps system running until program is closed
		int run = 1;
		while(run!=0) {
			// Employee or Manager Log In
			Scanner a = new Scanner(System.in);
			System.out.println("Are you [a] Employee or [b] Manager? Enter [q] to quit.");
			char employeeType = a.next().charAt(0);
			
			// Confirmation of Employee in the system
			// Employee Log In
			if(employeeType == 'a' || employeeType == 'A') {
				// Gets employee name
				Scanner b = new Scanner(System.in);
				System.out.println("Enter Employee name");
				String name = b.nextLine();
				
				// Checks to make sure they are an employee
				boolean nameCheck = employees.nameCheck(name);
				if(nameCheck == true) {
					// Gets password from employee
					Scanner c = new Scanner(System.in);
					System.out.println("Enter Employee's last 4 digits of their SSN");
					String SSN = c.nextLine();
					
					// Checks for correct password
					String SSNCheck = employees.getSSN(name);
					if(SSN.equals(SSNCheck)) {
						// Log in successful
						System.out.println("Employee log in successful.");
						int run2 = 1;
						while(run2!=0) {
							Scanner d = new Scanner(System.in);
							System.out.println("What would you like to do: ");
							System.out.println("[a] View Personal Information");
							System.out.println("[b] View Weekly Schedule");
							System.out.println("[c] Log out");
							char choice = a.next().charAt(0);
							
							if(choice == 'a' || choice == 'A') {
								// View Personal Information
								employees.printEmployeeInfo(name);
							} else if(choice == 'b' || choice == 'B') {
								// View Weekly Schedule
								
							} else if(choice == 'c' || choice == 'C') {
								// Log off
								System.out.println("Logging off...\n");
								run2 = 0;
							} else {
								System.out.println("Invalid Input.");
							}
						}
					}else {
						System.out.print("Incorrect Password.");
						return;
					}
				} else {
					System.out.println("Employee is not in the system.");
					return;
				}
				
			}else if(employeeType == 'b' || employeeType == 'B') {
				// Gets employee name
				Scanner b = new Scanner(System.in);
				System.out.println("Enter Manager's name: ");
				String name = b.nextLine();
				
				// Checks to make sure they are an manager
				boolean nameCheck = employees.nameCheck(name);
				if(nameCheck == true) {
					String managerStatusCheck = employees.isManager(name);
					if(managerStatusCheck == "yes") {
						// Gets password from manager
						Scanner c = new Scanner(System.in);
						System.out.println("Enter Manager's last 4 digits of their SSN: ");
						String SSN = c.nextLine();
						
						// Checks for correct password
						String SSNCheck = employees.getSSN(name);
						if(SSN.equals(SSNCheck)) {
							// Log in successful
							System.out.println("Manager log in successful\n");
							int runTime = 1;
							while(runTime!=0) {
								// User selection
								Scanner d = new Scanner(System.in);
								System.out.println("What would you like to do: ");
								System.out.println("[a] View Reports");
								System.out.println("[b] View Vendors");
								System.out.println("[c] Manage Employees");
								System.out.println("[d] Manage Employee Schedules");
								System.out.println("[e] View Personal Information");
								System.out.println("[f] Log Out");
								char choice = a.next().charAt(0);
								
								if(choice == 'a' || choice == 'A') {
									// View Reports
									
								} else if(choice == 'b' || choice == 'B') {
									// View Vendors
									
								} else if(choice == 'c' || choice == 'C') {
									int run2 = 1;
									while(run2!=0) {
										// Manage Employees Choice Selection
										Scanner e = new Scanner(System.in);
										System.out.println("What would you like to do: ");
										System.out.println("[a] Add Employee");
										System.out.println("[b] Edit Current Employee");
										System.out.println("[c] Delete Employee");
										System.out.println("[d] Print All Employee Information");
										System.out.println("[e] Exit Manage Employees");
										char choice2 = a.next().charAt(0);
										
										if(choice2 == 'a' || choice2 == 'A') {
											// Add Employee
											Scanner f = new Scanner(System.in);
											System.out.println("Enter new employee's name: ");
											String newName = f.nextLine();
											
											Scanner g = new Scanner(System.in);
											System.out.println("Enter new employee's employee Id number: ");
											String empid = g.nextLine();
											
											Scanner h = new Scanner(System.in);
											System.out.println("Enter new employee's position: ");
											String position = h.nextLine();
											
											Scanner i = new Scanner(System.in);
											System.out.println("Enter new employee's wage: ");
											String wage = i.nextLine();
											
											Scanner j = new Scanner(System.in);
											System.out.println("Enter new employee's weekly hours: ");
											String hours = j.nextLine();
											
											Scanner k = new Scanner(System.in);
											System.out.println("Enter new employee's social security number: ");
											String ssn = k.nextLine();
											
											String type = "no";
											Scanner l = new Scanner(System.in);
											System.out.println("Is the new employee a manager?\n[a] yes\n[b] no");
											char isManager = a.next().charAt(0);
											if(isManager == 'a' || isManager == 'A') {
												type = "yes";
											}										
											
											employees.addEmployee(newName, empid, position, wage, ssn, hours, type);
											System.out.print("New employee has been created. \n\n");
										} else if(choice2 == 'b' || choice2 == 'B') {
											// Edit Current Employee
											
											// Enter the name of the employee
											Scanner f = new Scanner(System.in);
											System.out.println("Enter the employee's name: ");
											String empName = f.nextLine();
											
											Scanner g = new Scanner(System.in);
											System.out.println("What would you like to edit?");
											System.out.println("[a] Name");
											System.out.println("[b] Employee Id");
											System.out.println("[c] Position");
											System.out.println("[d] Wage");
											System.out.println("[e] Hours");
											System.out.println("[f] Social Security Number");
											System.out.println("[g] Manager Status");
											char choice3 = a.next().charAt(0);
											
											if(choice3 == 'a' || choice3 == 'A') {
												Scanner h = new Scanner(System.in);
												System.out.println("Enter the employee's new name: ");
												String newName = h.nextLine();
												
												employees.changeName(empName, newName);
												System.out.print("Employee's name has been updated.\n");
											} else if(choice3 == 'b' || choice3 == 'B') {
												Scanner h = new Scanner(System.in);
												System.out.println("Enter the employee's new Id: ");
												String empid = h.nextLine();
												
												employees.changeEmpid(empName, empid);
												System.out.print("Employee's Id has been updated.\n");
											} else if(choice3 == 'c' || choice3 == 'C') {
												Scanner h = new Scanner(System.in);
												System.out.println("Enter the employee's new position: ");
												String position = h.nextLine();
												
												employees.changePosition(empName, position);
												System.out.print("Employee's position has been updated.\n");
											} else if(choice3 == 'd' || choice3 == 'D') {
												Scanner h = new Scanner(System.in);
												System.out.println("Enter the employee's new wage: ");
												String wage = h.nextLine();
												
												employees.changeWage(empName, wage);
												System.out.print("Employee's wage has been updated.\n");
											} else if(choice3 == 'e' || choice3 == 'E') {
												Scanner h = new Scanner(System.in);
												System.out.println("Enter the employee's new weekly hours: ");
												String hours = h.nextLine();
												
												employees.changeHours(empName, hours);
												System.out.print("Employee's hours has been updated.\n");
											} else if(choice3 == 'f' || choice3 == 'F') {
												Scanner h = new Scanner(System.in);
												System.out.println("Enter the employee's new social security number: ");
												String ssn = h.nextLine();
												
												employees.changeSSN(empName, ssn);
												System.out.print("Employee's ssn has been updated.\n");
											} else if(choice3 == 'g' || choice3 == 'G') {
												String type = "no";
												Scanner h = new Scanner(System.in);
												System.out.println("Is employee [a] manager or [b] employee: ");
												char choice4 = a.next().charAt(0);
												
												if(choice4 == 'a' || choice4 == 'A') {
													type = "yes";
												} else if(choice4 == 'b' || choice4 == 'B') {
													type = "no";
												} else {
													System.out.print("Invalid Input.");
												}
												employees.isManagerChange(empName, type);
												System.out.print("Employee's position rank has been updated.\n");
											} else {
												System.out.print("Invalid input.");
											}
										} else if(choice2 == 'c' || choice2 == 'C') {
											// Delete Employee
											System.out.print("Current employees");
											employees.printEmployees();
											
											Scanner f = new Scanner(System.in);
											System.out.println("Enter inactive employee's name: ");
											String empName = f.nextLine();
											
											employees.deleteEmployee(empName);
											System.out.print("Employee has been deleted.\n");
										} else if(choice2 == 'd' || choice2 == 'D') {
											System.out.print("Employee Records");
											employees.printEmployees();
										} else if(choice2 == 'e' || choice2 == 'E') {
											System.out.println("Logging off...\n");
											run2 = 0;
										} else {
											System.out.println("Invalid Input.");
										}
									}
								} else if(choice == 'd' || choice == 'D') {
									// Manage Employee Schedules
									Scanner e = new Scanner(System.in);
									System.out.println("Would you like to: ");
									System.out.println("[a] Create/Edit a shift for an employee");
									System.out.println("[b] Delete a shift from an employee");
									System.out.println("[c] Remove an employee for the entire schedule");
									System.out.println("[d] Print an Employee's schedule information");
									char choice2 = e.next().charAt(0);
									
									if(choice2 == 'a' || choice2 == 'A') {
										Scanner f = new Scanner(System.in);
										System.out.println("Enter the employee's name: ");
										String Name = f.nextLine();
										
										Scanner g = new Scanner(System.in);
										System.out.println("Enter the day of the shift: ");
										String shiftDay = g.nextLine();
										
										Scanner h = new Scanner(System.in);
										System.out.println("Enter the hour the shift starts: ");
										String shiftHourStart = h.nextLine();
										
										Scanner i = new Scanner(System.in);
										System.out.println("Enter the minute the shift starts: ");
										String shiftMinStart = i.nextLine();
										
										Scanner j = new Scanner(System.in);
										System.out.println("Enter the shift start meridiem: ");
										String shiftStartMeridiem = j.nextLine();
										
										Scanner k = new Scanner(System.in);
										System.out.println("Enter the hour the shift ends: ");
										String shiftHourEnd = k.nextLine();
										
										Scanner l = new Scanner(System.in);
										System.out.println("Enter the minute the shift ends: ");
										String shiftMinEnd = l.nextLine();
										
										Scanner m = new Scanner(System.in);
										System.out.println("Enter the shift end meridiem: ");
										String shiftEndMeridiem = m.nextLine();
										
										schedule.addShift(Name, shiftDay, shiftHourStart, shiftMinStart, shiftStartMeridiem, shiftHourEnd, shiftMinEnd, shiftEndMeridiem);
										System.out.println("Shift has been added");
										
										System.out.println("Daily hours: " + schedule.dailyHours(Name, shiftDay));
										System.out.println("Total hours: " + schedule.totalHours(Name));
									} else if(choice2 == 'b' || choice2 == 'B') {
										Scanner f = new Scanner(System.in);
										System.out.println("Enter the name of the employee: ");
										String Name = f.nextLine();
										
										Scanner g = new Scanner(System.in);
										System.out.println("Enter the day of the shift: ");
										String shiftDay = g.nextLine();
										
										schedule.removeShift(Name, shiftDay);
									} else if(choice2 == 'c' || choice2 == 'C') {
										
									} else if(choice2 == 'd' || choice2 == 'D') {
										schedule.printSchedule();
									} else {
										System.out.print("Invalid Input");
									}
									
								} else if(choice == 'e' || choice == 'E') {
									// View Personal Information
									employees.printEmployeeInfo(name);
								} else if(choice == 'f' || choice == 'F') {
									System.out.println("Logging off...\n");
									runTime = 0;
								} else {
									System.out.println("Invalid Input.");
								}
							}
						}else {
							System.out.print("Incorrect Password.");
							return;
						}
					}else {
						System.out.println("Employee position is not set as manager");
					}
				} else {
					System.out.println("Manager is not in the system.");
					return;
				}
			} else if (employeeType == 'q' || employeeType == 'Q') {
				System.out.println("System shutting down...");
				run = 0;
			} else {
				System.out.println("Invaild Input.");
			}
				
		}

	}
	
public static void inputMenu(Register register) {
		
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
			System.out.println("6. Return to Main Menu");
			System.out.println("----------------------------");
			choice = c.nextInt();
		
			switch (choice)
			{
			
				case 1: 
					System.out.print("Enter the line item ID for this input (as a nonnegative integer, ie, 0,1,2,18, etc.): ");
					lineItemID = c.nextInt();
					boolean exists = false;
					while (!exists)
					{
						if (lineItemID == lineItem.getLineItemID()) //|| lineItemID == lineItem1.getLineItemID() || 
						//lineItemID == lineItem2.getLineItemID() || lineItemID == lineItem3.getLineItemID() ||
						//lineItemID == lineItem4.getLineItemID()) //|| lineItemID == lineItem5.getLineItemID() ||
						//lineItemID == lineItem6.getLineItemID() || lineItemID == lineItem7.getLineItemID() ||
						//lineItemID == lineItem8.getLineItemID())
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
					System.out.print("Enter the category of goods/services the vendor is selling: ");
					category = s.nextLine();
					System.out.print("Enter the particular input that the vendor is providing: ");
					input = s.nextLine();
					System.out.print("Enter the cost of the input: $");
					inputCost = c.nextDouble();
					System.out.print("Enter the quantity of the input: ");
					inputQuantity = c.nextInt();
					System.out.print("Enter the discount percentage on this input (write as a positive decimal less than 1, ie, 0.05, 0.1, etc.): ");
					inputDiscount = c.nextDouble();
					System.out.print("Enter the tax rate applied to this input (write as a positive decimal less than 1, ie, 0.05, 0.1, etc.): ");
					taxRate = c.nextDouble();
					lineItem = new Input(lineItemID, name, category, input, inputCost, inputQuantity, inputDiscount, taxRate);
					inputDescription.add(lineItem);
					
					gc = lineItem.getGrossCost(inputCost, inputQuantity);
					grossCostTotal.put(lineItemID, gc);
					ta = lineItem.getTaxAmount(taxRate, inputCost, inputQuantity);
					taxAmountTotal.put(lineItemID, ta);
					da = lineItem.getDiscountAmount(inputCost, inputQuantity, inputDiscount);
					discountAmountTotal.put(lineItemID, da);
					nc = lineItem.getNetCost(taxRate, inputCost, inputQuantity, inputDiscount);
					netCostTotal.put(lineItemID, nc);
				break;
				case 2:
					System.out.println("-------------------------------------------------");
					System.out.println("|                Inputs Report                  |");
					System.out.println("-------------------------------------------------");
					System.out.println("-------------------------------------------------");
					System.out.print("ID | ");
					System.out.print("Vendor Name |");
					System.out.print("Vendor Category |");
					System.out.print("Input Name |");
					System.out.print("Unit Cost ($) |");
					System.out.print("Quantity |");
					System.out.print("Discount (%) |");
					System.out.print("Tax Rate (%) |");
					System.out.print("Discount Amount ($) |");
					System.out.print("Gross Cost ($) |");
					System.out.print("Tax Amount ($) |");
					System.out.print("Net Cost ($) \n");
					System.out.println("-------------------------------------------------");
					
					Iterator<Input> i = inputDescription.iterator();
					while(i.hasNext()) 
					{
					
						Input v = i.next();
						System.out.println(v + "help lol");
					
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
					//System.out.println(grossCostTotal);
					//System.out.println(discountAmountTotal);
					//System.out.println(taxAmountTotal);
					//System.out.println(netCostTotal);
					//System.out.print("Would you like to export this report? (Y)es | (N)o: ");
				break;
				case 3: 
					boolean found = false;
					System.out.print("Enter the line item ID of the line item to search for: ");
					int id = c.nextInt();
					System.out.println("-------------------------------------------------");
					System.out.print("ID | ");
					System.out.print("Vendor Name |");
					System.out.print("Vendor Category |");
					System.out.print("Input Name |");
					System.out.print("Unit Cost ($) |");
					System.out.print("Quantity |");
					System.out.print("Discount (%) |");
					System.out.print("Tax Rate (%) |");
					System.out.print("Discount Amount ($) |");
					System.out.print("Gross Cost ($) |");
					System.out.print("Tax Amount ($) |");
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
					System.out.println("Not a valid choice. Please try again.");
				break;
			}
		
		}while (choice != 6); return;
	
	}
}





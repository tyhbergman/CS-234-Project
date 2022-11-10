import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

public class PMM {
	
	public void pMM(Register register) {
		
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
		
		System.out.println("Option: ");
		
		x = scan.nextInt();
		
		switch (x) {
			case 1:
				addProduct(register);
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				getProductLog(register);
				break;
			case 6:
				System.out.println("Exiting PMM.");
				System.out.println();
				break;
			default:
		}
	}
	
	public void getProductLog(Register register) {
		
		Set<Entry<Integer,Product>> entrySet = register.getProductList().entrySet();
		for (Entry<Integer, Product> entry : entrySet) {
			System.out.println("ID: " + entry.getKey());
			System.out.println("Product: "  + entry.getValue().getName());
			System.out.println("\tPrice: $" + entry.getValue().getPrice());
			System.out.println("\tQuantity: " + entry.getValue().getQuantity());
			System.out.println("\tVendor: " + entry.getValue().getVendor());
			System.out.println();
		}
		
		
		
		
		
		
		
		
	}
	
	public void addProduct(Register register) {
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
				
				break;
			
			default:
				System.out.println("Input error, try again.");
				System.out.println();
				addProduct(register);
				break;
				
		}
		
	}
	
	public void addTea(Register register) {
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
		c = scan.next().charAt(0);
		
		switch (c) {
		case 'Y':
		case 'y':
			
			System.out.println();
			System.out.println("Would you like to enter additional product information?");
			System.out.println("(Choosing \"No\" will give the product default values.)");
			System.out.println("(Y)es | (N)o");
			
			d = scan.next().charAt(0);
			
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
					
					Tea tempTeaLong = new Tea(name, caffeine, sugarFree, seasonal, productDesc, price, discount, taxRate, quantity, vendor);
					
					register.getProductList().put(register.getAndIncrementProductIndex(), tempTeaLong);
					
					System.out.println();
					System.out.println("Tea product created!");
					System.out.println("Its memory location is: " + tempTeaLong);
					System.out.println();
					
					pMM(register);
					break;
			
					
				case 'N':
				case 'n':
					
					Tea tempTea = new Tea(name, price, quantity, vendor);
					
					register.getProductList().put(register.getAndIncrementProductIndex(), tempTea);
					
					System.out.println();
					System.out.println("Tea product created!");
					System.out.println("Its memory location is: " + tempTea);
					System.out.println();
					
					pMM(register);
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
			addProduct(register);
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
					
					boolean gluten = true;
					boolean sugarFree = false;
					String productDesc;
					double discount;
					double taxRate;
					
					
					System.out.println("Food contains gluten? (Y)es | (N)o");
					e = scan.next().charAt(0);
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
					
					Food tempFoodLong = new Food(name, gluten, sugarFree, productDesc, price, discount, taxRate, quantity, vendor);
					
					register.getProductList().put(register.getAndIncrementProductIndex(), tempFoodLong);
					
					System.out.println();
					System.out.println("Food product created!");
					System.out.println("Its memory location is: " + tempFoodLong);
					System.out.println();
					
					pMM(register);
					break;
					
				case 'N':
				case 'n':
					
					Food tempFood = new Food(name, price, quantity, vendor);
					
					register.getProductList().put(register.getAndIncrementProductIndex(), tempFood);
					
					System.out.println();
					System.out.println("Food product created!");
					System.out.println("Its memory location is: " + tempFood);
					System.out.println();
					
					pMM(register);
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
			addProduct(register);
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
	
	public void addApparel(Register register) {
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
					
					System.out.println("Enter the product description: ");
					productDesc = scan.nextLine();
					scan.nextLine();
					
					System.out.println("Enter the discount value for future sales (default discount is 0.15): ");
					discount = scan.nextDouble();
					
					System.out.println("Enter the tax rate (default tax rate is 1.08): ");
					taxRate = scan.nextDouble();
					
					Apparel tempApparelLong = new Apparel(name, color, productDesc, price, discount, taxRate, quantity, vendor);
					
					register.getProductList().put(register.getAndIncrementProductIndex(), tempApparelLong);
					
					System.out.println();
					System.out.println("Food product created!");
					System.out.println("Its memory location is: " + tempApparelLong);
					System.out.println();
					
					pMM(register);
					break;
					
				case 'N':
				case 'n':
					
					Apparel tempApparel = new Apparel(name, color, price, quantity, vendor);
					
					register.getProductList().put(register.getAndIncrementProductIndex(), tempApparel);
					
					System.out.println();
					System.out.println("Food product created!");
					System.out.println("Its memory location is: " + tempApparel);
					System.out.println();
					
					pMM(register);
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
			addProduct(register);
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
		String vendor;
		
		char c; char d; char e; //switch variables
		
		
		
		System.out.println("Name of shirt product: ");
		name = scan.nextLine();
		
		System.out.println("Enter the general color of shirt: ");
		color = scan.nextLine();
		
		System.out.println("Shirt is long sleeve? (Y)es | (N)o");
		e = scan.next().charAt(0);
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
					
					System.out.println("Enter the shirt description: ");
					productDesc = scan.nextLine();
					scan.nextLine();
					
					System.out.println("Enter the discount value for future sales (default discount is 0.15): ");
					discount = scan.nextDouble();
					
					System.out.println("Enter the tax rate (default tax rate is 1.08): ");
					taxRate = scan.nextDouble();
					
					Shirt tempShirtLong = new Shirt(name, color, longSleeve, hoodie, productDesc, price, discount, taxRate, sQuantity, mQuantity, lQuantity, xLQuantity, vendor);
					
					register.getProductList().put(register.getAndIncrementProductIndex(), tempShirtLong);
					
					System.out.println();
					System.out.println("Food product created!");
					System.out.println("Its memory location is: " + tempShirtLong);
					System.out.println();
					
					pMM(register);
					break;
					
				case 'N':
				case 'n':
					
					Shirt tempShirt = new Shirt(name, color, longSleeve, hoodie, price, sQuantity, mQuantity, lQuantity, xLQuantity, vendor);
					
					register.getProductList().put(register.getAndIncrementProductIndex(), tempShirt);
					
					System.out.println();
					System.out.println("Food product created!");
					System.out.println("Its memory location is: " + tempShirt);
					System.out.println();
					
					pMM(register);
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
			addProduct(register);
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
					
					System.out.println("Enter the hat description: ");
					productDesc = scan.nextLine();
					scan.nextLine();
					
					System.out.println("Enter the discount value for future sales (default discount is 0.15): ");
					discount = scan.nextDouble();
					
					System.out.println("Enter the tax rate (default tax rate is 1.08): ");
					taxRate = scan.nextDouble();
					
					Hat tempHatLong = new Hat(name, hatType, color, productDesc, price, discount, taxRate, quantity, vendor);
					
					register.getProductList().put(register.getAndIncrementProductIndex(), tempHatLong);
					
					System.out.println();
					System.out.println("Food product created!");
					System.out.println("Its memory location is: " + tempHatLong);
					System.out.println();
					
					pMM(register);
					break;
					
				case 'N':
				case 'n':
					
					Hat tempHat = new Hat(name, hatType, color, price, quantity, vendor);
					
					register.getProductList().put(register.getAndIncrementProductIndex(), tempHat);
					
					System.out.println();
					System.out.println("Hat product created!");
					System.out.println("Its memory location is: " + tempHat);
					System.out.println();
					
					pMM(register);
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
			addProduct(register);
			break;
		default:
			System.out.println("Input error. Starting over.");
			System.out.println();
			addHat(register);
			break;
		}
		
		
		
	}
}

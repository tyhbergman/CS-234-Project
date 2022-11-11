import java.util.Scanner;
import java.util.Set;
import java.util.Map.Entry;

public class ProductMenu {
	
	ProductMenu(){
		
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
				//mainMenuSmash(register);
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
				//addTea(register);
				break;
				
			case 'F':
			case 'f':
				System.out.println("\tYou've selected to add a food product.");
				System.out.println();
				//addFood(register);
				break;
			
			case 'A':
			case 'a':
				System.out.println("\tYou've selected to add an apparel product.");
				System.out.println();
				//chooseApparelType(register);
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
}

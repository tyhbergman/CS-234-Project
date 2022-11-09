import java.util.Scanner;

public class PMM {
	
	public PMM() {}
	
	public static void pMM() {
		
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
				addProduct();
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				break;
			case 6:
				break;
			default:
		}
	}
	
	public static void addProduct() {
		Scanner scan = new Scanner(System.in);
		char c;
		
		System.out.println("\tYou've chosen to add a product.");
		System.out.println();
		
		System.out.println("\tWhat is the type of product?");
		System.out.println("(T)ea | (F)ood | (Apparel) | (M)isc");
		
		c = scan.nextLine().charAt(0);
		System.out.println();
		
		switch (c) {
			case 'T':
			case 't':
				System.out.println("\tYou've selected to add a tea product.");
				System.out.println();
				addTea();
				break;
				
			case 'F':
			case 'f':
				System.out.println("\tYou've selected to add a food product.");
				System.out.println();
				addFood();
				break;
			
			case 'A':
			case 'a':
				System.out.println("\tYou've selected to add an apparel product.");
				System.out.println();
				
				break;
			
			case 'M':
			case 'm':
				System.out.println("\tYou've selected to add a miscellaneous product.");
				System.out.println();
				
				break;
			
			default:
				System.out.println("Input error, try again.");
				System.out.println();
				addProduct();
				break;
				
		}
		
	}
	
	public static Tea addTea() {
		Scanner scan = new Scanner(System.in);
		
		String name;
		int quantity;
		double price;
		String vendor;
		
		char c; char d; //switch variables
		
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
			System.out.println("Would you like to enter additional product information?");
			System.out.println("(Choosing \"No\" will give the product default values.)");
			System.out.println("(Y)es | (N)o");
			d = scan.next().charAt(0);
				switch (d) {
				case 'Y':
				case 'y':
					
					//need advanced method call here
				
				case 'N':
				case 'n':
					
					Tea tempTea = new Tea(name, price, quantity, vendor);
					
					return tempTea;
					
				default:
					System.out.println("Input error. Starting over.");
					System.out.println();
					addTea();
					break;
				}
			
		case 'N':
		case 'n':
			System.out.println("Starting over.");
			System.out.println();
			addProduct();
			break;
		default:
			System.out.println("Input error. Starting over.");
			System.out.println();
			addTea();
			break;
		}
		return null;
		
	}
	
	public static Food addFood() {
		Scanner scan = new Scanner(System.in);
		
		String name;
		int quantity;
		double price;
		String vendor;
		
		char c; char d; //switch variables
		
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
					
					//need advanced method call here
				
				case 'N':
				case 'n':
					
					Food tempFood = new Food(name, price, quantity, vendor);
					
					return tempFood;
					
				default:
					System.out.println("Input error. Starting over.");
					System.out.println();
					addFood();
					break;
				}
			
		case 'N':
		case 'n':
			System.out.println("Starting over.");
			System.out.println();
			addProduct();
			break;
		default:
			System.out.println("Input error. Starting over.");
			System.out.println();
			addFood();
			break;
		}
		return null;
		
	}
	
	public static Apparel addApparel() {
		Scanner scan = new Scanner(System.in);
		char b;
		
		System.out.println("What is the type of apparel product?");
		System.out.println("(H)at | (S)hirt | (M)isc");
		System.out.println();
		b = scan.next().charAt(0);
		
		switch (b) {
		case 'H':
		case 'h':
			System.out.println("\tYou've chosen to add a hat product.");
			System.out.println();
			//addHat();
			break;
		case 'S':
		case 's':
			System.out.println("\tYou've chosen to add a shirt product.");
			System.out.println();
			//addShirt();
			break;
		case 'M':
		case 'm':
			System.out.println("\tYou've chosen to add a miscellaneous product.");
			System.out.println();
			break;
		default:
			System.out.println("Input error. Starting over.");
			System.out.println();
			addApparel();
			break;
		}
		
	}
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package normal;
import java.util.Scanner;

public class SaleMenu {
	
	public void saleMenu(Register register, Employees employees, Employees schedule, ProductMenu productMenu, String name) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("\tSale Menu");
		System.out.println();
		
		System.out.println("1.) Add Item to Cart");
		System.out.println("2.) Execute Sale");
		System.out.println("3.) View Cart");
		System.out.println("4.) Clear Cart");
		System.out.println("5.) Print Product Log");
		System.out.println("6.) Quit Sale Menu");
		
		System.out.println();
		
		System.out.println("Select an option: ");
		
		int x = scan.nextInt();
		
		switch (x) {
			case 1:
				System.out.print("Enter the ID of item: ");
				int temp = scan.nextInt();
				
				//Check if item is in system before adding to cart
				if(!register.getProductList().containsKey(temp)) {
					System.out.println("Error! Product with that ID not in system. Try again.");
					System.out.println();
				} else {
				
				System.out.print("Enter the desired quantity: ");
				int tempQ = scan.nextInt();
				register.addToCart(register.getProductList().get(temp), tempQ);
				System.out.println();
				}
				saleMenu(register, employees, schedule, productMenu, name);
				break;
			case 2:
				register.sale();
				saleMenu(register, employees, schedule, productMenu, name);
				break;
			case 3:
				register.viewCart();
				saleMenu(register, employees, schedule, productMenu, name);
				break;
			case 4:
				register.clearCart();
				saleMenu(register, employees, schedule, productMenu, name);
				break;
			case 5:
				productMenu.getProductLog(register);
				saleMenu(register, employees, schedule, productMenu, name);
				break;
			case 6:
				System.out.println();
				System.out.println("Exiting Sale Menu...");
				System.out.println();
				break;
			default:
				System.out.println("Input error. Starting over.");
				System.out.println();
				saleMenu(register, employees, schedule, productMenu, name);
				break;
				
		}
	}
}

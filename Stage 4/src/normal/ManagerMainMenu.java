/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package normal;

import java.util.Scanner;

public class ManagerMainMenu {
	
	public String managerSignIn(Register register, Employees employees, Employees schedule, String username, String password) {
//		Scanner b = new Scanner(System.in);
//		System.out.println("Enter Manager's name: ");
//		String name = b.nextLine();
		
		// Checks to make sure they are an manager
		boolean nameCheck = employees.nameCheck(username);
		if(nameCheck == true) {
			String managerStatusCheck = employees.isManager(username);
			if(managerStatusCheck == "yes") {
				// Gets password from manager
//				Scanner c = new Scanner(System.in);
//				System.out.println("Enter Password: ");
//				String pass = c.nextLine();
				
				// Checks for correct password
				String passCheck = employees.getPass(username);
				if(password.equals(passCheck)) {
					// Log in successful
					//System.out.println("Manager log in successful\n");
					return "success";
				} else {
					SignIn signIn = new SignIn();
					System.out.println("Password Incorrect. Returning to log in page...");
					return "failed";
				}
			} else {
				SignIn signIn = new SignIn();
				System.out.println("User position is not set as manager. Returning to log in page...");
				return "not_manager";
			}
		} else {
			SignIn signIn = new SignIn();
			System.out.println("User is not in the system. Returning to log in page...");
			return "no_user";
		}
	}
	
	public void managerMainMenu(Register register, Employees employees, Employees schedule, String name) {
		SaleMenu saleMenu = new SaleMenu();
		ProductMenu productMenu = new ProductMenu();
		InputMenu inputMenu = new InputMenu();
		EmployeesMenu employeesMenu = new EmployeesMenu();
		SignIn signIn = new SignIn();
		Scanner scan = new Scanner(System.in);
		
		System.out.println("\tManager Main Menu");
		System.out.println();
		
		System.out.println("1.) Sale Menu");
		System.out.println("2.) Product Management");
		System.out.println("3.) Input Management");
		System.out.println("4.) Employee Management");
		System.out.println("5.) Records");
		System.out.println("6.) Log Out");
		
		int x = scan.nextInt();
		
		switch (x) {
		case 1:
			saleMenu.saleMenu(register, employees, schedule, productMenu, name);
			managerMainMenu(register, employees, schedule, name);
			break;
		case 2:
			productMenu.productMenu(register);
			managerMainMenu(register, employees, schedule, name);
			break;
		case 3:
			inputMenu.inputMenu();
			managerMainMenu(register, employees, schedule, name);
			break;
		case 4:
			employeesMenu.employeesMenu(register, employees, schedule, name);
			managerMainMenu(register, employees, schedule, name);
			break;
		case 5:
			register.salesRecords(register);
			System.out.println();
			System.out.println("\t\t| Employee Records |");
			employees.printEmployees();
			inputMenu.displayCostTotals();
			System.out.println();
			System.out.println("*** Exporting cost report is still under development ***");
			System.out.println();
			managerMainMenu(register, employees, schedule, name);
			break;
		case 6:
			System.out.println();
			System.out.println("Logging out...");
			System.out.println();
			signIn.signIn(register, employees, schedule);
			break;
		default:
			System.out.println("Input error. Resetting...");
			System.out.println();
			managerMainMenu(register, employees, schedule, name);
			break;
		}
		
	}
}

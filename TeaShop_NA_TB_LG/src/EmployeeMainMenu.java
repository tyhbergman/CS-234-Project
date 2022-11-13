import java.util.Scanner;

public class EmployeeMainMenu {
	
	EmployeeMainMenu(){
		
	}
	
	public String employeeSignin(Register register, Employees employees, Employees schedule) {
		Scanner b = new Scanner(System.in);
		System.out.println("Enter Employee name");
		String name = b.nextLine();
		
		boolean nameCheck = employees.nameCheck(name);
		if(nameCheck == true) {
			// Gets password from employee
			Scanner c = new Scanner(System.in);
			System.out.println("Enter Password");
			String SSN = c.nextLine();
			
			// Checks for correct password
			String SSNCheck = employees.getSSN(name);
			if(SSN.equals(SSNCheck)) {
				// Log in successful
				System.out.println("Employee log in successful.");
				System.out.println();
				return name;
			} else {
				SignIn signIn = new SignIn();
				System.out.println("Password Incorrect. Returning to log in page...");
				signIn.signIn(register, employees, schedule);
			}
		} else {
			SignIn signIn = new SignIn();
			System.out.println("User is not in system. Returning to log in page...");
			signIn.signIn(register, employees, schedule);
		}
		System.out.println();
		return " ";
	}
	
	public void employeeMainMenu(Register register, Employees employees, Employees schedule, String name) {
		ProductMenu productMenu = new ProductMenu();
		SaleMenu saleMenu = new SaleMenu();
		Scanner scan = new Scanner(System.in);
		
		System.out.println("\tMain Menu");
		System.out.println();
		
		System.out.println("1.) Sale Menu");
		System.out.println("2.) Employee Personal Information");
		System.out.println("3.) Log Out");
		
		int x = scan.nextInt();
		
		switch (x) {
		case 1:
			saleMenu.saleMenu(register, employees, schedule, productMenu, name);
			employeeMainMenu(register, employees, schedule, name);
			break;
		case 2:
			employees.printEmployeeInfo(name);
			employeeMainMenu(register, employees, schedule, name);
			break;
		case 3:
			System.out.println();
			System.out.println("Logging out...\n");
			SignIn signIn = new SignIn();
			signIn.signIn(register, employees, schedule);
			break;
		default:
			System.out.println("Input error. Resetting...");
			System.out.println();
			employeeMainMenu(register, employees, schedule, name);
			break;
		}
	}
}

import java.util.Scanner;

public class ManagerMainMenu {

	ManagerMainMenu(){
		
	}
	
	public String managerSignIn(Register register, Employees employees, Persistence file) {
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
					return name;
				} else {
					SignIn signIn = new SignIn();
					System.out.println("Password Incorrect. Returning to log in page...");
					signIn.signIn(register, file);
				}
			} else {
				SignIn signIn = new SignIn();
				System.out.println("User position is not set as manager. Returning to log in page...");
				signIn.signIn(register, file);
			}
		} else {
			SignIn signIn = new SignIn();
			System.out.println("User is not in the system. Returning to log in page...");
			signIn.signIn(register, file);
		}
		return " ";
	}
	
	public void managerMainMenu(Register register, Employees employees, Employees schedule, String name, Persistence file) {
		ProductMenu productMenu = new ProductMenu();
		InputMenu inputMenu = new InputMenu();
		EmployeesMenu employeesMenu = new EmployeesMenu();
		Scanner scan = new Scanner(System.in);
		
		System.out.println("\tMain Menu");
		System.out.println();
		
		System.out.println("1.) Product Management");
		System.out.println("2.) Input Management");
		System.out.println("3.) Employee Management");
		System.out.println("4.) Records");
		System.out.println("5.) Log Out");
		
		int x = scan.nextInt();
		
		switch (x) {
		case 1:
			productMenu.productMenu(register);
			managerMainMenu(register, employees, schedule, name, file);
			break;
		case 2:
			inputMenu.inputMenu(register);
			managerMainMenu(register, employees, schedule, name, file);
			break;
		case 3:
			employeesMenu.employeesMenu(register, schedule, employees, name, file);
			//employeeMainMenu(register, employees, name);
			break;
		case 4:
			employees.printEmployeeInfo(name);
			//employeeMainMenu(register, employees, name);
			break;
		case 5:
			System.out.println();
			System.out.println("Logging out...");
			SignIn signIn = new SignIn();
			signIn.signIn(register, file);
			break;
		default:
			System.out.println("Input error. Resetting...");
			System.out.println();
			managerMainMenu(register, employees, schedule, name, file);
			break;
		}
	}
}

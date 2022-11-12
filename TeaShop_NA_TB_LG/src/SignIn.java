import java.util.Scanner;

public class SignIn {

	SignIn(){
		
	}
	
	public void signIn(Register register, Employees employees, Employees schedule) {
		Scanner scan = new Scanner(System.in);
		employees.addDefaultEmployee();
		
		System.out.println("\tSign In");
		System.out.println();
		System.out.println("1.) Employee Log In");
		System.out.println("2.) Manager Log In");
		System.out.println("3.) Quit Tea Shop Application");
		
		int x = scan.nextInt();
		
		switch (x) {
		case 1:
			EmployeeMainMenu employeeSignIn = new EmployeeMainMenu();
			String name = employeeSignIn.employeeSignin(register, employees, schedule);
			employeeSignIn.employeeMainMenu(register, employees, schedule, name);
			break;
		case 2:
			ManagerMainMenu managerSignIn = new ManagerMainMenu();
			name = managerSignIn.managerSignIn(register, employees, schedule);
			managerSignIn.managerMainMenu(register, employees, schedule, name);
			break;
		case 3:
			System.out.println();
			System.out.println("Exiting.");
			break;
		default:
			System.out.println("Input error. Restarting...");
			System.out.println();
			signIn(register, employees, schedule);
			break;
		}
	}
}

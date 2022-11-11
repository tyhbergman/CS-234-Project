import java.util.Scanner;

public class EmployeesMenu {

	EmployeesMenu(){
		
	}
	
	public static void employeesMenu(Register register, Employees employees, Employees schedule, String name) {
		int runTime = 1;
		while(runTime!=0) {
			// User selection
			Scanner a = new Scanner(System.in);
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
	}
}

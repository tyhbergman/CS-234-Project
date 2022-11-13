import java.util.Scanner;

public class EmployeesMenu {

	public void employeesMenu(Register register, Employees employees, Employees schedule, String name) {
		ManagerMainMenu managerMainMenu = new ManagerMainMenu();
		int x; //counter variable
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("\tEmployee Management Menu");
		System.out.println();
		
		System.out.println("1.) Manage Employees");
		System.out.println("2.) Manage Employee Schedules");
		System.out.println("3.) View Personal Information");
		System.out.println("4.) Exit Employee Management");		
		System.out.println();
		
		System.out.println("Select an option: ");
		
		x = scan.nextInt();
		
		switch (x) {
		case 1:
			Scanner scan2 = new Scanner(System.in);
			System.out.println("What would you like to do: ");
			System.out.println("1.) Add Employee");
			System.out.println("2.) Edit Current Employee");
			System.out.println("3.) Delete Employee");
			System.out.println("4.) Print All Employee Information");
			System.out.println("5.) Exit Manage Employees");
			x = scan2.nextInt();
			
			switch (x) {
				case 1:
					// Add employee
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
					char isManager = l.next().charAt(0);
					if(isManager == 'a' || isManager == 'A') {
						type = "yes";
					}										
					
					employees.addEmployee(newName, empid, position, wage, ssn, hours, type);
					System.out.print("New employee has been created. \n\n");
					employeesMenu(register, employees, schedule, name);
					break;
				case 2:
					// Edit current employee
					Scanner n = new Scanner(System.in);
					System.out.println("Enter the employee's name: ");
					String empName = n.nextLine();
					
					Scanner o = new Scanner(System.in);
					System.out.println("What would you like to edit?");
					System.out.println("1.) Name");
					System.out.println("2.) Employee Id");
					System.out.println("3.) Position");
					System.out.println("4.) Wage");
					System.out.println("5.) Hours");
					System.out.println("6.) Social Security Number");
					System.out.println("7.) Manager Status");
					x = o.nextInt();
					
					switch(x) {
						case 1:
							// Change name
							Scanner p = new Scanner(System.in);
							System.out.println("Enter the employee's new name: ");
							String newName2 = p.nextLine();
							
							employees.changeName(empName, newName2);
							System.out.print("Employee's name has been updated.\n");
							break;
						case 2:
							// Change Id
							Scanner q = new Scanner(System.in);
							System.out.println("Enter the employee's new Id: ");
							String empid2 = q.nextLine();
							
							employees.changeEmpid(empName, empid2);
							System.out.print("Employee's Id has been updated.\n");
							break;
						case 3:
							// Change position
							Scanner r = new Scanner(System.in);
							System.out.println("Enter the employee's new position: ");
							String position2 = r.nextLine();
							
							employees.changePosition(empName, position2);
							System.out.print("Employee's position has been updated.\n");
							break;
						case 4:
							// Change wage
							Scanner s = new Scanner(System.in);
							System.out.println("Enter the employee's new wage: ");
							String wage2 = s.nextLine();
							
							employees.changeWage(empName, wage2);
							System.out.print("Employee's wage has been updated.\n");
							break;
						case 5:
							// Change hours
							Scanner t = new Scanner(System.in);
							System.out.println("Enter the employee's new weekly hours: ");
							String hours2 = t.nextLine();
							
							employees.changeHours(empName, hours2);
							System.out.print("Employee's hours has been updated.\n");
							break;
						case 6:
							// Change social security number
							Scanner u = new Scanner(System.in);
							System.out.println("Enter the employee's new social security number: ");
							String ssn2 = u.nextLine();
							
							employees.changeSSN(empName, ssn2);
							System.out.print("Employee's ssn has been updated.\n");
							break;
						case 7:
							// Change employee's position rank
							String type2 = "no";
							Scanner v = new Scanner(System.in);
							System.out.println("Is employee [a] manager or [b] employee: ");
							char choice4 = v.next().charAt(0);
							
							if(choice4 == 'a' || choice4 == 'A') {
								type2 = "yes";
							} else if(choice4 == 'b' || choice4 == 'B') {
								type2 = "no";
							} else {
								System.out.print("Invalid Input.");
							}
							employees.isManagerChange(empName, type2);
							System.out.print("Employee's position rank has been updated.\n");
							break;
						default:
							System.out.println("Invalid input.");
							employeesMenu(register, employees,schedule, name);
							break;
					}
					employeesMenu(register, employees, schedule, name);
					break;
				case 3:
					// Delete employee
					System.out.print("Current employees");
					employees.printEmployees();
					
					Scanner w = new Scanner(System.in);
					System.out.println("Enter inactive employee's name: ");
					String empName2 = w.nextLine();
					
					employees.deleteEmployee(empName2);
					System.out.print("Employee has been deleted.\n");
					employeesMenu(register, employees, schedule, name);
					break;
				case 4:
					// Print all employee information
					System.out.print("Employee Records");
					employees.printEmployees();
					employeesMenu(register, employees, schedule, name);
					break;
				case 5:
					// Exit
					System.out.println("Exiting Manage Employees...");
					employeesMenu(register, employees, schedule, name);
					break;
				default:
					// Loop Error
					System.out.println("Invaild Input.");
					employeesMenu(register, employees, schedule, name);
					break;
			}
			break;
		case 2:
			// Manage employee schedules
			Scanner e = new Scanner(System.in);
			System.out.println("Would you like to: ");
			System.out.println("1.) Create/Edit a shift for an employee");
			System.out.println("2.) Delete a shift from an employee");
			System.out.println("3.) Remove an employee for the entire schedule");
			System.out.println("4.) Print the schedule information");
			x = e.nextInt();
			
			switch(x) {
				case 1:
					// Create/Edit a shift 
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
					employeesMenu(register, employees, schedule, name);
					break;
				case 2:
					// Delete a shift 
					Scanner n = new Scanner(System.in);
					System.out.println("Enter the name of the employee: ");
					String Name2 = n.nextLine();
					
					Scanner o = new Scanner(System.in);
					System.out.println("Enter the day of the shift: ");
					String shiftDay2 = o.nextLine();
					
					schedule.removeShift(Name2, shiftDay2);
					employeesMenu(register, employees, schedule, name);
					break;
				case 3:
					// Remove an employee 
					Scanner y = new Scanner(System.in);
					System.out.println("Enter the name of the employee: ");
					String Name3 = y.nextLine();
					schedule.remove(Name3);
					employeesMenu(register, employees, schedule, name);
					break;
				case 4:
					// Print entire schedule
					schedule.printSchedule();
					employeesMenu(register, employees, schedule, name);
					break;
				default: 
					System.out.println("Invaild Input.");
					employeesMenu(register, employees, schedule, name);
					break;
			}
			break;
		case 3: 
			// View personal information

			employees.printEmployeeInfo(name);
			employeesMenu(register, employees, schedule, name);
			break;
		case 4:
			// Exit
			managerMainMenu.managerMainMenu(register, employees, schedule, name);
		default:
			break;
		}
	}
}

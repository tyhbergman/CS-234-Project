package EmployeePack;

public class Employees {

	private String name;
	private int empid; 
	private String position;
	private double wage;
	private String ssn;
	private int hours;
	
	Employees(){
		
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setEmpid(int empid) {
		this.empid = empid;
	}
	
	public int getEmpid() {
		return empid;
	}
	
	public void setPosition(String position) {
		this.position = position;
	}
	
	public String getPosition() {
		return position;
	}
	
	public void setWage(double wage) {
		this.wage = wage;
	}
	
	public double getWage() {
		return wage;
	}
	
	public void setSSN(String ssn) {
		this.ssn = ssn;
	}
	
	public String getSSN() {
		return ssn;
	}
	
	public void setHours(int hours) {
		this.hours = hours;
	}
	
	public int getHours() {
		return hours;
	}
	
	//public String searchEmp() {
		// Not complete atm
	//}	
	
}

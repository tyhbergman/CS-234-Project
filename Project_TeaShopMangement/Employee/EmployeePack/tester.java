package EmployeePack;

public class tester {

	public static void main(String[] args) {
		Employees ty = new Employees();
		Employees nick = new Employees();
		Employees laur = new Employees();
		
		ty.setName("Ty");
		nick.setName("Nick");
		laur.setName("Lauren");
		
		ty.setEmpid(123);
		nick.setEmpid(456);
		laur.setEmpid(789);

		ty.setPosition("Teammate 1");
		nick.setPosition("Teammate 2");
		laur.setPosition("Teammate 3");
		
		ty.setWage(10.00);
		nick.setWage(20.00);
		laur.setWage(30.00);
		
		ty.setSSN("*****0123");
		nick.setSSN("*****0456");
		laur.setSSN("*****0789");
		
		ty.setHours(10);
		nick.setHours(20);
		laur.setHours(30);
		
		System.out.print("Ty:");
		System.out.print("Name: " + ty.getName());
		System.out.print("Empid: " + ty.getEmpid());
		System.out.print("Position: " + ty.getPosition());
		System.out.print("Wage: " + ty.getWage());
		System.out.print("SSN: " + ty.getSSN());
		System.out.print("Hours: " + ty.getHours());
		
		System.out.print("Nick:");
		System.out.print("Name: " + nick.getName());
		System.out.print("Empid: " + nick.getEmpid());
		System.out.print("Position: " + nick.getPosition());
		System.out.print("Wage: " + nick.getWage());
		System.out.print("SSN: " + nick.getSSN());
		System.out.print("Hours: " + nick.getHours());
		
		System.out.print("Laur:");
		System.out.print("Name: " + laur.getName());
		System.out.print("Empid: " + laur.getEmpid());
		System.out.print("Position: " + laur.getPosition());
		System.out.print("Wage: " + laur.getWage());
		System.out.print("SSN: " + laur.getSSN());
		System.out.print("Hours: " + laur.getHours());
	}

}

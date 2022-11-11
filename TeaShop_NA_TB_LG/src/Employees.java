import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Employees {

	// Search method not working yet
	// Needs data to be fed in from Schedule
	
	private Map<String,ArrayList<String>> employees;
	private Map<String,Map<String, ArrayList<String>>> schedule;
	
	Employees(){
		this.employees = new HashMap<>();
		this.schedule = new HashMap<>();
	}
	
	public void addEmployee(String name, String empid, String position, String wage, String ssn, String hours, String type) {
		// Creates an ArrayList and adds each value to the ArrayList
		employees.put(name, new ArrayList<String>());
		employees.get(name).add(empid);
		employees.get(name).add(position);
		employees.get(name).add(wage);
		employees.get(name).add(ssn);
		employees.get(name).add(hours);
		employees.get(name).add(type);
	}
	
	public void addDefaultEmployee() {
		employees.put("manager", new ArrayList<String>());
		employees.get("manager").add("00001");
		employees.get("manager").add("Default Employee Profile");
		employees.get("manager").add("0.00");
		employees.get("manager").add("1234");
		employees.get("manager").add("0");
		employees.get("manager").add("yes");
	}
	
	public void printEmployeeInfo(String name) {
		System.out.print("\n");
        ArrayList<String> info = employees.get(name);
        String empid = info.get(0);
        String position = info.get(1);
        String wage = info.get(2);
        String ssn = info.get(3);
        String hours = info.get(4);
        String type = info.get(5);
            
        System.out.print("______________________________");
        System.out.print("\nEmployee Name: " + name);
        System.out.print("\n     Id: " + empid);
        System.out.print("\n     Position: " + position);
        System.out.print("\n     Wage: " + wage);
        System.out.print("\n     SSN: " + ssn);
        System.out.print("\n     Hours: " + hours);
        System.out.print("\n     isManager Type: " + type);
        System.out.print("\n");
    }
	
	public void printEmployees() {
		System.out.print("\n");
		for(Map.Entry<String,ArrayList<String>> entry: employees.entrySet())
        {
            String key=entry.getKey();
            ArrayList<String> info = employees.get(key);
            String empid = info.get(0);
            String position = info.get(1);
            String wage = info.get(2);
            String ssn = info.get(3);
            String hours = info.get(4);
            String type = info.get(5);
            
            System.out.print("______________________________");
            System.out.print("\nEmployee Name: " + key);
            System.out.print("\n     Id: " + empid);
            System.out.print("\n     Position: " + position);
            System.out.print("\n     Wage: " + wage);
            System.out.print("\n     SSN: " + ssn);
            System.out.print("\n     Hours: " + hours);
            System.out.print("\n     isManager Type: " + type);
            System.out.print("\n");
        }
	}
	
	public void deleteEmployee(String name) {
		employees.remove(name);
	}
	
	public void changeName(String name, String newName) {
		ArrayList<String> info = employees.get(name);
		employees.put(newName, info);
		employees.remove(name);
	}
	
	public boolean nameCheck(String name) {
		boolean result = employees.containsKey(name);
		return result;
	}
	
	public void changeEmpid(String name, String empid) {
		ArrayList<String> info = employees.get(name);
		info.set(0, empid);
		employees.put(name, info);
		
	}
	
	public String getEmpid(String name) {
		ArrayList<String> info = employees.get(name);
		String empid = info.get(0);
		return empid;
	}
	
	public void changePosition(String name, String position) {
		ArrayList<String> info = employees.get(name);
		info.set(1, position);
		employees.put(name, info);
	}
	
	public String getPosition(String name) {
		ArrayList<String> info = employees.get(name);
		String position = info.get(1);
		return position;
	}
	
	public void changeWage(String name, String wage) {
		ArrayList<String> info = employees.get(name);
		info.set(2, wage);
		employees.put(name, info);
	}
	
	public String getWage(String name) {
		ArrayList<String> info = employees.get(name);
		System.out.println(info);
		String wage = info.get(2);
		return wage;
	}
	
	public void changeSSN(String name, String ssn) {
		ArrayList<String> info = employees.get(name);
		info.set(3, ssn);
		employees.put(name, info);
	}
	
	public String getSSN(String name) {
		ArrayList<String> info = employees.get(name);
		String ssn = info.get(3);
		return ssn;
	}
	
	public void changeHours(String name, String hours) {
		ArrayList<String> info = employees.get(name);
		info.set(4, hours);
		employees.put(name, info);
	}
	
	public String getHours(String name) {
		ArrayList<String> info = employees.get(name);
		String hours = info.get(4);
		return hours;
	}
	
	//public String searchEmp() {
		// Not complete atm
	//}	
	
	public void isManagerChange(String name, String type) {
		ArrayList<String> info = employees.get(name);
		info.set(5, type);
		employees.put(name, info);
	}
	
	public String isManager(String name) {
		ArrayList<String> info = employees.get(name);
		String type = info.get(5);
		return type;
	}
	
	public void addShift(String name, String shiftDay, String shiftHourStart, String shiftMinStart, String shiftStartMeridiem, String shiftHourEnd, String shiftMinEnd, String shiftEndMeridiem) {
		Map<String, ArrayList<String>> innerScheduleMap = new HashMap<String, ArrayList<String>>();
		
		// Creates an ArrayList and adds each value to the ArrayList
		innerScheduleMap.put(shiftDay, new ArrayList<String>());
		innerScheduleMap.get(shiftDay).add(shiftHourStart);
		innerScheduleMap.get(shiftDay).add(shiftMinStart);
		innerScheduleMap.get(shiftDay).add(shiftStartMeridiem);
		innerScheduleMap.get(shiftDay).add(shiftHourEnd);
		innerScheduleMap.get(shiftDay).add(shiftMinEnd);
		innerScheduleMap.get(shiftDay).add(shiftEndMeridiem);
		
		schedule.put(name, innerScheduleMap);
	}
	
	public double dailyHours(String name, String shiftDay) {
		// Readies the ArrayList from the key to be cherry picked
		Map<String, ArrayList<String>> values = schedule.get(name);
		ArrayList<String> value = values.get(shiftDay);
		
		// Pulls specific variables from key and changes item from String to Double
		String shiftHourStart = value.get(0);
		double hourStart = Double.parseDouble(shiftHourStart);
		String shiftMinStart = value.get(1);
		double minStart = Double.parseDouble(shiftMinStart);
		String shiftHourEnd = value.get(3);
		double hourEnd = Double.parseDouble(shiftHourEnd);
		String shiftMinEnd = value.get(4);
		double minEnd = Double.parseDouble(shiftMinEnd);
		
		// Calculates total scheduled time for a single day
		String startMeridian = value.get(2);
		if(startMeridian.equals("PM")) {
			hourStart = hourStart + 12;
		}
		String endMeridian = value.get(5);
		if(endMeridian.equals("PM")) {
			hourEnd = hourEnd + 12;
		}
		minStart = minStart/60;
		minEnd = minEnd/60;
		double start = hourStart + minStart;
		double end = hourEnd + minEnd;		
		double dailyTotal = end - start;
		return dailyTotal;
	}
	
	public double totalHours(String name) {
		// Calculation of daily hours copied and pasted (will refactor soon)
		double totalHours = 0;
		Map<String, ArrayList<String>> values = schedule.get(name);
		for(Entry<String, ArrayList<String>> entry: values.entrySet())
        {
            String shiftDay = entry.getKey();
    		ArrayList<String> value = values.get(shiftDay);
    		
    		// Pulls specific variables from key and changes item from String to Double
    		String shiftHourStart = value.get(0);
    		double hourStart = Double.parseDouble(shiftHourStart);
    		String shiftMinStart = value.get(1);
    		double minStart = Double.parseDouble(shiftMinStart);
    		String shiftHourEnd = value.get(3);
    		double hourEnd = Double.parseDouble(shiftHourEnd);
    		String shiftMinEnd = value.get(4);
    		double minEnd = Double.parseDouble(shiftMinEnd);
    		
    		// Calculates total scheduled time for a single day
    		String startMeridian = value.get(2);
    		if(startMeridian.equals("PM")) {
    			hourStart = hourStart + 12;
    		}
    		String endMeridian = value.get(5);
    		if(endMeridian.equals("PM")) {
    			hourEnd = hourEnd + 12;
    		}
    		minStart = minStart/60;
    		minEnd = minEnd/60;
    		double start = hourStart + minStart;
    		double end = hourEnd + minEnd;		
    		double dailyTotal = end - start;
    		totalHours = totalHours + dailyTotal;
        }
		return totalHours;
	}
	
	public void removeShift(String name, String shiftDay) {
		// Removes a shift
		Map<String, ArrayList<String>> values = schedule.get(name);
		//innerScheduleMap.remove(shiftDay);
	}
	
	public void printSchedule() {
		System.out.print("\n");
		for(Entry<String, Map<String, ArrayList<String>>> entry: schedule.entrySet()) {
	        String key=entry.getKey();
	        System.out.print(schedule);
		}
	}
}

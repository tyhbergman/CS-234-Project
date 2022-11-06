package EmployeePack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Schedule {
	// Needs to pull data from Employee class
	private Map<String,ArrayList<String>> schedule;
	
	Schedule(){
		this.schedule = new HashMap<>();
	}
	
	public void addShift(String shiftDay, String shiftHourStart, String shiftMinStart, String shiftStartMeridiem, String shiftHourEnd, String shiftMinEnd, String shiftEndMeridiem) {
		// Creates an ArrayList and adds each value to the ArrayList
		schedule.put(shiftDay, new ArrayList<String>());
		schedule.get(shiftDay).add(shiftHourStart);
		schedule.get(shiftDay).add(shiftMinStart);
		schedule.get(shiftDay).add(shiftStartMeridiem);
		schedule.get(shiftDay).add(shiftHourEnd);
		schedule.get(shiftDay).add(shiftMinEnd);
		schedule.get(shiftDay).add(shiftEndMeridiem);
	}
	
	public double dailyHours(String shiftDay) {
		// Readies the ArrayList from the key to be cherry picked
		ArrayList<String> values = schedule.get(shiftDay);
		
		// Pulls specific variables from key and changes item from String to Double
		String shiftHourStart = values.get(0);
		double hourStart = Double.parseDouble(shiftHourStart);
		String shiftMinStart = values.get(1);
		double minStart = Double.parseDouble(shiftMinStart);
		String shiftHourEnd = values.get(3);
		double hourEnd = Double.parseDouble(shiftHourEnd);
		String shiftMinEnd = values.get(4);
		double minEnd = Double.parseDouble(shiftMinEnd);
		
		// Calculates total scheduled time for a single day
		String startMeridian = values.get(2);
		if(startMeridian.equals("PM")) {
			hourStart = hourStart + 12;
		}
		String endMeridian = values.get(5);
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
	
	public double totalHours() {
		// Calculation of daily hours copied and pasted (will refactor soon)
		double totalHours = 0;
		for(Map.Entry<String,ArrayList<String>> entry: schedule.entrySet())
        {
            String shiftDay = entry.getKey();
            ArrayList<String> values = schedule.get(shiftDay);
    		
    		// Pulls specific variables from key and changes item from String to Double
    		String shiftHourStart = values.get(0);
    		double hourStart = Double.parseDouble(shiftHourStart);
    		String shiftMinStart = values.get(1);
    		double minStart = Double.parseDouble(shiftMinStart);
    		String shiftHourEnd = values.get(3);
    		double hourEnd = Double.parseDouble(shiftHourEnd);
    		String shiftMinEnd = values.get(4);
    		double minEnd = Double.parseDouble(shiftMinEnd);
    		
    		// Calculates total scheduled time for a single day
    		String startMeridian = values.get(2);
    		if(startMeridian.equals("PM")) {
    			hourStart = hourStart + 12;
    		}
    		String endMeridian = values.get(5);
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
	
	public void removeShift(String shiftDay) {
		// Removes a shift
		schedule.remove(shiftDay);
	}
}

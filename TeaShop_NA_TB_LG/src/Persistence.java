import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

public class Persistence {
	private FileReader readFile;
	private FileWriter writeFile;
	private String reprint;
	private int transNum;
	
	Persistence() throws IOException{
		// You have to download PersistentInfo.txt with the correct path placement
		// Creates a path to PersistentInfo.txt and then reads the file
		this.readFile = new FileReader("C:\\CS234_Group\\Persistent Information\\PersistentInfo.txt");
		this.writeFile = new FileWriter("C:\\\\CS234_Group\\\\Persistent Information\\\\PersistentInfo.txt");
		this.transNum = 1;
	}
	
	public int findLine(String line) {
		Scanner scan = new Scanner(readFile);
			
		// Prints out the file line by line
		int lineNumber = 1;
		while (scan.hasNextLine()) {
			String nextLine = scan.nextLine();
			System.out.println("line: " + lineNumber);
			if (nextLine.equals(line)) {
			    return lineNumber;
			} else {
				lineNumber++;
			}
		}
		System.out.print("Line does not exist in file.");
		return lineNumber=0;
	}
	
	public int findTransactionRecord(int transNum) {
		String transNumCon = String.valueOf(transNum);
		String transLin = "	Transaction " + transNumCon;
		System.out.println(transLin);
		int line = findLine(transLin);
		return line;
		
	}
	
	public String addTrans(Double subTotal, int quantity, double total) {
		double taxCost = total-subTotal;
		String trans = "\n	Transaction " + transNum
				+ "\n		Transaction Id:"
				+ "\n			'" + transNum + "'"
				+ "\n		Total Cost:"
				+ "\n			'" + total + "'"
				+ "\n		Sub-Total Cost:"
				+ "\n			'" + subTotal + "'"
				+ "\n		Tax Cost:"
				+ "\n			'" + taxCost +"'"
				+ "\n		Total Quantity:"
				+ "\n			'" + quantity + "'";
		return trans;
	}
	
	public void write(String trans) throws IOException {
		String title = "Records: Tea Shop"
				+ "\n";
		String pastTrans = "\nRecords - Past Transactions"
				+ trans;
		
		reprint = title + pastTrans;
		writeFile.write(reprint);
		writeFile.close();
	}
			
}

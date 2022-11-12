import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Persistence {
	private FileReader file;
	
	Persistence() throws FileNotFoundException{
		// You have to download PersistentInfo.txt with the correct path placement
		// Creates a path to PersistentInfo.txt and then reads the file
		this.file = new FileReader("C:\\CS234_Group\\Persistent Information\\PersistentInfo.txt");
	}
	
	public int findLine(String line) {
		Scanner scan = new Scanner(file);
			
		// Prints out the file line by line
		int lineNumber = 1;
		while (scan.hasNextLine()) {
			String nextLine = scan.nextLine();
			System.out.println("line: " + lineNumber);
			if (nextLine.equals(line)) {
				scan.close();
			    return lineNumber;
			} else {
				lineNumber++;
			}
		}
		System.out.print("Line does not exist in file.");
		scan.close();
		return lineNumber=0;
	}
	
	public int findTransactionRecord(int transNum) {
		String transNumCon = String.valueOf(transNum);
		String transLin = "	Transaction " + transNumCon;
		System.out.println(transLin);
		int line = findLine(transLin);
		return line;
		
	}
	
	public String addTransactionRecord() {
		String something = " ";
		return something;
	}
			
}

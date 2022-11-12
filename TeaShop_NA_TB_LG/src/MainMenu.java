public class MainMenu {

	public static void main(String[] args) throws Exception {
		Persistence file = new Persistence();
		Register register = new Register();
		SignIn signIn = new SignIn();
		
		int line = file.findTransactionRecord(0);
		System.out.println("000 found on: "+ line);
		file = new Persistence();
		int line2 = file.findTransactionRecord(1);
		System.out.println("001 found on: "+ line2);
		signIn.signIn(register);

	}
	
}

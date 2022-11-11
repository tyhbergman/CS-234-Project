import java.util.Scanner;

public class MainMenu {

	public static void main(String[] args) {
		Register register = new Register();
		SignIn signIn = new SignIn();
		
		signIn.signIn(register);

	}
	
}

public class MainMenu {

	public static void main(String[] args) throws Exception {
		Persistence file = new Persistence();
		Register register = new Register();
		SignIn signIn = new SignIn();
		
		Product x = new Tea(register, "Coconut Tea", 4.50, 30, "Jimmy's");
		Tea a = new Tea(register, "Coconut Tea", 4.50, 30, "Jimmy's");
		Tea b = new Tea(register, "Matcha Tea", 5.02, 0, "Harry's");
		Tea c = new Tea(register, "Lemon Tea", 3.20, 80, "Jimmy's");
		Food d = new Food(register, "Cheesecake", 4.50, 10, "Anne's");
		Shirt e = new Shirt(register, "Sunset Logo", "Black", false, false, 4.50, 10,15, 15, 10, "Banana Republic");
		Tea f = new Tea(register, "Passonfruit", 5.02, 30, "Harry's");
		Hat g = new Hat(register, "Logo Beanie", "Beanie", "Tan", 3.20, 80, "Banana Republic");
		Food h = new Food(register, "Butter Biscuits", 2, 20, "Anne's");
		
		signIn.signIn(register);

	}
	
}

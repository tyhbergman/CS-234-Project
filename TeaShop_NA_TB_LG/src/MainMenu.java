import java.util.Scanner;

public class MainMenu {

	public static void main(String[] args) {
		Register store1 = new Register();
		SignIn signIn = new SignIn();
		
		Product x = new Product(store1, "Coconut Tea", 4.50, 30, "Jimmy's");
		Tea a = new Tea(store1, "Coconut Tea", 4.50, 30, "Jimmy's");
		Tea b = new Tea(store1, "Matcha Tea", 5.02, 0, "Harry's");
		Tea c = new Tea(store1, "Lemon Tea", 3.20, 80, "Jimmy's");
		Food d = new Food(store1, "Cheesecake", 4.50, 10, "Anne's");
		Shirt e = new Shirt(store1, "Sunset Logo", "Black", false, false, 4.50, 10, 15, 15, 10, "Banana Republic");
		Tea f = new Tea(store1, "Passionfruit Tea", 5.02, 0, "Harry's");
		Hat g = new Hat(store1, "Logo Beanie", "Beanie", "Tan", 3.20, 80, "Banana Republic");
		Food h = new Food(store1, "Butter Biscuits", 2, 20, "Anne's");
		
		
		
		
		signIn.signIn(store1);

	}
	
}

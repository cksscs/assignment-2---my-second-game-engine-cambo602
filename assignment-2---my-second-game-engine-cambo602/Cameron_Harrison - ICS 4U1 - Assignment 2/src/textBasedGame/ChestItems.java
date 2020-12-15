package textBasedGame;

public class ChestItems {

	public static void openChest(Player p) {
		
		System.out.println("Opening chest");
		
		int choice = randomNum(1, 3); // Generates random number to choose which reward the player gets
		
		// Adds two to the max and min of one of the players stats 
		if (choice == 1) {
			
			p.setDamageMin(p.getDamageMin() + 2);
			p.setDamageMax(p.getDamageMax() + 2);
			
			System.out.println("Max, and min damage increased by two");
		}
		else if (choice == 2) {
			p.setArmourMin(p.getArmourMin() + 2);
			p.setArmourMax(p.getArmourMax() + 2);
			
			System.out.println("Max, and min armour increased by two");
		}
		else {
			p.setHealMin(p.getHealMin() + 2);
			p.setHealMax(p.getHealMax() + 2);
			
			System.out.println("Max, and min healing increased by two");
		}
	}
	
	static int randomNum(int i, int j) { // Generates a random number
		
		int min = i;
		int max = j;
		
		int range = max - min + 1;
		
		return (int)(Math.random() * range) + min;
		
	}

}

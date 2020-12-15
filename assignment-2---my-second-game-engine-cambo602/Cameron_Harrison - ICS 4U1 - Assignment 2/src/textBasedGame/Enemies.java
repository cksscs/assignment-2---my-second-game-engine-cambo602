package textBasedGame;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Enemies {

	private String file; // Stores the text file on the enemy

	private String name; // Stores the name of the enemy
	private String deathQuote; // Stores the phrase printed after the enemy dies

	private String[] enemy = new String[11]; // Stores picture of the enemy

	// Vars that store the enemies stats
	private int hp; // Stores enemies health
	private int minHeal; // Stores minimum healing power of the enemy
	private int maxHeal; // Stores maximum healing power of the enemy
	private int minDamage; // Stores min damage enemy can deal
	private int maxDamage; // Stores max damage enemy can deal

	public Enemies(int h, String f, int minH, int maxH, int minD, int maxD) {
		this.hp = h;
		this.file = f;
		this.minHeal = minH;
		this.maxHeal = maxH;
		this.minDamage = minD;
		this.maxDamage = maxD;
	}

	public void genEnemies() { // Sets the enemy array equal to the enemy text file 
		BufferedReader enemyInput;
		try {
			enemyInput = new BufferedReader(new FileReader(this.file));

			name = enemyInput.readLine(); // Reads enemy name form file

			for (int i = 0; i < 11; i++) {
				enemy[i] = enemyInput.readLine(); // Reads each line from the enemy file to make a picture 

			}
			deathQuote = enemyInput.readLine(); // Reads the death quote from the text file

			enemyInput.close(); // Closes buffer reader

		} catch (IOException e) { // catches checked exceptions
			e.printStackTrace();
		}
	}

	public String combat(Player p) { // Runs the combat, prints the encounter, prompts user to pick an action, performs the action, randomly picks the enemies action, and repeats until the enemy or player dies
		
		int aiMaxHP = this.hp; // Stores the max hp of the enemy
		
		boolean inCombat = true; // Stores if the combat is still happening 

		Scanner combatInput = new Scanner(System.in); // Instantiates a scanner to take the combat instructions from player

		while (inCombat) { // Loops until someone dies
			
			// Prints the combat

			System.out.println("-------------------------------------------------"); // Top border of the encounter 

			System.out.println(this.name + "                         " + "You"); // Prints the enemies  name, and "You" above the combat

			String[] player = p.getPlayerDrawing(); // Gets the drawing of the player

			for (int i = 0; i < 11; i++) {
				int n = 30 - enemy[i].length();

				System.out.print(enemy[i]); // Prints each line of the enemy
				System.out.format("%" + n + "s", " "); // Prints n amount of spaces (n = 30 - length of the enemy line)
				System.out.print(player[i]); // Prints each line of the player
				System.out.print("\n"); // Prints a new line
			}
			System.out.println("   HP: " + this.hp + "                         HP: " + p.getHealth() + " & "
					+ p.getArmour() + " armour"); // Prints the HP of both players below the combat
			
			System.out.println("-------------------------------------------------"); // Prints bottom border of combat

			// Prints prompts for the players actions
			System.out.println("Type A to attack: (" + p.getDamageMin() + " - " + p.getDamageMax() + " Damage)");

			System.out.println("Type B to armour up: (" + p.getArmourMin() + " - " + p.getArmourMax() + " Armour)");

			System.out.println("Type H to heal (30 max): (" + p.getHealMin() + " - " + p.getHealMax() + " Health)");

			while (true) { // Loops until user makes a valid choice
				String input = combatInput.nextLine(); // Takes users input

				if (input.toLowerCase().equals("a")) { 

					int min = p.getDamageMin(); // Minimum of the random range
					int max = p.getDamageMax(); // Max of random range
					int range = max - min + 1; // Range of random numbers

					int attack = (int) (Math.random() * range) + min; // Picks a random attack amount
					this.hp -= attack; // Removes attack amount from the enemies health
					System.out.println("You attacked for " + attack + " damage"); // Prints how much damage the player dealt

					break;

				} else if (input.toLowerCase().equals("b")) {

					int min = p.getArmourMin(); // Minimum of the random range
					int max = p.getArmourMax(); // Max of random range
					int range = max - min + 1; // Range of random numbers

					int armour = (int) (Math.random() * range) + min; // Picks a random armour amount
					p.changeArmour(armour); // Adds that amount of armour to the player 
					System.out.println("You gained " + armour + " armour");// Prints how much armour was added to the player

					break;

				} else if (input.toLowerCase().equals("h")) {

					int min = p.getHealMin(); // Minimum of the random range
					int max = p.getHealMax(); // Max of random range
					int range = max - min + 1; // Range of random numbers

					int heal = (int) (Math.random() * range) + min; // Picks random amount of healing 
					p.changeHealth(heal); // Adds that amount to the players health (to a max of 30)
					System.out.println("You gained " + heal + " health (30 max)"); // Prints how much healing was done

					break;
				} else if (input.toLowerCase().equals("pass")) { // if player types 'pass' kill enemy (for testing purposes)
					this.hp -= 1000;
					
					break;
				} else {

					System.out.println("Invalid input please try again"); // prompts user to enter a valid input and reloops
				}
			}
			
			if (this.hp > 0) { // Checks if enemy is alive
				
				int aiChoice = (int) (Math.random() * 2) + 1; // Pick a random number to decide the action of the enemy

				if (aiChoice == 1) {
					int min = minHeal; // Minimum of the random range
					int max = maxHeal; // Max of random range
					int range = max - min + 1; // Range of random numbers

					int heal = (int) (Math.random() * range) + min; // Picks a random amount of healing
					
					if(this.hp + heal >= aiMaxHP) { // Checks if enemy will be above max health after healing
						this.hp = aiMaxHP; // Sets health to max
					}
					else { // If enemy health + healing is below max  
					this.hp += heal; // Add healing to the enemies health
					}
					System.out.println(this.name + " healed for " + heal + " health"); // Prints amount of healing the enemy did
				}
				if (aiChoice == 2) {
					int min = minDamage; // Minimum of the random range
					int max = maxDamage; // Max of random range
					int range = max - min + 1; // Range of random numbers

					int damage = (int) (Math.random() * range) + min; // Picks a random amount of damage for the enemy to do

					if (p.getArmour() <= 0) {

						p.changeHealth(damage * -1);

						System.out.println(this.name + " damaged you for " + damage + " health");
					} else if (p.getArmour() > 0 && p.getArmour() < damage) {

						int armour = p.getArmour();

						System.out.println(armour);

						p.changeArmour(armour * -1);

						damage -= armour;

						p.changeHealth(damage * -1);

						System.out.println(
								this.name + " damaged you for " + damage + " health" + "and " + armour + " armour");
					} else {

						p.changeArmour(damage * -1);

						System.out.println(this.name + " damaged you for " + damage + " armour");
					}
				}
			}

			if (p.getHealth() <= 0) {
				return "Game Over";
			} else if (this.hp <= 0) {
				System.out.println(deathQuote);
				ChestItems.openChest(p);
				p.setArmour(0);
				return "Done";
			}

		}
		return "Done";
	}
}

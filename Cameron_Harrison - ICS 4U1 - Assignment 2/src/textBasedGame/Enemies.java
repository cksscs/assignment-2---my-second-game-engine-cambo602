package textBasedGame;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Enemies {

	int hp;
	String file;

	String name;

	String[] enemy = new String[11];
	String[] enemyWithAttack = new String[11];

	public Enemies(int h, String f) {
		this.hp = h;
		this.file = f;
	}

	public void genEnemies() {
		BufferedReader enemyInput;
		try {
			enemyInput = new BufferedReader(new FileReader(this.file));

			name = enemyInput.readLine();

			for (int i = 0; i < 11; i++) {
				enemy[i] = enemyInput.readLine();

			}
			enemyInput.readLine();

			for (int i = 0; i < 11; i++) {
				enemyWithAttack[i] = enemyInput.readLine();

			}

			enemyInput.close(); // Closes buffer reader

		} catch (IOException e) { // catches checked exceptions
			e.printStackTrace();
		}
	}

	public void printEnemy() {

		for (int i = 0; i < 11; i++) {
			System.out.println(enemy[i]);
		}
	}

	public String combat(Player p) {

		boolean inCombat = true;

		Scanner combatInput = new Scanner(System.in);

		while (inCombat) {

			System.out.println("-------------------------------------");

			System.out.println(this.name + "                " + "You");

			for (int i = 0; i < 11; i++) {
				int n = 15 - enemy[i].length();

				System.out.print(enemy[i]);
				System.out.format("%1$" + n + "s", "");
				System.out.print(p.playerDrawing[i]);
				System.out.print("\n");
			}
			System.out.println(" HP: " + this.hp + "                 HP: " + p.getHealth() + " & " + p.getArmour() + " armour");

			System.out.println("-------------------------------------");

			System.out.println("Type A to attack: (" + p.getDamageMin() + " - " + p.getDamageMax() + " Damage)");

			System.out.println("Type B to armour up: (" + p.getArmourMin() + " - " + p.getArmourMax() + " Armour)");

			System.out.println("Type H to heal (30 max): (" + p.getHealMin() + " - " + p.getHealMax() + " Health)");

			while (true) {
				String input = combatInput.nextLine();

				if (input.toLowerCase().equals("a")) {
					
					int min = p.getDamageMin(); // Minimum of the random range
					int max = p.getDamageMax(); // Max of random range
				    int range = max - min + 1; // Range of random numbers
					
					int attack = (int)(Math.random() * range) + min;
					this.hp -= attack;
					System.out.println("You attacked for " + attack + " damage");
					
					break;
					
				}
				else if (input.toLowerCase().equals("b")) {
					
					int min = p.getArmourMin(); // Minimum of the random range
					int max = p.getArmourMax(); // Max of random range
				    int range = max - min + 1; // Range of random numbers
					
					int armour = (int)(Math.random() * range) + min;
					p.changeArmour(armour); 
					System.out.println("You gained " + armour + " armour");
					
					break;

				}
				else if (input.toLowerCase().equals("h")) {
					
					int min = p.getHealMin(); // Minimum of the random range
					int max = p.getHealMax(); // Max of random range
				    int range = max - min + 1; // Range of random numbers
					
					int heal = (int)(Math.random() * range) + min;
					p.changeArmour(heal); 
					System.out.println("You gained " + heal + " health (30 max)");
					
					break;
				}
				else {
					System.out.println("Invalid input please try again");
				}
			}
			
			

		}
		return "Done";
	}
}

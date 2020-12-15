package textBasedGame;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Player {
	
	// Player stats vars
	private int health; // Stores player health
	private int healMin; // Stores the minimum amount of healing the player can perform
	private int healMax; // Stores the maximum amount of healing the player can perform
	private int damageMin; // Stores the minimum amount of damage the player can perform
	private int damageMax; // Stores the maximum amount of damage the player can perform
	private int armourMin; // Stores the minimum amount of armour the player can add
	private int armourMax; // Stores the maximum amount of armour the player can add
	private int armour = 0; // Stores player armour
	
	// Player drawing vars
	private String file;
	private String[] playerDrawing = new String[11];

	public Player(int h, String f, int hMin, int hMax, int dMin, int dMax, int aMin, int aMax) {
		
		this.health = h;
		
		this.file = f;
		
		this.healMin  = hMin;
		
		this.healMax = hMax;
		
		this.damageMin = dMin;
		
		this.damageMax = dMax;
		
		this.armourMin = aMin;
		
		this.armourMax = aMax;
	}
	
	// Setters and getters
	public int getHealMin() {
		return healMin;
	}

	public void setHealMin(int healMin) {
		this.healMin = healMin;
	}

	public int getHealMax() {
		return healMax;
	}

	public void setHealMax(int healMax) {
		this.healMax = healMax;
	}

	public int getDamageMin() {
		return damageMin;
	}

	public void setDamageMin(int damageMin) {
		this.damageMin = damageMin;
	}

	public int getDamageMax() {
		return damageMax;
	}

	public void setDamageMax(int damageMax) {
		this.damageMax = damageMax;
	}

	public int getArmourMin() {
		return armourMin;
	}

	public void setArmourMin(int armourMin) {
		this.armourMin = armourMin;
	}

	public int getArmourMax() {
		return armourMax;
	}

	public void setArmourMax(int armourMax) {
		this.armourMax = armourMax;
	}
	
	public int getHealth() {
		return this.health;
	}
	
	public void changeHealth(int h) {
		
		if(this.health + h <= 30) {
			this.health += h;
		}
		else if (this.health + h > 30) {
			this.health = 30;
		}
	}
	
	public String[] getPlayerDrawing() {
		return playerDrawing;
	}
	
	public int getArmour() {
		return this.armour;
	}
	
	public void setArmour(int a) {
		this.armour = a;
	}
	public void changeArmour(int a) {
		this.armour += a;
	}
	
	public void genPlayer() {
		
		BufferedReader playerInput; // Declares buffer reader as playerInput
		try {
			playerInput = new BufferedReader(new FileReader(this.file));

			for (int i = 0; i < 11; i++) {
				playerDrawing[i] = playerInput.readLine(); // Sets each line of player file equal to a line in an array

			}

			playerInput.close(); // Closes buffer reader

		} catch (IOException e) { // catches checked exceptions
			e.printStackTrace();
		}
	}
}

package textBasedGame;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Player {

	private int health;
	
	private int healMin;
	
	private int healMax;
	
	private int damageMin;
	
	private int damageMax;
	
	private int armourMin;
	
	private int armourMax;
	
	private int armour = 0;
	
	private String file;
	
	String[] playerDrawing = new String[11];
	
	private String[] inventory = new String[5];
	
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
	
	public void addToInventory(String item) {
		
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
	
	public int getArmour() {
		return this.armour;
	}
	
	public void changeArmour(int a) {
		this.armour += a;
	}
	
	public void genPlayer() {
		BufferedReader playerInput;
		try {
			playerInput = new BufferedReader(new FileReader(this.file));

			for (int i = 0; i < 11; i++) {
				playerDrawing[i] = playerInput.readLine();

			}

			playerInput.close(); // Closes buffer reader

		} catch (IOException e) { // catches checked exceptions
			e.printStackTrace();
		}
	}
}

package textBasedGame;

public class Player {

	private int health;
	private int world;
	private String[] inventory = new String[5];
	
	public Player(int h, int w) {
		
		this.health = h;
		this.world = w;
	}
	
	public void addToInventory(String item) {
		
	}
	
	public int getHealth() {
		return this.health;
	}
	
	public void setHealth(int h) {
		this.health = h;
	}

}

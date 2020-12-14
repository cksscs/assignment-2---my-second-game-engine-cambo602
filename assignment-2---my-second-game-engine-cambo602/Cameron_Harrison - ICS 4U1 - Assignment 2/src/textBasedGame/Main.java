package textBasedGame;

/******************
 * Cameron Harrison 
 * ICS 4U1 
 * Assignment 2
 * Text Based Game 
 * 03/12/20
 *****************/

/*
 TODO; 
 1. Comments (Enemies and Map)
 2. Clean up things that may lose me marks (Ie. vars that should be private)
 */

public class Main {

	public static void main(String[] args) {

		Title.drawTitle("titleFile"); // Static function from title class, draws the title of the game

		System.out.println("Loading.... ");

		for (int i = 0; i < 21; i++) {
			sleep(10);
			System.out.println(i * 5 + "%");
		}
		
		Player p1 = new Player(30, "knight", 2, 5, 4, 7, 4, 6);
		p1.genPlayer(); // Generates image of player
		
		Map firstWorld = new Map(3, 3, "worldOneRooms", 1); // Instantiates the first room
		firstWorld.move(p1); // Calls function move which allows players to move
		
		Map secondWorld = new Map(3, 3, "worldTwoRooms", 2); // Instantiates the second room
		secondWorld.move(p1);
		
		Map thirdWorld = new Map(3, 3, "worldThreeRooms", 3); // Instantiates the third room
		thirdWorld.move(p1);
		
		Map fourthWorld = new Map(3, 3, "worldFourRooms", 4); // Instantiates the fourth room
		fourthWorld.move(p1);
		
		Map fifthWorld = new Map(3, 3, "worldFiveRooms", 5); // Instantiates the fifth room
		fifthWorld.move(p1);
		
		Map bossWorld = new BossRoom(3, 3, "bossRoom", 6); // Instantiates the boss room
		bossWorld.move(p1);
	}


	public static void sleep(int ms) {
		try {
			Thread.sleep(ms); // Sleeps for specified amount of time
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt(); // Catches exceptions
		}
	}
}

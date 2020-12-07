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
 1. Player Class 
 2. Boss Room Subclass 
 3. Create more worlds 
 4. Create chest functionality 
 5. Create working inventory 
 6. Create enemies/ fighting functions
 7. Change question marks to cool symbol so game looks better 
 8. Spruce up title
 9. Add description of each room (What is in the room/ the mood/tone of the room) and display it
 next to room drawing Legend of what symbols mean on the map, and display it next to room map
 10. Comments
 */

public class Main {

	public static void main(String[] args) {

		Title t = new Title("titleFile");

		t.drawTitle();

		System.out.println("Loading.... ");

		for (int i = 0; i < 21; i++) {
			sleep(1);
			System.out.println(i * 5 + "%");
		}
		
		Map firstWorld = new Map(3, 3, "worldOneRooms");
		firstWorld.move();
		
		Map secondWorld = new Map(3, 3, "worldTwoRooms");
		secondWorld.move();
		
		Map thirdWorld = new Map(3, 3, "worldThreeRooms");
		thirdWorld.move();
		
		Map fourthWorld = new Map(3, 3, "worldFourRooms");
		fourthWorld.move();
		
		Map fifthWorld = new Map(3, 3, "worldFiveRooms");
		fifthWorld.move();
	}


	public static void sleep(int ms) {
		try {
			Thread.sleep(ms); // Sleeps for specified amount of time
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt(); // Catches exceptions
		}
	}
}

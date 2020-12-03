package textBasedGame;

/*TODO;
 Player Class
 Boss Room Class
 Create more worlds
 Create chest functionality
 Create working inventory
 Create enemies/ fighting functions
 Change question marks to cool symbol so game looks better
 Spruce up title screen
 Add description of each room (What is in the room/ the mood/tone of the room) and display it next to room drawing
 Legend of what symbols mean on the map, and display it next to room map
*/

public class Main {

	public static void main(String[] args) {

		int playerQuadX = 0;
		int playerQuadY = 0;

		String moveDir;

		boolean[] roomDetails;

		title t = new title(5, "Game the Game");

		t.drawTitle();

		System.out.println("Loading.... ");

		for (int i = 0; i < 21; i++) {
			sleep(1);
			System.out.println(i * 5 + "%");
		}

		Map firstWorld = new Map(3, 3, "worldOneRooms");

		firstWorld.genRooms();

		firstWorld.printMap();
		while (true) {
			roomDetails = firstWorld.getRoomDetails(playerQuadX, playerQuadY);

			firstWorld.printRoom(playerQuadX, playerQuadY);

			moveDir = firstWorld.actionMneu(roomDetails, playerQuadX, playerQuadY);

			if (moveDir.toLowerCase().equals("w")) {
				
				playerQuadY -= 1;
			}
			if (moveDir.toLowerCase().equals("a")) {
				
				playerQuadX -= 1;
			}
			if (moveDir.toLowerCase().equals("d")) {
				
				playerQuadX += 1;
			}
			if (moveDir.toLowerCase().equals("s")) {
				
				playerQuadY += 1;
			}
			if (moveDir.toLowerCase().equals("v")) {
				
				break;
			}
		}
	}

	public static void sleep(int ms) {
		try {
			Thread.sleep(ms); // Sleeps for specified amount of time
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt(); // Catches exceptions
		}
	}
}

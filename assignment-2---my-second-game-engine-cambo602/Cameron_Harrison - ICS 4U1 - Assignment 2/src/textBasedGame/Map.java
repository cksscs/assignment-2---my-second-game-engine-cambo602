package textBasedGame;

// Import required classes
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Map {

	// Object vars
	private int mapLength;
	private int mapWidth;
	private String worldFile;
	private int worldNum;

	private String[] legend = { "?'s are walls", "c's are chests", "e's are enemies", "v's are vents to the next level",
			"b's are bosses", "" }; // Array to store the legend to display it next to each room

	private String[][][] rooms; // 3D array to store all rooms for the floor/world

	public Map(int ml, int mw, String w, int num) {

		this.mapLength = ml; // How many columns of rooms
		this.mapWidth = mw; // How many rows of rooms
		this.worldFile = w; // Sets the file of the map
		this.worldNum = num; // Sets which world this object is created for

		this.rooms = new String[ml][mw][6]; // Creates 3D array to stores columns, rows for rooms, and a 6 row picture
											// of the room
	}

	private void genRooms() { // Generates map of rooms for world one

		BufferedReader roomInput; // Declares buffer reader as oneRooms
		try {
			roomInput = new BufferedReader(new FileReader(this.worldFile)); // Sets buffer reader to read from text file
																			// that stores rooms
			for (int k = 0; k < this.mapWidth; k++) {
				for (int j = 0; j < this.mapLength; j++) {
					for (int i = 0; i < 6; i++) {
						rooms[k][j][i] = roomInput.readLine(); // Sets each line in the 3d array equal to a line in the
																// text file
					}
					roomInput.readLine(); // Skips a line after every room (I leave a blank space in the text file for
											// readability)
				}
			}
			if(this instanceof BossRoom) { 
				rooms = ((BossRoom) this).placeBoss(rooms); // Places boss in a random place on the map
			}
			
			roomInput.close(); // Closes buffer reader

		} catch (IOException e) { // catches checked exceptions
			e.printStackTrace();
		}
	}

	private void printMap() { // Function prints entire map
		for (int j = 0; j < 3; j++) {
			for (int i = 0; i < 6; i++) {
				System.out.println(rooms[j][0][i] + " " + rooms[j][1][i] + " " + rooms[j][2][i]); // Prints each line of
																									// map
			}
		}
		System.out.println(
				"I was debating whether or not to remove this map over view so the end user woudnt see the locations of things \nI decided to keep it in so you can navigate around easier. If you need to beat an enemy type 'pass'");
	}

	private boolean[] getRoomDetails(int x, int y) {

		boolean[] details = { false, false, false, false, false, false, false, false }; // Stores details of room from
																						// left to right,
																						// Stores if chest, if enemy, If
																						// an there is an exit up, left,
																						// right, or down. Stores if a
																						// vent
																						// to next world is in room. And
																						// sotres if a boss is present
		for (int i = 0; i < 6; i++) {
			if (rooms[y][x][i].contains("e")) { // If room has an e the program sees an enemy
				details[0] = true;
			}
		}
		for (int i = 0; i < 6; i++) {
			if (rooms[y][x][i].contains("c")) { // If room has a c the program sees a chest
				details[1] = true;
			}
		}
		for (int i = 0; i < 6; i++) {
			if (rooms[y][x][i].contains("v")) { // If room has a v the program sees a vent
				details[6] = true;
			}
		}
		if (rooms[y][x][0].charAt(4) == ' ') { // Checks if each side has an empty square, allows player to move through
												// it
			details[2] = true;
		}

		if (rooms[y][x][2].charAt(0) == ' ') {
			details[3] = true;
		}

		if (rooms[y][x][2].charAt(10) == ' ') {
			details[4] = true;
		}

		if (rooms[y][x][5].charAt(4) == ' ') {
			details[5] = true;
		}
		for (int i = 0; i < 6; i++) {
			if (rooms[y][x][i].contains("b")) { // If room has a b the program sees a boss
				details[7] = true;
			}
		}

		return details;
	}

	private void printRoom(int x, int y) { // Prints the room the player is in

		System.out.println("--------------------------------------------------");
		for (int i = 0; i < 6; i++) {
			System.out.print("| " + rooms[y][x][i] + " |" + legend[i]); // Prints each row of room, and the legend next
																		// to it

			int n = 44 - (rooms[y][x][i].length() + legend[i].length());

			System.out.format("%" + n + "s", " "); //

			System.out.print(" |\n");

		}
		System.out.println("--------------------------------------------------");
	}

	private String actionMneu(boolean[] details, int x, int y, Player p) { // Prompts user to pick an action, then
																			// executes the action

		boolean gameOver = false;

		String userInput; // Stores user input

		Scanner input = new Scanner(System.in); // Scanner to take user input

		// Prompts user with several actions, depending on what appears in the room
		if (details[0]) {
			System.out.println("Type E to attack enemy");
		} else if (details[7]) {
			System.out.println("Type B to start boss fight");
		}
		if (details[0] == false) {
			if (details[1]) {
				System.out.println("Type C to open the chest");
			}
			if (details[2]) {
				System.out.println("Type W to move up");
			}
			if (details[3]) {
				System.out.println("Type A to move left");
			}
			if (details[4]) {
				System.out.println("Type D to move right");
			}
			if (details[5]) {
				System.out.println("Type S to move down");
			}
			if (details[6]) {
				System.out.println("Type V to go down to the next floor");
			}
		}

		while (true) { // Loops until player enters a movement command

			userInput = input.nextLine(); // Takes users input
			
			if(worldNum == 6) {
				BossRoom.secretWord(userInput); // Checks if the **forbidden word** was uttered in PCMR's lair
			}

			if (details[0]) {
				if (userInput.toLowerCase().equals("e")) { // If user enters e, executes enemies code

					String enemyFile = ""; // Empty string to store the file of the enemy

					int randomFile = randomNum(1, 4); // Generates a random number that determines which enemy is
														// generated

					// Sets the enemy file depending on which number was generated
					if (randomFile == 1) {
						enemyFile = "alien";
					} else if (randomFile == 2) {
						enemyFile = "robot";
					} else if (randomFile == 3) {
						enemyFile = "rock";
					} else if (randomFile == 4) {
						enemyFile = "breakdancing";
					}

					Enemies e = new Enemies(randomNum(4 * worldNum, 6 * worldNum), enemyFile,
							randomNum(1 * worldNum, 2 * worldNum), randomNum(3 * worldNum, 5 * worldNum),
							randomNum(3, 6), randomNum(7, 12)); // Creates new enemy object with random stats, and file
																// set

					e.genEnemies(); // Sets enemies picture

					String result = e.combat(p); // Starts combat sequence

					if (result.equals("Game Over")) { // If user lost combat exit program
						System.out.println("Game Over");
						System.exit(0);
					} else if (result.equals("Done")) { // If user wins combat, removes enemy from map

						for (int i = 0; i < 6; i++) {
							rooms[y][x][i] = rooms[y][x][i].replace('e', ' '); // Removes e from map
						}
						break;
					}

				}
			 else {

				System.out.println("Please enter a valid input: "); // Prompts user to enter combat
				continue;
			}
		}
			if (details[7]) {
				if (userInput.toLowerCase().equals("b")) {

					Enemies boss = new Boss();

					boss.genEnemies(); // Sets enemies picture

					String result = boss.combat(p); // Starts combat sequence

					if (result.equals("Game Over")) { // If user lost combat exit program
						System.out.println("Game Over");
						System.exit(0);
					} else if (result.equals("Done")) { // If user wins combat, removes enemy from map

						Boss.youWin();
					}

				} else {

					System.out.println("Please renter a valid input: "); // Prompts user to enter combat
					continue;
				}
			}

			if (details[0] == false) {
				if (userInput.toLowerCase().equals("c") && details[1] == true) { // if user types c to open a chest

					ChestItems.openChest(p); // Gives player a random stat buff

					for (int i = 0; i < 6; i++) {
						rooms[y][x][i] = rooms[y][x][i].replace('c', ' '); // Removes chest from the map
					}

					break;
					
				// If user enters a movement command the loop breaks and the function returns what command is run
				} else if (userInput.toLowerCase().equals("w") && details[2] == true) {

					break;

				} else if (userInput.toLowerCase().equals("a") && details[3] == true) {

					break;

				} else if (userInput.toLowerCase().equals("d") && details[4] == true) {

					break;

				} else if (userInput.toLowerCase().equals("s") && details[5] == true) {
					break;

				} else if (userInput.toLowerCase().equals("v") && details[6] == true) {
					break;
				} else {

					System.out.println("Please enter a valid input: "); // Prompts user to renter a valid command and reloops 
					continue;
				}
			}
		}

	if(gameOver){
		return "Game Over"; // If the player dies function returns game over
	}

	return userInput; // Returns movement command to move function
	}

	public void move(Player p) {
		
		// Stores player pos 
		int playerQuadX = 0;
		int playerQuadY = 0;
		
		// if world is the boss room sets the player quadrant to the bottom of the map
		if (worldNum == 6) {
			playerQuadX = 1;
			playerQuadY = 2;
		}

		String moveDir; // Stores movement command

		boolean[] roomDetails; // Stores room features

		genRooms(); // Runs a method that fills an array with rooms, and map

		printMap(); // Prints entire map (I left this in so you can test the game easier)
		
		while (true) { // Loops until player dies or enters the next world
			
			roomDetails = getRoomDetails(playerQuadX, playerQuadY); // Stores which features each room has (If enemy, chest, if north/west/east/south exit if vent, and if boss)

			printRoom(playerQuadX, playerQuadY); // Prints the current room

			moveDir = actionMneu(roomDetails, playerQuadX, playerQuadY, p); // Gets user to pick a direction

			if (moveDir.equals("Game Over")) { // Breaks loop if player dies
				System.out.println("Game Over");
				break; 
			}
			
			// Moves player to an adjacent quad depending on which direction the player picks
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
			
			// If player enters the vent, it breaks the loop and creates the next world in Main 
			if (moveDir.toLowerCase().equals("v")) {

				System.out.println("Next world");
				playerQuadX = 0;
				playerQuadY = 0;
				break;

			}
		}
	}

	protected int randomNum(int i, int j) { // Generates a random number
		int min = i;
		int max = j;

		int range = max - min + 1;

		return (int) (Math.random() * range) + min;

	}

}

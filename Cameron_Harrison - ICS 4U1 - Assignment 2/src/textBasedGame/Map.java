package textBasedGame;

// Import required classes
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Map {

	private int mapLength;
	private int mapWidth;
	private String worldFile;

	Scanner input = new Scanner(System.in);

	private String[][][] rooms; // 3D array to store all rooms for the floor/world

	public Map(int ml, int mw, String w) {

		this.mapLength = ml; // How many columns of rooms
		this.mapWidth = mw; // How many rows of rooms
		this.worldFile = w; // Sets which world this object is created for

		this.rooms = new String[ml][mw][6]; // Creates 3D array to stores columns, rows for rooms, and a 6 row picture
											// of
		// the room
	}

	public void genRooms() { // Generates map of rooms for world one

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

			roomInput.close(); // Closes buffer reader

		} catch (IOException e) { // catches checked exceptions
			e.printStackTrace();
		}
	}

	public void printMap() {
		for (int j = 0; j < 3; j++) {
			for (int i = 0; i < 6; i++) {
				System.out.println(rooms[j][0][i] + " " + rooms[j][1][i] + " " + rooms[j][2][i]);
			}
		}
	}

	public boolean[] getRoomDetails(int x, int y) {

		boolean[] details = { false, false, false, false, false, false, false }; // Stores details of room from left to
																					// right,
																					// if chest, if enemy, exits up,
																					// left,
																					// right, or down. Stores if a vent
																					// to next world
																					// is in room
		for (int i = 0; i < 6; i++) {
			if (rooms[y][x][i].contains("e")) {
				details[0] = true;
			}
		}
		for (int i = 0; i < 6; i++) {
			if (rooms[y][x][i].contains("c")) {
				details[1] = true;
			}
		}
		for (int i = 0; i < 6; i++) {
			if (rooms[y][x][i].contains("v")) {
				details[6] = true;
			}
		}
		if (rooms[y][x][0].charAt(4) == ' ') {
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

		return details;
	}

	public void printRoom(int x, int y) {

		System.out.println("--------------------------------------");
		for (int i = 0; i < 6; i++) {
			System.out.println("| " + rooms[y][x][i] + " |");
		}
		System.out.println("--------------------------------------");
	}

	public String actionMneu(boolean[] details, int x, int y) {

		String userInput;

		if (details[0]) {
			System.out.println("Type E to attack enemy");
		}
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

		while (true) {

			userInput = input.nextLine();

			if (userInput.toLowerCase().equals("e") && details[0] == true) {

				continue;

			} else if (userInput.toLowerCase().equals("c") && details[1] == true) {

				continue;

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

				System.out.println("Please enter a vlaid input: ");
				continue;
			}
		}
		return userInput;
	}

	public void move() {

		int playerQuadX = 0;
		int playerQuadY = 0;

		String moveDir;

		boolean[] roomDetails;

		genRooms();

		printMap();
		while (true) {
			roomDetails = getRoomDetails(playerQuadX, playerQuadY);

			printRoom(playerQuadX, playerQuadY);

			moveDir = actionMneu(roomDetails, playerQuadX, playerQuadY);

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

				System.out.println("Next world");
				playerQuadX = 0;
				playerQuadY = 0;
				break;

			}
		}
	}

	/*
	 * private String replace(String str, int index, char replace) { if (str ==
	 * null) { return str; } else if (index < 0 || index >= str.length()) { return
	 * str; } char[] chars = str.toCharArray(); chars[index] = replace; return
	 * String.valueOf(chars); }
	 */
}

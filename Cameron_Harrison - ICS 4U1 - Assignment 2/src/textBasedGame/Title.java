package textBasedGame;

/******************
 * Cameron Harrison 
 * ICS 4U1 
 * Assignment 2
 * Title class, stores and prints title screen 
 * 03/12/20
 *****************/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Title {

	public static void drawTitle(String file) {

		Scanner tInput = new Scanner(System.in);
	
		BufferedReader titleInput; // Declares buffer reader as titleInput
		try {
			titleInput = new BufferedReader(new FileReader(file)); // Sets buffer reader to read from text file
																			// that stores the title
					for (int i = 0; i < 8; i++) {
						System.out.println(titleInput.readLine()); // Prints out each line in the title file
					}

			titleInput.close(); // Closes buffer reader
			
		} catch (IOException e) { // catches checked exceptions
			e.printStackTrace();
		}

		while (true) {

			if (tInput.nextLine().toLowerCase().equals("s")) {

				break;

			} else {

				System.out.println("Invalid input please try again");
			}
		}
	}

}

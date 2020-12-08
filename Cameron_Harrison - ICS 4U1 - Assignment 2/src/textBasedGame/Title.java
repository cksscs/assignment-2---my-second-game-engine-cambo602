package textBasedGame;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Title {

	String file;

	public Title(String f) {
		
		this.file = f;
	}

	public void drawTitle() {

		Scanner tInput = new Scanner(System.in);
	
		BufferedReader titleInput; // Declares buffer reader as titleInput
		try {
			titleInput = new BufferedReader(new FileReader(this.file)); // Sets buffer reader to read from text file
																			// that stores the title
					for (int i = 0; i < 7; i++) {
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
		
		tInput.close();
	}

}

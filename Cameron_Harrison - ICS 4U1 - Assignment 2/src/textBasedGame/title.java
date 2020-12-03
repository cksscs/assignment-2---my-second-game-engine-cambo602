package textBasedGame;

import java.util.Scanner;

public class title {

	int length;
	String title;

	public title(int l, String t) {

		this.length = l;
		this.title = t;
	}

	public void drawTitle() {

		Scanner titleInput = new Scanner(System.in);
		
		int l = this.length;
		String t = this.title;

		System.out.print("\n");

		for (int i = 0; i < t.length() + 2; i++) {
			System.out.print("# ");
		}

		System.out.print("\n");
		System.out.println("#       " + t + "       #");

		for (int i = 0; i < l - 2; i++) {
			System.out.print("# ");

			for (int j = 0; j < t.length(); j++) {
				System.out.print("@ ");
			}

			System.out.print("#");
			System.out.print("\n");
		}

		System.out.println("#      " + "Type S to start" + "      #");

		for (int i = 0; i < t.length() + 2; i++) {
			System.out.print("# ");
		}

		System.out.println("\n");
		while (true) {

			if (titleInput.nextLine().toLowerCase().equals("s")) {

				break;

			} else {

				System.out.println("Invalid input please try again");
			}
		}
	}

}

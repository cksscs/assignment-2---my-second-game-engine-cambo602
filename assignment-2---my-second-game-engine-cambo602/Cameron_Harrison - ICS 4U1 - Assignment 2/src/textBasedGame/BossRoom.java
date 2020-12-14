package textBasedGame;

public class BossRoom extends Map {

	public BossRoom(int mw, int ml, String file, int num) {
		super(ml, mw, file, num);
	}

	public String[][][] placeBoss(String[][][] map) { // Places boss in random part of the map
		int x = randomNum(0, 2); // generates an x & y cord to place boss
		int y = randomNum(0, 2);

		// Places boss indicator letter 'b' in the middle of the random room
		map[y][x][2] = replace(map[y][x][2], 4, 'b');
		map[y][x][2] = replace(map[y][x][2], 6, 'b');
		map[y][x][3] = replace(map[y][x][3], 4, 'b');
		map[y][x][3] = replace(map[y][x][3], 6, 'b');

		return map;
	}

	private String replace(String str, int index, char replace) { // Replaces a specific char in a string with another
																	// char
		char[] chars = str.toCharArray();
		chars[index] = replace;
		String result = String.valueOf(chars);
		
		return result;
	}

	static void secretWord(String word) { // Checks if player has spoken the forbidden word
		if (word.toLowerCase().equals("xbox")) {
			System.out.println(
					"You have spoken heresy is PCMR's sacred hall. PCMR becomes so enraged at the console peasent he litterally dies. Congrats?");
			System.exit(0);
		}
	}
}

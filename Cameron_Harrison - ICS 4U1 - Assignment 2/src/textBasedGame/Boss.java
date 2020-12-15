package textBasedGame;

/******************
 * Cameron Harrison 
 * ICS 4U1 
 * Assignment 2
 * Boss, Subclass of Enemies  
 * 03/12/20
 *****************/

// I was able to have all the functionality of the boss using the regular enemies class, but I wanted to show I knew how inheritance worked 

public class Boss extends Enemies{

	public Boss() {
		super(60, "pcmr", 6, 9, 6, 10);
	}

	
	static void youWin() { // Exits game if the player has defeated the boss
		System.out.println(
				"Congrats you have defeted PC Master Race and have asscended to god status within the reddit community");
		System.exit(0);
	}
}
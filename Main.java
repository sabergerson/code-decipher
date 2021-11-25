package bergerson.MAIN;

//import necessary packages
import java.io.IOException;

import bergerson.CRYPTO.ImperialDecypher_Bergerson;

public class BergersonHW3 {

	//MAIN METHOD
	
	public static void main(String[] args) throws IOException {
		//pre-process Rebel_1 file, pre-process Rebel_2 file
		ImperialDecypher_Bergerson.decypher("Rebel_1", "Rebel_1Cracked");
		ImperialDecypher_Bergerson.decypher("Rebel_2", "Rebel_2Cracked");

	}
}

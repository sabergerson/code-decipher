package bergerson.CRYPTO;

//import necessary packages
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ImperialDecypher_Bergerson {
	
	//GLOBAL VARIABLES 
	
	public static String DIR = "//Users//sage//eclipse-workspace//BergersonHW3//src//bergerson//";
	public static String lettersUC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static String lettersLC = "abcdefghijklmnopqrstuvwxyz";
	public static String numbers = "0123456789";
	
	//HELPER METHODS
	
	//name: lineNums
	//function: count number lines in a file
	//input: String (filename)
	//output: int (number lines)
	public static int lineNums(String filename) throws IOException {
		//read in file data
		BufferedReader BR = new BufferedReader(new FileReader(DIR+"ENCRYPTED_FILES//"+filename));	
		int lineNums = 0;
		while(BR.readLine() != null){
			//increment line numbers
			lineNums++;
		}
		BR.close();
		//return count of line numbers
		return lineNums;
	}
	
	//name: distributeValues
	//function: segregate array characters by upper case, lower case, number in line
	//input: String[] (original array)
	//output: String[][] (segregated array)
	public static String[][] distributeValues(String[] array) {	
		//initialize empty string array in which to sort values
		String[][] distribute = new String[array.length][3];	
		for (int i = 0; i < distribute.length; i++) {
			for (int j = 0; j < 3; j++) {
				distribute[i][j] = "";
			}
		}		
		//sort and separate characters into input array
		//upper case first, lower case second, numbers third
		for (int i1 = 0; i1 < array.length; i1++) {
			for (int j1 = 0; j1 < array[i1].length(); j1++) {
				if (lettersUC.indexOf(array[i1].charAt(j1)) != -1) {
					distribute[i1][0] += array[i1].charAt(j1);
				}
				else if (lettersLC.indexOf(array[i1].charAt(j1)) != -1) {
					distribute[i1][1] += array[i1].charAt(j1);
				}
				else if (numbers.indexOf(array[i1].charAt(j1)) != -1) {
					distribute[i1][2] += array[i1].charAt(j1);
				}
				else {
					continue;
				}
			}
		}
		//return the sorted array
		return distribute;
	}
	
	//name: sum
	//function: calculates sum of number values from string
	//input: String (numbers)
	//output: int (sum)
	public static int sum(String nums) {
		//add the integer value of each number character in string to sum
		int sum = 0;
		for (int i = 0; i < nums.length(); i++) {
			sum += Integer.parseInt(String.valueOf(nums.charAt(i)));
		}
		//return the sum
		return sum;	
	}
	
	//name: addCounts
	//function: appends segregated file lines with number of characters or sum
	//input: String[][] (segregated array)
	//output: void 
	public static void addCounts(String[][] matrix) {
		//add number of upper and lower case values or sum to the end of each entry
		for (int i = 0; i < matrix.length; i++) {
			matrix[i][0] += "->{"+matrix[i][0].length()+"}";
			matrix[i][1] += "->{"+matrix[i][1].length()+"}";
			matrix[i][2] += "->{"+sum(matrix[i][2])+"}";
		}
	}
	
	//MAIN METHOD
	
	//name: decypher
	//function: translates encrypted file into pre-processed file following the code
	//input: String (file name to decipher)
	//output: void (creates pre-processed file)
	public static void decypher(String filename, String newfile) throws IOException {	
		//declare BR/BW methods
		BufferedReader BR = new BufferedReader(new FileReader(DIR+"ENCRYPTED_FILES//"+filename));
		BufferedWriter BW = new BufferedWriter(new FileWriter(DIR+"PROCESSED_FILES//"+newfile, true));
		//create 1D array to hold each file line
		String[] data = new String[lineNums(filename)];
		//read in the file data and separate each line as an entry
		int index = 0;
		String line = "";
		while((line = BR.readLine()) != null) {
			String[] lineArray = line.split("\n");
			data[index] = lineArray[0];
			index++;
		}	
		BR.close();	
		//separate the characters in each line by upper, lower case or number
		String[][] distributed = distributeValues(data);
		//add number of characters or sum to end of each entry
		addCounts(distributed);
		//write new data to new file
		for (int i = 0; i < distributed.length; i++) {
			for (int j = 0; j < distributed[i].length; j++) {
				BW.write(distributed[i][j]+" ");
			}
			BW.write('\n');
		}
		BW.close();
	}
}

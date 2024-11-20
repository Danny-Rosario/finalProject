package finalProject.hashing;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Main class for the project
 * Its function is to print the results and read the files for further hashing.
 */

public class Main{

// Array that represents the word of each line in each entry 
private static String[] lines;

// Filepath of the txt file 
private static String filePath = "(insert file path)";

// The total value of the word in the ascii of its letters 
private static int[] ascii;

// The ascii value of each letter seperately in each entry 
private static int[][] asciiletter;

/**
 * Read File method
 * On this method, we read the text file and add all lines to the lines array, creating a list
 * to dynamically change the size for each time a new line that isnt null is read. All further 
 * operations are done in other parts.
 */
private static void readFile(){
	try{
		List<String> lineslist = new ArrayList<>();
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		String word;

		while ((word = reader.readLine()) != null) {
                	lineslist.add(word);
            	}

		reader.close();

		lines = lineslist.toArray(new String[0]);
	} catch (IOException e){
		System.out.println("Error occured");
 	}
}

/**
 * Conversion from Strings to the total ascii of each letter from the word. A string will be passed
 * in a loop to turn first to a char array, after into ascii, then proceeded to be added to a new array.
 * @return an array with integers representing each word.
 */
private static int[] conversionstring(){
	int[] ascii = new int[lines.length];
	asciiletter = new int[lines.length][];
	int sum;
	for(int i = 0; i < lines.length; i++){

		//Turn string into char arrays
		char[] letters = lines[i].toCharArray();
		sum = 0;
		asciiletter[i] = new int[letters.length];

		//Loop where characters get turn into ascii
		for(int j = 0; j < letters.length; j++){
			int value = (int) letters[j];
			sum += value;
			asciiletter[i][j] = value;
		}
		ascii[i] = sum;
	}
	return ascii;
}

/**
 * Only converts a single word into its ascii form
 * mostly used for the search of a word
 */
private static int conversionSingleString(String s){
	int sum = 0;
	char[] letters = s.toCharArray();

	//Loop where characters get turn into ascii
	for(int j = 0; j < letters.length; j++){
		int value = (int) letters[j];
		sum += value;
	}
	return sum;
}

    // Method to check if a number is prime
    //public static boolean isPrime(int num) {
        //if (num <= 1) return false; 
        //if (num <= 3) return true; 
        //if (num % 2 == 0 || num % 3 == 0) return false; 
        
        // Check divisors from 5 up to sqrt(num), skipping even numbers
        //for (int i = 5; i * i <= num; i += 6) {
            //if (num % i == 0 || num % (i + 2) == 0) return false;
        //}
        //return true;
    //}

    // Method to find the next prime after n
    //public static int nextPrime(int n) {
	//System.out.println("Checking next prime");
        //int candidate = n; 
        //while (!isPrime(candidate)) {
            //candidate++; 
        //}
	//System.out.println("Found the prime");
        //return candidate;
    //}

	//public static int sizeFactor(boolean prime, int size, double factor){
		//if(prime){
			//size = nextPrime(size);
		//}
		//double candidate = size * factor;
		//System.out.println("Number obtained " + candidate);
		//return (int) Math.ceil(candidate);
	//}

	//public static void writeFile(int[] results, String filename){
		//try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename))){
			//for(int i : results){
				//writer.write(String.valueOf(i));
				//writer.newLine();
			//}
		//} catch (IOException e) {
			//System.out.println("Failure!");
		//}

	//}


public static void main(String args[]){
	readFile();
	int[] ascii = conversionstring();
	//double[] factors = {2, 10/7};

	Hashing fnp = new Hashing(10);
	Hashing snp = new Hashing(10);
	Hashing fp = new Hashing(11);
	Hashing sp = new Hashing(11);

	//Frequency Tables
	System.out.println("Hashing Strategy: Linear Probing");
	System.out.println("Table Size: Non prime number");
	System.out.println("Load Factor: 0.5");
	int[] finaltable = fnp.hashing(ascii, 5);
	//writeFile(finaltable, "output.txt");
	System.out.println("Hashing Strategy: Linear Probing");
	System.out.println("Table Size: Non prime number");
	System.out.println("Load Factor: 0.7");
	snp.hashing(ascii, 7);
	System.out.println("Hashing Strategy: Linear Probing");
	System.out.println("Table Size: Prime number");
	System.out.println("Load Factor: 0.5");
	fp.hashing(ascii, 5);
	System.out.println("Hashing Strategy: Linear Probing");
	System.out.println("Table Size: Prime number");
	System.out.println("Load Factor: 0.7");
	sp.hashing(ascii, 7);

	//Probes per insertion
	System.out.println("Hashing Strategy: Linear Probing");
	System.out.println("Table Size: Non prime number");
	System.out.println("Load Factor: 0.5");
	fnp.probeCount(ascii, 5, lines);
	System.out.println("Hashing Strategy: Linear Probing");
	System.out.println("Table Size: Non prime number");
	System.out.println("Load Factor: 0.7");
	snp.probeCount(ascii, 7, lines);
	System.out.println("Hashing Strategy: Linear Probing");
	System.out.println("Table Size: Prime number");
	System.out.println("Load Factor: 0.5");
	fp.probeCount(ascii, 5, lines);
	System.out.println("Hashing Strategy: Linear Probing");
	System.out.println("Table Size: Prime number");
	System.out.println("Load Factor: 0.7");
	sp.probeCount(ascii, 7, lines);

	//Search
	System.out.println("Looking for the word: bagel");
	int searchKey = conversionSingleString("bagel");
	fnp.search(searchKey);
	snp.search(searchKey);
	fp.search(searchKey);
	sp.search(searchKey);
	System.out.println("Done!");
}
}
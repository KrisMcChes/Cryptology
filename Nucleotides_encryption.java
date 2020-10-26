//Kristina McChesney
//CSC 440 
//Winter 2019
//Assignment 1, Question 6.

package csc440;
import stdlib.StdOut;

public class CSC440_assignment1_6 {

	// (a) encrypt using shift cipher
	public static void shiftCipher(String alphabet, String plaintext, int shift){
		int alphLength = alphabet.length();
		for (int i=0; i<plaintext.length(); i++){
			char letter = plaintext.charAt(i);
			int cipherLetterValue = (alphabet.indexOf(letter) + shift ) % alphLength;
			StdOut.print(alphabet.charAt(cipherLetterValue));	
		}
	}
	
	// (b) encrypt using affine cipher
	public static void affineCipher(String alphabet, String plaintext, int a, int b){
		int alphLength = alphabet.length();
		for (int i=0; i<plaintext.length(); i++){
			char letter = plaintext.charAt(i);
			int newInteger = (a * alphabet.indexOf(letter) + b) % alphLength;
			StdOut.print(alphabet.charAt(newInteger));
		}
	}

	public static void main(String[] args) {

		String nucleotides = "ACGT";
		String plaintext = "GAATTCGCGGCCGCAATTAACCCTCACTAAAGGGATCTCTAGAACT";
		int shift = 3;
		int a = 3;
		int b = 2;

		StdOut.println("Encrypt the following sequence of nucleotides: \n'GAATTCGCGGCCGCAATTAACCCTCACTAAAGGGATCTCTAGAACT'");
		StdOut.println();

		StdOut.format("This text was encrypted using shift cipher with a shift %d:\n", shift);
		shiftCipher(nucleotides, plaintext, shift);				
		StdOut.println("\n");


		//The function used for the affine cipher is y = (ax + b) % 4
		//where a is restricted to only two values (1,3)\n"
		//and 0 < b <= 4 since the size of alphabet is 4/n"
		System.out.printf("This text was encrypted using affine function %dx + %d mod %d: \n", a, b, nucleotides.length());
		affineCipher(nucleotides, plaintext, a, b);	
	}
}

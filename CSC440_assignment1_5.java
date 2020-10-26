//Kristina McChesney
//CSC 440 
//Winter 2019
//Assignment 1, Question 5.

package csc440;
import stdlib.StdOut;

public class CSC440_assignment1_5 {

	public static void main(String[] args) {
		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		String cipherText = "ycvejqwvhqtdtwvwu";						//given ciphertext

		for (int shift=0; shift<26; shift++){							//looping through all 26 possible shifts (0-25)
			StdOut.format("Plaintext with shift: %d - ", shift);
			for (int i=0; i<cipherText.length(); i++){					//going through the ciphertext character by character
				char cipherTextChar = cipherText.charAt(i);				
				int plainTextIndex = (alphabet.indexOf(cipherTextChar)-shift+26) % 26;
				StdOut.print(alphabet.charAt(plainTextIndex));			
			}
			StdOut.println();
		}
	}
}

//Kristina McChesney
//CSC 440 
//Winter 2019
//Assignment 3, Question 5
package csc440;
import java.math.BigInteger;
import stdlib.StdOut;

public class BlumBlumShub_CSPRNG {
	
	// create an encryption key as a string of random 1's and 0's that has a size of a plaintext
	public static String blumBlumGenerator(int stringLength){	
		BigInteger p = new BigInteger("24672462467892469787");
		BigInteger q = new BigInteger("396736894567834589803");
		BigInteger n = p.multiply(q);
		BigInteger x = new BigInteger("873245647888478349013");
	    StringBuilder sb = new StringBuilder();
		
		for (int i=0; i<stringLength; i++){
			x = (x.pow(2)).mod(n); 	
			BigInteger y = new BigInteger("2");
			BigInteger z = x.mod(y);
			String randomBit = z.toString();
			sb.append(randomBit);
		}
			
		String key = sb.toString();
		return (key);
	}
	
	// create a ciphertext string using xor of input and the bit-key
	public static String createCiphertext(String input, String key){
		long inputLong = Long.parseLong(input,2);
		long randomLong = Long.parseLong(key,2);
		long encryptedLong = inputLong^randomLong;
		String encryptedBits = Long.toBinaryString(encryptedLong);
		
		return (encryptedBits);
	}
	
	// print the encrypted string in desired format
	public static void printEncryptedString(String encrypted, int inputLength){
		//check if encrypted string missing leading zeroes, add 0's if so 
		String toOutput = null;
		String zeroes = null;
		StringBuilder sb = new StringBuilder();
		if (encrypted.length()<inputLength){
			int difference = inputLength - encrypted.length();
			for (int i=0; i<difference;i++){
				sb.append('0');
			}
			zeroes = sb.toString();
		}
		toOutput = zeroes.concat(encrypted);
		
		// print the ciphertext as a sequence of bits of groups of 4
		String value = "4";
		toOutput = toOutput.replaceAll("(.{" + value + "})", "$1 ").trim();
		StdOut.println("Encrypted string:");
		StdOut.println(toOutput);
	}
	
	
	public static void main(String[] args) {
		String inputBits = "010100110100010101000011010101010101001001000101";
		int inputLength = inputBits.length();
		String keyBits = blumBlumGenerator(inputLength);
		String encryptedMessage = createCiphertext(inputBits,keyBits);
		printEncryptedString(encryptedMessage,inputLength);
	}
}



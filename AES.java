//Kristina McChesney
//CSC 440 
//Winter 2019
//Assignment 5
package csc440;
import java.math.BigInteger;
import stdlib.StdOut;

// implementation of Rijndael algorithms with key of length 128 bits and 10 encryption rounds 
public class AES {

	// changes each byte of the matrix to another byte from the s-box where
	// 1st four bits of the byte are the row and last four bits are column of the s-box 
	public static int[][] subBytes(int[][] array){

		// lookup table
		final int[][] s_box = 
			{{0x63, 0x7c, 0x77, 0x7b, 0xf2, 0x6b, 0x6f, 0xc5, 0x30, 0x01, 0x67, 0x2b, 0xfe, 0xd7, 0xab, 0x76}, 
				{0xca, 0x82, 0xc9, 0x7d, 0xfa, 0x59, 0x47, 0xf0, 0xad, 0xd4, 0xa2, 0xaf, 0x9c, 0xa4, 0x72, 0xc0}, 
				{0xb7, 0xfd, 0x93, 0x26, 0x36, 0x3f, 0xf7, 0xcc, 0x34, 0xa5, 0xe5, 0xf1, 0x71, 0xd8, 0x31, 0x15}, 
				{0x04, 0xc7, 0x23, 0xc3, 0x18, 0x96, 0x05, 0x9a, 0x07, 0x12, 0x80, 0xe2, 0xeb, 0x27, 0xb2, 0x75}, 
				{0x09, 0x83, 0x2c, 0x1a, 0x1b, 0x6e, 0x5a, 0xa0, 0x52, 0x3b, 0xd6, 0xb3, 0x29, 0xe3, 0x2f, 0x84}, 
				{0x53, 0xd1, 0x00, 0xed, 0x20, 0xfc, 0xb1, 0x5b, 0x6a, 0xcb, 0xbe, 0x39, 0x4a, 0x4c, 0x58, 0xcf}, 
				{0xd0, 0xef, 0xaa, 0xfb, 0x43, 0x4d, 0x33, 0x85, 0x45, 0xf9, 0x02, 0x7f, 0x50, 0x3c, 0x9f, 0xa8}, 
				{0x51, 0xa3, 0x40, 0x8f, 0x92, 0x9d, 0x38, 0xf5, 0xbc, 0xb6, 0xda, 0x21, 0x10, 0xff, 0xf3, 0xd2}, 
				{0xcd, 0x0c, 0x13, 0xec, 0x5f, 0x97, 0x44, 0x17, 0xc4, 0xa7, 0x7e, 0x3d, 0x64, 0x5d, 0x19, 0x73}, 
				{0x60, 0x81, 0x4f, 0xdc, 0x22, 0x2a, 0x90, 0x88, 0x46, 0xee, 0xb8, 0x14, 0xde, 0x5e, 0x0b, 0xdb}, 
				{0xe0, 0x32, 0x3a, 0x0a, 0x49, 0x06, 0x24, 0x5c, 0xc2, 0xd3, 0xac, 0x62, 0x91, 0x95, 0xe4, 0x79}, 
				{0xe7, 0xc8, 0x37, 0x6d, 0x8d, 0xd5, 0x4e, 0xa9, 0x6c, 0x56, 0xf4, 0xea, 0x65, 0x7a, 0xae, 0x08}, 
				{0xba, 0x78, 0x25, 0x2e, 0x1c, 0xa6, 0xb4, 0xc6, 0xe8, 0xdd, 0x74, 0x1f, 0x4b, 0xbd, 0x8b, 0x8a}, 
				{0x70, 0x3e, 0xb5, 0x66, 0x48, 0x03, 0xf6, 0x0e, 0x61, 0x35, 0x57, 0xb9, 0x86, 0xc1, 0x1d, 0x9e}, 
				{0xe1, 0xf8, 0x98, 0x11, 0x69, 0xd9, 0x8e, 0x94, 0x9b, 0x1e, 0x87, 0xe9, 0xce, 0x55, 0x28, 0xdf}, 
				{0x8c, 0xa1, 0x89, 0x0d, 0xbf, 0xe6, 0x42, 0x68, 0x41, 0x99, 0x2d, 0x0f, 0xb0, 0x54, 0xbb, 0x16}};

		// return array
		int[][] subByteArray = new int[4][4];

		// substitute each byte to another from the lookup table
		for (int i=0; i<array.length; i++)
			for (int j=0; j<array[0].length;j++){
				int a = array[i][j];
				String hex = String.format("%2s",Integer.toHexString(a)).replace(' ','0');
				char c0 = hex.charAt(0);
				char c1 = hex.charAt(1);
				subByteArray[i][j] = s_box[Character.getNumericValue(c0)][Character.getNumericValue(c1)];
			}

		return(subByteArray);
	};

	// the diffusion step where values of the rows in the matrix are shifted cyclically by 0,1,2 and 3 
	public static int[][] shiftRows(int[][] array){

		int[][] toReturn = new int[4][4];
		for (int i=0; i<array.length; i++)
			for (int j=0; j<array[i].length; j++){
				toReturn[i][j] = array[i][(j+i)%array[i].length];	
			}

		return (toReturn);
	};

	// Galois field multiplication by 2
	public static int mult2(int a){

		int r = (a<<1);
		if ((r & 0x100) != 0){
			r^=0x11b;
		}

		return(r);
	};

	// Galois field multiplication by 3
	public static int mult3(int a){
		return (mult2(a)^a);
	};

	// multiplication of two matrices where each column of our array is a four-term polynomial
	public static int[][] mixColumns(int[][] array){
		
		int[][] out = new int[4][4];
		
		for(int c=0; c<4; c++){
			out[0][c] = (	mult2(array[0][c])	^ mult3(array[1][c]) 	^ array[2][c] 			^ array[3][c]);
			out[1][c] = (	array[0][c] 		^ mult2(array[1][c]) 	^ mult3(array[2][c]) 	^ array[3][c]);
			out[2][c] = (	array[0][c] 		^ array[1][c] 			^ mult2(array[2][c]) 	^ mult3(array[3][c]));
			out[3][c] = (	mult3(array[0][c]) 	^ array[1][c] 			^ array[2][c] 			^ mult2(array[3][c]));
		}
		
		return(out);
	};
	
	// bitwise XOR of the subkey and state
	public static int[][] addRoundKey(int[][] array, int[][] subkey){
		
		int[][] toReturn = new int[4][4];
		
		for (int i=0;i<array.length;i++)
			for (int j=0;j<array[i].length;j++)
				toReturn[i][j] = array[i][j]^subkey[i][j];
		
		return(toReturn);
	};

	// transforming Big Integer into a two-dimensional array in a column-major order
	public static int[][] integerToState(BigInteger x){
		
		String intToState = x.toString(16);
		int[] row1 = new int[4];
		int[] row2 = new int[4];
		int[] row3 = new int[4];
		int[] row4 = new int[4];
		for (int i=0,j=0; i<intToState.length(); i+=8){
			String a = intToState.substring(i,i+2);
			int b = Integer.parseInt(a,16); 
			row1[j] = b;
			j++;}
		for (int i=2,j=0; i<intToState.length(); i+=8){
			String a = intToState.substring(i,i+2);
			int b = Integer.parseInt(a,16); 
			row2[j] = b;
			j++;}
		for (int i=4,j=0; i<intToState.length(); i+=8){
			String a = intToState.substring(i,i+2);
			int b = Integer.parseInt(a,16); 
			row3[j] = b;
			j++;}
		for (int i=6,j=0; i<intToState.length(); i+=8){
			String a = intToState.substring(i,i+2);
			int b = Integer.parseInt(a,16); 
			row4[j] = b;
			j++;}
		
		int[][] state = {row1,row2,row3,row4};
		
		return(state);
	};
	
	// convert state back to a Big Integer value
	public static BigInteger stateToInteger(int[][] state){
		
		StringBuilder x = new StringBuilder();
		for (int row = 0; row < state.length; row++)  
			for (int j = 0; j < state[row].length; j++){
				int intx = state[row][j];
				String Hex=Integer.toHexString(intx);
				x.append(Hex);
			} 
		
		String y = x.toString();
		BigInteger z = new BigInteger(y,16);
		return(z);
	};
	
	// method to print out a state as 4*4 array of hexadecimal values 
	public static void outputState(int[][] state){
		
		String xop = "";
		StringBuilder out = new StringBuilder();
		
		for (int row = 0; row < state.length; row++)  
			for (int j = 0; j < state[row].length; j++){		                
				xop += String.format("%2s",Integer.toHexString(state[row][j])).replace(' ','0');
				out.append(xop);}
		
		xop = xop.replaceAll("(.{2})", "$1 ").replaceAll("(.{12})", "$1\n").trim();
		StdOut.println(xop);
	}

	public static void main(String[] args) {
		//given plaintext
		BigInteger input = new BigInteger("3243f6a8885a308d313198a2e0370734",16);

		//array of 11 subkeys
		BigInteger[] keySchedule = new BigInteger[11];
		keySchedule[0] = new BigInteger("2b7e151628aed2a6abf7158809cf4f3c",16);
		keySchedule[1] = new BigInteger("a0fafe1788542cb123a339392a6c7605",16);
		keySchedule[2] = new BigInteger("f2c295f27a96b9435935807a7359f67f",16);
		keySchedule[3] = new BigInteger("3d80477d4716fe3e1e237e446d7a883b",16);
		keySchedule[4] = new BigInteger("ef44a541a8525b7fb671253bdb0bad00",16);
		keySchedule[5] = new BigInteger("d4d1c6f87c839d87caf2b8bc11f915bc",16);
		keySchedule[6] = new BigInteger("6d88a37a110b3efddbf98641ca0093fd",16);
		keySchedule[7] = new BigInteger("4e54f70e5f5fc9f384a64fb24ea6dc4f",16);
		keySchedule[8] = new BigInteger("ead27321b58dbad2312bf5607f8d292f",16);
		keySchedule[9] = new BigInteger("ac7766f319fadc2128d12941575c006e",16);
		keySchedule[10] = new BigInteger("d014f9a8c9ee2589e13f0cc8b6630ca6",16);

		StdOut.println("== Plaintext ==");
		int[][] plaintextState = integerToState(input); outputState(plaintextState);

		BigInteger key = keySchedule[0];
		int[][] keyState = integerToState(key);

		int[][] xored = addRoundKey(plaintextState,keyState);
		int[][] mixColumns = new int[4][4];

		// round 1-9
		for (int i=1;i<=9;i++){
			System.out.printf("\n== Round %d ==\n",i);
			StdOut.println("-- Start of round --");
			outputState(xored);

			StdOut.printf("\n-- After subBytes --\n");
			int[][] subBytes = subBytes(xored); outputState(subBytes);

			StdOut.printf("\n-- After shiftRows --\n");
			int[][] shiftRows = shiftRows(subBytes); outputState(shiftRows);

			StdOut.printf("\n-- After mixColumns --\n");
			mixColumns = mixColumns(shiftRows); outputState(mixColumns);

			key = keySchedule[i];
			keyState = integerToState(key);
			xored = addRoundKey(mixColumns,keyState);
		}	

		// round 10
		System.out.printf("\n== Round 10 ==\n");
		StdOut.println("-- Start of round --");
		key = keySchedule[9];
		keyState = integerToState(key);
		xored = addRoundKey(mixColumns,keyState); outputState(xored);

		StdOut.printf("\n-- After subBytes --\n");
		int[][] subBytes = subBytes(xored); outputState(subBytes);

		StdOut.printf("\n-- After shiftRows --\n");
		int[][] shiftRows = shiftRows(subBytes); outputState(shiftRows);
		
		// final result
		System.out.printf("\n== Ciphertext ==\n");
		key = keySchedule[10];
		keyState = integerToState(key);
		xored = addRoundKey(shiftRows,keyState); outputState(xored);

	}
}


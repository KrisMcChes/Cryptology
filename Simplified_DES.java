//Kristina McChesney
//CSC 440 
//Winter 2019
//Assignment 4, Question 3
package csc440;
import java.util.Scanner;
import stdlib.StdOut;

public class Simplified_DES {

	public static int expander(int R){
		String r = Integer.toBinaryString(R);
		String stringR = String.format("%6s",r).replace(' ', '0');

		StringBuilder sb = new StringBuilder();
		sb.append(stringR.charAt(0));
		sb.append(stringR.charAt(1));
		sb.append(stringR.charAt(3));
		sb.append(stringR.charAt(2));
		sb.append(stringR.charAt(3));
		sb.append(stringR.charAt(2));
		sb.append(stringR.charAt(4));
		sb.append(stringR.charAt(5));

		String s = sb.toString();
		int expandedR = Integer.parseInt(s,2);
		return(expandedR);
	}

	public static int exclusive_or(int V1, int V2){
		int out = V1^V2;
		return(out);
	}

	public static int s_boxes(int bits8){
		int left4 = (bits8 & 0b11111111) >> 4;
		int right4 = bits8 & 0b1111;
		String l0 = Integer.toBinaryString(left4);
		String r0 = Integer.toBinaryString(right4);
		String leftS = String.format("%4s",l0).replace(' ','0');
		String rightS = String.format("%4s",r0).replace(' ','0');

		String S1_0[] = {"101", "010", "001", "110", "011", "100", "111", "000"};
		String S1_1[] = {"001", "100", "110", "010", "000", "111", "101", "011"};
		String S2_0[] = {"100", "000", "110", "101", "111", "001", "011", "010"};
		String S2_1[] = {"101", "011", "000", "111", "110", "010", "001", "100"};

		String columnS1 = leftS.substring(1,4);
		int indexS1 = Integer.parseInt(columnS1,2);	

		String columnS2 = rightS.substring(1,4);
		int indexS2 = Integer.parseInt(columnS2,2);		

		StringBuilder sb = new StringBuilder();
		if (leftS.charAt(0) == '0'){		
			String x = String.format("%3s",S1_0[indexS1]).replace(' ','0');
			sb.append(x);
		}
		else {
			String x = String.format("%3s",S1_1[indexS1]).replace(' ','0');
			sb.append(x);
		}

		if (rightS.charAt(0) == '0'){		
			String y = String.format("%3s",S2_0[indexS2]).replace(' ','0');
			sb.append(y);
		}
		else {
			String y = String.format("%3s",S2_1[indexS2]).replace(' ','0');
			sb.append(y);
		}

		String st = sb.toString();
		int sBoxOut = Integer.parseInt(st,2);

		return(sBoxOut);
	}

	public static String encrypt(int plaintext, int[] key){
		int L0 = (plaintext & 0b111111000000) >> 6;
		int R0 = plaintext & 0b111111;

		String sL0 = Integer.toBinaryString(L0);
		String stL0 = String.format("%6s",sL0).replace(' ','0');

		String sR0 = Integer.toBinaryString(R0);
		String stR0 = String.format("%6s",sR0).replace(' ','0');

		System.out.printf("L0 = %d (%s); R0 = %d (%s)\n",L0,stL0,R0,stR0);

		// 1st round
		int L1 = R0;
		String sL1 = Integer.toBinaryString(L1);
		String stL1 = String.format("%6s",sL1).replace(' ','0');

		int expanded = expander(R0);
		int fExored = exclusive_or(expanded,key[0]);
		int sboxed = s_boxes(fExored);

		int R1 = exclusive_or(L0,sboxed);
		String sR1 = Integer.toBinaryString(R1);
		String stR1 = String.format("%6s",sR1).replace(' ','0');
		System.out.printf("L1 = %d (%s); R1 = %d (%s)\n",L1,stL1,R1,stR1);
		StdOut.println("-----------------------------------");

		// 2nd round
		int L2 = R1;
		String sL2 = Integer.toBinaryString(L2);
		String stL2 = String.format("%6s",sL2).replace(' ','0');

		int expanded2 = expander(R1);
		int fExored2 = exclusive_or(expanded2,key[1]);
		int sboxed2 = s_boxes(fExored2);

		int R2 = exclusive_or(L1,sboxed2);
		String sR2 = Integer.toBinaryString(R2);
		String stR2 = String.format("%6s",sR2).replace(' ','0');

		System.out.printf("L2 = %d (%s); R2 = %d (%s)\n",L2,stL2,R2,stR2);
		StdOut.println("-----------------------------------");

		// 3rd round
		int L3 = R2;
		String sL3 = Integer.toBinaryString(L3);
		String stL3 = String.format("%6s",sL3).replace(' ','0');

		int expanded3 = expander(R2);
		int fExored3 = exclusive_or(expanded3,key[2]);
		int sboxed3 = s_boxes(fExored3);

		int R3 = exclusive_or(L2,sboxed3);
		String sR3 = Integer.toBinaryString(R3);
		String stR3 = String.format("%6s",sR3).replace(' ','0');
		System.out.printf("L3 = %d (%s); R3 = %d (%s)\n",L3,stL3,R3,stR3);
		StdOut.println("-----------------------------------");

		// 4th round
		int L4 = R3;
		String sL4 = Integer.toBinaryString(L4);
		String stL4 = String.format("%6s",sL4).replace(' ','0');

		int expanded4 = expander(R3);
		int fExored4 = exclusive_or(expanded4,key[3]);
		int sboxed4 = s_boxes(fExored4);

		int R4 = exclusive_or(L3,sboxed4);
		String sR4 = Integer.toBinaryString(R4);
		String stR4 = String.format("%6s",sR4).replace(' ','0');
		System.out.printf("L4 = %d (%s); R4 = %d (%s)\n",L4,stL4,R4,stR4);
		StdOut.println("-----------------------------------");

		StringBuilder sb = new StringBuilder(); 
		sb.append(stL4);
		sb.append(stR4);

		String encrypted = sb.toString(); 
		return(encrypted);
	}

	public static void main(String[] args){		
		Scanner userInput = new Scanner(System.in);
		System.out.print("Enter plaintext int: ");
		String plaintextInput = userInput.nextLine();
		System.out.print("Enter key int: ");
		String keyInput = userInput.nextLine();
		userInput.close();

		int plaintextInt = Integer.parseInt(plaintextInput);
		String sP = Integer.toBinaryString(plaintextInt);
		String stP = String.format("%12s",sP).replace(' ','0');

		int keyInt = Integer.parseInt(keyInput);
		String sK = Integer.toBinaryString(keyInt);
		String stK = String.format("%9s",sK).replace(' ','0');

		System.out.printf("Plaintext = %d (%s); Key = %d (%s)\n\n",plaintextInt,stP,keyInt,stK);

		String k1 = stK.substring(0,8);
		String k2 = stK.substring(1,9);
		String k3 = (stK.substring(2,9))+(stK.charAt(0));
		String k4 = (stK.substring(3,9))+(stK.substring(0,2));	

		int key1 = Integer.parseInt(k1,2);
		int key2 = Integer.parseInt(k2,2);
		int key3 = Integer.parseInt(k3,2);
		int key4 = Integer.parseInt(k4,2);

		int[] keyArray = {key1,key2,key3,key4}; 

		String encrypted = encrypt(plaintextInt, keyArray);
		int encInt = Integer.parseInt(encrypted,2);
		String y = Integer.toBinaryString(encInt);
		String encStr = String.format("%12s",y).replace(' ','0');
		System.out.printf("Ciphertext = %d (%s)",encInt,encStr);
	}

}

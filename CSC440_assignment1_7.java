//Kristina McChesney
//CSC 440 
//Winter 2019
//Assignment 1, Question 7.

package csc440;
import stdlib.StdOut;

public class CSC440_assignment1_7 {
	
	//program to determine the likely length of the key used to create given ciphertext. 
	public static void main(String[] args) {
		String cipherText = "QHDLXNQLYNGAIGWBCERJFEARNIBKXUSVGZXKYNPXXTKGAATZRQCRFYIDCCLYXHUQXEIXFAFGEAMMALYRGAYXQMTGACDJSYRTLEXUVRVIYFFEGXFKOYSPHGBBYTRESOXUNTXXAKLUAWYDINAAWCZWIFVMCROIUCEIFJYDJAYZJBEOTMUSGAGAYYQNIPTFPYMCBOYDYYSVGWDOJTBZLMFBYJXLQCUDRRIGMIUYWMQUUFRPCZQHTVJOUJSMNRVQQZEJYLACNHRFCPTFENZYEJCLYMBQUCGUMYQDBUAWLQTMOAXCZJBEABHQJYEAMQQDNIRLNTUINRMCYUJAQTZQMGOEXUDEONQPIDBXWNKNIEUNQMBQDUFGXLFXYBVKNTEZCBFJGJUTVHHMBWOZIFQNCTLMBQELYVGNTUHIAXNQUHSROYZJCEFUIACVOBFVAEGBBHGNEIMOHIYRIOZQ";
		int ciTextLen = cipherText.length();		
		char[] arrayText = cipherText.toCharArray();

		for (int shift=1; shift<14; shift++){
			int coincidenceCount = 0;
			for (int index = 0; index < ciTextLen-1; index++) {
				int shiftedIndex = (index + shift) % ciTextLen;
				if (arrayText[index] == arrayText[shiftedIndex])
					coincidenceCount++;
			}
		StdOut.println("Shift: " + shift + "; coincidence: " + coincidenceCount);
		}
	}
}

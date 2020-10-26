//Kristina McChesney
//CSC 440 
//Winter 2019
//Assignment 6
package csc440;

import java.math.BigInteger;
import java.util.HashMap;
import stdlib.StdOut;

public class ShortPlaintext_Attack {

	public static HashMap<BigInteger, Integer> listOne(BigInteger c, BigInteger e, BigInteger n, int N){
		System.out.println("List one is being created");
		HashMap<BigInteger, Integer> resultHM = new HashMap<>();
		// computes the list of values cx^-e(mod n)
		for (int x=0; x<N; x++){
			try {
				BigInteger x_inv_pow = BigInteger.valueOf(x).modPow(e.negate(),n);
				BigInteger result = (c.multiply(x_inv_pow)).mod(n);
				resultHM.put(result,x);
			}
			catch (Exception exc) {
				System.out.printf("Inverse for %d doesn't exist. %s\n",x,exc);
			}
		}
		System.out.println("List one created");
		return(resultHM);
	}

	public static void listTwo(BigInteger e, BigInteger n, int N, HashMap<BigInteger, Integer> hm1){
		System.out.println("List two is running");
		// computes y^e(mod n)
		for (int y=0; y<N; y++){
			BigInteger result = BigInteger.valueOf(y).modPow(e,n);
			// is the value computed in step 2 exists in step 1
			if (hm1.containsKey(result)) {
				System.out.println("Match found");
				BigInteger x = BigInteger.valueOf(hm1.get(result));
				// find a message = index of the matching value from the 1st list * index of the second list
				BigInteger message = BigInteger.valueOf(y).multiply(x);
				StdOut.println("The message is: " + message);
				break;
			}
		}
	}

	public static void main(String[] args) {

		BigInteger mod = new BigInteger ("665491722871");
		BigInteger exponent = new BigInteger ("710221");
		BigInteger cipher = new BigInteger ("121259080226");
		int listSize = (int)Math.pow(2,21);

		HashMap<BigInteger,Integer> hm1 = listOne(cipher,exponent,mod,listSize);
		listTwo(exponent,mod,listSize,hm1);

	}

}

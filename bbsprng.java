//Kristina McChesney
//CSC 440 
//Winter 2019
//Assignment 2, Question 5.
package csc440;
import java.math.BigInteger;
import stdlib.StdOut;

public class bbsprng {

	public static void main(String[] args) {
		blumBlumGenerator();
	}

	public static void blumBlumGenerator(){	
		BigInteger p = new BigInteger("24672462467892469787");
		BigInteger q = new BigInteger("396736894567834589803");

		BigInteger n = p.multiply(q);
		StdOut.println("n: " + n);

		BigInteger x = new BigInteger("873245647888478349013");

		BigInteger x0 = (x.pow(2)).mod(n);	
		StdOut.println("x0: " + x0);

		BigInteger x1 = (x0.pow(2)).mod(n);	
		StdOut.println("x1: " + x1);

		BigInteger x2 = (x1.pow(2)).mod(n);	
		StdOut.println("x2: " + x2);

		BigInteger x3 = (x2.pow(2)).mod(n);	
		StdOut.println("x3: " + x3);

		BigInteger x4 = (x3.pow(2)).mod(n);	
		StdOut.println("x4: " + x4);

		BigInteger x5 = (x4.pow(2)).mod(n);	
		StdOut.println("x5: " + x5);

		BigInteger x6 = (x5.pow(2)).mod(n);	
		StdOut.println("x6: " + x6);

		BigInteger x7 = (x6.pow(2)).mod(n);	
		StdOut.println("x7: " + x7);

		BigInteger x8 = (x7.pow(2)).mod(n);	
		StdOut.println("x8: " + x8);
	}
}
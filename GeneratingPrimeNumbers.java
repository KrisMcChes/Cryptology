//Kristina McChesney
//CSC 440 
//Winter 2019
//Assignment 3, Question 6
package csc440;
import java.util.Random;

public class GeneratingPrimeNumbers {
	
	// generate a random integer between 2^30 and 2^31 
	public static int randomIntGenerator(int min, int max){
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}
	
	// primality test based on Fermat's Little Theorem
	public static boolean isProbablyPrime(long p) {
		for (int index = 1; index <= 20; index++){
			long a = Math.round(Math.floor(Math.random()*(p-2)+2.0));
			if (powermod(a, p-1, p) != 1) {
				return false;
			}
		}
		return true;
	}

	public static long powermod(long i, long e, long m) {
		// System.out.println(i + " " + e + " " + m);
		if (e == 0) return 1;
		if (e % 2 == 0) return squaremod(powermod(i, e/2, m), m) % m;
		else return i * powermod(i, e-1, m) % m;
	}

	public static long squaremod(long i, long m) {
		return i*i % m;
	}

	public static void main(String[] args) {
		// random numbers range
		int min = (int)Math.pow(2,30);
		int max = (int)Math.pow(2,31);
		
		// generate random integers within given range and check if they are prime
		for(int i=0; i<16; i++){
			int count = 0;
			int continueLoop = 1;
			while(continueLoop==1){
				long randomInt = (long)randomIntGenerator(min,max);
				//	StdOut.println(randomInt);
				boolean isPrime = isProbablyPrime(randomInt);
				//	StdOut.println(isPrime);
				count++;
				if (isPrime == true){
					System.out.printf("Prime: %d\t",randomInt);
					continueLoop = 0;
					}
			}
			System.out.printf("Took %2d attempts to find prime\n", count);
		}
	}
}

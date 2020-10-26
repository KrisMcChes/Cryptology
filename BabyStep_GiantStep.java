//Kristina McChesney
//CSC 440 
//Winter 2019
//Assignment 6
package csc440;

import java.math.BigInteger;
import java.util.HashMap;
import stdlib.StdOut;

public class BabyStep_GiantStep {

	public static HashMap<BigInteger, Integer> BabyStep(int N, BigInteger alpha, BigInteger p){
		HashMap<BigInteger, Integer> resultHM = new HashMap<>();
		// compute list f values a^j (mod p);
		for (int j=0; j<N; j++)
		{
			BigInteger result = alpha.modPow(BigInteger.valueOf(j),p);
			resultHM.put(result,j);
		}
		return(resultHM);
	};

	public static void GiantStep(int N, BigInteger a, BigInteger b, BigInteger p, HashMap<BigInteger,Integer> map){
		// compute second list ba^-Nk (mod p)
		for (int k=0; k<N; k++){
			int exponent = N*k;
			BigInteger ainverse = a.modPow(BigInteger.valueOf(exponent).negate(),p);
			BigInteger result = (b.multiply(ainverse)).mod(p);
			// is the value computed exists in the first list?
			if (map.containsKey(result)){
				int j = map.get(result);
				int message = j+N*k;
				StdOut.println("Message is: " + message);
			}
		}
	}

	public static void main(String[] args) {
		BigInteger p = new BigInteger ("595117");
		BigInteger alpha = new BigInteger ("1002");		
		BigInteger beta = new BigInteger ("437083");
		int n = 772; //(square root p-1)+1

		HashMap<BigInteger,Integer> bstep = BabyStep(n,alpha,p);
		GiantStep(n,alpha,beta,p,bstep);
	}
}

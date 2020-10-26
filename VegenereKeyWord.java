//Kristina McChesney
//CSC 440 
//Winter 2019
//Assignment 2, Question 4b.
package csc440;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import algs31.BinarySearchST;
import stdlib.StdOut;

public class VegenereKeyWord {
	public static void main(String[] args) {
		String cipherText = "VVHQWVVRHMUSGJGTHKIHTSSEJCHLSFCBGVWCRLRYQTFSVGAHWKCUHWAUGLQHNSLRLJSHBLTSPISPRDXLJSVEEGHLQWKASSKUWEPWQTWVSPGOELKCQYFNSVWLJSNIQKGNRGYBWLWGOVIOKHKAZKQKXZGYHCECMEIUJOQKWFWVEFQHKIJRCLRLKBIENQFRJLJSDHGRHLSFQTWLAUQRHWDMWLGUSGIKKFLRYVCWVSPGPMLKASSJVOQXEGGVEYGGZMLJCXXLJSVPAIVWIKVRDRYGFRJLJSLVEGGVEYGGEIAPUUISFPBTGNWWMUCZRVTWGLRWUGUMNCZVILE";
//		String cipherText = "LNKNWQXCKEWAZHVJWAEORNLUPSSAITOEROVNWUIWTVVTZSIFPUTDAGUUTGEVWHTAZGDIEBIPWSZJBUHDZOBELBLPQBQOWPPRLNYWJVWAEPABISFQBUDMVNQPDAEZQAWHVCMBOOXEKNORVHIGLOEOWSWHVLZRVEEPBBWHVLIFWIEDIOLTRJBFRFKDIGFOEPQAHNKPPRVEWWKGVSVAURGTFIMGRTYNWJVODATVJHKKVGKEFNQTLNFBACHCZAAGKAKIGFWEIUWSPYJPMELEJWAVWHROJRHNTWTYHDSUWAHOWKCEJRVWBRVTGDQYRSFLPRUSFJULUEKQZAKODAQGRCTQZEHDKKURLNKDIGVODABULNXIQTKTGAZUDPJXMZDDVKCGRNKDQFTUVOBVRNSUXNWIVJBYBATYCZXLRPQAJAEZZRILVYBVQGFJIYOSFNBFRFWWKGVWYEKUFOLHLCRSJEJYBHRRMNQYSAIELNXKVVWAWPMEIIMAGRDRJSWENIRHTBZEUIGFHLWPWFSETQTNWEFJBUHSLXRRFTRJLQUENQXFRMVOPBUTEKBRVTYAARLEEHIEJEUEVVQTFWAXHTTDWSWHVYWAFLLOQBQSNDQPKTYAVFHEDALGRMVLZBEASHMSUODPPNWPVNQBGTFPPRSRVOMAWDRUQUDVVOBRDDZHGCXRJQMQWHVOIZHOSFMPWIYKXRWHRPQZDYSAMKFUJALSRRVJBRUIECWAWHVOMCHRJKVNODVPIVOSROQTLVVPPRPTFOPBZTYWBVKAMAVBWBVAVUDSKUQAFODEVTWORZMPLSZKVZBWFNSVVNFSVRDRCUNVQIJDMQEUKWAVWWZHTGDKVIMGZOFNBUUEVIWEHYVWZFWOTKUCOEKAQGDNUWAZBHVWTGKIJBIEIRFIAGUOECQUDVVXMRQUICMQWOGQJYLSYPPVVASOBEDCKEPNYEDKZRHSGAKVDLCUJRHNZJLHFEUPWQRTYEANVMISIYOATAEURIJJWJVTLZGVQGKDMADTLNIYKIJPWEBOWPPRPACWGNUCYEXROAXKPNVAINQIHDRPIYPOJPMKDCKHGGKEJWURJEEAZNOCFJKYXSZKVFWHRPQUDVVKVGKEFNQTLNFBACHCZAAYDSKUMNUHVOMAWTFIMNPEDKQERNKDQFVUSFMPWWZPPNUEHQMFWTYWBVZOLHLSRRNWZQLTKKAVUCYWZYHSCUMYOWYKARQTZPBBWHVHQAQERJABFIVPGNQDZPQFSUSHQFKEUEVGKEKDQEGVFHCZHOWPPRMOLNVNOOWPPNWSFYQRWYJEZPOYVHTNQDUNPBRKVNEURBFPPXQENKNZBWFNSGKECWBGHRYWDVQGIAIQPYJGMGFHFBPBQOLNMQPESUBULNBEVTLTRZDVVASHMGRPLXTVVHNEBUPRNWTYDCVOMKFECHMAWMVIWVUSFIMOUIVBMKWRRYBFIRFIULPAEQAPUIGPAGKIJWJFWRRYBJKITDQARWGQJYLSYICFWNVYMFVAIETLEEZIXRUFVYBVFAEJWGKEIAOVYEIANRUEEYMFDNUWCGKOIEBVHSWKZZBSVRMEDLJPIGHMVJBFDNUEUHVTKNCFWTFPPRUERZMEUEGKAVQGJKURFOEBQQHNTAQAPYRYKHUATUVBGOLXBRURFNAJLLCDIIHCIAXGLNKDWHJHZDWCHIYWDRDLNWGFEEVJKNXTZKCFLNKNCFWIECBBJOFZIHWHFNQGLEJWTBQEZYIAKEIAOVYEFJTLWHVCMAHRRHKBQCCQAVRNJWBJKITDQUDVVWZELVVZEVWHRBMJIATPAVQICHCFWRRPQBQBLPEULCYEPBSEZJUBVTTWARVWZHTFXFWEKRQOFJMPDNWAMYPOIAARQSZXTRWHRJQQROWPPRQETAAFLTPKNUHRVWNGHRGQJYLSYEVTLNUABNLLRHTGKEWWKGVWZPPEHFVNMAFEJKVJKITDULFOEYTHVIFJAUDVVXMRQGIKCAGEUWVQLHFLMVQAWQBHUENKZXWOUKBULSWKZVDMNATYDWRNMGKAKOKNUCVHGNVIECTRSOZJBVVDZOKHVSVZQAWHZODBOUDAWAZHZYPSDCKOKNQNFPJRDDUQKRGOWPMADPGWZRQTCUTRDDZJOGRCFJKYXSZKVFGIIAKGOYFLXBVIKABBWHFOMNWWYEKULHRRMNURZRMQDFREZEHSLHBPDNSAWOWAZJMQRNCUJLIUCHGFWAKEVTDNUXIYDNTEVTWHVBIPWSRJLNUGLIMAWSFJJBWHJELRVOWAIPKQLAAGLOEWVQWHZOKNQNFPXBVSZXTLEEYAZRGOEA";
		findKey(cipherText);
	}
	// program to find the length of the key
	public static int findKeySize(String cipherText){
		int ciTextLen = cipherText.length();
		char[] arrayText = cipherText.toCharArray();
		ArrayList<Integer> coincidenceArray = new ArrayList<>();
		for (int shift=1; shift<14; shift++){						//try key length from 1 to 13
			int coincidenceCount = 0;
			for (int index = 0; index < ciTextLen-1; index++) {		//for each character in ciphertext
				int shiftedIndex = (index + shift) % ciTextLen;
				if (arrayText[index] == arrayText[shiftedIndex])
					coincidenceCount++;
			}
			coincidenceArray.add(coincidenceCount);
		}

		int maxCoincidence = Collections.max(coincidenceArray);
		int shift = (coincidenceArray.indexOf(maxCoincidence))+1;
		StdOut.println("Key size is: "+ shift);
		return(shift);
	}

	// program to find the length of the key
	public static String findKey(String text){
		String cipherText = text.toLowerCase();
		int keyLength = findKeySize(cipherText);
		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		ArrayList<Character> keyword = new ArrayList<>();
		//do n times (n=size of the keyword)
		for (int l=0; l<keyLength; l++){
			// populate ST with alphabet letters
			BinarySearchST<Character, BigDecimal> letterOccur = new BinarySearchST<>();
			for (int j=0;j<alphabet.length();j++){
				char c = alphabet.charAt(j);
				letterOccur.put(Character.valueOf(c), BigDecimal.valueOf(0));
			}
			// count occurrences of the letters from ciphertext
			int totalLettersAtPosition = 0;
			for (int index = l; index < cipherText.length(); index+=keyLength){
				char c = cipherText.charAt(index);
				BigDecimal d = letterOccur.get(c);
				totalLettersAtPosition++;
				letterOccur.put(c, d.add(new BigDecimal(1)));
			}
			// create a vector with occurrences of the letters
			ArrayList<BigDecimal> vectorV = new ArrayList<>();
			for (char c: letterOccur.keys()){
				BigDecimal v = letterOccur.get(c);
				vectorV.add(v);
			}

			// count frequencies of letters in given ciphertext ()
			ArrayList<BigDecimal> vectorW = new ArrayList<>();
			for (int i=0; i<vectorV.size();i++){
				BigDecimal v = vectorV.get(i);
				BigDecimal t = BigDecimal.valueOf(totalLettersAtPosition);
				BigDecimal w = v.divide(t,4,RoundingMode.HALF_DOWN);	
				vectorW.add(w);
			}	

			// frequencies of letters in English texts in alphabetic order
			ArrayList<BigDecimal> vectorA0 = new ArrayList<>(); 		
			vectorA0.add(new BigDecimal(".082"));
			vectorA0.add(new BigDecimal(".015"));
			vectorA0.add(new BigDecimal(".028"));
			vectorA0.add(new BigDecimal(".043"));
			vectorA0.add(new BigDecimal(".127"));
			vectorA0.add(new BigDecimal(".022"));
			vectorA0.add(new BigDecimal(".020"));
			vectorA0.add(new BigDecimal(".061"));
			vectorA0.add(new BigDecimal(".070"));
			vectorA0.add(new BigDecimal(".002"));
			vectorA0.add(new BigDecimal(".008"));
			vectorA0.add(new BigDecimal(".040"));
			vectorA0.add(new BigDecimal(".024"));
			vectorA0.add(new BigDecimal(".067"));
			vectorA0.add(new BigDecimal(".075"));
			vectorA0.add(new BigDecimal(".019"));
			vectorA0.add(new BigDecimal(".001"));
			vectorA0.add(new BigDecimal(".060"));
			vectorA0.add(new BigDecimal(".063"));
			vectorA0.add(new BigDecimal(".091"));
			vectorA0.add(new BigDecimal(".028"));
			vectorA0.add(new BigDecimal(".010"));
			vectorA0.add(new BigDecimal(".023"));
			vectorA0.add(new BigDecimal(".001"));
			vectorA0.add(new BigDecimal(".020"));
			vectorA0.add(new BigDecimal(".001"));

			// list for holding 25 results of max value W*Ai computations
			ArrayList<BigDecimal> maxValue = new ArrayList<>(); 

			// create 25 vectors Ai, where values are shifted to the right by i position
			for (int j=0;j<25;j++){
				BigDecimal temp = vectorA0.get(vectorA0.size()-1); 
				for(int i = vectorA0.size()-1; i > 0; i--){
					vectorA0.set(i,vectorA0.get(i-1)); 
				}
				vectorA0.set(0, temp);     

				//compute product of W and Aj vector 
				BigDecimal sum = new BigDecimal(0);
				for (int i=0; i<vectorA0.size();i++){
					BigDecimal a = vectorA0.get(i);
					BigDecimal w = vectorW.get(i);
					BigDecimal product = a.multiply(w);
					sum = sum.add(product);
				}
				maxValue.add(sum);
			}
			BigDecimal maxVal = Collections.max(maxValue); 
			int letterIndex = maxValue.indexOf(maxVal)+1;
			char keyChar = alphabet.charAt(letterIndex);
			keyword.add(keyChar);
		}
		String listString = "";
		for (char c: keyword){
			listString += c;
		}
		StdOut.println("Keyword is: "+listString);
		return(listString);
	}
}


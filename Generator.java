// Password generation algorithms mainly run in this class


import java.util.ArrayList;
import java.util.Collections;

public class Generator {

	int generateASCIIChar(int lowerLimit, int upperLimit) {
		ArrayList<Integer> dictionaryIndexes = new ArrayList<Integer>();
		int limiter = lowerLimit;
		while (upperLimit != limiter) {
			dictionaryIndexes.add(limiter);
			limiter++;
		}

		return dictionaryIndexes.get((int) (Math.random() * dictionaryIndexes
				.size()));
	}

	char generateACapitalLetter() {
		return (char) generateASCIIChar(65, 90);
	}

	char generateASmallLetter() {
		return (char) generateASCIIChar(97, 122);
	}

	char generateLetter() {
		double sentinel = Math.random() * 100;
		int val = (int) sentinel;
		if (val >= 55) {
			return generateACapitalLetter();
		} else {
			return generateASmallLetter();
		}
	}

	char generateANumber() {
		return (char) generateASCIIChar(48, 58);

	}

	char generateASpecialChar1() {
		return (char) generateASCIIChar(32, 47);
	}

	char generateASpecialChar2() {
		return (char) generateASCIIChar(58, 64);
	}

	char generateASpecialChar() {
		double sentinel = Math.random() * 100;
		int val = (int) sentinel;
		if (val >= 55) {
			return generateASpecialChar1();
		} else {
			return generateASpecialChar2();
		}
	}

	char generateOnePassChar6() { // mode = 6
		double sentinel = Math.random() * 900;
		int val = (int) sentinel;
		if (val < 300) {
			return generateANumber();
		}
		if (val >= 300 && val < 820) {
			return generateLetter();
		} else {
			return generateASpecialChar();
		}

	}

	char generateOnePassChar5() { // mode = 5
		double sentinel = Math.random() * 900;
		int val = (int) sentinel;
		if (val < 700) {
			return generateLetter();
		} else {
			return generateASpecialChar();
		}

	}

	char generateOnePassChar4() { // mode = 4
		double sentinel = Math.random() * 900;
		int val = (int) sentinel;
		if (val < 700) {
			return generateANumber();
		} else {
			return generateASpecialChar();
		}

	}

	char generateOnePassChar3() { // mode = 3
		double sentinel = Math.random() * 900;
		int val = (int) sentinel;
		if (val < 500) {
			return generateLetter();
		} else {
			return generateANumber();
		}

	}

	char generateOnePassChar2() { // mode = 2
		return generateASpecialChar();
	}

	char generateOnePassChar1() { // mode = 1
		return generateLetter();
	}
	char generateOnePassChar0() { // mode = 0
		return generateANumber();
	}

	String generateAPassword(int length, int mode) { // 0 -> numbers only, 1->
														// letters only,
														// 2->special chars only
														// 3-> number+letter,
														// 4-> number+special
														// chars,
														// 5->letter+special
														// chars
														// 6-> all valid, others
														// not accepted !
		char[] passChars = new char[length];
		for (int i = 0; i < length; i++) {
			char c = 0;
			if(mode == 0) {
				c = generateOnePassChar0();
			}
			else if(mode == 1) {
				c = generateOnePassChar1();
			}
			else if(mode == 2) {
				c = generateOnePassChar2();
			}
			else if(mode == 3) {
				c = generateOnePassChar3();
			}
			else if(mode == 4) {
				c = generateOnePassChar4();
			}
			else if(mode == 5) {
				c = generateOnePassChar5();
			}
			else if(mode == 6) {
				c = generateOnePassChar6();
			}
			passChars[i] = c;
		}
		String pass = new String(passChars);
		return pass;
	}


}

package com.parse.starter;

public class GameProgram {

	int numGuess;
	int randNum;
	boolean isWon;
	boolean isLoser;

	public GameProgram() {
		numGuess = 0;
		randNum = 52;
	}

	public int guess(int num) {
		if (numGuess < 3) {
			if (num < 40 || num > 60) {
				numGuess++;
				return 1;
			} else if (num >= 40 && num <= 51 
					|| num > 52 && num <= 60) {
				numGuess++;
				return 2;
			} else if (num == 52) {
				numGuess++;
				isWon = true;
				return 3;
			}
		}
		return -1;
	}

	public boolean hasWon() {
		return isWon;
	}
	
	public boolean hasLost() {
		return isLoser;
	}

}

package core;

import java.util.HashSet;
import java.util.Set;

import core.InvalidLetterException;
import io.Input;
import io.Output;

public class Game{
	private static final int MAX_ATTEMPTS = 6;
	private int chancesLeft = MAX_ATTEMPTS;
	
	private final Set<Character> guessedLetters = new HashSet<>();
	
	public void start(){
		
		Dictionary dictionary = Dictionary.instance();
		Word word = dictionary.nextWord();
		
		Output.writeToConsole(word);
		
		while(!word.revealed() && chancesLeft > 0){
			char letter = readLetter();
			if(word.reveal(letter)){
				onHit();
			}else{
				onMiss();
			}
			
			guessedLetters.add(letter);
			Output.writeToConsole(word);
		}
		
		onEnd(word);
	}
	
	private char readLetter(){
		while(true){
			try{
				return validLetter(Input.readFromKeyBoard("Letter => "));
			}catch(InvalidLetterException e){
				Output.writeToConsole("ERROR : " + e.getMessage());
			}
		}
	}
	
	private char validLetter(String text) throws InvalidLetterException{
		String trimmedText = text.trim();
		
		if(trimmedText.length() == 0){
			throw new InvalidLetterException("Empty letter is not allowed");
		}
		
		if(trimmedText.length() > 1){
			throw new InvalidLetterException("Only one letter is allowed");
		}
		
		char letter = trimmedText.charAt(0);
		
		if(!Character.isLetter(letter)){
			throw new InvalidLetterException("Only letters are allowed");
		}
		
		char upperLetter = Character.toUpperCase(letter);
		
		if(guessedLetters.contains(upperLetter)){
			throw new InvalidLetterException("Letter already guessed");
		}
		
		return upperLetter;
	}
	
	private void onHit(){
		Output.writeToConsole("Hit a letter!");
	}
	
	private void onMiss(){
		chancesLeft--;
		Output.writeToConsole("Miss a letter!... " + chancesLeft + " chances(s) left");
	}
	
	private void onEnd(Word word){
		if(word.revealed()){
			Output.writeToConsole("Tou did it! :)");
			return;
		}	
		
		Output.writeToConsole("The word was: " + word.show());
		Output.writeToConsole("Game Over! You lost");
	}
}

package core;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Random;

import io.Input;

public class Dictionary{
	private static final String PATH_FILE = "/resources/dictionary.txt";
	private static Dictionary INSTANCE = new Dictionary();
	
	private final List<String> words;
	private final Random random = new Random();
	
	private Dictionary(){
		words = Input.readLinesFromFile(PATH_FILE);
	}
	
	public Word nextWord(){
		return new Word(
			words.get(
				random.nextInt(0, words.size())
			)
		);
	}
	
	public static Dictionary instance(){
		return INSTANCE;
	}
}
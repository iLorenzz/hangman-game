package io;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import static java.nio.charset.StandardCharsets.UTF_8;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public final class Input{
	private static final Scanner SCANNER = new Scanner(System.in);
	
	private Input(){}
	
	public static List<String> readLinesFromFile(String path){
		URL url = Input.class.getResource(path);
		
		if(url == null){
			throw new RuntimeException("File not found " + path);
		}
		
		try{
			return Files.readAllLines(Path.of(url.toURI()), UTF_8);
		}catch(URISyntaxException | IOException e){
			throw new RuntimeException("Error loading dictionary ", e);
		}
	}
	
	public static String readFromKeyBoard(String message){
		if(!Objects.isNull(message)){
			Output.writeToConsole(message + ": ", false);
		}
		
		return SCANNER.nextLine();
	}
}

package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class TestPlayer {
	public static void main(String[] args) throws IOException {
		
		

		
		File file = new File("resources/testPlayer.out");
		BufferedReader fileBufferedReader = new BufferedReader(new FileReader(file));
		BufferedReader stdBufferedReader = new BufferedReader(new InputStreamReader(System.in));
		String line, s;
		
		while ((line = fileBufferedReader.readLine()) != null) {
			if(line == "") continue;
		    while(!stdBufferedReader.readLine().trim().equals("ready"));
		    log("go");
		    System.out.println(line.trim());
		    System.out.flush();
		    //log("wrote");
		}
		fileBufferedReader.close();
		stdBufferedReader.close();

		
	}
	
	private static void log(String s) throws FileNotFoundException, UnsupportedEncodingException {
		PrintWriter writer = new PrintWriter("resources/testPlayer.log", "UTF-8");
		writer.write(s);
		writer.close();
	}
}

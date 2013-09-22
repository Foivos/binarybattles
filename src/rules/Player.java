package rules;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

public class Player {
	private static int INPUT_LENGTH = 1024;
	public int id;
	
	
	private InputStream in;
	private OutputStream out;
	PrintStream stream;
	private Process p;
	
	
	public Player(String command) {
		try {
			p = Runtime.getRuntime().exec(command);
			in = p.getInputStream();
			out = p.getOutputStream();
			stream = new PrintStream(out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void send(String string) throws IOException {
		System.out.println("Writing: " + string);
		stream.println(string.trim());
		stream.flush();
	}
	public String read() throws IOException {
		byte[] buffer = new byte[INPUT_LENGTH];
		while(in.read(buffer)<= 1);
		String s = (new String(buffer)).trim();
		System.out.println("Reading:" + s + "|");
		return s;
	}
}

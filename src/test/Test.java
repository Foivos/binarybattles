package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

public class Test {
	public static void main(String[] args) throws InterruptedException {
		InputStream in;
		OutputStream out;
		Process p;
		String command = "java -classpath ./bin test.TestPlayer";
		PrintStream stream;
		try {
			p = Runtime.getRuntime().exec(command);
			in = p.getInputStream();
			out = p.getOutputStream();
			stream = new PrintStream(out);
			
			
			String string = "Hello";
			System.out.println("Writing: " + string);
			stream.println(string);
			stream.flush();
			
			byte[] buffer = new byte[1024];
			in.read(buffer);
			String s = new String(buffer);
			System.out.println("Reading: " + s);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

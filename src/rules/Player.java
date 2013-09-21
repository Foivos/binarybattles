package rules;

import java.io.InputStream;
import java.io.OutputStream;

public class Player {
	public int id;
	private InputStream in;
	private OutputStream out;
	public void send(String string) {
		out.write(string.trray());
		
	}
}

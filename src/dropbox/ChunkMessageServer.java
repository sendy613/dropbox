package dropbox;

import java.io.OutputStream;
import java.io.PrintWriter;

public class ChunkMessageServer extends Messages {

	public ChunkMessageServer(){
		string = "CHUNKSERVER";
	}

	@Override
	public void perform(OutputStream outStream, String[] array) {
		writer = new PrintWriter(outStream);
		
	}
}

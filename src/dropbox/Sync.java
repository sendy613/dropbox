package dropbox;

import java.io.OutputStream;
import java.io.PrintWriter;

public class Sync extends Messages {

	public Sync(){
		string = "DOWNLOAD";
	}


	@Override
	public void perform(OutputStream outStream, String[] array) {
		writer = new PrintWriter(outStream);
		
	}

}

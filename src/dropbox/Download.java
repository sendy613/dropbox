package dropbox;

import java.io.OutputStream;
import java.io.PrintWriter;

public class Download extends Messages{


	public Download(){
		string = "DOWNLOAD";
	}


	@Override
	public void perform(OutputStream outStream) {
		writer = new PrintWriter(outStream);
		
	}

}

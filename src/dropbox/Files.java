package dropbox;

import java.io.OutputStream;
import java.io.PrintWriter;

public class Files extends Messages{

	public Files(){
		string = "FILES";
	}

	@Override
	public void perform(OutputStream outStream) {
		writer = new PrintWriter(outStream);
		
	}

}

package dropbox;

import java.io.OutputStream;
import java.io.PrintWriter;

public class FileMessage extends Messages {


	public FileMessage(FileCache fileCache){
		string = "FILE";
		this.fileCache = fileCache;
	}

	@Override
	public void perform(OutputStream outStream) {
		writer = new PrintWriter(outStream);
		
	}

	
}

package dropbox;

import java.io.OutputStream;
import java.io.PrintWriter;



public class ListMessage extends Messages {

	public ListMessage(FileCache fileCache){
		string = "LIST";
		this.fileCache = fileCache;
	}


	@Override
	public void perform(OutputStream outStream) {
		writer = new PrintWriter(outStream);
		writer.println("FILES " + fileCache.getNumberFiles());
		for(int i =0; i<fileCache.getNumberFiles(); i++){
			writer.println("FILE ");
		}
		
		
	}

}

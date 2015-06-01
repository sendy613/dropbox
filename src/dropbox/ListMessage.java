package dropbox;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class ListMessage extends Messages {

	public ListMessage(FileCache fileCache){
		string = "LIST";
		this.fileCache = fileCache;
	}


	@Override
	public void perform() {
		ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream());
		
		
	}

}

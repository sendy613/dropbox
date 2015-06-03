package dropbox;

import java.io.OutputStream;
import java.io.PrintWriter;

public class ChunkMessageClient extends Messages{


	public ChunkMessageClient(){
		string = "CHUNKCLIENT";
	}

	@Override
	public void perform(OutputStream outStream) {
		writer = new PrintWriter(outStream);
		
	}

}

package dropbox;

import java.io.File;
import java.util.List;

public class Client {
	private FileCache fileCache;

	public Client(){
		new File("user/").mkdir();
	}
	
	
	
	public void Sync(String filename, int lastModified, int FileSize){
		
	}
}

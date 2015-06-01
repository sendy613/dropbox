package dropbox;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Client {
	private FileCache fileCache;
	List<Messages> messages;

	public Client(){
		new File("user/").mkdir();
		messages = new ArrayList<Messages>();
		messages.add(new Sync());
		messages.add(new ChunkMessageClient());
		messages.add(new FileMessage(fileCache));
		messages.add(new Files());
		

	}
	
	
	
	public void Sync(String filename, int lastModified, int FileSize){
		
	}
}

package dropbox;

import java.io.File;
import java.util.List;

public class FileCache {
//this exist on the hardrive
	
	private static final String ROOT = "dropbox/";
	public FileCache(){
		new File(ROOT).mkdir();
	}
	public List<File> getFiles(String userName){
		return null;
		
	}
	
	public void addChunk(Chunk chunk){//array of bites
		
	}
	
	public void getChunk(File file,String filename, int start, int length){
		
	}
}

package dropbox;

import java.io.File;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

public class FileMessage extends Messages {

	private final int MAXCHUNKSIZE = 512;

	public FileMessage(FileCache fileCache){
		string = "FILE";
		this.fileCache = fileCache;
	}

	@Override
	public void perform(OutputStream outStream, String[] array) {
		writer = new PrintWriter(outStream);
		List<File> files = fileCache.getFiles();
		Boolean found =false;
		File fileFound = null;
		int indexFound = -1;
		for(int i =0; i<files.size(); i++){
			if(files.get(i).getName().equalsIgnoreCase(array[1])){
				found =true;
				fileFound=files.get(i);
				indexFound=i;
				break;
			}
		}
		if(found && fileFound.lastModified() != Long.parseLong(array[2])){
			//download and get rid
			files.remove(indexFound);
			sendDownloadMessage(fileFound);
		}
		else if(!found){
			//download
			sendDownloadMessage(fileFound);
		}
		
	}
public void sendDownloadMessage(File fileFound){
	long fileSize = fileFound.length();
	long sizeLeft = fileSize;
	long offset = 0;
	while(sizeLeft>0){
		if(sizeLeft >MAXCHUNKSIZE){
		writer.println("DOWNLOAD "+ fileFound.getName()+ " " + offset + " " + MAXCHUNKSIZE);
		sizeLeft -= MAXCHUNKSIZE;
		offset += MAXCHUNKSIZE;
		}
		else{
			writer.println("DOWNLOAD "+ fileFound.getName()+ " " + offset + " " + sizeLeft);
			break;
		}
	}
}
	
}

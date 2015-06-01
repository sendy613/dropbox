package dropbox;

public class FileMessage extends Messages {


	public FileMessage(FileCache fileCache){
		string = "FILE";
		this.fileCache = fileCache;
	}

	@Override
	public void perform() {
		// TODO Auto-generated method stub
		
	}

	
}

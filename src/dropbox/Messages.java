package dropbox;

public abstract class Messages {

	String string;
	FileCache fileCache;
	public boolean matches(String message){
		return string.equalsIgnoreCase(message);
	}
	public abstract void perform();
	
}

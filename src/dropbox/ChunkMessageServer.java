package dropbox;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;

import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;

public class ChunkMessageServer extends Messages {
	
	

	public ChunkMessageServer(FileCache fileCache) {
		string = "CHUNK";
		this.fileCache=fileCache;
	}

	@Override
	public void perform(OutputStream outStream, String[] array) {
		writer = new PrintWriter(outStream);
		// CHUNK [filename] [last modified] [filesize] [offset] [base64 encoded
		// bytes]

		// do i need to create a new file every time the chunk message is
		// received? will it go to the same file as before?
		try {
			File file = new File(array[1]);
			RandomAccessFile raf = new RandomAccessFile(file, "rw");
			Long position = Long.parseLong(array[4]);
			raf.seek(position);
			byte[] b = Base64.decode(array[5]);
			raf.write(b);
			//do we need to add this file to our fileCache?
			
			
			
			//do i need to change the date last modified in the server
			/*if (file.lastModified() != System.currentTimeMillis()) {
				file.setLastModified(System.currentTimeMillis());
			}*/
			
			//needs to send a sync message to all of the clients.
		} catch (NumberFormatException | IOException | Base64DecodingException e) {
			e.printStackTrace();
		}
	}
}

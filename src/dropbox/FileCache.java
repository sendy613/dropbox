package dropbox;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileCache {
	// this exist on the hardrive

	private static final String ROOT = "dropbox/";

	public FileCache() {
		new File(ROOT).mkdir();
	}

	public List<File> getFiles(String userName) {
		File folder = new File(ROOT + "/" + userName);

		File[] listOfFiles = folder.listFiles();

		/*
		 * for (File file : listOfFiles) { if (file.isFile()) {
		 * 
		 * } }
		 */
		ArrayList<File> array = new ArrayList<File>(Arrays.asList(listOfFiles));
		return array;

	}

	public void addChunk(Chunk chunk) {// array of bites
		File file = new File(chunk.getFilename());
		RandomAccessFile raf;
		try {
			raf = new RandomAccessFile(file, "rw");
			raf.seek(chunk.getStart());
			raf.write(chunk.getBytes());
			raf.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Chunk getChunk(File file, String filename, int start, int length) {
		byte[] b = null;
		try {
			RandomAccessFile raf = new RandomAccessFile(file, "rw");
			raf.read(b, start, length);
			raf.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return new Chunk(filename, b, start);
	}
}

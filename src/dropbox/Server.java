package dropbox;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class Server implements ReaderListener {
	private List<Messages> messages;
	private FileCache fileCache;
	private ServerSocket serverSocket;
	private LinkedList<Socket> sockets;

	public Server(FileCache fileCache, List<String> userNames) {
		this.fileCache = fileCache;
		this.messages = new ArrayList<Messages>();
		messages.add(new ListMessage(this.fileCache));
		messages.add(new ChunkMessageServer());
		messages.add(new Download());
		sockets = new LinkedList<Socket>();

		try {

			this.serverSocket = new ServerSocket(2009);
			Socket socket;
			while (true) {
				socket = serverSocket.accept();
				synchronized (sockets) {
					sockets.add(socket);
				}
				ReaderThread readerThread = new ReaderThread(socket, this);
				readerThread.start();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void onLineRead(String line, OutputStream out) {
		String[] array = line.split(" ");
		Messages message = null;
		for (Messages m : messages) {
			if (m.matches(array[0])) {
				message = m;
				break;
			}
		}

		if (message == null) {
			try {
				throw new InvalidDataException();
			} catch (InvalidDataException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		message.perform(out, array);

	}

	@Override
	public void onCloseSocket(Socket socket) {
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	

	public Chunk Download(String filename, int offset, int chunkSize) {
		List<File> list = List();
		File requested = null;
		for (File f : list) {
			if (f.getName().compareTo(filename) == 0) {
				requested = f;
				break;
			}
		}
		if (requested == null) {
			// do something
		}

		Chunk c = fileCache.getChunk(requested, filename, offset, chunkSize);
		return c;
	}

	public void Chunk(String filename, int lastModified, int fileSize,
			int offset, String base64) {
		File f = new File(filename);
		f.setLastModified((Integer) lastModified);
		Chunk chunk = fileCache.getChunk(f, filename, offset, fileSize);
		fileCache.addChunk(chunk);

	}

}

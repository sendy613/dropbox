package dropbox;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;


public class Server {
	private List<String> userNames;
	private FileCache fileCache;
	private ServerSocket serverSocket;
	private LinkedList<Socket> sockets;
	private LinkedBlockingQueue<String> queue;
	private WriterThread writerThread;

	public Server(FileCache fileCache, List<String> userNames) {
		this.fileCache = fileCache;
		this.userNames = userNames;
		sockets = new LinkedList<Socket>();
		queue = new LinkedBlockingQueue<String>();

		try {

			this.serverSocket = new ServerSocket(2009);
			Socket socket;
			writerThread = new WriterThread(queue, sockets);
			Thread threadWrite = new Thread(writerThread);
			threadWrite.start();
			while (true) {
				socket = serverSocket.accept();
				synchronized(sockets){
				sockets.add(socket);
				}
				ReaderRunnable readerThread = new ReaderRunnable(socket, this);
				Thread threadRead = new Thread(readerThread);
				threadRead.start();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void onLineRead(String line) {
		queue.add(line);

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

}
}

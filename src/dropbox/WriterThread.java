package dropbox;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingQueue;

public class WriterThread implements Runnable {
	private LinkedBlockingQueue<String> queue;
	private LinkedList<Socket> sockets;

	public WriterThread(LinkedBlockingQueue<String> queue, LinkedList<Socket> sockets) {
		this.queue = queue;
		this.sockets = sockets;
	}

	@Override
	public void run() {
		OutputStream out;
		PrintWriter writer;
		String s = null;
		while(true){			
			try {
				s = queue.take();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			synchronized(sockets){
				Iterator<Socket> iter = sockets.iterator();
				while(iter.hasNext()){
					Socket sck = iter.next();
					try{
						out = sck.getOutputStream();
						writer = new PrintWriter(out);
						writer.println(s);
						writer.flush();
						}
						catch (IOException e) {
							iter.remove();
							e.printStackTrace();
						} 
				}
			}
		} 

	}
	
}

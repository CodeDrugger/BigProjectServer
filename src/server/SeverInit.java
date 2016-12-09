package server;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import tasks.LineTask;

public class SeverInit {
	private ServerSocket serverSocket;
	private ExecutorService cachedThreadPool;
	public void startServer()
	{
		cachedThreadPool = Executors.newCachedThreadPool();
		try {
			setServerSocket(new ServerSocket(8888));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (true) {
			try {
				Socket socket = serverSocket.accept();
				System.out.println("Request Accepted!Address:" + socket.getInetAddress() + ":" + socket.getPort());
				DataInputStream input = new DataInputStream(socket.getInputStream());
				String msg = input.readUTF();
				System.out.println("Message:" + msg);
				input.close();
				LineTask lt = new LineTask(msg);
				cachedThreadPool.execute(lt);
				socket.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public ServerSocket getServerSocket() {
		return serverSocket;
	}

	public void setServerSocket(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}

	public ExecutorService getCachedThreadPool() {
		return cachedThreadPool;
	}

	public void setCachedThreadPool(ExecutorService cachedThreadPool) {
		this.cachedThreadPool = cachedThreadPool;
	}
	
}

package server;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import tasks.LineTask;

public class SeverInit {
	private ServerSocket serverSocket;
	private ExecutorService cachedThreadPool;
	
	public void startServer(Map<String, Socket> socketMap)
	{
		cachedThreadPool = Executors.newCachedThreadPool();
		
		try {
			setServerSocket(new ServerSocket(10240));
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
				String[] data = msg.split("\\^\\&\\^");//0:myid 1:hisid 2:msg  
				socketMap.put(data[0], socket);
				input.close();
				LineTask lt = new LineTask(data,socketMap);
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

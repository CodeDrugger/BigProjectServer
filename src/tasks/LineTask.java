package tasks;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Map;

public class LineTask implements Runnable {
	private String[] data;
	private Map<String, Socket> socketMap;

	public LineTask(String[] data, Map<String, Socket> socketMap) {
		super();
		this.data = data;
		this.socketMap = socketMap;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		if(data.length != 2) 
			System.out.println("GG");
		try {
			Socket socket = socketMap.get(data[1]);
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			out.writeUTF(data[2]);
			out.flush();
			out.close();
			socket.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

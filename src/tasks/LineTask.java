package tasks;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class LineTask implements Runnable {
	private String msg;
	
	public LineTask(String msg) {
		super();
		this.msg = msg;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		String[] data = msg.split("\\^\\&\\^");
		if(data.length != 2) 
			System.out.println("GG");
		try {
			Socket socket = new Socket(data[0], 10240);
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			out.writeUTF(data[1]);
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

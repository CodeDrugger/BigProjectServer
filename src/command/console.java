package command;

import java.net.Socket;
import java.util.Map;

import server.SeverInit;

public class console {
	private static Map<String, Socket> socketMap;
	public static void main(String args[]) { 
        SeverInit severInit = new SeverInit();
        severInit.startServer(socketMap);
    } 
}

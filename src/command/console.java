package command;

import server.SeverInit;

public class console {
	public static void main(String args[]) { 
        SeverInit severInit = new SeverInit();
        severInit.startServer();
    } 
}

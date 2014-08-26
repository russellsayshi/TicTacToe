import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.*;

import javax.swing.JFrame;

public class Server extends Thread {
	
	Server server;
	Host host;
	PrintWriter send;
	int port = 4231;
	
	Server(int p) {
		port = p;
	}
	
	public void setHost(Host host) {
		this.host = host;
	}
	
	static ServerSocket ss;
	static Socket s;
	
	public void run() {
		
		System.out.println("Attempting to start server...");
		boolean portInUse = true;
		try {
			ss = new ServerSocket(port);
			portInUse = false;
			InetAddress localHost = InetAddress.getLocalHost();
			host.closeHost();  //If there wasn't an error in the previous line, close host.
			host.dispose();
			System.out.println("Server initiated!");
			JFrame jf = TicTacToe.dialog("Hosting game.", "Waiting for requests.  Key: " + Key.generateKey(localHost.getHostAddress(), port));
			jf.addWindowListener(new WindowAdapter() {
			    @Override
			    public void windowClosing(WindowEvent we) {
			    	try {
						ServerSocket ss = Server.getServerSocket();
						ss.close();
				    	new Menu();
			    	} catch (IOException e) {
						e.printStackTrace();
						System.exit(1);
					}
			    }
			});
			
			System.out.println("Waiting for requests...");
			s = ss.accept();
			jf.dispose();
			send = new PrintWriter(s.getOutputStream(), true);
			BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
			
			String received;
			
			Game game = new Game(1, this);
			
			while((received = input.readLine()) != null) {
				if(received.equals("END")) {
					JFrame closed = TicTacToe.dialog("Sorry!", "The other player has closed their game.");
					JFrame gameFrame = game.getFrame();
					gameFrame.dispose();
					closed.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					closed.addWindowListener(new WindowAdapter() {
					    @Override
					    public void windowClosing(WindowEvent we) {
					    	new Menu();
					    }
					});
					break;
				}
				System.out.println("Received: " + received);
				game.placeOppositeLetter(Integer.parseInt(received), "x");
			}
			
			
			s.close();
			ss.close();
			
		} catch(SocketException e) {
			if(portInUse) {
				System.out.println("Error: " + e);
				e.printStackTrace();
				TicTacToe.dialog("Uh oh!", "The port you chose is already in use...");
			} else {
				System.out.println("Closed socket.");
			}
		} catch(Exception e) {
			if(!portInUse) {
				System.out.println("Error: " + e);
				e.printStackTrace();
				TicTacToe.dialog("Ba-humpbug!", "There was an error creating the server.");
			}
		}
		
	}
	
	public void sendData(String data) {
		send.println(data);
	}
	
	public static ServerSocket getServerSocket() {
		return ss;
	}
	
	public static Socket getSocket() {
		return s;
	}
	
}

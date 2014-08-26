import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JFrame;


public class Client extends Thread {
	
	int port;
	String ip;
	Join joinWindow;
	
	public Client(String ip, int port, Join joinWindow) {
		this.ip = ip;
		this.port = port;
		this.joinWindow = joinWindow;
	}
	
	PrintWriter send;
	
	public void run() {
		System.out.println("Attempting to connect to server...");
		try {
			Socket s = new Socket(ip, port);
			send = new PrintWriter(s.getOutputStream(), true);
			BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
			
			joinWindow.closeWindow();
			
			String received;
			
			Game game = new Game(0, this);
			
			while((received = input.readLine()) != null) {
				if(received.equals("END")) {
					JFrame closed = TicTacToe.dialog("Sorry!", "The other player has closed their game, bye!");
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
				game.placeOppositeLetter(Integer.parseInt(received), "o");
			}
			
			s.close();
			
		} catch(Exception e) {
			System.out.println("Failed connecting to server!");
			TicTacToe.dialog("Are you sure you typed that key correctly?", "There is no server I can find associated with that key.");
			e.printStackTrace();
		}
	}
	
	public void sendData(String data) {
		send.println(data);
	}

}
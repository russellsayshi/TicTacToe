import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Host extends JFrame implements ActionListener {
	
	/**
	 * 
	 */
	public static boolean hostClose = false;
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		new Host();
	}
	
	JTextField port;
	
	Host() {
		setSize(300,120);
		GridBagLayout gbl = new GridBagLayout();
		setLayout(gbl);
		setLocationRelativeTo(null);
		setTitle("Create a game");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent we) {
		    	if(Host.hostClose == false) {
		    		new Menu();
		    	}
		    }
		});
		GridBagConstraints gbc = new GridBagConstraints();
		
		JPanel portPanel = new JPanel();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbl.setConstraints(portPanel, gbc);
		
		JLabel portLabel = new JLabel("Please enter the desired port:  ");
		portPanel.add(portLabel);
		
		port = new JTextField(4);
		port.setText("4231");
		port.addActionListener(this);
		portPanel.add(port);
		
		add(portPanel);
		
		Button createGame = new Button("Create Game");
		createGame.addActionListener(this);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbl.setConstraints(createGame, gbc);
		add(createGame);
		
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		try {
			Server server = new Server(Integer.parseInt(port.getText()));
			server.setHost(this);
			server.start();
		} catch(Exception e) {
			TicTacToe.dialog("There was an error creating the server", "Hmm... It appears there was an error creating the server.");
			e.printStackTrace();
		}
	}

	public void closeHost() {
		hostClose = true;
		setDefaultCloseOperation(1);
        WindowEvent wev = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
	}
	
}

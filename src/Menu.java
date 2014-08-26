import java.awt.Button;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.*;

public class Menu extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	public static void main(String args[]) {
		new Menu();
	}

	public Menu() {
		setSize(300, 150);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("Tic Tac Toe");
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		JPanel container = new JPanel(gbl);
		gbc.gridx=0;
		gbc.gridy=0;
		
		JPanel gameOptions = new JPanel();
		GridBagLayout gbl1 = new GridBagLayout();
		gameOptions.setLayout(gbl1);
		gbc.gridx=0;
		gbc.gridy=1;
		gbl.setConstraints(gameOptions, gbc);
		
		JPanel logoPanel = new JPanel();
		Logo logo = new Logo();
		logo.setSize(70,50);
		logoPanel.add(logo);
		gameOptions.add(logoPanel);
		
		JPanel gameLaunchOptions = new JPanel();
		gameLaunchOptions.setLayout(new BoxLayout(gameLaunchOptions, BoxLayout.Y_AXIS));
		Button joinGame = new Button("Join Game");
		joinGame.addActionListener(this);
		Button createGame = new Button("Create Game");
		createGame.addActionListener(this);
		Button singlePlayer = new Button("Single Player");
		singlePlayer.setEnabled(false);
		singlePlayer.addActionListener(this);
		gameLaunchOptions.add(joinGame);
		gameLaunchOptions.add(createGame);
		gameLaunchOptions.add(singlePlayer);
		
		gameOptions.add(gameLaunchOptions);
		
		container.add(gameOptions);
		
		
		
		add(container);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		String buttonText = ae.getActionCommand();
		if(buttonText == "Join Game") {
			closeMenu();
			new Join();
			dispose();
		} else if(buttonText == "Create Game") {
			closeMenu();
			new Host();
			dispose();
		}
	}
	
	public void closeMenu() {
		setDefaultCloseOperation(1);
        WindowEvent wev = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
	}

}

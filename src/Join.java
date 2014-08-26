import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class Join extends JFrame implements ActionListener {
	
	public static boolean closeJoin = false;
	private static final long serialVersionUID = -6720980345445182955L;

	public static void main(String[] args) {
		new Join();
	}
	
	JTextField key;
	
	public Join() {
		setSize(380,90);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent we) {
		    	if(Join.closeJoin == false) {
		    		new Menu();
		    	}
		    }
		});
		setTitle("Join Game");
		setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		JPanel p = new JPanel();
		p.setLayout(new FlowLayout());
		panel.add(p, BorderLayout.CENTER);
		
		JLabel keyLabel = new JLabel("Please enter the key of the game you'd like to join: ", SwingConstants.CENTER);
		p.add(keyLabel, BorderLayout.CENTER);
		
		key = new JTextField(10);
		key.addActionListener(this);
		p.add(key);
		
		JButton join = new JButton("Join");
		join.addActionListener(this);
		p.add(join);
		
		add(panel);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		try {
			System.out.println("--INFO FROM JOIN WINDOW--");
			Object[] keyResults = Key.parseKey(key.getText());
			System.out.println("IP: " + keyResults[0]);
			System.out.println("Port: " + keyResults[1]);
			System.out.println("--END INFO FROM JOIN WINDOW--");
			new Client(String.valueOf(keyResults[0]), Integer.parseInt(String.valueOf((keyResults[1]))), this).start();
		} catch (Exception e) {
			TicTacToe.dialog("Well... there seems to be a problem.", "Hmm... That key doesn't parse.  Make sure you copied it exactly.");
			e.printStackTrace();
		}
	}
	
	public void closeWindow() {
		closeJoin = true;
		dispose();
	}

}

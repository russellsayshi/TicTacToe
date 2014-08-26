import javax.swing.*;

public class TicTacToe {
	
	public static void main(String[] args) {
		try {
			new Menu();
		} catch(Exception e) {
			error(e);
		}
	}
	
	public static void error(Exception e) {
		System.out.println("Error!  Tic Tac Toe is shutting down.");
		dialog("There was an error!", "There was an error running this code.\n  Error: \"" + e + "\"");
		e.printStackTrace();
	}
	
	public static JFrame dialog(String title, String display) {
		JFrame f = new JFrame(title);
		JLabel msg = new JLabel(display);
		msg.setHorizontalAlignment(SwingConstants.CENTER);
		f.add(msg);
		f.pack();
		f.setSize(600,150);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setVisible(true);
		return f;
	}

}

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.JFrame;

public class Game extends Canvas implements MouseMotionListener, MouseListener {
	
	int hostedGame = 1;
	Client client = null;
	Server server = null;
	JFrame f;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Game c;
	
	public Game(int hG, Client client) {
		this.client = client;
		setup(hG);
	}
	
	public Game(int hG, Server server) {
		this.server = server;
		setup(hG);
	}
	
	private void setup(int hG) {
		c = this;
		addMouseMotionListener(this);
		addMouseListener(this);
		setBackground(Color.WHITE);
		hostedGame = hG;
		System.out.println(hG + " " + hostedGame);
		f = new JFrame();
		f.setSize(500,500);
		f.add(c);
		f.addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent we) {
		    	if(client != null) {
		    		client.sendData("END");
		    	} else if(server != null) {
		    		server.sendData("END");
		    	}
		    	System.exit(0);
		    }
		});
		f.setLocationRelativeTo(null);
		f.setTitle("Tic Tac Toe - Game");
		f.setVisible(true);
		
		if(hG == 0) {
			playerChange(1);
			originalMode = "o";
		} else {
			playerChange(0);
			originalMode = "x";
		}
	}
	
	public void paint(Graphics g) {
		g.fillRect(180,70,5,300);
		g.fillRect(315,70,5,300);
		g.fillRect(80,160,340,5);
		g.fillRect(80,280,340,5);
		if(player==0) {
			g.drawString("It's your turn!", 10, 20);
		} else {
			g.drawString("It's your opponent's turn!", 10, 20);
		}
		if(placedSquares[0]) {
			drawLetter(95, 135, originalMode);
		} if(placedSquares[1]) {
			drawLetter(220, 135, originalMode);
		} if(placedSquares[2]) {
			drawLetter(335, 135, originalMode);
		} if(placedSquares[3]) {
			drawLetter(95, 250, originalMode);
		} if(placedSquares[4]) {
			drawLetter(220, 250, originalMode);
		} if(placedSquares[5]) {
			drawLetter(335, 250, originalMode);
		} if(placedSquares[6]) {
			drawLetter(95, 360, originalMode);
		} if(placedSquares[7]) {
			drawLetter(220, 360, originalMode);
		} if(placedSquares[8]) {
			drawLetter(335, 360, originalMode);
		}

		if(originalMode == "x") {
			otherMode = "o";
		} else {
			otherMode = "x";
		}

		if(otherPlacedSquares[0]) {
			drawLetter(95, 135, otherMode);
		} if(otherPlacedSquares[1]) {
			drawLetter(220, 135, otherMode);
		} if(otherPlacedSquares[2]) {
			drawLetter(335, 135, otherMode);
		} if(otherPlacedSquares[3]) {
			drawLetter(95, 250, otherMode);
		} if(otherPlacedSquares[4]) {
			drawLetter(220, 250, otherMode);
		} if(otherPlacedSquares[5]) {
			drawLetter(335, 250, otherMode);
		} if(otherPlacedSquares[6]) {
			drawLetter(95, 360, otherMode);
		} if(otherPlacedSquares[7]) {
			drawLetter(220, 360, otherMode);
		} if(otherPlacedSquares[8]) {
			drawLetter(335, 360, otherMode);
		}
	}
	
	int player = 0; //0 == you, 1 == someone else
	
	public void playerChange(int p) {
		Graphics g = getGraphics();
		player = p;
		if(p == 0) {
			g.drawString("It's your turn!", 10, 20);
			repaint();
			if(hostedGame == 1) {
				mode = "x";
			} else {
				mode = "o";
			}
		} else {
			g.drawString("It's your opponent's turn!", 10, 20);
			repaint();
			mode = "";
		}
	}
	
	private void drawLetter(int x, int y, String letter) {
		Graphics g = getGraphics();
		Color startColor = g.getColor();
		if(letter == "x") {
			g.setColor(Color.RED);
		} else if(letter == "o") {
			g.setColor(Color.BLUE);
		}
		
		g.setFont(new Font("SansSerif", Font.BOLD, 100));
		g.drawString(letter, x, y);
		
		g.setColor(startColor);
	}
	
	public void mouseDragged(MouseEvent e) {
	}
	
	String originalMode = "x";
	int square;
	int oldsquare;
	public String mode = "x";
	String otherMode;
	
	public void mouseMoved(MouseEvent e) {
		oldsquare = square;
		if(e.getX() > 80 && e.getY() > 70 && e.getX() < 430 && e.getY() < 370) {
			if(e.getX() < 180 && e.getY() < 160) {
				square = 1;
				if((!placedSquares[square - 1]) && (!otherPlacedSquares[square - 1]))
					drawLetter(95, 135, mode);
			} else if(e.getX() < 315 && e.getY() < 160) {
				square = 2;
				if((!placedSquares[square - 1]) && (!otherPlacedSquares[square - 1]))
					drawLetter(220, 135, mode);
			} else if(e.getX() > 315 && e.getY() < 160) {
				square = 3;
				if((!placedSquares[square - 1]) && (!otherPlacedSquares[square - 1]))
					drawLetter(335, 135, mode);
			} else if(e.getX() < 180 && e.getY() < 280) {
				square = 4;
				if((!placedSquares[square - 1]) && (!otherPlacedSquares[square - 1]))
					drawLetter(95, 250, mode);
			} else if(e.getX() < 315 && e.getY() < 280) {
				square = 5;
				if((!placedSquares[square - 1]) && (!otherPlacedSquares[square - 1]))
					drawLetter(220, 250, mode);
			} else if(e.getX() > 315 && e.getY() < 280) {
				square = 6;
				if((!placedSquares[square - 1]) && (!otherPlacedSquares[square - 1]))
					drawLetter(335, 250, mode);
			} else if(e.getX() < 180 && e.getY() > 280) {
				square = 7;
				if((!placedSquares[square - 1]) && (!otherPlacedSquares[square - 1]))
					drawLetter(95, 360, mode);
			} else if(e.getX() < 315 && e.getY() > 280) {
				square = 8;
				if((!placedSquares[square - 1]) && (!otherPlacedSquares[square - 1]))
					drawLetter(220, 360, mode);
			} else if(e.getX() > 315 && e.getY() > 280) {
				square = 9;
				if((!placedSquares[square - 1]) && (!otherPlacedSquares[square - 1]))
					drawLetter(335, 360, mode);
			}
		} else {
			square = 0;
		}
		if(oldsquare != square) {
			repaint();
		}
	}
	
	boolean placedSquares[] = new boolean[9];
	boolean otherPlacedSquares[] = new boolean[9];

	@Override
	public void mouseClicked(MouseEvent e) {
		if(square != 0 && player == 0 && (!placedSquares[square - 1]) && (!otherPlacedSquares[square - 1])) {
			System.out.println("\t-----" + square);
			System.out.println("\t" + placedSquares[square - 1]);
			if(square == 1) {
				placedSquares[0] = true;
				drawLetter(95, 135, mode);
				if(client != null) {
					client.sendData("1");
				} else if(server != null) {
					server.sendData("1");
				}
			} else if(square == 2) {
				placedSquares[1] = true;
				drawLetter(220, 135, mode);
				if(client != null) {
					client.sendData("2");
				} else if(server != null) {
					server.sendData("2");
				}
			} else if(square == 3) {
				placedSquares[2] = true;
				drawLetter(335, 135, mode);
				if(client != null) {
					client.sendData("3");
				} else if(server != null) {
					server.sendData("3");
				}
			} else if(square == 4) {
				placedSquares[3] = true;
				drawLetter(95, 250, mode);
				if(client != null) {
					client.sendData("4");
				} else if(server != null) {
					server.sendData("4");
				}
			} else if(square == 5) {
				placedSquares[4] = true;
				drawLetter(220, 250, mode);
				if(client != null) {
					client.sendData("5");
				} else if(server != null) {
					server.sendData("5");
				}
			} else if(square == 6) {
				placedSquares[5] = true;
				drawLetter(335, 250, mode);
				if(client != null) {
					client.sendData("6");
				} else if(server != null) {
					server.sendData("6");
				}
			} else if(square == 7) {
				placedSquares[6] = true;
				drawLetter(95, 360, mode);
				if(client != null) {
					client.sendData("7");
				} else if(server != null) {
					server.sendData("7");
				}
			} else if(square == 8) {
				placedSquares[7] = true;
				drawLetter(220, 360, mode);
				if(client != null) {
					client.sendData("8");
				} else if(server != null) {
					server.sendData("8");
				}
			} else if(square == 9) {
				placedSquares[8] = true;
				drawLetter(335, 360, mode);
				if(client != null) {
					client.sendData("9");
				} else if(server != null) {
					server.sendData("9");
				}
			}
			checkWin();
			playerChange(1);
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}
	
	public void placeOppositeLetter(int square, String letter) {
		if(originalMode == "x") {
			otherMode = "o";
		} else {
			otherMode = "x";
		}
		if(square == 1) {
			otherPlacedSquares[0] = true;
			drawLetter(95, 135, otherMode);
		} else if(square == 2) {
			otherPlacedSquares[1] = true;
			drawLetter(220, 135, otherMode);
		} else if(square == 3) {
			otherPlacedSquares[2] = true;
			drawLetter(335, 135, otherMode);
		} else if(square == 4) {
			otherPlacedSquares[3] = true;
			drawLetter(95, 250, otherMode);
		} else if(square == 5) {
			otherPlacedSquares[4] = true;
			drawLetter(220, 250, otherMode);
		} else if(square == 6) {
			otherPlacedSquares[5] = true;
			drawLetter(335, 250, otherMode);
		} else if(square == 7) {
			otherPlacedSquares[6] = true;
			drawLetter(95, 360, otherMode);
		} else if(square == 8) {
			otherPlacedSquares[7] = true;
			drawLetter(220, 360, otherMode);
		} else if(square == 9) {
			otherPlacedSquares[8] = true;
			drawLetter(335, 360, otherMode);
		}
		checkWin();
		playerChange(0);
	}
	
	static JFrame winframe;
	static JFrame loseframe;
	static JFrame tieFrame;
	
	static public JFrame getWinframe() {
		return winframe;
	}
	
	static public JFrame getLoseframe() {
		return loseframe;
	}
	
	static public JFrame getTieframe() {
		return tieFrame;
	}
	
	public void checkWin() {
		if((placedSquares[0] && placedSquares[1] && placedSquares[2]) || (placedSquares[3] && placedSquares[4] && placedSquares[5]) || (placedSquares[6] && placedSquares[7] && placedSquares[8]) || (placedSquares[0] && placedSquares[3] && placedSquares[6]) || (placedSquares[1] && placedSquares[4] && placedSquares[7]) || (placedSquares[2] && placedSquares[5] && placedSquares[8]) || (placedSquares[0] && placedSquares[4] && placedSquares[8]) || (placedSquares[2] && placedSquares[4] && placedSquares[6])) {
			winframe = TicTacToe.dialog("Congradulations!", "You've just won the game!");
			winframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			winframe.addWindowListener(new WindowAdapter() {
			    @Override
			    public void windowClosing(WindowEvent we) {
			    	try {
			    		Server.getSocket().close();
						Server.getServerSocket().close();
					} catch (IOException e) {
						e.printStackTrace();
					} finally {
						Game.getWinframe().dispose();
						new Menu();
					}
			    }
			});
			f.setVisible(false);
		} else if((otherPlacedSquares[0] && otherPlacedSquares[1] && otherPlacedSquares[2]) || (otherPlacedSquares[3] && otherPlacedSquares[4] && otherPlacedSquares[5]) || (otherPlacedSquares[6] && otherPlacedSquares[7] && otherPlacedSquares[8]) || (otherPlacedSquares[0] && otherPlacedSquares[3] && otherPlacedSquares[6]) || (otherPlacedSquares[1] && otherPlacedSquares[4] && otherPlacedSquares[7]) || (otherPlacedSquares[2] && otherPlacedSquares[5] && otherPlacedSquares[8]) || (otherPlacedSquares[0] && otherPlacedSquares[4] && otherPlacedSquares[8]) || (otherPlacedSquares[2] && otherPlacedSquares[4] && otherPlacedSquares[6])) {
			loseframe = TicTacToe.dialog("Bummer...", "You lose.");
			loseframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			loseframe.addWindowListener(new WindowAdapter() {
			    @Override
			    public void windowClosing(WindowEvent we) {
			    	try {
			    		Server.getSocket().close();
						Server.getServerSocket().close();
					} catch (IOException e) {
						e.printStackTrace();
					} finally {
						Game.getLoseframe().dispose();
						new Menu();
					}
			    }
			});
			f.setVisible(false);
		} else {
			for(int i = 0; i < 9; i++) {
				if(otherPlacedSquares[i] == false && placedSquares[i] == false) break;
				if(i == 8) {
					tieFrame = TicTacToe.dialog("Tie", "You've just tied!");
					tieFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					tieFrame.addWindowListener(new WindowAdapter() {
					    @Override
					    public void windowClosing(WindowEvent we) {
					    	try {
					    		Server.getSocket().close();
								Server.getServerSocket().close();
							} catch (IOException e) {
								e.printStackTrace();
							} finally {
								Game.getTieframe().dispose();
								new Menu();
							}
					    }
					});
					f.setVisible(false);
				}
			}
		}
	}
	
	public JFrame getFrame() {
		return f;
	}

}

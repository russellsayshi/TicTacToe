import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;



public class Logo extends Canvas {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9195703391956772662L;

	public void paint(Graphics OriginalGraphics) {
        Graphics2D g = (Graphics2D) OriginalGraphics;
        g.setStroke(new BasicStroke(3));
		g.drawLine(7,17,52,17);
		g.drawLine(7,37,52,37);
		g.drawLine(20,9,20,47);
		g.drawLine(40,9,40,47);
		g.setColor(Color.RED);
		g.drawLine(5,22,15,32);
		g.drawLine(5,32,15,22);
		g.setColor(Color.BLUE);
		g.drawOval(25, 3, 10, 10);
	}
}

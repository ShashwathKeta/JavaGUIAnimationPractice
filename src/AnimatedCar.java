import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Timer;
import java.awt.Canvas;

class AnimatedCar extends Canvas
{
	private int x;
	private int y;
	private Timer timer;
	private final static int SLEEP = 50;  //bigger # = slower animation	
	
	public AnimatedCar(int width, int heigth)
	{
		setSize(width, heigth);
		setVisible(true);
		setBackground(Color.WHITE);
		
		x = 0;
		y = 50;

		ActionListener paintCaller = new ActionListener(){
			public void actionPerformed(ActionEvent event)
			{
				repaint();  //each time timer fires it will call paint
			}
		};
		timer = new Timer(SLEEP, paintCaller);
		timer.start();
	}	
	
	public void update(Graphics window)
	{
	   paint(window);	
	}
	
	
	// this method will get called over and over again (every 50 ms)
	public void paint( Graphics window )
	{
		window.setColor(Color.WHITE);
		window.fillRect(0,0,getWidth(),getHeight());
		
		window.setColor(Color.BLACK);
		window.drawString("LOOK AT DEM CARS", 25, 50 );

		window.setColor(Color.GREEN);
		window.fillRect(x,y+20,80,40);
		window.fillRect(x, y+5, 60, 55);

		window.setColor(Color.black);
		window.fillArc(x+5, y+60, 20, 20, 0, 360);
		window.fillArc(x+57, y+60, 20, 20, 0, 360);
		// ***** draw all of the parts of the car using your x and y variables
		
		
		// draw a wheel
		
		
		// draw another wheel
		
		
		
		// increment x by 50
		x = x + 15;

		
		// if x has reached the far right side of the screen (use getWidth())
		// set it back to zero
		
		if (x > getWidth()) {
			x = -100;
		}
	}	
}
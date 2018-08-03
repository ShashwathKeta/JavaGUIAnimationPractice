
import javax.swing.JFrame;


// Uses GraphicsRunner.java and AnimatedCar.java

import static java.lang.System.*;

@SuppressWarnings("serial")
public class GraphicsRunner extends JFrame
{
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;

	public GraphicsRunner()
	{
		super("Graphics Runner");
		setSize(WIDTH,HEIGHT);

		getContentPane().add(new AnimatedCar(WIDTH, HEIGHT));

		setVisible(true);
	}

	public static void main( String args[] )
	{
		@SuppressWarnings("unused")
		GraphicsRunner run = new GraphicsRunner();

		out.println();
		out.println();

		// fill in your name
		out.println("My name is ????????");
		out.println();


	}
}
// PictureViewer

// You will need to find all of the 
// FINISH ME  comments and finish the code
// You will also need at least 4 pictures
// make sure that the pics are either 
// jpg, gif, or png file formats
// gif files can not be animated
// files should not be too large
// Also, make sure your filenames are spelled exactly as they
//    they are here. (CASE MATTERS)
// Your code should work for any number of pictures, so don't say
// something like:   pictureIndex = 3;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.awt.image.BufferedImage;

@SuppressWarnings("serial")
public class PictureViewer extends JFrame implements  ActionListener
{ 

  // ***** declaration of JFrame variables *****

  
  // define a mainPanel for components
  JPanel mainPanel;  

  // ***** declaration of menu variables *****

  // define a menu bar variable to hold JMenus
  JMenuBar  menuBar;
  
  // define some JMenus and their JMenuItems
  // define a JMenu called fileMenu and add menuItems
  JMenu     fileMenu;
  JMenuItem exitMenuItem;
   
  // define JPanels for a BorderLayout
  JPanel     northPanel;
  JPanel     southPanel;
  JPanel     westPanel;
  JPanel     eastPanel;
  DrawPanel  centerPanel;

  // for buffering
  BufferedImage back;
  


  // JButtons to move to first, next, previous, and last pictures
  // we will add these JButtons to the southPanel
  // we will need to create the objects and call
  // each objects addActionListener(this) method
  JButton    firstButton;
  JButton    nextButton;
  JButton    previousButton;
  JButton    lastButton;
  

  // define instance variables that you need  
  ArrayList<String> pictureFilenames;
  
  int pictureIndex; 
  	
  private Image currentImage;
  	
  
  // ***** public void initialize *****

  public void initialize( )
  { 
  	// FINISH ME
	// create the pictureFilenames object (new it!)
	// notice that it is of what type?  (Look above in your instance variables)
	pictureFilenames = new ArrayList <String>();
	
  	// FINISH ME
	// now add the filenames to the ArrayList
	// by calling the add method of pictureFilenames 
	pictureFilenames.add("flower1.jpg");
	pictureFilenames.add("flower2.jpg");
	pictureFilenames.add("flower3.jpg");
	pictureFilenames.add("flower4.jpg");
	
  	// FINISH ME
	// we will start by showing the first picture in our list
	// so we will set pictureIndex to zero
	pictureIndex = 0;

    
    // create a mainPanel for components
    mainPanel = new JPanel();    

    // ***** assignments for menu variables *****

    menuBar            = new JMenuBar();
    
    fileMenu           = new JMenu("File");
    exitMenuItem       = new JMenuItem("Exit");
    
     // add mnemonics to the menu system
    fileMenu.setMnemonic('F');
    exitMenuItem.setMnemonic('x');
    
    // add the menuItem addActionListener(this) calls
    // fileMenu
    exitMenuItem.addActionListener(this);
        
    // add menuItems to the fileMenu
    fileMenu.add(exitMenuItem);
    
    
    // add menus to the menuBar
    menuBar.add(fileMenu);
    
    // attach the JMenuBar to the Window
    // by calling the setJMenuBar method and
    // passing it menuBar
    setJMenuBar(menuBar);
    
    
    // ***** create JPanels for a BorderLayout *****
    northPanel   = new JPanel();
    southPanel   = new JPanel();
    westPanel    = new JPanel();
    eastPanel    = new JPanel();

	centerPanel  = new DrawPanel();


    mainPanel.setLayout(new BorderLayout());


	// FINISH ME
	// create each JButton object (new it!)
	// and call the JButton's addActionListener(this) method
	// the first JButton has been done for you
	firstButton = new JButton("First");
	firstButton.addActionListener(this);
	
	previousButton = new JButton("Previous");
	previousButton.addActionListener(this);

	nextButton = new JButton("Next");
	nextButton.addActionListener(this);

	lastButton = new JButton("Last");
	lastButton.addActionListener(this);



	// FINISH ME
	// now add the JButton objects to the southPanel
	// the Layout Manager is by default a FlowLayout
	// from left to right as added
	// the firstButton has been added for you
	southPanel.add(firstButton);
	southPanel.add(previousButton);
	southPanel.add(nextButton);
	southPanel.add(lastButton);

	
	
    northPanel.setBackground(new Color(115,205,255));
    southPanel.setBackground(new Color(115,205,255));
    westPanel.setBackground(new Color(115,205,255));
    eastPanel.setBackground(new Color(115,205,255));
    // ***** You need to add buttons, etc. to the 5 panels *****


    // ***** add the panels to the mainPanel 5 areas *****
    mainPanel.add(northPanel,BorderLayout.NORTH);
    mainPanel.add(southPanel,BorderLayout.SOUTH);
    mainPanel.add(eastPanel,BorderLayout.EAST);
    mainPanel.add(westPanel,BorderLayout.WEST);
    mainPanel.add(centerPanel,BorderLayout.CENTER);

    // make the mainPanel be the main content area and show it
    setContentPane(mainPanel);
 
    
    // FINISH ME
    // call the showImage() method to show the image
    showImage();
    
    
    setVisible(true);  // always show the screen last
  }   // end of public void initialize 



  // ***** default constructor *****

  public PictureViewer( )
  { 
    // initialize variables

    setSize(800,600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    // FINISH ME
    // add your name to the title
    setTitle("Picture Viewer Shashwath");


    initialize( );

  }

  // ***** ActionListener interface methods *****

  // start of actionPerformed (ActionListener interface)
  public void actionPerformed(ActionEvent e)
  {
    Object source = e.getSource();
    if (source == exitMenuItem)
    {
      System.exit(0);
    }  // end of if
    
    // FINISH ME 
    // HANDLE THE JButton objects to move
    // to the first picture, next picture, etc.
    // the first button has been handled for you
    
    else if (source == firstButton)
    {
    	// source references the JButton that got clicked
    	// set the index of the picture that you want to show
    	pictureIndex = 0;
      	showImage();
    }  // end of if
    else if (source == previousButton && pictureIndex >= 1) {
    	pictureIndex = pictureIndex - 1;
    	showImage();
    }
    else if (source == nextButton && pictureIndex < 3) {
    	pictureIndex = pictureIndex + 1;
    	showImage();
    }
    else if (source == lastButton) {
    	pictureIndex = 3;
    	showImage();
    }
    // FINISH ME
    // HANDLE THE other JButtons
    
    
    
    
  }  // end of actionPerformed





  // *************************************************
  // *************************************************
  // ***** main method *******************************
  // *************************************************
  // *************************************************
  public static void main(String[] arguments)
  {
    @SuppressWarnings("unused")
	PictureViewer pictureViewer = new PictureViewer( );
  }


  public void showImage()
  {
  	// FINISH ME
  	// load in the image that you want to display
  	// the getImage method below needs the filename of the image
  	// remember that the ArrayList reference is 
  	// called pictureFilenames
  	// and the index is called pictureIndex
	currentImage = Toolkit.getDefaultToolkit().getImage(pictureFilenames.get(pictureIndex));
	
	
	// FINISH ME
	// update the centerPanel
	// repaint will call the update method and pass it the Graphics library reference
	// the update() method will then call the paintComponent method
	// call the centerPanel's repaint() method
	centerPanel.repaint(); 	
  }







// *************************************************************************************
// *************************************************************************************
// *************************************************************************************
// *************************************************************************************
// this is the centerPanel for the picture  (this is an inner class)
// *************************************************************************************
// *************************************************************************************
// *************************************************************************************
class DrawPanel extends JPanel 
{
	
	public DrawPanel()
	{
		super();
	}

	// DO NOT CALL THIS METHOD, it will get
	// called by the repaint() method
    public void update(Graphics window)
    {
	   paintComponent(window);
    }
   

	// DO NOT CALL THIS METHOD, it will get
	// called by the update() method above
	public void paintComponent(Graphics g)
	{
		super.paintComponent((Graphics2D)g);
		Graphics2D g2 = (Graphics2D) g;

		//take a snap shop of the current screen and same it as an image
		//that is the exact same width and height as the current screen
		// if(back==null)
		back = (BufferedImage)(createImage(getWidth(),getHeight()));

		//create a graphics reference to the back ground image
		//we will draw all changes on the background image
		Graphics gMemory = back.createGraphics();

		// clear the screen (not really needed here)
		//gMemory.setColor(Color.BLACK);
		//gMemory.fillRect(0,0,getWidth(),getHeight());
		
		// we will draw the currentImage and take up 
		//    the entire centerPanel
		gMemory.drawImage(currentImage,
			0, 
			0, 
			getWidth(),
			getHeight(),
			centerPanel);
		
		
   		g2.drawImage(back, null, 0, 0);	
	}



}  // end of class DrawPanel


  
} // end of class PictureViewer


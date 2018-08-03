import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.image.BufferedImage;
import java.io.File;


public class Hangman extends JFrame implements  ActionListener
{ 

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

private final int MAX_BAD_GUESSES = 10;

  // for buffering
  private BufferedImage back;
  

  // ***** declaration of JFrame variables *****

  
  // define a mainPanel for components
  JPanel mainPanel;  

  // ***** declaration of menu variables *****

  // define a menu bar variable to hold JMenus
  JMenuBar  menuBar;
  
  // define some JMenus and their JMenuItems
  // define a JMenu called fileMenu and add menuItems
  JMenu     fileMenu;
  JMenuItem newGameMenuItem;
  JMenuItem exitMenuItem;
    
     
  // define JPanels for a BorderLayout
  JPanel     northPanel;
  JPanel     southPanel;
  JPanel     westPanel;
  JPanel     eastPanel;
  DrawPanel  centerPanel;

  ArrayList <JButton> letterButtons;   // a list of buttons A-Z
  
  ArrayList <Character> lettersUsed;   // the letters that have been used
  
  ArrayList <String> theWords;  // the list of words to select from
  
  String  theWord;
  String  guessedWord;
  int     numBadGuesses;
  boolean gameOver;
  
  JLabel  northTitle;  // a label can be added to a panel and displays text (or an image or both)
  
  // ***** public void initialize *****

  public void initialize( )
  { 
  	theWord = "";
  	guessedWord = "";
  	gameOver = false;
  	numBadGuesses = 0;
  	
  	northTitle = new JLabel("H A N G M A N");  // create the JLabel and have it say "H A N G M A N"
  	// we will add the JLabel to the northPanel later

    // ** new it *******************************************************************    
	letterButtons = new ArrayList<JButton>();  // create a new ArrayList of JButton objects 
	
	// we will create the list of buttons here and add them to the panel later
	char letter = 'A';
    for (int i=0; i < 26; i++)
    {
    	// create a new JButton with a letter on it (use the variable letter)
    	JButton button = new JButton(""+letter);
    	
    	
    	// call the button's addActionListener method so that we can received clicks
    	button.addActionListener(this);
    	
    	// add the button to the ArrayList letterButtons
    	letterButtons.add(button);
    	
    	// increase the letter by one (++)
    	letter++;     	
    }
    
    // Create a list of words to use
    theWords = new ArrayList<String>();
    

    // Add a bunch of words to the list
    try {
    	Scanner scan = new Scanner(new File("Dictionary.txt"));
    	
    	while (scan.hasNextLine()) {
    		String word = scan.nextLine();
    		theWords.add(word);
    	}
    	scan.close();
    }
    catch (Exception e){
    	System.out.println("Error: Could not find file");
    }
    // call selectNewWord() to get a new word from the list of words
    selectNewWord();
    
    
    // create a mainPanel for components
    mainPanel = new JPanel();    

    // ***** assignments for menu variables *****

    menuBar            = new JMenuBar();
    
    fileMenu           = new JMenu("File");
    newGameMenuItem    = new JMenuItem("New Game");
    exitMenuItem       = new JMenuItem("Exit");
    
        
    // add mnemonics to the menu system
    fileMenu.setMnemonic('F');
    newGameMenuItem.setMnemonic('n');  // new game
    exitMenuItem.setMnemonic('x'); // exit or quit program
    
    // add the menuItem addActionListener(this) calls
    // fileMenu
    newGameMenuItem.addActionListener(this);   // so we can respond to a click
    exitMenuItem.addActionListener(this);  // so we can respond to a click
    
    
    // add menuItems to the fileMenu
    fileMenu.add(newGameMenuItem);
    // fileMenu.add();  // add a separator
    fileMenu.add(exitMenuItem);
    
    
    // add menus to the menuBar
    menuBar.add(fileMenu);
    
    // attach the JMenuBar to the Window
    setJMenuBar(menuBar);
    
    
    // ***** create JPanels for a BorderLayout *****
    northPanel   = new JPanel();
    southPanel   = new JPanel();
    westPanel    = new JPanel();
    eastPanel    = new JPanel();
    centerPanel  = new DrawPanel();;

    mainPanel.setLayout(new BorderLayout());
    southPanel.setLayout(new GridLayout(2,13));  // for the buttons A - Z

    northPanel.setBackground(new Color(115,205,255));
    southPanel.setBackground(new Color(115,205,255));
    westPanel.setBackground(new Color(115,205,255));
    eastPanel.setBackground(new Color(115,205,255));
   
   
    // ***** You need to add buttons, etc. to the 5 panels *****
    
    
    
	for (int i=0; i < 26; i++)
	{
	    JButton button = letterButtons.get(i); // get a reference to the button (letterButtons.???)
	    // call southPanel's add method to add this button to the panel
	    southPanel.add(button);
	}

	
	// add northTitle to the north panel (call the add method)
	northPanel.add(northTitle);
	
	

    // ***** add the panels to the mainPanel 5 areas *****
    mainPanel.add(northPanel,BorderLayout.NORTH);
    mainPanel.add(southPanel,BorderLayout.SOUTH);
    mainPanel.add(eastPanel,BorderLayout.EAST);
    mainPanel.add(westPanel,BorderLayout.WEST);
    mainPanel.add(centerPanel,BorderLayout.CENTER);
	
    // make the mainPanel be the main content area and show it
    setContentPane(mainPanel);
    setVisible(true);  // always show the screen last
  }   // end of public void initialize 



  // ***** default constructor *****
  public Hangman( )
  { 
    // initialize variables

    setSize(800,600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setTitle("H A N G M A N"); 

    initialize( );

  }

  // ***** ActionListener interface methods *****

  // this method is called for us anytime a button is clicked or a menu item is clicked
  // source is a reference or pointer to the object that got clicked
  // start of actionPerformed (from the ActionListener interface)
  public void actionPerformed(ActionEvent e)
  {
    Object source = e.getSource();
    if (source == exitMenuItem)
    {
      System.exit(0);
    }  // end of if
    else if (source == newGameMenuItem)
    {
    
      // call the new game method
      newGame();
      
    }  // end of if
    else if (!gameOver)
    {
	    for (int i=0; i < 26; i++)
	    {
	    	JButton button = letterButtons.get(i);
	    	if (source == button && button.isEnabled())
	    	{	    		
	    		// call addLetterToGuessedWord(letter)
	    		// if it returns false, then
	    		// numBadGuesses++;  add one if the letter is not in the word	    		
				// see if we have a winner, and if so, modify the screen
				
				char letter = button.getText().charAt(0);
				
				if (addLetterToGuessedWord(letter))
				{
					
				}
				else
				{
					numBadGuesses++;	
				}


				if (theWord.equals(guessedWord))
				{
					gameOver = true;
				}
				else if (numBadGuesses >= MAX_BAD_GUESSES)
				{
					gameOver = true;
				}
	    		
	    		// dim the button so that they cannot click it again
	    		button.setEnabled(false);
	    			    		
	    		
	    		// redraw the screen		
	    		centerPanel.repaint();
	    				
	    		return;  // we are finished with the button clicked logic so get out of here
	    	}
	    }
	    
	    centerPanel.repaint();
    	
    }
  }  // end of actionPerformed


  // ***** main method *****
  public static void main(String[] arguments)
  {
	  new Hangman();
  }

  public boolean addLetterToGuessedWord(char letter)
  {
  	 // move through the theWord with a loop
  	 // check and see if letter is a match
  	 // if so, add it to guessedWord else keep the current character in guessedWord
     
     
     boolean letterFound = false;
     
     // create a temporary string variable and set it to guessedWord
     // reset guessedWord to ""
     String temp = guessedWord;
     guessedWord = "";
     
     for (int i=0; i < theWord.length(); i++)
     {
     	// is the letter == ith character of theWord ???
     	// if so, add the letter to guessedWord and set letterFound to true
     	// if not, add the ith character of your temp variable to guessedWord
     	if (letter == theWord.charAt(i))
     	{
     		letterFound = true;
     		guessedWord += letter;
     	}
     	else
     	{
     		guessedWord += temp.charAt(i);
     	}
     	
     }
     
     return letterFound; // READ THIS!!!  return true if the letter was in theWord else false
  }
  
  public void selectNewWord()
  {
  	// assign a new word from theWords to theWord 	(Math.random())
  	// set guessedWord to all underlines
  	
  	int r = (int) (Math.random()*theWords.size());
  	theWord = theWords.get(r);
  	
  	guessedWord = "";
  	for (int i=0; i<theWord.length(); i++)
  	{
  		guessedWord += "_";
  	}
  	
  }
  
  
  public void newGame()
  {
  	// enable all the letterButtons - call each button's setEnabled method and pass it true
  	// remember you have these stored in ArrayList letterButtons
	
	for (int i=0; i<26; i++)
	{
		letterButtons.get(i).setEnabled(true);
	}

  	// set gameOver to false
  	gameOver = false;
  	numBadGuesses = 0;
  	
  	// select a new word and update guessedWord to all underlines - call a method
  	selectNewWord();
  	

  	// call centerPanel.repaint(); to update your screen
  	centerPanel.repaint();
  	
  }
  
  
class DrawPanel extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String testXY="X= Y=";
	boolean dragging = false;
	
	public DrawPanel()
	{
		super();
	}

    public void update(Graphics window)
    {
	   paintComponent(window);
    }
   
	public void paintComponent(Graphics g)
	{
		super.paintComponent((Graphics2D)g);
		Graphics2D g2 = (Graphics2D) g;

		//take a snap shop of the current screen and same it as an image
		//that is the exact same width and height as the current screen
		//if(back==null)
		   back = (BufferedImage)(createImage(getWidth(),getHeight()));

		//create a graphics reference to the back ground image
		//we will draw all changes on the background image
		Graphics gMemory = back.createGraphics();

		// clear the screen
		gMemory.setColor(Color.BLACK);
		gMemory.fillRect(0,0,getWidth(),getHeight());

 		gMemory.setColor(Color.WHITE); 		 		
 		
 		
 		// draw the guessedWord so that the user sees what they need to guess
 		Font font = new Font("Courier New",Font.BOLD,32);
		gMemory.setFont(font);

 		int x = 200;
 		for (int i=0; i<guessedWord.length(); i++)
 		{
 			gMemory.drawString(guessedWord.charAt(i)+"",x,440);
 			x += 30;
 		}
 		
 		if (gameOver)
 		{
 			// FINISH ME
 			// draw a You Win or You Lose message below 
 			// use gMemory.drawString("your message",x location, y location);	
 			// x location should be replaced by a number
 			// y location should be replaced by a number
 			
			if (theWord.equals(guessedWord))
			{
				// do the you win message here
				font = new Font("Courier New",Font.BOLD,32);
				gMemory.setFont(font);
				gMemory.drawString("Y O U   W I N!",450,200);	
			}
			else if (numBadGuesses >= MAX_BAD_GUESSES)
			{
				// do the you lose message here		
				font = new Font("Courier New",Font.BOLD,32);
				gMemory.setFont(font);
				gMemory.drawString("Y O U   L O S E!",450,200);	
			}
 		}

 		gMemory.setColor(Color.lightGray);
 		gMemory.fillRect(170,40,15,350);  // draw main pole
 		gMemory.fillRect(185,40,200,30);  // draw top piece
 		gMemory.setColor(Color.YELLOW);
 		gMemory.fillRect(350,70,4,40);
 		
 		if (numBadGuesses >= 1)
 		{
 			gMemory.setColor(Color.RED);
 			gMemory.drawArc(316, 110, 68, 68, 0, 360);
 			// FINISH ME
 			// draw the head
 			// gMemory.setColor(....);
 			// gMemory.drawOval(....);
 			// draw head

 		}

 		if (numBadGuesses >= 2)
 		{
 			gMemory.fillArc(332, 135, 10, 10, 0, 360);
 			// FINISH ME
 			// draw the left eye
 			// gMemory.setColor(....);
 			// gMemory.drawOval(....);

 		}
 		
 		
 		if (numBadGuesses >= 3)
 		{
 			gMemory.fillArc(370, 135, 10, 10, 0, 360);
 			// FINISH ME
 			// draw right eye


 		}
 		
 		if (numBadGuesses >= 4)
 		{
 			gMemory.fillRect(353, 140, 7, 15);
 			// FINISH ME
 			// draw nose


 		}
 
 		if (numBadGuesses >= 5)
 		{
 			// FINISH ME
 		 	// draw mouth
 			gMemory.setColor(Color.RED);
 			gMemory.drawArc(335,160,34,25,0,180);
		}

 		if (numBadGuesses >= 6)
 		{
 			// FINISH ME
 			// draw main body
 			gMemory.setColor(Color.RED);
	 		gMemory.fillRect(320,178,64,130);
 		}

 		if (numBadGuesses >= 7)
 		{
 			gMemory.drawLine(320, 178, 300, 320);
 			// FINISH ME
 		 	// draw left arm

		}

 		if (numBadGuesses >= 8)
 		{
 			gMemory.drawLine(384, 178, 404, 320);
 			// FINISH ME
 		 	// draw rigth arm


		}

 		if (numBadGuesses >= 9)
 		{
 			gMemory.fillRect(320, 308, 10, 100);
 			// FINISH ME
 			// draw left leg


 		}

 		if (numBadGuesses >= 10)
 		{
 			gMemory.fillRect(374, 308, 10, 100);
 			// FINISH ME
 		 	// draw right leg


		}
 		
 		
 		
		// *** show the screen by copying the image to the graphics display ********
		// you do this last !!!!!!!!!!
   		g2.drawImage(back, null, 0, 0);	
 		
 	}  // end of public void paintComponent(Graphics g)
}


  
} // end of class Hangman
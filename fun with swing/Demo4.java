/*
 * This is a Java Swing experiment.
 * This program utilizes object serialization to save and restore an operational GUI object.
 *
 * When this program is run, a GUI will appear very briefly only to disappear and reappear. It
 * is actually being serialized, destroyed, and reinstantiated from its serialized state.
 */
import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//=============================================================================//
public class Demo4{
  public static void main(String[] args){
    MyProcess process = new MyProcess();
  }//end main()
}//end Demo4
//=============================================================================//
class MyProcess{
  MyProcess(){
    try{
	  //open up a stream for writing objects and saving them to a tmp file
	  FileOutputStream fileOut = new FileOutputStream("tmp");
	  ObjectOutputStream outStream = new ObjectOutputStream(fileOut);
	  
	  GUI oldObject = new GUI();
	  
	  //write a custom GUI object to disk, dispose of the GUI completely, flush and close the output stream
	  outStream.writeObject(oldObject);
	  oldObject.dispose();
	  outStream.flush();
	  outStream.close();
	  
	  //open up a stream for reading objects
	  FileInputStream fileIn = new FileInputStream("tmp");
	  ObjectInputStream inStream = new ObjectInputStream(fileIn);
	  
	  try{
	    //read custom GUI object from file
		GUI newObject = (GUI)inStream.readObject();
		
		//invoke one of the objects methods to instantiate and register
		//listener objects on the components in the custom GUI, thereby
		//(re)activating it
		newObject.activate();
	  }catch(ClassNotFoundException ex){
	    System.out.println(ex);
	  }//end try-catch block
	  
	  inStream.close();
	}catch(IOException ex){
	  System.out.println(ex);
	}//end try-catch block
  }//end no-args constructor
}//end MyProcess
//=============================================================================//
/*
 * This GUI delegates the instantiation of its event listeners to
 * the activate() method, so that they can be reset in the event of
 * being reinstantiated from a serialized state.
 * The activate() method also resets the frame configuration.
 */
class GUI extends JFrame implements Serializable{
  JButton singButton, whistleButton;
  
  public GUI(){
    this.setLayout(new FlowLayout());
	this.setTitle("Serializable GUI");
	
	this.singButton = new JButton("Sing");
	this.whistleButton = new JButton("Whistle");
	
	this.getContentPane().add(singButton);
	this.getContentPane().add(whistleButton);
	
	this.setSize(new Dimension(300, 75));
	this.setVisible(true); //displays temporarily
  }//end no-args constructor
  
  public void activate(){
    this.singButton.addActionListener(new ActionListener(){
	    public void actionPerformed(ActionEvent e){
	      System.out.println("I am singing, tra la la...");
	    }//end actionPerformed()
	  }//end ActionListener()
	);//end addActionListener()
	
	this.whistleButton.addActionListener(new ActionListener(){
	    public void actionPerformed(ActionEvent e){
		  System.out.println("I am whistling, tweet tweet tweet...");
		}//end actionPerformed()
	  }//end ActionListener()
	);//end addActionListener()
	
	//research using WindowListeners instead to customize the effects of trying to close the window/frame
	this.setDefaultCloseOperation(3); //3 = JFrame.EXIT_ON_CLOSE, courtesy of daGerman
	this.setSize(new Dimension(300, 75));
	this.setVisible(true);
	this.repaint();
  }//end activate()
}//end GUI
//=============================================================================//
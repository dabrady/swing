/*
 * A Java Swing experiment.
 * I want to do the same thing by extending JFrame, too.
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Demo1{
  JFrame frame;
  
  public Demo1(){
    this.frame = new JFrame();
  }
	
  public static void createAndShowGUI(){
	Demo1 demo = new Demo1();
	
	demo.frame.setTitle("!");	
	demo.frame.setDefaultCloseOperation(3);
	demo.frame.getContentPane().add(demo.createHomeScreen());
	demo.frame.setSize(719, 476);
	demo.frame.setVisible(true);
	demo.frame.pack();
  }

  public Container createOverworld(){
 	JPanel contentPane = new JPanel(); //default BorderLayout
	
	//set homescreen background image
	ImageIcon icon = createImageIcon("overworld.png", "");
	JLabel backgroundImage = new JLabel(icon);
	
	contentPane.add(backgroundImage);
	
	return contentPane; 
  }
  
  public Container createHomeScreen(){
	JPanel contentPane = new JPanel(); //default BorderLayout
	contentPane.setLayout(null);
	contentPane.setPreferredSize(new Dimension(719, 476));
	
	//set homescreen background image
	ImageIcon icon = createImageIcon("homescreen.png", "");
	JLabel backgroundImage = new JLabel(icon);
	backgroundImage.setBounds(0, 20, 719, 456);
	
	JButton start = new JButton("START");
	start.setBounds(500, 300, 75, 50);	
	
	contentPane.add(start);
	contentPane.add(backgroundImage);
	
	return contentPane;
  }
  
  public Container createBattleScreen(){  
    JPanel contentPane = new JPanel(); //default BorderLayout
	
    //set battlescreen background image
	ImageIcon icon = createImageIcon("battlescreen.png", "");
	JLabel backgroundImage = new JLabel(icon);
	
	contentPane.add(backgroundImage);
	
	return contentPane;
  }
  
  public void setMenuBar(int i){
	switch(i){
	  case 1:
		//
		break;
	  case 2:
	    //
		break;
	  default:
	    break;
	}
  }
  
  public static JMenuBar createMenuBar1(){
    JMenuBar menuBar = new JMenuBar();
	JMenu menu;
	JMenuItem menuItem;
		
	//Build pokemon menu
	menu = new JMenu("Pokemon");
	menu.setMnemonic(KeyEvent.VK_P);
	menuBar.add(menu);
	
	menuItem = new JMenuItem("Charmander", KeyEvent.VK_C);
	menu.add(menuItem);
	
	menuItem = new JMenuItem("Pikachu", KeyEvent.VK_P);
	menu.add(menuItem);
	
	menuItem = new JMenuItem("Bulbasaur", KeyEvent.VK_B);
	menu.add(menuItem);
	
	menuItem = new JMenuItem("Squirtle", KeyEvent.VK_Q);
	menu.add(menuItem);
	
	menuItem = new JMenuItem("Charizard", KeyEvent.VK_Z);
	menu.add(menuItem);
	
	menuItem = new JMenuItem("Magikarp", KeyEvent.VK_K);
	menu.add(menuItem);
	
	menuItem = new JMenuItem("Eeevee", KeyEvent.VK_E);
	menu.add(menuItem);
	
    //Build bag menu
    menu = new JMenu("Bag");
	menu.setMnemonic(KeyEvent.VK_B);
	menuBar.add(menu);
	
	menuItem = new JMenuItem("Potion", KeyEvent.VK_P);
	menu.add(menuItem);
	
	menuItem = new JMenuItem("Super Potion", KeyEvent.VK_S);
	menu.add(menuItem);
	
	menuItem = new JMenuItem("Rope", KeyEvent.VK_R);
	menu.add(menuItem);
	
	menuItem = new JMenuItem("Rare Candy", KeyEvent.VK_C);
	menu.add(menuItem);
	
	//Build save menu
	menu = new JMenu("Save");
	menu.setMnemonic(KeyEvent.VK_S);
	menuBar.add(menu);
	
	//Build run menu
	menu = new JMenu("Run");
	menu.setMnemonic(KeyEvent.VK_R);
	menuBar.add(menu);
	
	return menuBar;
  }
  
  public static JMenuBar createMenuBar2(){
	JMenuBar menuBar = new JMenuBar();
	JMenu menu;
	JMenuItem menuItem;
	
	//Build pokemon menu
	menu = new JMenu("Pokemon");
	menu.setMnemonic(KeyEvent.VK_P);
	menuBar.add(menu);
	
	menuItem = new JMenuItem("Charmander", KeyEvent.VK_C);
	menu.add(menuItem);
	
	menuItem = new JMenuItem("Pikachu", KeyEvent.VK_P);
	menu.add(menuItem);
	
	menuItem = new JMenuItem("Bulbasaur", KeyEvent.VK_B);
	menu.add(menuItem);
	
	menuItem = new JMenuItem("Squirtle", KeyEvent.VK_Q);
	menu.add(menuItem);
	
	menuItem = new JMenuItem("Charizard", KeyEvent.VK_Z);
	menu.add(menuItem);
	
	menuItem = new JMenuItem("Magikarp", KeyEvent.VK_K);
	menu.add(menuItem);
	
	menuItem = new JMenuItem("Eeevee", KeyEvent.VK_E);
	menu.add(menuItem);
	
	//Build bag menu
    menu = new JMenu("Bag");
	menu.setMnemonic(KeyEvent.VK_B);
	menuBar.add(menu);
	
	menuItem = new JMenuItem("Potion", KeyEvent.VK_P);
	menu.add(menuItem);
	
	menuItem = new JMenuItem("Super Potion", KeyEvent.VK_S);
	menu.add(menuItem);
	
	menuItem = new JMenuItem("Rope", KeyEvent.VK_R);
	menu.add(menuItem);
	
	menuItem = new JMenuItem("Rare Candy", KeyEvent.VK_C);
	menu.add(menuItem);
	
	//Build config menu
	menu = new JMenu("Config");
	menu.setMnemonic(KeyEvent.VK_C);
	menuBar.add(menu);
	
	menuItem = new JMenuItem("Display");
	menu.add(menuItem);
	
	menuItem = new JMenuItem("Sound");
	menu.add(menuItem);
	
	menuItem = new JMenuItem("Controls");
	menu.add(menuItem);
	
	//Build special move menu
	menu = new JMenu("Special");
	menu.setMnemonic(KeyEvent.VK_X);
	menuBar.add(menu);
	
	menuItem = new JMenuItem("Fly");
	menu.add(menuItem);
	
	menuItem = new JMenuItem("Cut");
	menu.add(menuItem);
	
	//Build save menu
	menu = new JMenu("Save");
	menu.setMnemonic(KeyEvent.VK_S);
	menuBar.add(menu);
	
	return menuBar;
  }
	
  // Returns an ImageIcon, or null if the path was invalid
  protected static ImageIcon createImageIcon(String path,
										   String description) {
	java.net.URL imgURL = Demo1.class.getResource(path);
	if (imgURL != null) {
		return new ImageIcon(imgURL, description);
	} else {
		System.err.println("Couldn't find file: " + path);
		return null;
	}
  }
  
  public static void main(String[] args){
    //Schedule a job for the event-dispatching thread:
    //creating and showing this application's GUI.
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
                                                 public void run() {
                                                   createAndShowGUI();
                                                 }
                                         });
  }
}
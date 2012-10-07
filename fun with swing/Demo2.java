/*
 * A Java Swing experiment.
 * A PJFrame has three custom panels, and can switch between them. It also restricts a program to creating only one instance of a PJFrame by keeping a static instance of itself.
 * This demo uses a toolbar.
 */
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.*;

class PJFrame extends JFrame{
  private static PJFrame myInstance; //an instance of itself, used to restrict the number of instances of a PJFrame to a single shared instance among all components
  public static HomeScreen1 home;  //since there can only be one instance of PJFrame, I thought it fitting (though redundant) to make its fields static (shared among all instances)
  public static OverWorld1 ow;
  public static BattleScreen1 battle;
   
  private PJFrame(String title){
    super(title);
	home = new HomeScreen1(this, "homescreen.png");
	ow = new OverWorld1(this, "overworld.png");
	battle = new BattleScreen1(this, "battlescreen.png");
	this.setContentPane(home);  //replaces the content pane with the home panel; using frame.getContentPane() will return the home panel
  }
  
  public static PJFrame getInstance(){
    if (myInstance == null)
	  myInstance = new PJFrame("");
	
	return myInstance;
  }

  public static PJFrame getInstance(String title){
    if (myInstance == null)
	  myInstance = new PJFrame(title);
	  
	return myInstance;
  }  
  
}

class ImagePanel1 extends JPanel{
  BufferedImage image;
  
  public ImagePanel1(String url){
	try{
	  this.image = ImageIO.read(new File(url));
	}catch(IOException ex){
	  ex.printStackTrace();
	}
  }
  
  public void paintComponent(Graphics g){
    super.paintComponent(g);
	g.drawImage(this.image, 0, 0, null);
  }
}

class HomeScreen1 extends ImagePanel1 implements ActionListener{
  public PJFrame parent;
  public JPanel contentPane;
  
  public HomeScreen1(PJFrame parent, String url){
    super(url);
	this.parent = parent;
	this.setLayout(new BorderLayout());
    this.setPreferredSize(new Dimension(719, 456));	
	/*
	 * In a BorderLayout, components are resized to fill the region they're placed in. By adding components
     * to a patsy panel with a different layout, and placing the patsy in a BorderLayout, the panel is the component
	 * that gets resized (set transparent so the background image shows through), but the added component stays its preferred size.
	 * 
	 * Using Box.createRigidArea(), we can add what appears to be empty space (but is actually just a transparent rectangle) between
	 * visible components, to separate them or aid in positioning.
	 */
	 
	this.contentPane = new JPanel();
	this.contentPane.setOpaque(false); //allows the background image to show through
	this.contentPane.setLayout(new FlowLayout());
	
	this.add(this.contentPane, BorderLayout.CENTER);
	
	JButton button = new JButton("START");
	button.setPreferredSize(new Dimension(75, 50));
	button.addActionListener(this);
	
	this.contentPane.add(Box.createRigidArea(new Dimension(350, 600))); //offsets our button
	this.contentPane.add(button);
  }
  
  public JPanel getContentPane(){
    return this.contentPane;
  }
  
  public void actionPerformed(ActionEvent e){
    this.setVisible(false);
	this.parent.setContentPane(this.parent.ow);
	this.parent.ow.setVisible(true);
  }
}

class OverWorld1 extends ImagePanel1 implements ActionListener{
  public PJFrame parent;
  public JPanel contentPane;
  
  public OverWorld1(PJFrame parent, String url){
    super(url);
	this.parent = parent;
	this.setLayout(new BorderLayout());
    this.setPreferredSize(new Dimension(719, 456));	
	/*
	 * In a BorderLayout, components are resized to fill the region they're placed in. By adding components
     * to a patsy panel with a different layout, and placing the patsy in a BorderLayout, the panel is the component
	 * that gets resized (set transparent so the background image shows through), but the added component stays its preferred size.
	 * 
	 * Using Box.createRigidArea(), we can add what appears to be empty space (but is actually just a transparent rectangle) between
	 * visible components, to separate them or aid in positioning.
	 */
	 
	this.contentPane = new JPanel();
	this.contentPane.setOpaque(false); //allows the background image to show through
	this.contentPane.setLayout(new FlowLayout());
	
    JButton button = new JButton("Battle");
	button.setPreferredSize(new Dimension(75, 50));
	button.addActionListener(this);
	
	this.contentPane.add(Box.createRigidArea(new Dimension(200, 400))); //offsets our button
	this.contentPane.add(button);
	
	this.add(createToolBar(parent), BorderLayout.PAGE_START);
	this.add(this.contentPane, BorderLayout.CENTER);
  }
  
  public JPanel getContentPane(){
    return this.contentPane;
  }
  
  public JPopupMenu createPokemonMenu(){
    JPopupMenu popup = new JPopupMenu();
	JMenuItem menuItem;
	//These items do nothing; they need ActionListeners
	menuItem = new JMenuItem("Charmander", KeyEvent.VK_C);
	popup.add(menuItem);
	
	menuItem = new JMenuItem("Pikachu", KeyEvent.VK_P);
	popup.add(menuItem);
	
	menuItem = new JMenuItem("Bulbasaur", KeyEvent.VK_B);
	popup.add(menuItem);
	
	menuItem = new JMenuItem("Squirtle", KeyEvent.VK_Q);
	popup.add(menuItem);
	
	menuItem = new JMenuItem("Charizard", KeyEvent.VK_Z);
	popup.add(menuItem);
	
	menuItem = new JMenuItem("Magikarp", KeyEvent.VK_K);
	popup.add(menuItem);
	
	menuItem = new JMenuItem("Eeevee", KeyEvent.VK_E);
	popup.add(menuItem);
	
	return popup;
  }
  
  public JPopupMenu createBagMenu(){
    JPopupMenu popup = new JPopupMenu();
	JMenuItem menuItem;
	//These items do nothing; they need ActionListeners   
	menuItem = new JMenuItem("Potion(2)", KeyEvent.VK_P);
	popup.add(menuItem);
	
	menuItem = new JMenuItem("Super Potion(1)", KeyEvent.VK_S);
	popup.add(menuItem);
	
	menuItem = new JMenuItem("Rope(1)", KeyEvent.VK_R);
	popup.add(menuItem);
	
	menuItem = new JMenuItem("Rare Candy(0)", KeyEvent.VK_C);
	popup.add(menuItem);
	
	return popup;
  }
  
  public JToolBar createToolBar(PJFrame parent){
    //needed to be final so as to use within an inner class (i.e. the MouseAdapter)
	final PJFrame frame = parent;
	final OverWorld1 self = this;
	final JToolBar toolbar;
	final JPopupMenu popup1, popup2;
	final JButton button1, button2, button3, button4;
	
	toolbar = new JToolBar("Game Things");
	toolbar.setPreferredSize(new Dimension(this.parent.getPreferredSize().width, 20));
	toolbar.setFloatable(false);
	
	popup1 = createPokemonMenu();
	button1 = new JButton("Pokemon");
	button1.setMnemonic(KeyEvent.VK_P);
	button1.addMouseListener(new MouseAdapter(){
	  public void mouseClicked(MouseEvent e){
	    if (e.getButton() == MouseEvent.BUTTON1){
	      popup1.show(e.getComponent(), e.getComponent().getX(), e.getComponent().getY() + e.getComponent().getHeight());
		}
      }
	});
	toolbar.add(button1);
	
	popup2 = createBagMenu();
	button2 = new JButton("Bag");
	button2.setMnemonic(KeyEvent.VK_B);
	button2.addMouseListener(new MouseAdapter(){
      public void mouseClicked(MouseEvent e){
	    if (e.getButton() == MouseEvent.BUTTON1){
		  //i've no idea why the same code for the above popup doesn't work here -_- the x-coord is WAY off...
	      popup2.show(e.getComponent(), e.getComponent().getX() - (2 * e.getComponent().getWidth()), e.getComponent().getY() + e.getComponent().getHeight());
		}
      }
	});
	toolbar.add(button2);

	//does nothing; no event listener
    button3 = new JButton("Save");
    button3.setMnemonic(KeyEvent.VK_S);
    toolbar.add(button3);
	
	//should only be enabled or visible in the OverWorld1; currently switches back to home screen
	button4 = new JButton("Quit");
	button4.setMnemonic(KeyEvent.VK_Q);
	button4.addActionListener(new ActionListener(){
	  public void actionPerformed(ActionEvent e){
	    self.setVisible(false);
		frame.setContentPane(frame.home);
		frame.home.setVisible(true);
	  }
	});
	toolbar.add(button4);
	
	return toolbar;
  }
  
  public void actionPerformed(ActionEvent e){
    this.setVisible(false);
	this.parent.setContentPane(this.parent.battle);
	this.parent.battle.setVisible(true);
  }
}

class BattleScreen1 extends ImagePanel1{
  public PJFrame parent;
  public JPanel contentPane;
  
  public BattleScreen1(PJFrame parent, String url){
    super(url);
	this.parent = parent;
	this.setLayout(new BorderLayout());
    this.setPreferredSize(new Dimension(719, 456));	
	/*
	 * In a BorderLayout, components are resized to fill the region they're placed in. By adding components
     * to a patsy panel with a different layout, and placing the patsy in a BorderLayout, the panel is the component
	 * that gets resized (set transparent so the background image shows through), but the added component stays its preferred size.
	 * 
	 * Using Box.createRigidArea(), we can add what appears to be empty space (but is actually just a transparent rectangle) between
	 * visible components, to separate them or aid in positioning.
	 */
	 
	this.contentPane = new JPanel();
	this.contentPane.setOpaque(false); //allows the background image to show through
	this.contentPane.setLayout(new FlowLayout());
	
	this.add(createToolBar(parent), BorderLayout.PAGE_START);
	this.add(this.contentPane, BorderLayout.CENTER);
  }
  
  public JPanel getContentPane(){
    return this.contentPane;
  }
  
  public JPopupMenu createPokemonMenu(){
    JPopupMenu popup = new JPopupMenu();
	JMenuItem menuItem;
	//These items do nothing; they need ActionListeners
	menuItem = new JMenuItem("Charmander", KeyEvent.VK_C);
	popup.add(menuItem);
	
	menuItem = new JMenuItem("Pikachu", KeyEvent.VK_P);
	popup.add(menuItem);
	
	menuItem = new JMenuItem("Bulbasaur", KeyEvent.VK_B);
	popup.add(menuItem);
	
	menuItem = new JMenuItem("Squirtle", KeyEvent.VK_Q);
	popup.add(menuItem);
	
	menuItem = new JMenuItem("Charizard", KeyEvent.VK_Z);
	popup.add(menuItem);
	
	menuItem = new JMenuItem("Magikarp", KeyEvent.VK_K);
	popup.add(menuItem);
	
	menuItem = new JMenuItem("Eeevee", KeyEvent.VK_E);
	popup.add(menuItem);
	
	return popup;
  }
  
  public JPopupMenu createBagMenu(){
    JPopupMenu popup = new JPopupMenu();
	JMenuItem menuItem;
	//These items do nothing; they need ActionListeners   
	menuItem = new JMenuItem("Potion(2)", KeyEvent.VK_P);
	popup.add(menuItem);
	
	menuItem = new JMenuItem("Super Potion(1)", KeyEvent.VK_S);
	popup.add(menuItem);
	
	menuItem = new JMenuItem("Rope(1)", KeyEvent.VK_R);
	popup.add(menuItem);
	
	menuItem = new JMenuItem("Rare Candy(0)", KeyEvent.VK_C);
	popup.add(menuItem);
	
	return popup;
  }
  
  public JToolBar createToolBar(PJFrame parent){
    //needed to be final so as to use within an inner class (i.e. the MouseAdapter)
	final PJFrame frame = parent;
	final ImagePanel1 self = this;
	final JToolBar toolbar;
	final JPopupMenu popup1, popup2;
	final JButton button1, button2, button3, button4;
	
	toolbar = new JToolBar("Game Things");
	toolbar.setPreferredSize(new Dimension(this.parent.getPreferredSize().width, 20));
	toolbar.setFloatable(false);
	
	popup1 = createPokemonMenu();
	button1 = new JButton("Pokemon");
	button1.setMnemonic(KeyEvent.VK_P);
	button1.addMouseListener(new MouseAdapter(){
	  public void mouseClicked(MouseEvent e){
	    if (e.getButton() == MouseEvent.BUTTON1){
	      popup1.show(e.getComponent(), e.getComponent().getX(), e.getComponent().getY() + e.getComponent().getHeight());
		}
      }
	});
	toolbar.add(button1);
	
	popup2 = createBagMenu();
	button2 = new JButton("Bag");
	button2.setMnemonic(KeyEvent.VK_B);
	button2.addMouseListener(new MouseAdapter(){
      public void mouseClicked(MouseEvent e){
	    if (e.getButton() == MouseEvent.BUTTON1){
		  //i've no idea why the same code for the above popup doesn't work here -_- the x-coord is WAY off...
	      popup2.show(e.getComponent(), e.getComponent().getX() - (2 * e.getComponent().getWidth()), e.getComponent().getY() + e.getComponent().getHeight());
		}
      }
	});
	toolbar.add(button2);

	//does nothing; no event listener
    button3 = new JButton("Save");
    button3.setMnemonic(KeyEvent.VK_S);
    toolbar.add(button3);
	
	//should only be enabled or visible during battles; currently switches back to ow screen
	button4 = new JButton("Run");
	button4.setMnemonic(KeyEvent.VK_R);
	button4.addActionListener(new ActionListener(){
	  public void actionPerformed(ActionEvent e){
	    self.setVisible(false);
		frame.setContentPane(frame.ow);
		frame.ow.setVisible(true);
	  }
	});
	toolbar.add(button4);
	
	return toolbar;
  }
}

public class Demo2{
  public static void runGUI(){
    PJFrame frame = PJFrame.getInstance("!");
	frame.setDefaultCloseOperation(3);
	frame.setSize(719, 456);
	frame.pack();    
	frame.setVisible(true);
  }
  
  public static void main(String[] args){
	//Schedule a job for the event dispatch thread:
	//creating and showing this application's GUI.
	SwingUtilities.invokeLater(new Runnable() {
		public void run() {
	      //Turn off metal's use of bold fonts
		  UIManager.put("swing.boldMetal", Boolean.FALSE);
		  runGUI();
		}
	});
  }
}
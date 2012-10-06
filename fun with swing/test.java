import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class test{
  public static void main(String[] args){
    final JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(3);
	frame.setLayout(new FlowLayout());
	
	final JMenuBar mb = test.createMenuBar();
		
	final JButton button = new JButton("Disable MenuBar");
	button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
			  button.getParent().getParent().remove(mb);
			  button.getParent().getParent().repaint();
			}
		});
		
	final JButton button2 = new JButton("Enable MenuBar");
	button2.addActionListener(new ActionListener(){
	         public void actionPerformed(ActionEvent e){
			   frame.setJMenuBar(mb);
			   frame.repaint();
			 }
	    });
		
	frame.setJMenuBar(mb);
	frame.getContentPane().add(button);
	frame.getContentPane().add(button2);
	
	frame.pack();
	frame.setVisible(true);
  }
  
  public static JMenuBar createMenuBar(){
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
}
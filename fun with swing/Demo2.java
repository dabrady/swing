/*
 * A Java Swing experiment.
 * A JFrame that has three custom panels.
 */
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.*;

class PFrame extends JFrame{
  HomeScreen home = new HomeScreen("homescreen.png");
  OverWorld ow = new OverWorld("overworld.png");
  BattleScreen battle = new BattleScreen("battlescreen.png");
  int currentPanel = 0; //0 for home, 1 for overworld, 2 for battle; used for setting menubar
  
  public PFrame(){
    super();
	this.setContentPane(new ImagePanel("homescreen.png"));
  }
  
  public PFrame(String title){
    super(title);
  }
  
  
  
}

class ImagePanel extends JPanel{
  BufferedImage image;
  
  public ImagePanel(String url){
	try{
	  this.image = ImageIO.read(new File(url));
	  this.setPreferredSize(new Dimension(719, 476));
	}catch(IOException ex){
	  ex.printStackTrace();
	}
  }
  
  public void paintComponent(Graphics g){
    super.paintComponent(g);
	g.drawImage(this.image, 0, 0, null);
  }
}

class HomeScreen extends ImagePanel{
  public HomeScreen(String url){
    super(url);
  }
}

class OverWorld extends ImagePanel{
  public OverWorld(String url){
    super(url);
  }
}

class BattleScreen extends ImagePanel{
  public BattleScreen(String url){
    super(url);
  }
}

public class Demo2{
  public static void main(String[] args){
    PFrame frame = new PFrame("!");
	frame.setDefaultCloseOperation(3);
	frame.setSize(719, 476);
	frame.setVisible(true);
	frame.pack();
  }
}
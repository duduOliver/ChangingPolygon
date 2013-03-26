import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Path2D;

public class Viewer extends JPanel {
	
	RandomPolygon alfa = new RandomPolygon(3);
	//System.out.println("Hello World!");
	
	  public void paintComponent(Graphics g) {
		  super.paintComponent(g);
		  g.setColor(Color.green);
		  Graphics2D g2 = (Graphics2D)g.create();
		  
		  Path2D p = new Path2D.Double();
		  p.moveTo(600,100);
		  Vector temp = new Vector(600, 100);
		  for(int i = 0; i < alfa.getDot().length; i++){			  
			  temp.setX(temp.getX() + alfa.getDot()[i].getX());
			  temp.setY(temp.getY() + alfa.getDot()[i].getY());
			  p.lineTo(temp.getX(), temp.getY());
			  //System.out.println(temp.getX() + ", " + temp.getY());
		  }
		  g2.draw(p);
		  //alfa.printDots();
	  }   
	  
		  public static void main(String[] args) {
		    JFrame frame = new JFrame();
		    frame.setTitle("ChangingPolygon");
		    frame.setSize(420, 360);
		    Container contentPane = frame.getContentPane();
			//alfa.printDots();

		    contentPane.add(new Viewer());
		    //contentPane.add(new JScrollPane(SCROLLBARS_AS_NEEDED));
		    //JScrollPane jsp = new JScrollPane();
		    
		    //jsp.add(new Viewer());
		    frame.show();
		  }
		}

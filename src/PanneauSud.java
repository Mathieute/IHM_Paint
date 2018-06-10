import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class PanneauSud extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; 

	public void paintComponent (Graphics g) {
		
		//CLASSE QUI PERMET D'AVOIR LE LOGO À GAUCHE DU PANEL
		
		super.paintComponent(g);
		Image logo = new ImageIcon("mlg.png").getImage();
		
		g.drawImage(logo,5,0,logo.getWidth(this)/3,logo.getHeight(this)/3,this);
		
	}
	
}

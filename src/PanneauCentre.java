import java.awt.Graphics;

import javax.swing.JPanel;


public class PanneauCentre extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PanneauCentre() { 				//CLASSE QUI PERMET D'AVOIR LE FOND BLANC POUR LE PANNEAU DU CENTRE
		
		super();
		this.setOpaque(true);
		//this.setBackground(Color.WHITE);
	}
	
	public void paintComponent (Graphics g) {
	
		//REDÉFINITION PERMETTANT DE REDESSINER LES FIGURES SAUVEGARDÉES SUR NOTRE PANNEAUCENTRE
		
		super.paintComponent(g);
		
		for (Dessin fig : Donnees.figures) {
			if (fig.getForme().equals("Cercle")){
				g.setColor(fig.getCouleur());
				g.fillOval (fig.getPosition().x - fig.getTaille() /2, fig.getPosition().y - fig.getTaille() /2, fig.getTaille(), fig.getTaille());
			}
			else if (fig.getForme().equals("Carre")){
				g.setColor(fig.getCouleur());
				g.fillRect(fig.getPosition().x - fig.getTaille() /2, fig.getPosition().y - fig.getTaille() /2, fig.getTaille(), fig.getTaille());
			}		
		}	
	}	
}

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;



public class Paint extends JFrame {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	JToggleButton butCrayon;
	JToggleButton butGomme;
	JComboBox<String> listeCouleur;	
	JComboBox<String> listeFormes;
	JComboBox<String> listeFond;
	JTextField choixTaille;
	PanneauCentre pCentre;




	public Paint() {										//CONSTRUCTEUR DU PAINT (PLEIN ÉCRAN)
		super("Paint");
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); //FENTRE EN PLEIN ECRAN
		
		this.initialise();
		this.setBounds(100,100,900,600);
		this.setVisible(true);
	}
		
	public void initialise() {								//INITIALISE LE MENU ET LES DIFFERENTES PARTIES (NORTH, CENTER etc.) DANS LA FENETRE 
		
		this.setJMenuBar(getMenuBarre());
		
		this.setLayout(new BorderLayout());
		
		
		this.add(getPanelNorth(),BorderLayout.NORTH);
		this.add(getPanelCentre(),BorderLayout.CENTER);
		this.add(getPanelSouth(),BorderLayout.SOUTH);		
	}
	
	public JMenuBar getMenuBarre() {
		
		
		JMenuBar menuBarre = new JMenuBar(); 
		//MENU FICHIER -> QUITTER
		JMenu menuFichier = new JMenu("Fichier");
		JMenuItem menuQuitter = new JMenuItem("Quitter");
		
		//ASSOCIE UN LISTENER À L'ITEM "QUITTER" DU MENU
		ButQuitter butQuit = new ButQuitter();
		menuQuitter.addActionListener(butQuit);
		
		menuFichier.add(menuQuitter);
		//MENU À PROPOS
		JMenu menuAPropos = new JMenu("A propos");
		//AJOUT DU MENU-LISTENER AU MENU "À PROPOS"
		ButAbout butAbout = new ButAbout();
		menuAPropos.addMenuListener(butAbout);
		
		menuBarre.add(menuFichier);
		menuBarre.add(menuAPropos);
		
		return menuBarre;		
	}
	
	
	public JPanel getPanelNorth() {
		
		JPanel pNorth = new JPanel(new FlowLayout());
		
		//MENU DÉROULANT DE COULEURS
		pNorth.add(new JLabel ("Couleur :"));
		this.listeCouleur = new JComboBox<String>(Donnees.couleurs);
		pNorth.add(this.listeCouleur);
		
		//MENU DÉROULANT DES FORMES
		pNorth.add(new JLabel ("  Forme :"));
		this.listeFormes = new JComboBox<String>(Donnees.formes);
		pNorth.add(this.listeFormes);
		
		//MENU DEROULANT DES COULEURS DE FOND
		pNorth.add(new JLabel (" Couleur du fond :"));
		this.listeFond = new JComboBox<String>(Donnees.fond);
		pNorth.add(this.listeFond);
		this.listeFond.addActionListener(new ButFond());
		
		//LABEL + ESPACE POUR CHOIX DE LA TAILLE
		pNorth.add(new JLabel("  Taille :"));
		
		choixTaille = new JTextField(6);
		pNorth.add(choixTaille);
		
		//BOUTONS AVEC LES IMAGES (GOMME/CRAYON)
			//recupere l'image Crayon en icone, puis la convertit en image pour la redimensionner avant de la reconvertir en logo
		ImageIcon logoCrayon = new ImageIcon(new ImageIcon("logoCrayon.png").getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH));
		this.butCrayon = new JToggleButton(logoCrayon);
		pNorth.add(this.butCrayon);
			//ajouter le listener au bouton CRAYON
		ListCrayGom listCrayonGomme = new ListCrayGom();
		this.butCrayon.addActionListener(listCrayonGomme);
		
			//meme principe pour l'image mais avec la Gomme
		ImageIcon logoGomme = new ImageIcon(new ImageIcon("logoGomme.png").getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH));
		this.butGomme = new JToggleButton(logoGomme);
		pNorth.add(this.butGomme);
			//ajouter le listener au bouton GOMME
		this.butGomme.addActionListener(listCrayonGomme);
		
		return pNorth;
	}
	
	
	public JPanel getPanelSouth() {
		
		JPanel pSouth = new PanneauSud();
		//AJOUT DES 2 BOUTONS AU PANEL SUD
		JButton boutonEffacer = new JButton("Effacer tout");
		JButton boutonUndo = new JButton("Revenir en arriere");
		
		boutonEffacer.addActionListener(new butErase());
		boutonUndo.addActionListener(new butUndo());
		
		pSouth.add(boutonEffacer);
		pSouth.add(boutonUndo);
		
		return pSouth;
	}
	
	public PanneauCentre getPanelCentre() {
		
		butDessin boutonCentre = new butDessin();
		
		pCentre = new PanneauCentre();
		//AJOUTE LE LISTENER AU PANNEAU DU CENTRE
		pCentre.addMouseListener(boutonCentre);
		
		pCentre.setBackground(Color.WHITE);
		
		return pCentre;
	}
	
	class ListCrayGom implements ActionListener {
		
		public void actionPerformed (ActionEvent e){
			if (butCrayon.isSelected() && butGomme.isSelected()) {
				
				//MESSAGE D'ERREUR QUI APPARAIT LORSQUE L'ON SÉLECTIONNE LA GOMME ET LE CRAYON
				JOptionPane.showMessageDialog(null, "Impossible de selectionner le crayon et la gomme en meme temps",
													"Erreur",
													JOptionPane.ERROR_MESSAGE);
				butGomme.setSelected(false);		//DESACTIVE AUTOMATIQUEMENT LE BOUTON GOMME LORSQUE LES DEUX BOUTONS SONT SELECTIONNES
			}	
		}
		
	}
	
	
	
	
	//ACTION-LISTENER POUR LE BOUTON QUITTER
	class ButQuitter implements ActionListener {
		
		public void actionPerformed (ActionEvent e){
			System.exit(0); //ferme losqu'activé
		}
	}
	
	
	//LISTENER DU MENU POUR LE "A PROPOS"
	class ButAbout implements MenuListener {
		
		public void menuSelected(MenuEvent e){
			
			//OUVRE UNE FENeTRE A PROPOS AVEC LE TEXTE "CREE PAR MATHIEU etc." DANS LE CAS OU LE MENU EST SELECTIONNE
			JOptionPane.showMessageDialog(null, "Cree par Mathieu Lecomte - IHM", "A propos", JOptionPane.PLAIN_MESSAGE);
		}		
		public void menuDeselected(MenuEvent e) {			
		}		
		public void menuCanceled(MenuEvent e) {			
		}		
	}
	
	
	class butDessin implements MouseListener {

		public void mouseClicked(MouseEvent e) {
		
			Graphics g = pCentre.getGraphics();			
			
			//INITIALISE UNE VARIABLE INT EN FONCTION DE LA TAILLE CHOISIE DANS LE JTEXTFIELD
			int tailleSel = 100;
			
			if (!choixTaille.getText().equals(""))
				tailleSel = Integer.parseInt(choixTaille.getText());
			
			
			//INITIALISE L'EMPLACEMENT DE LA SOURIS DANS 2 INT X ET Y
			int x = e.getX();
			int y = e.getY();
			
			//CREER UN CARRE OU UN ROND EN FONCTION DE CE QUI EST SELECTIONNE DANS LA COMBOBOX
			if(listeFormes.getSelectedItem().equals("Cercle")) {
				if (butCrayon.isSelected()) {
					if (listeCouleur.getSelectedItem().equals("Rouge")) {
						g.setColor(Color.RED);
						g.fillOval (x-tailleSel/2,y-tailleSel/2,tailleSel,tailleSel);
						Donnees.figures.add(new Dessin(tailleSel, "Cercle", Color.RED, new Point(x,y)));
					}
					else if (listeCouleur.getSelectedItem().equals("Vert")) {
						g.setColor(Color.GREEN);
						g.fillOval (x-tailleSel/2,y-tailleSel/2,tailleSel,tailleSel);
						Donnees.figures.add(new Dessin(tailleSel, "Cercle", Color.GREEN, new Point(x,y)));
					}
					else if (listeCouleur.getSelectedItem().equals("Bleu")) {
						g.setColor(Color.BLUE);
						g.fillOval (x-tailleSel/2,y-tailleSel/2,tailleSel,tailleSel);
						Donnees.figures.add(new Dessin(tailleSel, "Cercle", Color.BLUE, new Point(x,y)));
					}
					else if (listeCouleur.getSelectedItem().equals("Cyan")) {
						g.setColor(Color.CYAN);
						g.fillOval (x-tailleSel/2,y-tailleSel/2,tailleSel,tailleSel);
						Donnees.figures.add(new Dessin(tailleSel, "Cercle", Color.CYAN, new Point(x,y)));
					}
					else if (listeCouleur.getSelectedItem().equals("Jaune")) {
						g.setColor(Color.YELLOW);
						g.fillOval (x-tailleSel/2,y-tailleSel/2,tailleSel,tailleSel);
						Donnees.figures.add(new Dessin(tailleSel, "Cercle", Color.YELLOW, new Point(x,y)));
					}
					else if (listeCouleur.getSelectedItem().equals("Magenta")) {
						g.setColor(Color.MAGENTA);
						g.fillOval (x-tailleSel/2,y-tailleSel/2,tailleSel,tailleSel);
						Donnees.figures.add(new Dessin(tailleSel, "Cercle", Color.MAGENTA, new Point(x,y)));
					}
				}
				
				//DANS LE CAS OU LE BOUTON DE GOMME EST SELECTIONNE, LES RONDS SERONT DESSINES DE LA COULEUR DU FOND
				else if (butGomme.isSelected()) {
					g.setColor(pCentre.getBackground());
					g.fillOval (x-tailleSel/2,y-tailleSel/2,tailleSel,tailleSel);
					Donnees.figures.add(new Dessin(tailleSel, "Cercle", pCentre.getBackground(), new Point(x,y)));
				}
			}
			
			else if(listeFormes.getSelectedItem().equals("Carre")) {
				if (butCrayon.isSelected()) {
					if(listeCouleur.getSelectedItem().equals("Rouge")) {
						g.setColor(Color.RED);
						g.fillRect(x-tailleSel/2, y-tailleSel/2, tailleSel, tailleSel);
						Donnees.figures.add(new Dessin(tailleSel, "Carre", Color.RED, new Point(x,y)));
					}
					else if (listeCouleur.getSelectedItem().equals("Vert")) {
						g.setColor(Color.GREEN);
						g.fillRect(x-tailleSel/2, y-tailleSel/2, tailleSel, tailleSel);
						Donnees.figures.add(new Dessin(tailleSel, "Carre", Color.GREEN, new Point(x,y)));
					}
					else if (listeCouleur.getSelectedItem().equals("Bleu")) {
						g.setColor(Color.BLUE);
						g.fillRect(x-tailleSel/2, y-tailleSel/2, tailleSel, tailleSel);
						Donnees.figures.add(new Dessin(tailleSel, "Carre", Color.BLUE, new Point(x,y)));
					}
					else if (listeCouleur.getSelectedItem().equals("Cyan")) {
						g.setColor(Color.CYAN);
						g.fillRect(x-tailleSel/2, y-tailleSel/2, tailleSel, tailleSel);
						Donnees.figures.add(new Dessin(tailleSel, "Carre", Color.CYAN, new Point(x,y)));
					}
					else if (listeCouleur.getSelectedItem().equals("Jaune")) {
						g.setColor(Color.YELLOW);
						g.fillRect(x-tailleSel/2, y-tailleSel/2, tailleSel, tailleSel);
						Donnees.figures.add(new Dessin(tailleSel, "Carre", Color.YELLOW, new Point(x,y)));
					}
					else if (listeCouleur.getSelectedItem().equals("Magenta")) {
						g.setColor(Color.MAGENTA);
						g.fillRect(x-tailleSel/2, y-tailleSel/2, tailleSel, tailleSel);
						Donnees.figures.add(new Dessin(tailleSel, "Carre", Color.MAGENTA, new Point(x,y)));
					}
				}
				
				//DANS LE CAS OU LE BOUTON DE GOMME EST SELECTIONNE, LES CARRES SERONT DESSINES EN BLANCS
				else if (butGomme.isSelected()) {
					g.setColor(pCentre.getBackground());
					g.fillRect (x-tailleSel/2,y-tailleSel/2,tailleSel,tailleSel);
					Donnees.figures.add(new Dessin(tailleSel, "Carre", pCentre.getBackground(), new Point(x,y)));
				}
			}
			
						
			
		}

		public void mouseEntered(MouseEvent arg0) {	}

		public void mouseExited(MouseEvent arg0) { }

		public void mousePressed(MouseEvent arg0) {	}

		public void mouseReleased(MouseEvent arg0) { }
		
	}
	
	
	class butUndo implements ActionListener {						//CLASSE INTERNE POUR LE BOUTON "RETOUR EN ARRIERE"
		public void actionPerformed (ActionEvent e){
			
			Donnees.figures.remove(Donnees.figures.size()-1);
			pCentre.repaint();			
		}	
	}
	
	
	class butErase implements ActionListener {						//CLASSE INTERNE POUR LE BOUTON "TOUT EFFACER"
		public void actionPerformed (ActionEvent e){
			
			Donnees.figures.clear();
			pCentre.repaint();
		}
	}	
	
	
	class ButFond implements ActionListener {
		public void actionPerformed (ActionEvent e){
			
			Color couleur1;
			//Color couleur2;
			
			if((listeFond.getSelectedItem().equals("Blanc"))) {
				couleur1 = Color.WHITE;
			}	
			else /*if((listeFond.getSelectedItem().equals("Noir")))*/ {
				couleur1 = Color.BLACK;
			}	
			
			for (Dessin fig : Donnees.figures){
				if (fig.getCouleur().equals(pCentre.getBackground())){
					fig.setCouleur(couleur1);
				}
			}
			pCentre.setBackground(couleur1);
		}
	}
	
	
	public static void main (String arg[]) {
		
		new Paint();
	}
	
}

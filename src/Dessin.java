import java.awt.Color;
import java.awt.Point;


public class Dessin {
	
	private int taille;
	private String forme;
	private Color couleur;
	private Point position;
	
	
	public Dessin(int taille, String forme, Color couleur, Point position) {
		this.taille = taille;
		this.forme = forme;
		this.couleur = couleur;
		this.position = position;
	}
	
	public int getTaille() {
		return taille;
	}
	
	public void setTaille(int taille) {
		this.taille = taille;
	}
	public String getForme() {
		return forme;
	}
	public void setForme(String forme) {
		this.forme = forme;
	}
	public Color getCouleur() {
		return couleur;
	}
	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}
	
	public Point getPosition() {
		return this.position;
	}
	
	public void setPosition(Point position) {
		this.position = position;
	}
	
	
		
		
}

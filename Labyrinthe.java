// Christophe Gagnier et Jessica Gauvin
// Devoir 2
import javax.swing.*;
import java.awt.*;
import java.awt.event.*; 
import javax.swing.BoxLayout;

public class Labyrinthe {

	private int l, h, sortie, secondes, viesInitiales;
	private Personnage joueur;
	private ListeMuret liste;
	private boolean gameOver = false;
	private double densite;

	public double getDensite(){return densite;}
	public int getL(){ return l;}
	public int getH(){ return h;}
	public Personnage getJoueur(){ return joueur;}
	public int getSortie(){ return sortie;}
	public ListeMuret getListe(){return liste;}
	public int getSecondes(){return secondes;}


	public void setJoueurNord() { joueur.setY(joueur.y-1);}				//Set servant à déplacer le joueur pour l'intelligence artificielle
	public void setJoueurEst() { joueur.setX(joueur.x+1);}
	public void setJoueurSud() { joueur.setY(joueur.y+1);}
	public void setJoueurOuest() { joueur.setX(joueur.x-1);}

	public Labyrinthe(int largeur, int hauteur, double densite, int secondes, int vies){
		l = largeur;
		h = hauteur;
		this.secondes = secondes;
		this.densite = densite;
		viesInitiales = vies;

		// Position joueur 
		int positiony;
		positiony = (int) Math.ceil(Math.random() * hauteur-1);

		// Position de la sortie
		sortie = (int) Math.ceil(Math.random() * hauteur-1);

		joueur = new Personnage( 0.5 , positiony+0.5 ,vies);

		System.out.println("positionx: "+ joueur.x() + " positiony: "+ joueur.y()  + " sortie: "+ sortie);

		liste = new ListeMuret();
		// Position murets verticaux
		for (int x = 1;x<l ;x++) {
			for (int y=0;y<h ;y++) {
				if(Math.random() < densite) {
					Muret a = new Muret( x, y, false, false);
					liste.ajouteDebut(a);
				}
			}
		}
		// Position murets horizontaux
		for (int x = 0;x<l ;x++) {
			for (int y=1;y<h ;y++) {
				if(Math.random() < densite) {
					Muret a = new Muret( x, y, true, false);
					liste.ajouteDebut(a);
				}
			}
		}
		//liste.printMuret();
	}

	public String toString(){

		String muretH = "-------";
		String blank = "       ";
		String murjoueur = "   @   ";


		String out="";
		for (int y = 0;y<=h;y++) {
			for (int x = 0;x<l;x++) {
				if(y==0|| y==h){
					out+= ( ((x==0)?"+":"") + muretH + ((x==l-1)?"+":"-"));
				}
				else{

					Muret a = new Muret(x,y,true,true);
					boolean mur = a.equals(liste.chercherMuret(a));
					out+=(((x==0)?"|":"") + (mur?muretH:blank) +((x==l-1)?"|":" "));
				}
			}
			out+="\n";
			// Les case intérieur
			if(y !=h ){
				for (int v = 0;v<3 ;v++ ) {
					for (int xj = 0;xj<l;xj++) {

						// Est-ce qu'on doit afficher un joueur
						boolean caseJoueur = ((Math.floor(joueur.y()) == y) && (Math.floor(joueur.x())==xj) && (v == 1));

						// Est-ce qu'on doit afficher un mur
						Muret a = new Muret(xj,y,false,true);
						boolean murVertical = a.equals(liste.chercherMuret(a));
						String murVer = (murVertical?"|":" ");

						// Est-ce que la sortie est présente
						boolean positionSortie = (y == sortie);

						out+= ( ((xj==0)?"|":murVer) + (caseJoueur?murjoueur:blank) + (((xj==l-1) && (!positionSortie))?"|":""));
					}
					out+="\n";	
				}
			}
		}
		return out;

	}

	public boolean deplace(char direction){

		switch (Character.toLowerCase(direction)) {
			case 'd':
				return deplacementValide(joueur.x(),joueur.y(),(joueur.x()+1.0),joueur.y());
			case 'g': case 's':
				return deplacementValide(joueur.x(),joueur.y(),(joueur.x()-1.0),joueur.y());
			case 'h': case 'e':
				return deplacementValide(joueur.x(),joueur.y(),joueur.x(),(joueur.y()-1.0));
			case 'b': case 'x':
				return deplacementValide(joueur.x(),joueur.y(),joueur.x(),(joueur.y()+1.0));
			default: 
				break;
			
		}
		System.out.println("Mauvaise direction entrée, veuillez rééssayer.");
		return false;
	}
	public boolean deplacementValide(double joueurX, double joueurY, double x, double y){

		if((Math.floor(joueurY) == sortie)&& x > this.l){
			gameOver = true;
			gameOver();
			return true;
		}

		if (x < 0 || x>this.l ||y<0||y>this.h)
			return false;

		if(joueurY != y){ // Muret horizontal

			int posMuretY = (int)(((y - joueurY)<0)?Math.ceil(y):Math.floor(y));
			Muret a = new Muret((int)Math.floor(x),posMuretY,true,true);

			boolean bloquer = a.equals(liste.chercherMuret(a));

			if(bloquer){ // S'il y a mur, on l'affiche, puis on réduit les vies du joueur
				liste.chercherMuret(a).setVisible(true);
				joueur.setVie(joueur.getVie()-1);
				gameOver();
				return false;
			}
		}
		if(joueurX != x){ // On vérifie la présence muret vertical

			int posMuretX = (int)(((x - joueurX)<0)?Math.ceil(x):Math.floor(x));
			Muret a = new Muret(posMuretX,(int)Math.floor(y),false,true);

			boolean bloquer = a.equals(liste.chercherMuret(a));

			if(bloquer){ // S'il y a mur, on l'affiche, puis on réduit les vies du joueur
				liste.chercherMuret(a).setVisible(true);
				joueur.setVie(joueur.getVie()-1);
				gameOver();
				return false;
			}
		}



		// Si aucune des conditions n'as retourner false, on change position joueur et on le bouge
		joueur.setX(x);
		joueur.setY(y);
		return true;
	}

	public void gameOver(){
		if((joueur.getVie()<=0)|| gameOver){

			Object[] options = { "Quitter", "Rejouer"};

	        JPanel panel = new JPanel();
	        panel.add(new JLabel("Partie terminé, "+(gameOver?"Bravo, vous êtes parvenu jusqu'à la sortie en commettant seulement "+(viesInitiales-joueur.getVie())+" erreurs.":"Vous avez perdu, vous avez épuisé vos "+viesInitiales+" vies!")));

	        int result= JOptionPane.showOptionDialog(null, 
	        			panel, 
	        			"Game Over",
		                JOptionPane.YES_NO_CANCEL_OPTION, 
		                JOptionPane.PLAIN_MESSAGE,
		                null, 
		                options, 
		                options[0]);
	        if (result == JOptionPane.YES_OPTION){
	            System.exit(0);
	        }
	        else if(result == JOptionPane.NO_OPTION){
	        	//À faire
	        }

		}

	}



}
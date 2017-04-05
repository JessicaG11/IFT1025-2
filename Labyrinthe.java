public class Labyrinthe {

	private int l, h;
	private Personnage joueur;
	private ListeMuret liste;
	private int sortie;

	public Labyrinthe(int largeur, int hauteur, double densité, int secondes, int vies){
		l = largeur;
		h = hauteur;

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
				if(Math.random() < densité) {
					Muret a = new Muret(x,y,false,true);
					liste.ajouteDebut(a);
				}
			}
		}
		// Position murets horizontaux
		for (int x = 0;x<l ;x++) {
			for (int y=1;y<h ;y++) {
				if(Math.random() < densité) {
					Muret a = new Muret(x,y,true,true);
					liste.ajouteDebut(a);
				}
			}
		}

		//System.out.println("Longueur liste: " + liste.longueur());

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
		if (x < 0 || x>this.l ||y<0||y>this.h)
			return false;
		else{
			joueur.setX(x);
			joueur.setY(y);
			return true;
		}
	}



}
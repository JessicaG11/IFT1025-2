public class Labyrinthe {

	public int l, h; // L et H suffissent
	public Personnage joueur;
	public ListeMuret liste;
	public int sortie;		//Double

	public Labyrinthe(int largeur, int hauteur, double densité, int secondes, int vies){
		l = largeur;
		h = hauteur;

		// Position joueur 
		int positiony;
		positiony = (int) Math.ceil(Math.random() * hauteur-1);
		sortie = (int) Math.ceil(Math.random() * hauteur);

		joueur = new Personnage( 0.5 , positiony+0.5 ,vies);

		//System.out.println("positionx: "+ joueur.x + " positiony: "+ joueur.y  + " sortie: "+ sortie);

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
						boolean caseJoueur = ((Math.floor(joueur.y) == y) && (Math.floor(joueur.x)==xj) && (v == 1));

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
		//À faire
		return true;
	}


}
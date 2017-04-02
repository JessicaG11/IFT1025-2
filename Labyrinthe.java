public class Labyrinthe {

	public int l, h; // L et H suffissent
	public Personnage joueur;
	public ListeMuret liste;
	public double sortie;		//Double

	public Labyrinthe(int largeur, int hauteur, double densité, int secondes, int vies){
		l = largeur;
		h = hauteur;

		// Position joueur 
		int positionx, positiony;
		positionx = (int) Math.ceil(Math.random() * largeur);
		positiony = (int) Math.ceil(Math.random() * hauteur);

		joueur = new Personnage((double)positionx+0.5,(double)positiony+0.5,vies);

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
			for (int y=0;y<h-1 ;y++) {
				if(Math.random() < densité) {
					Muret a = new Muret(x,y,true,true);
					liste.ajouteDebut(a);
				}
			}
		}


		//À faire
	}

	public String toString(){
		//À faire
	}

	public boolean deplace(char direction){
		//À faire
	}


}
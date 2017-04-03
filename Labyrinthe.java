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

		System.out.println("Position x"+ ((double)positionx+0.5));

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
	}

	public String toString(){

		String muretH = "-------";
		String blank = "       ";


		String out="";
		for (int y = 0;y<=h;y++) {

			for (int x = 0;x<l;x++) {
				if(y==0|| y==h){
					out+= ( ((x==0)?"+":"") + muretH + ((x==l-1)?"+":"-"));
				}
				else{
					out+="";
				}
				
			}
			out+="\n";

			if(y !=h ){
				for (int v = 0;v<3 ;v++ ) {
					for (int xj = 0;xj<l;xj++) {
						out+= ( ((xj==0)?"|":"") + blank + ((xj==l-1)?"|":" "));	
					}
					out+="\n";	
				}
			}

			//out+="\n";
			
		}
		return out;

	}

	public boolean deplace(char direction){
		//À faire
		return true;
	}


}
// Christophe Gagnier et Jessica Gauvin
// Devoir 2
import javax.swing.*;
public class AI {
	private int[][] plan;
	private Labyrinthe lab;
	private int lar, haut;
	private AffichageLaby map;

	public AI(int l, int h, Labyrinthe lab, AffichageLaby map){

		this.lab = lab; 
		this.map = map;

		lar = (l*2)+1;
		haut = (h*2)+1;

		plan = new int[haut][lar];						//-1: mur

		for(int i=0; i<haut; i++){
			for(int j=0; j<lar; j++){
				plan[i][j] = 0;
			}
		}
		// On bloque les intersections
		for(int i=0; i<haut; i+=2){
			for(int j=0; j<lar; j+=2){
				plan[i][j] = -1;
			}
		}

		for( int i=0; i<lar; i++){							//initialise contour
			plan[0][i] = -1;
			plan[haut-1][i] = -1;
			
		}


		for( int i=0; i<haut; i++){
			plan[i][0] = -1;
			plan[i][lar-1] = -1;
		}

		ListeMuret liste = lab.getListe();
		NoeudMuret n = liste.premier;
			
		while(n != null ){										//initialise mur
			if(n.valeur().horizontal())
				plan[n.valeur().y()*2][(n.valeur().x()*2)+1] = -1;
			else { plan[(n.valeur().y()*2)+1][n.valeur().x()*2] = -1;}
			n = n.prochain();
		}

		plan[lab.getSortie()*2 +1][lar-1] = 1;
		
	}

		public boolean parcours(int x, int y){

		System.out.println("On est rendu en: "+x+","+y);
		System.out.println(toString());
		/*
		try {
		    Thread.sleep(1000);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}*/

		if (x < 0 || x>=lar ||(y<0)|| (y>=haut)) 		// Out of bounds
			return false;
		if(plan[y][x] == 1) 							// Si on a trouver sortie labyrinthe
			return true;
		if(plan[y][x] == -1 || plan[y][x] == 3)			// Est-ce que la position est bloqué, soit déja visité comme chemin, ou comme mur	
			return false;

		plan[y][x] = 3;									//on le note d'un 3 car la case est inclu (pour l'instant) dans notre chemin
														//fonction récursive pour vérifier le chemin
		if(parcours(x, (y-1)) == true)					// Chemin Nord
			return true;
		if(parcours(x+1, y) == true)					// Chemin est
			return true;
		if(parcours(x, (y+1)) == true)					// Chemin sud
			return true;
		if(parcours(x-1, y) == true)					// Chemin ouest
			return true;

		plan[y][x] = -1; 								//On le note -1 car aucun sens n'a fonctionné, sans issu
		return false;
	}

	public void bouge(){
		System.out.println("Début de bouge");

		int joueurX = (int)((lab.getJoueur().x)*2);
		int joueurY = (int)((lab.getJoueur().y)*2);

		System.out.println("Position: "+ joueurX + "," + joueurY);
		System.out.println(this.toString());

		if(!(parcours( joueurX , joueurY )))
			System.out.print("LOOSER");
		else{
			//while( (joueurY != lab.getSortie()*2) && (joueurX != lar-1) )
			deplacement(joueurX, joueurY);
		}
	}

	public void deplacement(int x, int y){
		System.out.println("Phase déplacement en : "+x+","+y);

		try {
		    Thread.sleep(1000);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}

		if( (y == lab.getSortie()*2+1) && (x == lar-1) )
			return;

		if((y-2 >0) && plan[y-2][x] == 3 && plan[y-1][x] == 3){
			plan[y-2][x] = 0;
			lab.deplace('h');
			map.repaint();
			try {
			    Thread.sleep(10000);
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}

			System.out.println(lab);

			deplacement(x, y-2);			
		}
		if((x+2 <lar) && plan[y][x+2] == 3 && plan[y][x+1] == 3){
			plan[y][x+2] = 0;
			plan[y][x+1] = 0;
			lab.deplace('d');
			map.repaint();
			try {
			    Thread.sleep(10000);
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
			System.out.println(lab);

			deplacement(x+2, y);
		}
		if((y+2 <haut) && plan[y+2][x] == 3 && plan[y+1][x] == 3){
			plan[y+2][x] = 0;
			lab.deplace('b');
			map.repaint();
			try {
			    Thread.sleep(10000);
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
			System.out.println(lab);

			deplacement(x, y+2);
		}
		if((x-2 >0) && plan[y][x-2] == 3 && plan[y][x-1] == 3){
			plan[y][x-2] = 0;
			lab.deplace('g');
			map.repaint();
			try {
			    Thread.sleep(10000);
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}

			System.out.println(lab);

			deplacement(x-2, y);
		}
	}

	public String toString(){

		String out = "";

		for (int i = 0; i<haut;i++) {
			String line = "";
			for (int j = 0;j<lar ;j++ ) {
				line+="|"+plan[i][j]+((plan[i][j]==-1)?"":" ");
			}
			out+=line+"|\n";
		}
		return out;
	}
}
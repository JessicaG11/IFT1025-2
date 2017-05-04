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

		for(int i=0; i<haut; i+=2){						// On bloque les intersections
			for(int j=0; j<lar; j+=2){
				plan[i][j] = -1;
			}
		}

		for( int i=0; i<lar; i++){						//initialise contour
			plan[0][i] = -1;
			plan[haut-1][i] = -1;
			
		}

		for( int i=0; i<haut; i++){
			plan[i][0] = -1;
			plan[i][lar-1] = -1;
		}

		ListeMuret liste = lab.getListe();
		NoeudMuret n = liste.premier;
			
		while(n != null ){								//initialise mur
			if(n.valeur().horizontal())
				plan[n.valeur().y()*2][(n.valeur().x()*2)+1] = -1;
			else { plan[(n.valeur().y()*2)+1][n.valeur().x()*2] = -1;}
			n = n.prochain();
		}

		plan[lab.getSortie()*2 +1][lar-1] = 1;
		
	}

		public boolean parcours(int x, int y){

		if (x < 0 || x>=lar ||(y<0)|| (y>=haut)) 		
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

	// Début de notre IA, va tenter un parcours, s'il n'en trouve pas, on affiche message erreur, sinon on déplace le joueur
	public void bouge(){

		int joueurX = (int)((lab.getJoueur().x)*2);
		int joueurY = (int)((lab.getJoueur().y)*2);

		if(!(parcours( joueurX , joueurY ))) // Si le chemin est impossible, on averti le joueur et lui propose de recommencer
			lab.aucuneSolution();
		else{
			deplacement(joueurX, joueurY);
		}
	}

	// Déplace le joueur sur la graphique en avec un temps d'attente entre les mouvements de 1 secondes
	public void deplacement(int x, int y){
		
		new java.util.Timer().schedule( 
        	new java.util.TimerTask() {
            	@Override
            	public void run() {
			
				if( (y == lab.getSortie()*2+1) && (x == lar-2) ){  	// Quand le joueur est rendu vis-à-vis la sortie, on le fin sortir et met fin à la fonction
					lab.deplace('d');
					return;
				}
				// Direction Nord
				if((y-2 >0) && plan[y-2][x] == 3 && plan[y-1][x] == 3){
					plan[y-2][x] = 0;
					plan[y-1][x] = 0;
					
					lab.deplace('h');
					map.repaint();

					deplacement(x, y-2);			
				}
				// Direction Est		
				if((x+2 <lar) && plan[y][x+2] == 3 && plan[y][x+1] == 3){
					plan[y][x+2] = 0;
					plan[y][x+1] = 0;

					lab.deplace('d');
					map.repaint();

					deplacement(x+2, y);
				}
				// Direction Sud
				if((y+2 <haut) && plan[y+2][x] == 3 && plan[y+1][x] == 3){
					plan[y+2][x] = 0;
					plan[y+1][x] = 0;
					
					lab.deplace('b');
					map.repaint();

					deplacement(x, y+2);
				}
				// Direction Ouest
				if((x-2 >0) && plan[y][x-2] == 3 && plan[y][x-1] == 3){
					plan[y][x-2] = 0;
					plan[y][x-1] = 0;
							
					lab.deplace('g');
					map.repaint();

					deplacement(x-2, y);
				}
		
            	}
        	} , 1000);
	}

	// Méthode utile pour debug, permet afficher le comportement de notre AI
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
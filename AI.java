// Christophe Gagnier et Jessica Gauvin
// Devoir 2
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

		for( int i=1; i<lar; i+=2){							//initialise contour
			plan[0][i] = -1;
			plan[haut-1][i] = -1;
			
		}


		for( int i=1; i<haut; i+=2){
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

		plan[lab.getSortie()*2][lar-1] = 1;
		
	}

		public boolean parcours(int x, int y){
		if (x < 0 || x>=lar ||y<0||y>=haut)
			return false;
		if(plan[y][x] == -1)		
			return false;
		if(plan[y][x] == 1)
			return true;
		plan[y][x] = 3;									//on le note d'un 3 car la case est inclu (pour l'instant) dans notre chemin
		if(parcours(x, y-1) == true)					//fonction récursive pour vérifier le chemin
			return true;
		if(parcours(x+1, y) == true)
			return true;
		if(parcours(x, y+1) == true)
			return true;
		if(parcours(x-1, y) == true)
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
			while( (joueurY != lab.getSortie()*2) && (joueurX != lar-1) )
				deplacement(joueurX, joueurY);
		}
	}

	public void deplacement(int x, int y){
		if(plan[y-1][x] == 3){
			plan[y-1][x] = 0;
			lab.setJoueurNord();
			deplacement(x, y-2);
			map.repaint();
			
		}
		if(plan[y][x+1] == 3){
			plan[y][x+1] = 0;
			lab.setJoueurEst();
			deplacement(x+2, y);
			map.repaint();
		}
		if(plan[y+1][x] == 3){
			plan[y+1][x] = 0;
			lab.setJoueurSud();
			deplacement(x, y+2);
			map.repaint();
		}
		if(plan[y][x-1] == 3){
			plan[y][x-1] = 0;
			lab.setJoueurOuest();
			deplacement(x-2, y);
			map.repaint();
		}
	}

	public String toString(){

		String out = "";

		for (int i = 0; i<haut;i++) {
			String line = "";
			for (int j = 0;j<lar ;j++ ) {
				line+=plan[i][j]+"";
			}
			out+=line+"\n";
		}
		return out;
	}
}
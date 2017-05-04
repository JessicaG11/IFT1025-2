// Christophe Gagnier et Jessica Gauvin
// Devoir 2
public class Muret{

	private int x, y;
	private boolean horizontal, visible;

	// Le constructeur pour initialiser nos paramètres
	public Muret(int x, int y, boolean horizontal, boolean visible){
		this.x = x;
		this.y = y;
		this.horizontal = horizontal;
		this.visible = visible;
	}

	// Get
	public int x(){return this.x;}
	public int y(){return this.y;}
	public boolean horizontal(){return this.horizontal;}
	public boolean visible(){return this.visible;}

	// Compare deux objets de type Muret pour vérifier s'ils sont pareils
	public boolean equals(Muret leMuret){
		if (leMuret == null)
			return false;
		if(leMuret.x() == this.x && leMuret.y() == this.y && leMuret.horizontal() == this.horizontal)
			return true;
		return false;
	}
	// Set
	public void setVisible(boolean etat) {this.visible = etat;}
}



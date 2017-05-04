// Christophe Gagnier et Jessica Gauvin
// Devoir 2
public class Personnage{

	public double x, y;
	private int vie;

	// Notre constructeur qui initialise nos parametres
	public Personnage(double x,double y, int vie){
		this.x = x;
		this.y = y;
		this.vie = vie;
	}

	// Get
	public double x(){return this.x;}	
	public double y(){return this.y;}
	public int getVie(){return this.vie; }
	
	// Set
	public void setX(double x){this.x = x;}	
	public void setY(double y){this.y = y;}	
	public void setVie(int vie){this.vie = vie;}

}
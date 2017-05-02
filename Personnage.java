// Christophe Gagnier et Jessica Gauvin
// Devoir 2
public class Personnage{

	public double x, y;
	private int vie;

	public Personnage(double x,double y, int vie){
		this.x = x;
		this.y = y;
		this.vie = vie;
	}

	public double x(){return this.x;}	
	public double y(){return this.y;}
	public void setX(double x){this.x = x;}	
	public void setY(double y){this.y = y;}	
	public void setVie(int vie){this.vie = vie;}
	public int getVie(){return this.vie; }

	//public void dessine(Graphics g, int x1, int y1, int x2, int y2){
		//À faire
	//}
}
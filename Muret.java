public class Muret{

	int x, y;
	boolean horizontal, visible;

	public Muret(int x, int y, boolean horizontal, boolean visible){
		this.x = x;
		this.y = y;
		this.horizontal = horizontal;
		this.visible = visible;
	}

	public boolean equals(Muret leMuret){
		if (leMuret == null)
			return false;
		if(leMuret.x == this.x && leMuret.y == this.y && leMuret.horizontal == this.horizontal)
			return true;
		return false;
	}

	public void setVisible(boolean etat) {
		this.visible = etat;
	}

	public String toString(){
		return ("x: "+x+ " y: " + y + " horizontal: " + horizontal+ " visible: " + visible);
	}

}
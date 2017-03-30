public class Muret{

	private int x, y;
	private boolean horizontal, visible;

	public Muret(int x, int y, boolean horizontal, boolean visible){
		this.x = x;
		this.y = y;
		this.horizontal = horizontal;
		this.visible = visible;
	}

	public boolean equals(Muret leMuret){
		if(leMuret.x == this.x && leMuret.y == this.y && leMuret.horizontal == this.horizontal)
			return true;
		return false;
	}

	//Méthodes pour rendre le mur visible/invisible
}
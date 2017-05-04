// Christophe Gagnier et Jessica Gauvin
// Devoir 2
public class ListeMuret{

	public NoeudMuret premier;

	// Je sais pas si c'est nécessaire
	public ListeMuret() {
		premier = null;
	}
	// Ajouter un muret au début de la liste
	public void ajouteDebut(Muret v){
		premier = new NoeudMuret(v,premier);
	}
	// Set la visibilité de nos murets
	public void setVisibleMurets(boolean etat){ // True pour visible false pour invisible
		NoeudMuret n = premier;
		while(n!= null){
			n.valeur().setVisible(etat);
			n = n.prochain();
		}
	}

	// Cherche à traver notre liste pour un muret
	public Muret chercherMuret(Muret m) {
		NoeudMuret n = premier;
		while (n != null && !(n.valeur().equals(m))){
			n = n.prochain();
		}
		return ((n == null)?(null):(n.valeur()));
	}

 	// Nous retourne la longueur de notre liste
	public int longueur(){
		NoeudMuret n = premier;
		int nb=0;
		if(premier==null) 
			return 0;
		while(n!=null){
			nb++;
			n=n.prochain();
		}
		return nb;
	}

	// Fonction pour debug qui affiche les différents murets
	public void printMuret() {
		NoeudMuret n = premier;
		if(premier == null)
			System.out.println("Vide");
		else{
			while (n != null){
				System.out.println("x: " + n.valeur().x() + " y: "+ n.valeur().y() + " horizontal: " + n.valeur().horizontal());
				n = n.prochain(); 
			}
		}
	}


}
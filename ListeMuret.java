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
			n.valeur.setVisible(etat);
			n = n.prochain;
		}
	}
	// Cherche à traver notre liste pour un muret
	public Muret chercherMuret(Muret m) {
		NoeudMuret n = premier;
		while (n != null && n.valeur != m)
			n = n.prochain; 
		return n;
	}

}
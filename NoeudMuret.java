// Christophe Gagnier et Jessica Gauvin
// Devoir 2
public class NoeudMuret{
	private Muret valeur;
	private NoeudMuret prochain;

	// Constructeur qui initialise notre objet
	public NoeudMuret(Muret v, NoeudMuret p){
		valeur = v;
		prochain = p;
	}

	public Muret valeur(){return this.valeur;}	
	public NoeudMuret prochain(){return this.prochain;}	
}
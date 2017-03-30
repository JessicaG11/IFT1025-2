public class Laby {

	public static void main(String[] args) {
		if(args.length != 5) {
			System.out.println("Nombre de paramètres incorrects.");
			System.out.println("Utilisation: java Laby <hauteur> <largeur> <densite> <duree visible> <nb vies>");
			System.out.println("Ex: java Laby 10 20 0.20 10 5");
		}
	}
}


import java.util.Scanner;
public class JeuLaby{

	private static boolean gameOver = false;
	private static boolean hasMoved = false;

	public static void main(String[] args) {

		if(args.length != 5) {
			System.out.println("Nombre de paramètres incorrects.");
			System.out.println("Utilisation: java Laby <hauteur> <largeur> <densite> <duree visible> <nb vies>");
			System.out.println("Ex: java jeuLaby 10 20 0.20 10 5");
			System.exit(0);
		}

		Scanner scan = new Scanner(System.in);

		Labyrinthe jeu = new Labyrinthe(Integer.parseInt(args[0]),Integer.parseInt(args[1]),Double.parseDouble(args[2]),Integer.parseInt(args[3]),Integer.parseInt(args[4]));

		System.out.println(jeu.toString());

		while(!gameOver){

			hasMoved = false;

			while(!hasMoved){
				System.out.println("Quelle direction voulez-vous allez? ");
				String reponse = scan.nextLine();
				if(reponse.length() == 1){
					hasMoved = jeu.deplace(reponse.charAt(0));

				}
				else
					System.out.println("Déplacement non valide!");
			}


			System.out.println(jeu.toString());


		}
	}
	
}
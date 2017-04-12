import java.util.Scanner;
import java.awt.*;
import javax.swing.*;
import javax.swing.BoxLayout;

public class JeuLaby{

	private static boolean gameOver = false;
	private static boolean hasMoved = false;
	private JFrame frame;
	
	

	public JeuLaby(Labyrinthe jeu) {
		frame = new JFrame();
		frame.setSize(300, 400);
		frame.setTitle("Le jeu du Labyrinthe");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		JPanelLAby jp = new JPanelLAby(jeu);
		frame.add(jp);
		frame.pack();
		frame.setMinimumSize(new Dimension(1400, 800));
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		
		
		


		if(args.length != 5) {
			System.out.println("Nombre de paramètres incorrects.");
			System.out.println("Utilisation: java Laby <hauteur> <largeur> <densite> <duree visible> <nb vies>");
			System.out.println("Ex: java jeuLaby 10 20 0.20 10 5");
			System.exit(0);
		}

		Scanner scan = new Scanner(System.in);

		Labyrinthe jeu = new Labyrinthe(Integer.parseInt(args[0]),Integer.parseInt(args[1]),Double.parseDouble(args[2]),Integer.parseInt(args[3]),Integer.parseInt(args[4]));
		
		JeuLaby lol = new JeuLaby(jeu);

		
		//System.out.println(jeu.toString());

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

			//System.out.println(jeu.toString());
			// Caller repaint
		}
	}
	
}
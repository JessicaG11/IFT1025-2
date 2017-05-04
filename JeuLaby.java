// Christophe Gagnier et Jessica Gauvin
// Devoir 2
import java.util.Scanner;
import java.awt.*;
import javax.swing.*;
import javax.swing.BoxLayout;
import javax.swing.Timer;

public class JeuLaby{

	private static boolean gameOver = false;
	private static boolean hasMoved = false;
	public JFrame frame;
	private static JPanelLAby jp;
	public int l,h,vie;


	public JeuLaby(Labyrinthe jeu) {
		frame = new JFrame();
		frame.setSize(300, 400);
		frame.setTitle("Le jeu du Labyrinthe");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		jp = new JPanelLAby(jeu);
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

		Labyrinthe jeu = new Labyrinthe(Integer.parseInt(args[0]),Integer.parseInt(args[1]),Double.parseDouble(args[2]),Integer.parseInt(args[3]),Integer.parseInt(args[4]));
		
		// Version Swing 
		JeuLaby lol = new JeuLaby(jeu);

		// Version console
		Scanner scan = new Scanner(System.in);

		// Tant que le jeu n'est pas terminé, on demande des directions
		while(!gameOver){

			hasMoved = false;
			System.out.println(jeu);

			// Tant que notre joueur n'a pas bouger, on redemande dans quel direction il veut aller
			while(!hasMoved){
				System.out.println("Quelle direction voulez-vous allez? ");
				String reponse = scan.nextLine();
				if(reponse.length() == 1){
					hasMoved = jeu.deplace(reponse.charAt(0));
					jp.getPanel().repaint();
					jp.getLabelVie().setText("Nombre de vies restantes : "+jeu.getJoueur().getVie());
					jp.getAffichage().repaint();
				}
				else
					System.out.println("Déplacement non valide!");
			}

		}
	}
	
}
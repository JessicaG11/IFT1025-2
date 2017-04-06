import javax.swing.*;
import java.awt.*;
import java.awt.event.*; 

public class JPanelLAby extends JPanel{
	private AffichageLaby map;
	public JPanel panel;

	public JPanel getPanel(){ return panel;}

	public JPanelLAby(Labyrinthe jeu){
		panel = new JPanel (new BorderLayout ());
		setLayout(new BorderLayout());
		
		this.map = new AffichageLaby(jeu);

		JButton b = new JButton("Nord"); 
		JButton c = new JButton("SUD");
		JButton d = new JButton("ouest");
		JButton e = new JButton("est");

		add( b, BorderLayout.NORTH);
		add( map, BorderLayout.CENTER);
		add( c, BorderLayout.SOUTH);
		add( d, BorderLayout.WEST);
		add( e, BorderLayout.EAST);

		panel.setVisible(true);
	}



	//Bouton qu'on veut à gauche
	public JPanel mouvement(){
		JPanel mouvement = new JPanel(new BorderLayout());
		setLayout(new BorderLayout());

		JButton gauche = new JButton("Gauche");
		JButton droite = new JButton("Droite");
		JButton bas = new JButton("Bas");
		JButton haut = new JButton("Haut");

		mouvement.add( gauche, BorderLayout.WEST);
		mouvement.add( droite, BorderLayout.EAST);
		mouvement.add( bas, BorderLayout.SOUTH);
		mouvement.add( haut, BorderLayout.NORTH);

		mouvement.setVisible(true);


		return mouvement;
	}

	//Bouton qu'on veut à droite 
	public JPanel autres(){
		JPanel autres = new JPanel(new BorderLayout());
		setLayout(new BorderLayout());

		JButton gauche = new JButton("Murs visibles");
		JButton droite = new JButton("Murs invisible");
		JButton bas = new JButton("Intelligence artificielle");
		JButton haut = new JButton("Nouvelle partie");

		autres.add( gauche, BorderLayout.WEST);
		autres.add( droite, BorderLayout.EAST);
		autres.add( bas, BorderLayout.SOUTH);
		autres.add( haut, BorderLayout.NORTH);

		autres.setVisible(true);


		return autres;

	}
}

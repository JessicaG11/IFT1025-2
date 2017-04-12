import javax.swing.*;
import java.awt.*;
import java.awt.event.*; 
import javax.swing.BoxLayout;

public class JPanelLAby extends JPanel{
	
	private AffichageLaby map;
	

	public JPanelLAby(Labyrinthe jeu){						//On créé le Jpanel principal
		JPanel panel = new JPanel (new BorderLayout ());
		setLayout(new BorderLayout());
		
		this.map = new AffichageLaby(jeu);

		JButton b = new JButton("NORTH");
		b.setPreferredSize(new Dimension(80, 80));
		JButton c = new JButton("SUD");
		c.setPreferredSize(new Dimension(80, 80));

		panel.add( b, BorderLayout.NORTH);							//
		panel.add( map, BorderLayout.CENTER);						//On place le jeu au centre
		panel.add( c, BorderLayout.SOUTH);							//On place le nombre de vie dans le bas
		panel.add( mouvement(), BorderLayout.EAST);					//On place les boutons pour le déplacement à droite
		panel.add( autres(panel), BorderLayout.WEST);				//On place les boutons pour les murs à gauche

		add(panel);

		panel.setVisible(true); 
	}

	protected ImageIcon createImageIcon(String path, int longueur, int hauteur) {			//Créer et redimensionner un image
   	 	java.net.URL imgURL = getClass().getResource(path);
    	if (imgURL != null) {
    		ImageIcon imageIcon = new ImageIcon(imgURL); 
			Image image = imageIcon.getImage();  
			Image nouvelle = image.getScaledInstance( longueur, hauteur,  java.awt.Image.SCALE_SMOOTH);  
			return (new ImageIcon(nouvelle));  
    	}
    	else { return null; }
	}

	


	//Le panel avec les boutons de déplacements
	public JPanel mouvement(){
		JPanel mouvement = new JPanel(new BorderLayout());
		setLayout(new BorderLayout());

		JButton gauche = new JButton(createImageIcon("gauche.png", 90, 140));
		gauche.setPreferredSize(new Dimension(150, 200));
		JButton droite = new JButton(createImageIcon("droite.png", 100, 200));
		droite.setPreferredSize(new Dimension(150, 200));
		JButton bas = new JButton(createImageIcon("bas.png", 100, 100));
		bas.setPreferredSize(new Dimension(200, 200));
		JButton haut = new JButton(createImageIcon("haut.png", 100, 100) );
		haut.setPreferredSize(new Dimension(200, 200));

		mouvement.add( gauche, BorderLayout.WEST);
		mouvement.add( droite, BorderLayout.EAST);
		mouvement.add( bas, BorderLayout.SOUTH);
		mouvement.add( haut, BorderLayout.NORTH);

		gauche.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               System.out.println("gauche");
            }
        });

        droite.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("droite");
            }
        });

        bas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("bas");
            }
        });

        haut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("haut");
            }
        });

		mouvement.setVisible(true);

		return mouvement;
	}

	//Le panel avec les boutons pour les murs
	public  JPanel autres(JPanel panel){
		JPanel autres = new JPanel();

		autres.setLayout(new GridLayout( 4, 1));

		JButton visibles = new JButton("Murs visibles");
		JButton invisibles = new JButton("Murs invisible");
		JButton intelligence = new JButton("Intelligence artificielle");
		JButton nouvelle = new JButton("Nouvelle partie");

		autres.add( visibles, BorderLayout.WEST);
		autres.add( invisibles, BorderLayout.EAST);
		autres.add( intelligence, BorderLayout.SOUTH);
		autres.add( nouvelle, BorderLayout.NORTH);

		visibles.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               System.out.println("visibles");
            }
        });

        invisibles.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("invisibles");
            }
        });

        intelligence.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("intelligence");
            }
        });

        nouvelle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("nouvelle partie");
            }
        });

		autres.setVisible(true);

		return autres;

	}

}

import javax.swing.*;
import java.awt.*;

public class AffichageLaby extends JComponent{
	public Labyrinthe laby;

	public AffichageLaby(Labyrinthe jeu){
		this.setPreferredSize(new Dimension(1000, 500));	
		laby = jeu;
	}

	public void paintComponent(Graphics g){
		
		//Les dimensions
		int casex = getWidth()/laby.getL(); 
		int casey = getHeight()/laby.getH();
		
		Graphics2D g2 = (Graphics2D) g;

		//Le fond blanc
		fond(g2);
		
		//Le contour verticale
		contourVertical(g2);
		
		//La sortie 
		sortie(g2, casex, casey);

		//Le contour horizontal
		contourHorizontal(g2);
		
		//Le joueur 
		personne(g2, casex, casey);

		//Les murs
		mursVisibles(g2, casex, casey);
	}


	public void fond(Graphics2D g2){										//Dessiner le fond blanc
		Rectangle box = new Rectangle(0, 0, getWidth() , getHeight());
		g2.setColor(Color.white);
		g2.draw(box);
		g2.fill(box);
	}

	public void contourVertical(Graphics2D g2){								//Dessiner le contour vertical du labyrinthe
		g2.setStroke(new BasicStroke(10));
		g2.setColor(Color.black);
		g2.drawLine( getWidth(), 0, getWidth(), getHeight());
		g2.drawLine( 0, 0, 0, getHeight());
	}

	public void sortie(Graphics2D g2, int x, int y){						//Dessiner la sortie du labyrinthe
		g2.setColor(Color.white);
		g2.drawLine( getWidth(), (int)Math.round(y*(laby.getSortie()+1)), getWidth(), (int)Math.round(y*(laby.getSortie()+1)-y));
	}

	public void contourHorizontal(Graphics2D g2){							//Dessiner le contour horixontal du labyrinthe
		g2.setColor(Color.black);
		g2.drawLine( 0, 0, getWidth(), 0);
		g2.drawLine( 0, getHeight(), getWidth(), getHeight());
	}

	public void personne(Graphics2D g2, int x, int y){						//Dessiner le joueur
		Personnage joueur = laby.getJoueur();
		g2.setColor(Color.red);
		g2.fillOval( (int)Math.round(joueur.x*x), (int)Math.round(joueur.y*y), getWidth()/75, getWidth()/75);
	}

	public void mursTous(Graphics2D g2, int x, int y){						//Dessiner tous les murs
		ListeMuret liste = laby.getListe();
		g2.setColor(Color.black);

		NoeudMuret n = liste.premier;
			
		while(n != null ){
			if(n.valeur().horizontal())
				g2.drawLine( n.valeur().x()*x, n.valeur().y()*y, n.valeur().x()*x+x, n.valeur().y()*y);
			else { g2.drawLine( n.valeur().x()*x, n.valeur().y()*y, n.valeur().x()*x, n.valeur().y()*y+y);}
			n = n.prochain();
		}
	}	

	public void mursVisibles(Graphics2D g2, int x, int y){			//Dessiner les murs visibles
		ListeMuret liste = laby.getListe();
		g2.setColor(Color.black);

		NoeudMuret n = liste.premier;
			
		while(n != null ){
			if(n.valeur().visible()) {
				if(n.valeur().horizontal())
					g2.drawLine( n.valeur().x()*x, n.valeur().y()*y, n.valeur().x()*x+x, n.valeur().y()*y);
				else { g2.drawLine( n.valeur().x()*x, n.valeur().y()*y, n.valeur().x()*x, n.valeur().y()*y+y);}
			}
			n = n.prochain();
		}
	}	
}

import javax.swing.*;
import java.awt.*;

public class AffichageLaby extends JComponent{
	public Labyrinthe laby;

	


	public AffichageLaby(Labyrinthe jeu){
		this.setPreferredSize(new Dimension(1500, 700));	
		laby = jeu;
	}

	public void paintComponent(Graphics g){
		
		//Les dimensions
		int casex = getWidth()/laby.getL(); 
		int casey = getHeight()/laby.getH();
		
		Graphics2D g2 = (Graphics2D) g;

		//Le fond blanc
		Rectangle box = new Rectangle(0, 0, getWidth() , getHeight());
		g2.setColor(Color.white);
		g2.draw(box);
		g2.fill(box);

		//Le contour verticale
		g2.setStroke(new BasicStroke(10));
		g2.setColor(Color.black);
		g2.drawLine( getWidth(), 0, getWidth(), getHeight());
		g2.drawLine( 0, 0, 0, getHeight());

		//La sortie 
		g2.setColor(Color.white);
		g2.drawLine( getWidth(), (int)Math.round(casey*(laby.getSortie()+1)), getWidth(), (int)Math.round(casey*(laby.getSortie()+1)-casey));	//Plus juste si on avait pas à arrondir... (pas parfait quand beaucoup de case)
		
		//Le contour horizontale
		g2.setColor(Color.black);
		g2.drawLine( 0, 0, getWidth(), 0);
		g2.drawLine( 0, getHeight(), getWidth(), getHeight());

		//Le joueur 
		Personnage joueur = laby.getJoueur();
		g2.setColor(Color.red);
		g2.fillOval( (int)Math.round(joueur.x*casex), (int)Math.round(joueur.y*casey), getWidth()/75, getWidth()/75);

		//Les murs

	}


	
}
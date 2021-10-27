package logica;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
/**
 * 
 * @author Yeferson Benavides - Yennyfer Aviles - Maria Isabel Mosquera
 *
 */
public class Resorte extends JPanel{
	
	private Image imgResorte;
	
	public Resorte() {
		preInit();
	}
		
	public Image getResorte() {
		return imgResorte;
	}

	public void setResorte(Image prmResorte) {
		this.imgResorte = prmResorte;
	}

	public void preInit() {
		try {
			imgResorte = ImageIO.read(getClass().getClassLoader().getResource("resorte-2.png"));
		} catch (IOException e) {
			System.out.println("La imagen no se encuentra en la ubicación indicada.");
		}
	}
	
	@Override
	public void paint(Graphics g) {
		g.drawImage(imgResorte, 0, 0, getWidth(), getHeight(), this);
	}
}

package logica;

import java.awt.Color;
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
public class Bloque extends JPanel{

	/* Atributos */
	private int atrAncho;
	private int atrLargo;
	private Image imgBloque;

	/* Constructor */
	public Bloque() {
		atrAncho = 150;
		atrLargo = 150;
		setSize(atrAncho, atrLargo);
		
		try {
			imgBloque = ImageIO.read(getClass().getClassLoader().getResource("bloque.png"));
		} catch (IOException e) {
			System.out.println("La imagen no se encuentra en la ubicación indicada.");
		}
	}
	
	public Bloque(int prmAncho, int prmLargo, Color prmColor) {
		super();
		this.atrAncho = prmAncho;
		this.atrLargo = prmLargo;
		setSize(prmAncho, prmLargo);
		setBackground(prmColor);
	}

	public int getAtrAncho() {
		return atrAncho;
	}

	public void setAtrAncho(int prmAncho) {
		this.atrAncho = prmAncho;
	}

	public int getAtrLargo() {
		return atrLargo;
	}

	public void setAtrLargo(int prmLargo) {
		this.atrLargo = prmLargo;
	}
	
	@Override
	public void paint(Graphics g) {
		g.drawImage(imgBloque, 0, 0, getWidth(), getHeight(), this);
	}
	
	
}

package logica;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JPanel;
/**
 * 
 * @author Yeferson Benavides - Yennyfer Aviles - Maria Isabel Mosquera
 *
 */
public class Plano extends JPanel{
	
	private String atrTitulo;
	private String atrMensajeX;
	private String atrMensajeY;
	private String atrPosMax;
	private String atrVelMax;
	private int posMsjPosMax;
	private int posMsjVelMax;
	private String phi;
	private String periodo;
	private String omega;
	private String ecPosicion;
	private String ecVelocidad;
	
	// Dos coordenadas para graficar una linea
    private double atrX1;
    private double atrY1;
    private double atrX2;
    private double atrY2;
    
    // Representa el tamaño de las gráficas
    private int atrEscalaX = 100;
    private int atrEscalaY = 100;
    
    // Para guardar cada par de coordenadas
    private ArrayList<Double> Dx1;
    private ArrayList<Double> Dx2;
    private ArrayList<Double> Dy1;
    private ArrayList<Double> Dy2;
	
    private Color atrColorOnda;
    
	public Plano() {
		atrTitulo = "";
		atrMensajeX = "t";
		atrMensajeY = "";
		atrPosMax = "";
		atrVelMax = "";
		posMsjPosMax = 0;
		phi = "";
		periodo = "";
		omega = "";
		ecPosicion = "";
		ecVelocidad = "";
		
		Dx1 = new ArrayList<Double>();
        Dx2 = new ArrayList<Double>();
        Dy1 = new ArrayList<Double>();
        Dy2 = new ArrayList<Double>();
        
		setBackground(new Color(4, 62, 80));
		setLayout(null);
	}
	
	/*Getters y Setters*/
	public String getAtrTitulo() {
		return atrTitulo;
	}

	public void setAtrTitulo(String atrTitulo) {
		this.atrTitulo = atrTitulo;
	}

	public String getAtrMensajeX() {
		return atrMensajeX;
	}

	public void setAtrMensajeX(String atrMensajeX) {
		this.atrMensajeX = atrMensajeX;
	}

	public String getAtrMensajeY() {
		return atrMensajeY;
	}

	public void setAtrMensajeY(String atrMensajeY) {
		this.atrMensajeY = atrMensajeY;
	}

	public String getAtrPosMax() {
		return atrPosMax;
	}

	public void setAtrPosMax(String atrPosMax) {
		this.atrPosMax = atrPosMax;
	}

	public String getAtrVelMax() {
		return atrVelMax;
	}

	public void setAtrVelMax(String atrVelMax) {
		this.atrVelMax = atrVelMax;
	}

	public int getPosMsjPosMax() {
		return posMsjPosMax;
	}

	public void setPosMsjPosMax(int posMsjPosMax) {
		this.posMsjPosMax = posMsjPosMax;
	}

	public int getPosMsjVelMax() {
		return posMsjVelMax;
	}

	public void setPosMsjVelMax(int posMsjVelMax) {
		this.posMsjVelMax = posMsjVelMax;
	}

	public String getPhi() {
		return phi;
	}

	public void setPhi(String phi) {
		this.phi = phi;
	}

	public String getPeriodo() {
		return periodo;
	}

	public double getAtrX1() {
		return atrX1;
	}

	public void setAtrX1(double atrX1) {
		this.atrX1 = atrX1;
	}

	public double getAtrY1() {
		return atrY1;
	}

	public void setAtrY1(double atrY1) {
		this.atrY1 = atrY1;
	}

	public double getAtrX2() {
		return atrX2;
	}

	public void setAtrX2(double atrX2) {
		this.atrX2 = atrX2;
	}

	public double getAtrY2() {
		return atrY2;
	}

	public void setAtrY2(double atrY2) {
		this.atrY2 = atrY2;
	}

	public ArrayList<Double> getDx1() {
		return Dx1;
	}

	public void setDx1(ArrayList<Double> dx1) {
		Dx1 = dx1;
	}

	public ArrayList<Double> getDx2() {
		return Dx2;
	}

	public void setDx2(ArrayList<Double> dx2) {
		Dx2 = dx2;
	}

	public ArrayList<Double> getDy1() {
		return Dy1;
	}

	public void setDy1(ArrayList<Double> dy1) {
		Dy1 = dy1;
	}

	public ArrayList<Double> getDy2() {
		return Dy2;
	}

	public void setDy2(ArrayList<Double> dy2) {
		Dy2 = dy2;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	
	public String getOmega() {
		return omega;
	}
	
	public void setOmega(String omega) {
		this.omega = omega;
	}

	public String getEcPosicion() {
		return ecPosicion;
	}

	public void setEcPosicion(String ecPosicion) {
		this.ecPosicion = ecPosicion;
	}

	public String getEcVelocidad() {
		return ecVelocidad;
	}

	public void setEcVelocidad(String ecVelocidad) {
		this.ecVelocidad = ecVelocidad;
	}

	public int getAtrEscalaX() {
		return atrEscalaX;
	}
	
	public void setAtrEscalaX(int prmEscala) {
		this.atrEscalaX = prmEscala;
	}
	
	public int getAtrEscalaY() {
		return atrEscalaY;
	}
	
	public void setAtrEscalaY(int prmEscala) {
		this.atrEscalaY = prmEscala;
	}
	
	public Color getAtrColorOnda() {
		return atrColorOnda;
	}

	public void setAtrColorOnda(Color atrColorOnda) {
		this.atrColorOnda = atrColorOnda;
	}
	
	public void paintComponent( Graphics g ) {
         super.paintComponent(g);
         
         // Dibujando plano cartesiano
         g.setColor(Color.white);
         g.drawString(atrTitulo, this.getWidth()/2, 20);
         g.drawLine(0, this.getHeight()/2, this.getWidth(), this.getHeight()/2);
         g.drawString(atrMensajeX, this.getWidth()-20, (this.getHeight()/2)+20);
         g.drawLine((this.getWidth()/5), 0,this.getWidth()/5 , this.getHeight());
         g.drawString(atrMensajeY, (this.getWidth()/5)-50, 20);
        
         // Mostrando datos de posición
         if(atrPosMax != "") {
        	 g.setFont(new Font("Verdana", Font.BOLD, 12));          	 
        	 g.drawString(atrPosMax, (this.getWidth()/5)-50, (int)((this.getHeight()/2)-posMsjPosMax));
        	 g.drawString("-"+atrPosMax, (this.getWidth()/5)-55, (int)((this.getHeight()/2)+posMsjPosMax+8));
        	 g.drawString("\u03A6= "+phi + " rad", (this.getWidth()/4)+40, this.getHeight()-20);
        	 g.drawString("T = " + periodo + " s", (this.getWidth()/3)+110, this.getHeight()-20);
        	 g.drawString("\u03C9 = "+ omega + " rad/s", (this.getWidth()/2)+130, this.getHeight()-20);
        	 g.drawString("X(t) = " + ecPosicion, (this.getWidth()/2)-40, 38);
         }
         
         // Mostrando datos de velocidad
         if(atrVelMax != "") {
        	 g.setFont(new Font("Verdana", Font.BOLD, 12));        	 
        	 g.drawString(atrVelMax, (this.getWidth()/5)-50, (int)((this.getHeight()/2)-posMsjVelMax));
        	 g.drawString("-"+atrVelMax, (this.getWidth()/5)-55, (int)((this.getHeight()/2)+posMsjVelMax+8));
        	 g.drawString("V(t)= "+ ecVelocidad, (this.getWidth()/2)-50, 38);
         }     
	} 
	
	@Override
	public void paint(Graphics g) {
		
	    // Guarda los pares de coordenadas
        Dx1.add(atrX1);
        Dx2.add(atrX2);
        Dy1.add(atrY1);
        Dy2.add(atrY2);
        
        super.paint(g);
        
        g.setColor(atrColorOnda);
        
        // Se grafican lineas entre todos los puntos guardados
        for(int i=0;i<Dx2.size();i++){
            
        	// Configurando estilo de la linea
            Graphics2D g2 = (Graphics2D)g;
            g2.setStroke(new BasicStroke(3,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
            
            // Dibuja la linea, dx,dy multiplicadas por la escala de la grafica
            g.drawLine((int)realX((Dx1.get(i))*atrEscalaX), (int)realY((Dy1.get(i))*atrEscalaY), (int)realX((Dx2.get(i))*atrEscalaX), (int)realY((Dy2.get(i))*atrEscalaY));
        }
	}
	
	// Para convertir el valor resultante de la ecuación a una posición en el plano cartesiano
    public double realX(double x){
        return x + this.getWidth()/5;
    }
    
    public double realY(double y){
        return (3*y) + this.getHeight()/2;
    }
    
    public void borrarOnda(){
        this.Dx1.clear();
        this.Dx2.clear();
        this.Dy1.clear();
        this.Dy2.clear();
    }

	

}

package controlador;

import java.awt.Canvas;
import java.awt.Color;

import logica.Bloque;
import logica.Plano;
import logica.Resorte;
import presentacion.FramePrincipal;
/**
 * 
 * @author Yeferson Benavides - Yennyfer Aviles - Maria Isabel Mosquera
 *
 */
public class Controlador {
	
	/** Variables de clase **/
	private Bloque atrBloque;
	private Resorte atrResorte;
	private Plano atrPlanoPosicion, atrPlanoVelocidad;
	private double k, m, posInic, velInic;
	private double A, phi, W;
	private boolean esEcSeno;
	
	/** Constantes de clase **/
	private final double CONST_POSINIC = 0;
	private final double CONST_ELASTICIDAD = 1000;
	private final double CONST_MASA = 2;
	private final double CONST_VELINIC = 1;
	
	/** Constructor **/
	public Controlador() {
		atrBloque = new Bloque();
		atrResorte = new Resorte();
		atrPlanoPosicion = new Plano();
		atrPlanoVelocidad = new Plano();
	}
	
	/** Getters y Setters */
	public Bloque getBloque() {
		return atrBloque;
	}

	public void setBloque(Bloque bloque) {
		this.atrBloque = bloque;
	}
		
	public Resorte getResorte() {
		return atrResorte;
	}

	public void setResorte(Resorte resorte) {
		this.atrResorte = resorte;
	}

	public Plano getAtrPlanoPosicion() {
		return atrPlanoPosicion;
	}

	public void setAtrPlanoPosicion(Plano planoPos) {
		this.atrPlanoPosicion = planoPos;
	}

	public Plano getAtrPlanoVelocidad() {
		return atrPlanoVelocidad;
	}

	public void setAtrPlanoVelocidad(Plano atrPlanoVelocidad) {
		this.atrPlanoVelocidad = atrPlanoVelocidad;
	}
	

	/**
	 * Ubica el bloque en la posición en que se encontraba por primera vez.
	 */
	public void reiniciarPosBloque() {
		atrBloque.setLocation((int)FramePrincipal.posInicialBloque.getWidth(), (int)FramePrincipal.posInicialBloque.getHeight());
	}
	
	/**
	 * Limpia los datos como constantes, mensajes y gráficas mostrados en el plano cartesiano. Lo deja solo con su estructura básica.
	 */
	public void reiniciarPlano() {
		atrPlanoPosicion.removeAll();
		atrPlanoPosicion.repaint();
	}
	

	/**
	 * Método general que prepara los datos que van a utilizarse en la ecuación de movimiento.
	 * Valida que los datos de las variables A, k y m sean mayores a cero y
	 * en caso tal, se encarga de asignarles el valor de las constantes predefinidas. Posteriormente,
	 * realiza el llamado al método que se encarga de mover el bloque.
	 */
	public void iniciarMovmnto() {
		obtenerDatos();
		calcularConstantes();
		mover(A, W, phi, esEcSeno);
	}
	
	/**
	 * Método encargado de recibir el texto introducido en cada caja de texto y en el checkbox.
	 * Si lo que se obtiene es un valor nulo, se encarga de asignarle a cada variable el valor de las constantes predefinidas
	 */
	public void obtenerDatos() {
		if (FramePrincipal.txtPosInicial.getText() == null) 
			posInic = CONST_POSINIC;
		else
			posInic = Double.parseDouble(FramePrincipal.txtPosInicial.getText())/100;
		if (FramePrincipal.txtKElastica.getText() == null)
			k = CONST_ELASTICIDAD;
		else
			if (Double.parseDouble(FramePrincipal.txtKElastica.getText()) <= 0)
				k = CONST_ELASTICIDAD;
			else
				k = Double.parseDouble(FramePrincipal.txtKElastica.getText());
		if (FramePrincipal.txtMasa.getText() == null)
			m = CONST_MASA;
		else
			if (Double.parseDouble(FramePrincipal.txtMasa.getText()) <= 0)
				m = CONST_MASA;
			else
				m = Double.parseDouble(FramePrincipal.txtMasa.getText());
		if (FramePrincipal.txtVelocidad.getText() == null)
			velInic = CONST_VELINIC;
		else
			velInic = Double.parseDouble(FramePrincipal.txtVelocidad.getText());
	
		if(FramePrincipal.chkSeno.isSelected() == true)
			esEcSeno = true;
		else
			esEcSeno = false;
	}
	
	/**
	 *  Encuentra el valor de la amplitud y del desfase a partir de las condiciones iniciales.
	 */
	public void calcularConstantes() {
		W = Math.sqrt(k/m);
		double cociente = posInic/velInic;
		A = Math.sqrt(Math.pow(posInic, 2)+Math.pow(velInic/W, 2));
		phi = Math.atan(W*cociente);
		if (esEcSeno == false) {
			if(posInic == 0 ) {
				phi = Math.PI/2;
			}
			else {
				double cociente2 = velInic/posInic;
				phi = Math.atan(-cociente2/W);
			}
		}
		corregirValorPhi();
	}
	
	/**
	 *  Se valida el valor encontrado del ángulo phi, teniendo en cuenta el signo de sen(phi) y cos(phi) respecto a las condiciones iniciales.
	 */
	public void corregirValorPhi() {
		if(esEcSeno == true) {
			if(posInic > 0 && velInic > 0) {
				//senoPositivo = true; cosenoPositivo = true;
				//debe estar en el primer cuadrante
				if(phi < 0  ||  phi > (Math.PI/2)) {
					phi = Math.abs(phi);
					phi = Math.PI-phi;
				}
			}
			else if(posInic < 0 && velInic > 0) {
				//senoPositivo = false; cosenoPositivo = true;
				//debe estar en cuarto cuadrante
				if(phi < 0  ||  phi > (2*Math.PI)) {
					phi = Math.abs(phi);
					phi = (2*Math.PI)-phi;
				}
			}
			else if(posInic > 0 && velInic < 0) {
				//senoPositivo = true; cosenoPositivo = false;
				//debe estar en el segundo cuadrante
				if(phi < 0  ||  phi > (Math.PI)) {
					phi = Math.abs(phi);
					phi = Math.PI-phi;
				}
			}
			else if(posInic < 0 && velInic < 0) {
				//senoPositivo = false; cosenoPositivo = false;
				//debe estar en el tercer cuadrante
				if(phi < Math.PI  ||  phi > ((3*Math.PI)/2)) {
					phi = Math.abs(phi);
					phi = Math.PI+phi;
				}
			}
		}
		else {
			if(posInic > 0 && velInic > 0) {
				//senoPositivo = false; cosenoPositivo = true;
				//debe estar en el cuarto cuadrante
				if(phi < 0  ||  phi > (2*Math.PI)) {
					phi = Math.abs(phi);
					phi = (2*Math.PI)-phi;
				}
			}
			else if(posInic < 0 && velInic > 0) {
				//senoPositivo = false; cosenoPositivo = false;
				//debe estar en tercer cuadrante
				if(phi < Math.PI  ||  phi > ((3*Math.PI)/2)) {
					phi = Math.abs(phi);
					phi = Math.PI+phi;
				}
			}
			else if(posInic > 0 && velInic < 0) {
				//senoPositivo = true; cosenoPositivo = true;
				//debe estar en el primer cuadrante
				if(phi < 0  ||  phi > (Math.PI/2)) {
					phi = Math.abs(phi);
					phi = Math.PI-phi;
				}
			}
			else if(posInic < 0 && velInic < 0) {
				//senoPositivo = true; cosenoPositivo = false;
				//debe estar en el segundo cuadrante
				if(phi < 0  ||  phi > Math.PI) {
					phi = Math.abs(phi);
					phi = Math.PI-phi;
				}
			}
		}

	}
	
	/**
	 *  Se prepara el plano cartesiano para posteriormente graficar la función elegida.
	 *  Se muestran datos de constantes y ecuación de movimiento calculada.
	 */
	public void actualizarPlano() {
		String varAmplitud = "" + A;
		String varPhi = "" + phi;
		String varW = "" + W;
		String varVelMax = "" + W*A;
		String varPeriodo = (2*Math.PI)/W + "";
		
		varAmplitud = corregirLongitudCadena(varAmplitud);
		varVelMax = corregirLongitudCadena(varVelMax);
		varW = corregirLongitudCadena(varW);
		varPhi = corregirLongitudCadena(varPhi);
		varPeriodo = corregirLongitudCadena(varPeriodo);
		
		atrPlanoPosicion.setAtrEscalaX(300);
		atrPlanoPosicion.setAtrEscalaY(150);
		atrPlanoPosicion.setAtrPosMax(varAmplitud);
		atrPlanoPosicion.setPosMsjPosMax((int)(A*atrPlanoPosicion.getAtrEscalaY() + 10));
		atrPlanoPosicion.setOmega(varW);
		atrPlanoPosicion.setPhi(varPhi);
		atrPlanoPosicion.setPeriodo(varPeriodo);
		atrPlanoPosicion.setAtrColorOnda(Color.red);

		atrPlanoVelocidad.setAtrEscalaX(300);
		atrPlanoVelocidad.setAtrEscalaY(8);
		atrPlanoVelocidad.setAtrVelMax(varVelMax);
		atrPlanoVelocidad.setPosMsjVelMax((int)(W*A*atrPlanoVelocidad.getAtrEscalaY() + 25));
		atrPlanoVelocidad.setAtrColorOnda(Color.blue);
		
		if(FramePrincipal.chkSeno.isSelected() == true) {
			atrPlanoPosicion.setEcPosicion(""+varAmplitud+"*Sen("+varW+"t + "+varPhi+")");
			atrPlanoVelocidad.setEcVelocidad(""+varAmplitud+"*"+varW+"*Cos("+varW+"t + "+varPhi+")");
		}
		else {
			if(FramePrincipal.chkCoseno.isSelected() == true) {
				atrPlanoPosicion.setEcPosicion(""+varAmplitud+"*Cos("+varW+"t + "+varPhi+")");
				atrPlanoVelocidad.setEcVelocidad("-"+varAmplitud+"*"+varW+"*Sen("+varW+"t + "+varPhi+")");
			}
		}
		atrPlanoPosicion.repaint();
		atrPlanoVelocidad.repaint();
	}
	
	/** Se valida y modifica (si se requiere) la longitud de una cadena de texto, para posteriormente ser mostrada en la interfaz gráfica.
	 * @param prmCadena
	 * @return cadena de texto con máximo 6 caracteres
	 */
	public String corregirLongitudCadena(String prmCadena) {
		if(prmCadena.length() > 6) {
			prmCadena = prmCadena.substring(0, 5);
		}
		return prmCadena;
	}
		
	/**
	 * Se encarga de desplazar el bloque y de dibujar las gráficas de posición y velocidad utilizando la ecuación de movimiento armónico simple con los parámetros dados por el usuario
	 * @param A Amplitud de movimiento
	 * @param W Frecuencia natural de vibración
	 * @param p Desfase
	 * @param usarSeno Variable que permite conocer el tipo de ecuación a utilizar (Asen(Wt+p) o Acos(Wt+p))
	 */
	public void mover(double A, double W, double p, boolean usarSeno) {
		int distanciaXaPared = FramePrincipal.distXaPared;
		int alturaResorte = atrResorte.getHeight(); 
		int posIniXBloque = atrBloque.getX();
		int nuevaPosX = 0, graficaPosX = 0, graficaPosY = 0, graficaVelX = 0, graficaVelY = 0;
		Canvas puntoGraficaPos, puntoGraficaVel;
		
		double x1=0,y1Pos=0,x2=0,y2Pos=0;
		double y1Vel=0,y2Vel=0;
        double cont=0;
        double saltosDe=0.01;
        
		actualizarPlano();
		atrBloque.setLocation(posIniXBloque + (int)posInic, atrBloque.getY());
		
		
		if (usarSeno == true) {
			
			while(true) {
				// Se calculan dos puntos (la posicion y la velocidad en dos instantes de tiempo consecutivos)
				// Estos datos permiten desplazar el bloque y a la vez trazar lineas para las gráficas
				
				// Para la posición
				x1 = cont;
				y1Pos= -A * (Math.sin((W * x1) + p));
	            x2=cont+saltosDe;
	            y2Pos= -A * (Math.sin((W * x2) + p));
	            
	            // Para la velocidad 
	            // Se usan los mismos valores de "x" ya que se calcula la velocidad en el mismo instante de tiempo que se usó para la posición.
				y1Vel= -A * W * Math.cos((W * x1) + p);
	            y2Vel= -A * W * (Math.cos((W * x2) + p));
	            
	            atrBloque.setLocation( (int) (posIniXBloque + y1Pos*100), atrBloque.getY());
				atrResorte.setSize(atrBloque.getX() - distanciaXaPared + 50, alturaResorte);
				
				// como la escala para el tiempo es la misma en ambas gráficas, se utiliza solo una de ellas para verificar si ya se llenaron los planos
	            if(x1*atrPlanoPosicion.getAtrEscalaX()<atrPlanoPosicion.getWidth()){
	                this.graficarOnda(x1, y1Pos, x2, y2Pos, atrPlanoPosicion);
	                this.graficarOnda(x1, y1Vel, x2, y2Vel, atrPlanoVelocidad);
	            }
	            
	            //aumento contador "x"
	            cont += saltosDe;
	            
	            try { 
	                Thread.sleep(50);
	            } catch (InterruptedException ex) {
	            	
	            }
	            
			}
			
		} 
		else {
			
			while(true) {
				// Se calculan dos puntos (la posicion y la velocidad en dos instantes de tiempo consecutivos)
				// Estos datos permiten desplazar el bloque y a la vez trazar lineas para las gráficas
				
				// Para la posición
				x1 = cont;
				y1Pos= -A * (Math.cos((W * x1) + p));
	            x2=cont+saltosDe;
	            y2Pos= -A * (Math.cos((W * x2) + p));
	            
	            // Para la velocidad 
	            // Se usan los mismos valores de "x" ya que se calcula la velocidad en el mismo instante de tiempo que se usó para la posición.
				y1Vel= A * W * Math.sin((W * x1) + p);
	            y2Vel= A * W * (Math.sin((W * x2) + p));
	            
	            atrBloque.setLocation( (int) (posIniXBloque + y1Pos*100), atrBloque.getY());
				atrResorte.setSize(atrBloque.getX() - distanciaXaPared + 50, alturaResorte);
				
				// como la escala para el tiempo es la misma en ambas gráficas, se utiliza solo una de ellas para verificar si ya se llenaron los planos
	            if(x1*atrPlanoPosicion.getAtrEscalaX()<atrPlanoPosicion.getWidth()){
	                this.graficarOnda(x1, y1Pos, x2, y2Pos, atrPlanoPosicion);
	                this.graficarOnda(x1, y1Vel, x2, y2Vel, atrPlanoVelocidad);
	            }
	            
	            //aumento contador "x"
	            cont += saltosDe;
	            
	            try { 
	                Thread.sleep(50);
	            } catch (InterruptedException ex) {
	            	
	            }
	            
			}
			
		}
	}
	
	public void graficarOnda(double x1, double y1, double x2, double y2, Plano prmPlano) {
		prmPlano.setAtrX1(x1);
		prmPlano.setAtrY1(y1);
		prmPlano.setAtrX2(x2);
		prmPlano.setAtrY2(y2);
		prmPlano.repaint();
	}
	
}

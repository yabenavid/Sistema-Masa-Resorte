package presentacion;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controlador.Controlador;
import logica.Bloque;
import logica.Plano;
import logica.Resorte;
/**
 * 
 * @author Yeferson Benavides - Yennyfer Aviles - Maria Isabel Mosquera
 *
 */
public class FramePrincipal extends JFrame implements Runnable{

	/* Variables de clase */
	private Controlador objControlador;
	private Thread thrMovimiento;
	private Resorte objResorte;
	private Bloque objBloque;
	private Plano atrPlanoPos;
	private Plano atrPlanoVel;
	
	/* Elementos Swing*/
	private JPanel pnlDatos;
	private Canvas objPiso;
	private Canvas objPared;
	private JLabel lbPosInicial;
	private JLabel lbKElastica;
	private JLabel lbVelocidad;
	private JLabel lbMasa;
	private JLabel lbMensaje1;
	private JButton btnIniciar;
	private JButton btnDetener;
	
	/* Constantes de color*/
	private final Color clrPanelDatos = new Color(24, 44, 97);
	private final Color clrPiso = Color.BLACK;
	private final Color clrPared = Color.BLACK;
	
	/* 	Variables estáticas */
	public static JPanel pnlSistema;
	public static JTextField txtPosInicial;
	public static JTextField txtKElastica;
	public static JTextField txtMasa;
	public static JTextField txtVelocidad;
	public static JCheckBox chkSeno;
	public static JCheckBox chkCoseno;
	public static Dimension posInicialBloque;
	public static Dimension dimPantalla;
	public static int distXaPared;
	
	public FramePrincipal() {

		setTitle("Sistema Masa Resorte");
		Toolkit miPantalla = Toolkit.getDefaultToolkit();
		dimPantalla = miPantalla.getScreenSize();
		setSize((int)dimPantalla.getWidth(), (int)dimPantalla.getHeight());
		objControlador = new Controlador();
		setResizable(false);

		inicializarComponentes();
						
	}
	
	public void inicializarComponentes() {
		
		/** SISTEMA MASA RESORTE **/
		pnlSistema = new JPanel();
		pnlSistema.setSize(600, 600);
		pnlSistema.setLayout(null);
		
		JLabel lblFondoSistema = new JLabel();
		lblFondoSistema.setSize(700,700);
		
		Image imgFondo;
		try {
			imgFondo = ImageIO.read(getClass().getClassLoader().getResource("fondoc.jpg"));
			ImageIcon imgfondoIcon = new ImageIcon(imgFondo);
			lblFondoSistema.setIcon(imgfondoIcon);
		} catch (IOException e) {
			System.out.println("La imagen no se encuentra en la ubicación indicada.");
		}
		
		// PISO Y PARED
		objPiso = new Canvas();
		objPiso.setSize((int) dimPantalla.getWidth(),5);
		objPiso.setLocation(0,(getHeight()/2)+100);
		objPiso.setBackground(clrPiso);
		objPiso.setVisible(true);
		pnlSistema.add(objPiso);
		
		objPared = new Canvas();
		objPared.setSize(3,objPiso.getY());
		objPared.setLocation(70,0);
		objPared.setBackground(clrPared);
		objPared.setVisible(true);
		pnlSistema.add(objPared);
		
		distXaPared = objPared.getX() + objPared.getWidth();
		
		// IMAGEN BLOQUE
		objBloque = objControlador.getBloque();
		posInicialBloque = new Dimension(300, objPiso.getY()-objBloque.getHeight());
		objBloque.setLocation((int)posInicialBloque.getWidth(), (int)posInicialBloque.getHeight());
		pnlSistema.add(objBloque);
				
		// IMAGEN RESORTE
		objResorte = objControlador.getResorte();
		objResorte.setSize(objBloque.getX()-distXaPared+50,objBloque.getHeight()+300);
		objResorte.setLocation(distXaPared-8, objPiso.getY()-(int)(2*objBloque.getHeight()));
		objResorte.setVisible(true);
		pnlSistema.add(objResorte);
		pnlSistema.setBorder(BorderFactory.createLoweredSoftBevelBorder());
		add(pnlSistema);

		pnlSistema.add(lblFondoSistema);
		
		/** GRÁFICAS DE FUNCIONES  **/
		JPanel pnlGrafFunciones = new JPanel();
		pnlGrafFunciones.setPreferredSize(new Dimension(getWidth()/2, getHeight()/2));
		pnlGrafFunciones.setLayout(new GridLayout(2,0));
		
		atrPlanoPos = objControlador.getAtrPlanoPosicion();
		atrPlanoPos.setAtrTitulo("Posición (X(t))");
		atrPlanoPos.setAtrMensajeY("X(t)");
		atrPlanoPos.setSize(new Dimension(pnlGrafFunciones.getWidth(), 150));
		atrPlanoPos.setBorder(BorderFactory.createRaisedBevelBorder());
		atrPlanoPos.setVisible(true);
		
		atrPlanoVel = objControlador.getAtrPlanoVelocidad();
		atrPlanoVel.setAtrTitulo("Velocidad (V(t))");
		atrPlanoVel.setAtrMensajeY("V(t)");
		atrPlanoVel.setSize(new Dimension(pnlGrafFunciones.getWidth(), 150));
		atrPlanoVel.setBackground(Color.gray);
		atrPlanoVel.setBorder(BorderFactory.createRaisedBevelBorder());
		atrPlanoVel.setVisible(true);

		pnlGrafFunciones.setVisible(true);
		pnlGrafFunciones.add(atrPlanoPos);
		pnlGrafFunciones.add(atrPlanoVel);
		
		add(pnlGrafFunciones, BorderLayout.EAST);
		
		atrPlanoVel.setFont(new Font("Verdana",Font.BOLD,19));
		atrPlanoPos.setFont(new Font("Verdana",Font.BOLD,19));	
		
		/*---------------------------------------------*/
				
		/** ENTRADA DE DATOS **/
		pnlDatos = new JPanel();
		pnlDatos.setBackground(clrPanelDatos.brighter());
		pnlDatos.setPreferredSize(new Dimension(this.getWidth(), 80));
		
		lbPosInicial = new JLabel("Posición Inicial (cm)");
		lbVelocidad = new JLabel("Velocidad Inicial (m/s)");
		lbKElastica = new JLabel("Constante Elástica (N/m)");
		lbMasa = new JLabel("Masa (Kg)");
		lbMensaje1 = new JLabel("Ecuación");
		
		lbPosInicial.setForeground(Color.white);
		lbPosInicial.setFont(new Font("Verdana",Font.BOLD,18));
		
		lbVelocidad.setForeground(Color.white);
		lbVelocidad.setFont(new Font("Verdana",Font.BOLD,18));
		
		lbKElastica.setForeground(Color.white);
		lbKElastica.setFont(new Font("Verdana",Font.BOLD,18));
		
		lbMasa.setForeground(Color.white);
		lbMasa.setFont(new Font("Verdana",Font.BOLD,18));
		
		lbMensaje1.setForeground(Color.white);
		lbMensaje1.setFont(new Font("Verdana",Font.BOLD,18));
		
		
		txtPosInicial = new JTextField("5",4);
		txtKElastica = new JTextField("1000",4);
		txtVelocidad = new JTextField("1",4);
		txtMasa = new JTextField("2",4);
		
		txtPosInicial.setFont(new Font("Verdana",Font.BOLD,14));
		txtKElastica.setFont(new Font("Verdana",Font.BOLD,14));
		txtVelocidad.setFont(new Font("Verdana",Font.BOLD,14));
		txtMasa.setFont(new Font("Verdana",Font.BOLD,14));
		
		chkSeno = new JCheckBox("Seno", true);
		chkCoseno = new JCheckBox("Coseno");
		chkSeno.setToolTipText("La ecuación de movimiento a utilizar será de la forma: ASen(\u03C9t+\u03A6)");
		chkCoseno.setToolTipText("La ecuación de movimiento a utilizar será de la forma: ACos(\u03C9t+\u03A6)");
		btnIniciar = new JButton("Iniciar");
		btnDetener = new JButton("Detener");
		
		chkSeno.setFont(new Font("Verdana",Font.BOLD,15));
		chkCoseno.setFont(new Font("Verdana",Font.BOLD,15));
		btnIniciar.setBackground(new Color(0, 98, 102));
		btnIniciar.setForeground(Color.green);
		btnIniciar.setFont(new Font("Verdana",Font.BOLD,20));
		
		btnDetener.setBackground(new Color(0, 98, 102));
		btnDetener.setForeground(Color.green);
		btnDetener.setFont(new Font("Verdana",Font.BOLD,20));
		
		pnlDatos.add(lbPosInicial);
		pnlDatos.add(txtPosInicial);
		pnlDatos.add(lbVelocidad);
		pnlDatos.add(txtVelocidad);
		pnlDatos.add(lbKElastica);
		pnlDatos.add(txtKElastica);
		pnlDatos.add(lbMasa);
		pnlDatos.add(txtMasa);
		pnlDatos.add(lbMensaje1);
		pnlDatos.add(chkSeno);
		pnlDatos.add(chkCoseno);
		pnlDatos.add(btnIniciar);
		pnlDatos.add(btnDetener);
		
		add(pnlDatos, BorderLayout.SOUTH);
		
		/*-----------------------*/
		
		/** EVENTOS **/
		btnIniciar.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(btnIniciar.isEnabled()) {
					objControlador.reiniciarPosBloque();
					atrPlanoPos.borrarOnda();
					atrPlanoVel.borrarOnda();
					btnIniciar.setEnabled(false);
					iniciar();
				}
				super.mouseClicked(e);
			}
		});
		
		btnIniciar.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == 10) {
					if(btnIniciar.isEnabled()) {
						objControlador.reiniciarPosBloque();
						atrPlanoPos.removeAll();
						atrPlanoPos.borrarOnda();
						atrPlanoPos.repaint();
						atrPlanoVel.removeAll();
						//atrPlanoVel.repaint();
						btnIniciar.setEnabled(false);
						iniciar();
					}
					super.keyReleased(e);
				}

			}
		});
		
		btnDetener.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {

				if(btnIniciar.isEnabled() == false) {
					btnIniciar.setEnabled(true);
					thrMovimiento.stop();
					thrMovimiento = null;
				}
				super.mouseClicked(e);
			}
		});
		
		chkSeno.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(chkSeno.isSelected()) {
					chkCoseno.setSelected(false);
				}
				else {
					chkCoseno.setSelected(true);
				}
			}
		});
		
		chkCoseno.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(chkCoseno.isSelected()) {
					chkSeno.setSelected(false);
				}
				else {
					chkSeno.setSelected(true);
				}
			}
		});
		
		txtPosInicial.addFocusListener(new VerificaEnteros());
		txtVelocidad.addFocusListener(new VerificaEnteros());
		txtMasa.addFocusListener(new VerificaEnteros());
		txtKElastica.addFocusListener(new VerificaEnteros());
				
	}
	
	public void iniciar() {
		thrMovimiento = new Thread(this);
		thrMovimiento.start();
	}
	
	@Override
	public void run() {
		objControlador.iniciarMovmnto();
	}
	
	
	/**
	 * Clase interna utilizada para validar los datos ingresados en las cajas de texto, antes de procesarlos.
	 *
	 */
	class VerificaEnteros implements FocusListener{

		@Override
		public void focusGained(FocusEvent e) {}

		@Override
		public void focusLost(FocusEvent e) {
			
			Object objFuente = e.getSource();
			JTextField textboxFuente = (JTextField) objFuente;
			String varDato = textboxFuente.getText();
			Exception excMasaInvalida = new Exception();
			boolean esCorrecto = false;
			
			try{
				
				double aux = Double.parseDouble(varDato);   
				if (objFuente.equals(txtMasa) == true) {
					if(aux <= 0) {
						throw excMasaInvalida;
					}
				}
				esCorrecto = true;
				
			}catch (NumberFormatException ex){ 
				JOptionPane.showMessageDialog(null, "No ingresó un número válido", "Error", 0);
			}catch (Exception ex2) {
				JOptionPane.showMessageDialog(null, "Masa menor o igual a cero", "Error", 0);
			}     
			
			finally {
				if(esCorrecto) {
					textboxFuente.setBackground(Color.WHITE);
					textboxFuente.setForeground(Color.black);
				}
				else {
					textboxFuente.requestFocus();
					textboxFuente.setBackground(Color.red.brighter());
					textboxFuente.setForeground(Color.WHITE);
				}
			}
		}
	}
	
	// starter
	public static void main (String[] args) {
		FramePrincipal frmPrincipal = new FramePrincipal();
		frmPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPrincipal.setVisible(true);
	}
	
}




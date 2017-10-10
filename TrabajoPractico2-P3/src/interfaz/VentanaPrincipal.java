package interfaz;


import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;



import org.openstreetmap.gui.jmapviewer.JMapViewer;


import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import interfaz.Mapa;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class VentanaPrincipal {

	private JFrame frame;
	private JMapViewer mapa;
	private Mapa grafo;
	private boolean ingresarPunto;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal window = new VentanaPrincipal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Conexiones telefónicas");
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/imagenes/icono_telefono.png")));
		frame.setBounds(250,100, 800, 600);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		ingresarPunto=false;
		
		mapa=new JMapViewer();
		grafo=new Mapa(this);
		
		//Se posiciona en las coordenadas geográficas de Argentina
		mapa.setDisplayPositionByLatLon(-33.123827, -60.354550, 5); 
		frame.setContentPane(mapa);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(619, 0, 175, 571);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		
		//Agregamos los botones y accionamos los respectivos botones
		JButton botonLocalidad = new JButton("Agregar localidad");
		botonLocalidad.setFont(new Font("Consolas", Font.PLAIN, 12));
		botonLocalidad.setBounds(10, 49, 161, 23);
		botonLocalidad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Localidad localidad = new Localidad();
				localidad.setVisible(true);
				ingresarPunto=true;
			}
		});
		panel.add(botonLocalidad);
		
		JButton botonAGM = new JButton("Calcular AGM");
		botonAGM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		botonAGM.setFont(new Font("Consolas", Font.PLAIN, 12));
		botonAGM.setBounds(10, 106, 161, 23);
		panel.add(botonAGM);
		botonAGM.setEnabled(false); 
		
		JLabel lblCostoTotal = new JLabel("Costo Total:");
		lblCostoTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblCostoTotal.setFont(new Font("Consolas", Font.BOLD, 15));
		lblCostoTotal.setBounds(22, 430, 122, 33);
		panel.add(lblCostoTotal);
		
		mapa.addMouseListener(new MouseAdapter() {
		@Override
        public void mouseClicked(MouseEvent e) {
			
				if (e.getButton() == MouseEvent.BUTTON1 && ingresarPunto==true) {
					grafo.getCoordenadas().add(mapa.getPosition(e.getPoint()));
					grafo.agregarLocalidad();
					ingresarPunto=false;
                }	
			
		}
			
		});
	}

		public JMapViewer getMapa() {
			return mapa;
		}
		


	}

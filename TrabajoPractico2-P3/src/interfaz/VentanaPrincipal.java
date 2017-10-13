package interfaz;


import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;











import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;




import org.openstreetmap.gui.jmapviewer.interfaces.TileSource;

import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import interfaz.Mapa;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

import java.awt.Color;

public class VentanaPrincipal{

	private JFrame frame;
	private JMapViewer mapa;
	private Mapa grafo;
	private boolean ingresarPunto;
	private JTextField textLatitud;
	private JTextField textLongitud;
	 private Point posicionActualFrame;
	 private Coordinate posicionActualMapa;
	
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
//		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		ingresarPunto=false;
		
		mapa=new JMapViewer();
		grafo=new Mapa(this);
		
		//Se posiciona en las coordenadas geográficas de Argentina
		mapa.setDisplayPositionByLatLon(-33.123827, -60.354550, 5); 
		
		frame.setContentPane(mapa);
		mapa.setLayout(null);
		


		JPanel panel = new JPanel();
		panel.setBounds(606, 0, 178, 561);
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		
		//Agregamos los botones y accionamos los respectivos botones
		JButton botonLocalidad = new JButton("Agregar localidad");
		botonLocalidad.setFont(new Font("Consolas", Font.PLAIN, 12));
		botonLocalidad.setBounds(4, 11, 161, 23);
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
//				grafo.calcularAGM();
			}
		});
		botonAGM.setFont(new Font("Consolas", Font.PLAIN, 12));
		botonAGM.setBounds(4, 278, 161, 23);
		panel.add(botonAGM);
		botonAGM.setEnabled(false); 
		
		JLabel lblCostoTotal = new JLabel("Costo Total:");
		lblCostoTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblCostoTotal.setFont(new Font("Consolas", Font.BOLD, 15));
		lblCostoTotal.setBounds(22, 396, 122, 33);
		panel.add(lblCostoTotal);
		
		JLabel costo = new JLabel("");
		costo.setBounds(32, 425, 112, 39);
		panel.add(costo);
		
		textLatitud = new JTextField();
		textLatitud.setBackground(Color.WHITE);
		textLatitud.setHorizontalAlignment(SwingConstants.CENTER);
		textLatitud.setEnabled(false);
		textLatitud.setBounds(10, 126, 155, 20);
		panel.add(textLatitud);
		textLatitud.setColumns(10);
		
		textLongitud = new JTextField();
		textLongitud.setBackground(Color.WHITE);
		textLongitud.setHorizontalAlignment(SwingConstants.CENTER);
		textLongitud.setEnabled(false);
		textLongitud.setBounds(10, 194, 155, 20);
		panel.add(textLongitud);
		textLongitud.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Latitud:");
		lblNewLabel.setFont(new Font("Consolas", Font.PLAIN, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(32, 95, 96, 14);
		panel.add(lblNewLabel);
		
		JLabel longitud = new JLabel("Longitud:");
		longitud.setHorizontalAlignment(SwingConstants.CENTER);
		longitud.setFont(new Font("Consolas", Font.PLAIN, 14));
		longitud.setBounds(47, 169, 81, 14);
		panel.add(longitud);
		
//		JLabel uso = new JLabel("Haga click derecho para mover el mapa");
//		uso.setForeground(Color.BLACK);
//		uso.setBackground(Color.GRAY);
//		uso.setHorizontalAlignment(SwingConstants.CENTER);
//		uso.setBounds(0, 547, 620, 14);
//		mapa.add(uso);

		
		
		mapa.addMouseMotionListener(new MouseAdapter() {
		
		
			@Override
            public void mouseMoved(MouseEvent e) {
                textLatitud.setText(""+mapa.getPosition(e.getPoint()).getLat());
                textLongitud.setText(""+mapa.getPosition(e.getPoint()).getLon());
            }
        });
		
	
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

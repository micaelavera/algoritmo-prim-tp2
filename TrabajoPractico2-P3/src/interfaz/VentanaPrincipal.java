package interfaz;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.interfaces.TileSource;
import org.openstreetmap.gui.jmapviewer.tilesources.BingAerialTileSource;
import org.openstreetmap.gui.jmapviewer.tilesources.OsmTileSource;

import serializacion.Manager;

import javax.swing.border.EtchedBorder;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import interfaz.Mapa;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

import java.awt.Color;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.SystemColor;
import java.io.IOException;
import java.util.ArrayList;

public class VentanaPrincipal{

	private JFrame frame;
	private JMapViewer mapa;
	private Mapa interfaz;
	private Coordinate posicionActualMapa;
	private boolean ingresarPunto;
	private JTextField textLatitud;
	private JTextField textLongitud;
	private JLabel costo;
	private JButton botonLocalidad,generarLocalidad;
	private String provincia;
	
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
		frame.setBounds(200,0,1000, 700);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		ingresarPunto=false;
		
		JLabel longitud = new JLabel("Longitud:");
		longitud.setBounds(26, 609, 99, 14);
		frame.getContentPane().add(longitud);
		longitud.setHorizontalAlignment(SwingConstants.CENTER);
		longitud.setFont(new Font("Consolas", Font.PLAIN, 14));
		
		textLongitud = new JTextField();
		textLongitud.setBounds(135, 606, 155, 20);
		textLongitud.setBackground(Color.WHITE);
		textLongitud.setHorizontalAlignment(SwingConstants.CENTER);
		textLongitud.setEnabled(false);
		textLongitud.setColumns(10);
		frame.getContentPane().add(textLongitud);
		
		JLabel latitud= new JLabel("Latitud:");
		latitud.setBounds(26, 649, 99, 14);
		frame.getContentPane().add(latitud);
		latitud.setFont(new Font("Consolas", Font.PLAIN, 14));
		latitud.setHorizontalAlignment(SwingConstants.CENTER);
		
		textLatitud = new JTextField();
		textLatitud.setBounds(135, 646, 155, 20);
		textLatitud.setBackground(Color.WHITE);
		textLatitud.setHorizontalAlignment(SwingConstants.CENTER);
		textLatitud.setEnabled(false);
		textLatitud.setColumns(10);
		frame.getContentPane().add(textLatitud);
			
		JLabel lblCostoTotal = new JLabel("Costo Total:");
		lblCostoTotal.setBounds(576, 554, 122, 33);
		lblCostoTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblCostoTotal.setFont(new Font("Consolas", Font.BOLD, 15));
		frame.getContentPane().add(lblCostoTotal);
	
		costo = new JLabel("");
		costo.setHorizontalAlignment(SwingConstants.CENTER);
		costo.setOpaque(true);
		costo.setBackground(Color.WHITE);
		costo.setForeground(Color.BLUE);
		costo.setFont(new Font("Consolas", Font.PLAIN, 16));
		costo.setBounds(576, 582, 143, 45);
		frame.getContentPane().add(costo);
			
		mapa=new JMapViewer();
		mapa.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		mapa.setBounds(0, 0, 994, 524);
		frame.getContentPane().add(mapa);
		interfaz=new Mapa();
	
		//Se posiciona en las coordenadas geográficas de Argentina
		mapa.setDisplayPositionByLatLon(-33.123827, -60.354550, 5); 
		
		//Agregamos los botones y accionamos los respectivos botones
		botonLocalidad = new JButton("Agregar localidad");
		botonLocalidad.setFont(new Font("Consolas", Font.PLAIN, 12));
		botonLocalidad.setBounds(26,560, 161, 23);
		botonLocalidad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Localidad localidad=new Localidad();
				localidad.setVisible(true);
				if(localidad.validarDatos()==true){
					ingresarPunto=true;
					provincia=localidad.getProvincia();
					generarLocalidad.setEnabled(false);
				}
				
			}});
		frame.getContentPane().add(botonLocalidad);	
		
		JButton botonAGM = new JButton("Calcular Árbol Generador Mínimo");
		botonAGM.setBounds(287, 560, 254, 23);
		botonAGM.setFont(new Font("Consolas", Font.PLAIN, 12));
		botonAGM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			try{
				interfaz.obtenerArbolGeneradorMinimo(mapa);
				botonAGM.setEnabled(false);
				botonLocalidad.setEnabled(false);
				costo.setText("$ "+Integer.toString((int)interfaz.getCosto()));
				
			}
			catch (Exception exception){
				mostrarError(exception.getMessage());
			}
			}

		});
		frame.getContentPane().add(botonAGM);
		
		JLabel ayuda = new JLabel("Utilice el bot\u00F3n derecho del mouse para moverse dentro del mapa , y con doble clic del bot\u00F3n izquierdo\r\n o la rueda del mouse para ampliar");
		ayuda.setFont(new Font("Consolas", Font.PLAIN, 12));
		ayuda.setHorizontalAlignment(SwingConstants.CENTER);
		ayuda.setBounds(10, 523, 952, 14);
		frame.getContentPane().add(ayuda);
		
	

        
        generarLocalidad = new JButton("Generar localidades");
        generarLocalidad.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		String instanciaSelecionada="instancia1";
				try{
					muestraMapa(instanciaSelecionada);
					generarLocalidad.setEnabled(false);
				}catch(IOException exception){
					System.out.println("Error al cargar la instancia");
				}
			}
        	
        });
        generarLocalidad.setBounds(800, 645, 184, 23);
        frame.getContentPane().add(generarLocalidad);
        
       
//		JComboBox <TileSource> tileSourceSelector = new JComboBox <>(new TileSource[]{
//		new OsmTileSource.Mapnik(), new OsmTileSource.CycleMap(),
//	    new BingAerialTileSource(),
//		});
//
//	    tileSourceSelector.addItemListener(new ItemListener() {
//	    @Override
//	     public void itemStateChanged(ItemEvent e) {
//	      mapa.setTileSource((TileSource) e.getItem());
//	       }
//	     });
//		tileSourceSelector.setBounds(34, 11, 92, 20);
//		mapa.add(tileSourceSelector);

		mapa.addMouseMotionListener(new MouseAdapter() {
		@Override
        public void mouseMoved(MouseEvent e) {
			posicionActualMapa = mapa.getPosition(e.getPoint());
			textLatitud.setText("" + posicionActualMapa.getLat());
	        textLongitud.setText("" + posicionActualMapa.getLon());
            }
        });

		//Eventos Mouselistener
		mapa.addMouseListener(new MouseAdapter() {
		@Override
        public void mouseClicked(MouseEvent e) {
			if (e.getButton() == MouseEvent.BUTTON1 && ingresarPunto==true) {
				interfaz.getCoordenadas().add(new Coordenada(provincia,posicionActualMapa.getLat(),posicionActualMapa.getLon()));
				interfaz.agregarLocalidad(mapa);
				ingresarPunto=false;
			}
		}
		});
	}

	private void mostrarError(String message){
		JOptionPane.showMessageDialog(frame, message);
}
	
	
	private void muestraMapa(String instancia) throws IOException {
		Manager dao=new Manager("src/serializacion/"+instancia+".json");
		//Agrego las coordenadas al mapa
		for(Coordenada vertice: dao.obtenerVertices())
			interfaz.getCoordenadas().add(new Coordenada(vertice.getProvincia(),vertice.getLatitud(),vertice.getLongitud()));
			interfaz.agregarLocalidad(mapa);
	}
}

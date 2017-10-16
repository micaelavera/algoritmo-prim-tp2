package interfaz;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;

import javax.swing.border.EtchedBorder;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import grafo.GrafoConPesos;
import grafo.Prim;
import interfaz.Mapa;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VentanaPrincipal{

	private JFrame frame;
	private JMapViewer mapa;
	private Mapa interfaz;
	private boolean ingresarPunto;
	private JTextField textLatitud;
	private JTextField textLongitud;
	private JLabel textCosto;
	private Coordinate posicionActualMapa;
	private GrafoConPesos AGM,GrafoCompleto;
	private double costo;
	
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
		frame = new JFrame("Conexiones telef�nicas");
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/imagenes/icono_telefono.png")));
		frame.setBounds(0,10, 1314, 725);
		
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		ingresarPunto=false;
	
		
		JLabel longitud = new JLabel("Longitud:");
		longitud.setBounds(46, 609, 99, 14);
		frame.getContentPane().add(longitud);
		longitud.setHorizontalAlignment(SwingConstants.CENTER);
		longitud.setFont(new Font("Consolas", Font.PLAIN, 14));
		
		textLongitud = new JTextField();
		textLongitud.setBounds(155, 606, 155, 20);
		textLongitud.setBackground(Color.WHITE);
		textLongitud.setHorizontalAlignment(SwingConstants.CENTER);
		textLongitud.setEnabled(false);
		textLongitud.setColumns(10);
		frame.getContentPane().add(textLongitud);
		
		JLabel latitud= new JLabel("Latitud:");
		latitud.setBounds(46, 649, 99, 14);
		frame.getContentPane().add(latitud);
		latitud.setFont(new Font("Consolas", Font.PLAIN, 14));
		latitud.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		textLatitud = new JTextField();
		textLatitud.setBounds(155, 646, 155, 20);
		textLatitud.setBackground(Color.WHITE);
		textLatitud.setHorizontalAlignment(SwingConstants.CENTER);
		textLatitud.setEnabled(false);
		textLatitud.setColumns(10);
		frame.getContentPane().add(textLatitud);
		
//		
		//Agregamos los botones y accionamos los respectivos botones
		JButton botonLocalidad = new JButton("Agregar localidad");
		botonLocalidad.setFont(new Font("Consolas", Font.PLAIN, 12));
		botonLocalidad.setBounds(95,554, 161, 23);
		
		botonLocalidad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Localidad localidad=new Localidad();
				localidad.setVisible(true);
				if(localidad.validarDatos()==true){
					ingresarPunto=true;
				}
		
			}});
		frame.getContentPane().add(botonLocalidad);	
		
		
		
		JLabel lblCostoTotal = new JLabel("Costo Total:");
		lblCostoTotal.setBounds(975, 548, 122, 33);
		lblCostoTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblCostoTotal.setFont(new Font("Consolas", Font.BOLD, 15));
		frame.getContentPane().add(lblCostoTotal);
	
		
		textCosto = new JLabel();
		textCosto.setEnabled(false);
		textCosto.setFocusable(false);
		
		
		textCosto.setHorizontalAlignment(SwingConstants.CENTER);
		textCosto.setBounds(975, 606, 122, 57);
		frame.getContentPane().add(textCosto);
		
		
				
		
		mapa=new JMapViewer();
		mapa.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		mapa.setBounds(0, 0, 1362, 524);
		frame.getContentPane().add(mapa);
		interfaz=new Mapa(this);
	
		//Se posiciona en las coordenadas geogr�ficas de Argentina
		mapa.setDisplayPositionByLatLon(-33.123827, -60.354550, 5); 
		

		JButton botonAGM = new JButton("Calcular AGM");
		botonAGM.addActionListener(new ActionListener() {
			@Override
			/**
			 * Si se presiona el boton obtener el arbol generador minimo, se intentara pedirle dicha funcion a la interfaz.
			 * En caso de que la misma devuelva una excepcion, se mostrara el cartel con el error.
			 */
			public void actionPerformed(ActionEvent e) {
				//Calcula el algoritmo de Prim de todos los vertices que se agregaron
				AGM=Prim.AGM(interfaz.cargarGrafo(interfaz.getCoordenadas()));
				interfaz.toArista(AGM, mapa); //Dibuja las aristas del anterior grafo cargado 
				costo=interfaz.getCosto();
				textCosto.setText(Integer.toString((int)costo));;
			}
//				try{
////					interfaz.obtenerArbolGeneradorMinimo(mapa);
//				}
//				catch (Exception exception){
//					mostrarError(exception.getMessage());
//				}
//			}
		});
		botonAGM.setBounds(332, 554, 161, 23);
		botonAGM.setFont(new Font("Consolas", Font.PLAIN, 12));
		frame.getContentPane().add(botonAGM);
		
		
		JLabel lblNewLabel = new JLabel("Utilice el bot\u00F3n derecho del mouse para moverse dentro del mapa , y con doble clic del bot\u00F3n izquierdo o la rueda del mouse para ampliar");
		lblNewLabel.setFont(new Font("Consolas", Font.PLAIN, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 529, 1274, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnCalcularGrafoCompleto = new JButton("Calcular Grafo Completo");
		btnCalcularGrafoCompleto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GrafoCompleto=interfaz.cargarGrafo(interfaz.getCoordenadas());
				interfaz.toArista(GrafoCompleto, mapa);
			}
		});
		btnCalcularGrafoCompleto.setFont(new Font("Consolas", Font.PLAIN, 12));
		btnCalcularGrafoCompleto.setBounds(332, 605, 223, 23);
		frame.getContentPane().add(btnCalcularGrafoCompleto);
		

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

		// Eventos Mouselistener
		mapa.addMouseListener(new MouseAdapter() {
		@Override
        public void mouseClicked(MouseEvent e) {
			if (e.getButton() == MouseEvent.BUTTON1 && ingresarPunto==true) {
				interfaz.getCoordenadas().add(posicionActualMapa);
				interfaz.agregarLocalidad();
				System.out.println(interfaz.toString());
				ingresarPunto=false;
			}
		}
		});
	}
	
	
//	private void seleccionandoRuta() {
//		for (MapMarker punto: mapa.getMapMarkerList()){
//
//			if ( !puntosSeleccionados.contains(punto)){
//				this.puntosSeleccionados.add(punto);
////				campoCoord1.setText(Interfaz.round(posicionActualMapa.getLat(), 4) +"; "+ Interfaz.round(posicionActualMapa.getLon(), 4));
//			}
//		}
//		if (this.puntosSeleccionados.size()>=2){
//			dibujarLineaEntrePuntosSeleccionados();
////			deseleccionarPuntos();
//		}
//	}
//	
//	private void dibujarLineaEntrePuntosSeleccionados() {
//		Coordinate inicio = this.puntosSeleccionados.get(0).getCoordinate();
//		Coordinate destino = this.puntosSeleccionados.get(1).getCoordinate();
//		dibujarLineaEntrePuntos(inicio, destino, null, mapa);
//	}
	
 

	
	
	public JMapViewer getMapa() {
		return mapa;
	}
	
	
	private void mostrarError(String message){
		JOptionPane.showMessageDialog(frame, message);
	}
}

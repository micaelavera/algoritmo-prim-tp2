package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Localidad extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelLocalidad;
	private JTextField textProvincia;
	private JComboBox<String> comboBoxHabitantes;
	private JTextField textLocalidad;
	

	private boolean validandoDatos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Localidad frame = new Localidad();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Localidad() {
		setTitle("Ingreso de datos");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Localidad.class.getResource("/imagenes/icono_ubicacion.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(400,200, 492, 250);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		validandoDatos=false;
	
		panelLocalidad = new JPanel();
		panelLocalidad.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelLocalidad);
		
		JLabel provincia = new JLabel("Ingrese provincia:");
		provincia.setBounds(30, 31, 118, 14);
		panelLocalidad.add(provincia);
	
		JLabel localidad = new JLabel("Ingrese localidad:");
		localidad.setBounds(30, 66, 106, 14);
		panelLocalidad.add(localidad);
		
		JLabel cantHabitantes = new JLabel("Cantidad de habitantes: ");
		cantHabitantes.setBounds(30, 105, 142, 14);
		panelLocalidad.add(cantHabitantes);
	
		comboBoxHabitantes = new JComboBox<String>();
		comboBoxHabitantes.setBounds(200, 102, 238, 20);
		comboBoxHabitantes.setBackground(Color.WHITE);
		comboBoxHabitantes.setModel(new DefaultComboBoxModel<String>(new String[] {"(seleccione cantidad de habitantes)", "menos de 1000", "2000-3000", "4000-5000", "6000-7000", "8000-9000", "m\u00E1s de 10.000"}));
		panelLocalidad.add(comboBoxHabitantes);
		
		textProvincia = new JTextField();
		textProvincia.setBounds(200, 28, 100, 20);
		panelLocalidad.add(textProvincia);
		
		textLocalidad = new JTextField();
		textLocalidad.setBounds(200, 63, 100, 20);
		panelLocalidad.add(textLocalidad);
		textLocalidad.setColumns(10);
		
		JButton botonCargarDatos = new JButton("Cargar datos");
		botonCargarDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validarDatos();
				if(validandoDatos==true){
					dispose();
					JOptionPane.showMessageDialog(null, "Marque en el mapa las coordenadas","Mensaje",JOptionPane.INFORMATION_MESSAGE);	
				}	
			}
		});
		botonCargarDatos.setBounds(182, 166, 118, 23);
		panelLocalidad.add(botonCargarDatos);
		panelLocalidad.setLayout(null);
	}

	public void validarDatos(){
		if(textLocalidad.getText().equals("") || textProvincia.getText().equals("")|| comboBoxHabitantes.getSelectedItem().equals(null)){
			JOptionPane.showMessageDialog(null,"Es obligatorio la carga de todos los datos", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
			validandoDatos=false;
		}else{
//			JOptionPane.showMessageDialog(null, "Procesando la información","Mensaje",JOptionPane.INFORMATION_MESSAGE);
			validandoDatos=true;
		}
	}
	
	
	public JTextField getTextLocalidad() {
		return textLocalidad;
	}
	}



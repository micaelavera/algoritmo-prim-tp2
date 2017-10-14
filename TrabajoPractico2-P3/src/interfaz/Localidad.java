package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Toolkit;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JComboBox;

public class Localidad extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JComboBox<String> comboBoxProvincias;
	private JTextField textLocalidad;
	private JComboBox<String> comboBoxHabitantes; 
		
	public static void main(String[] args) {
		try {
			Localidad dialog = new Localidad();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Localidad() {
		setTitle("Ingreso de datos");
		setBounds(400,200, 492, 250);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Localidad.class.getResource("/imagenes/icono_ubicacion.png")));
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setModal(true);
		{
			
		JButton botonCargarDatos = new JButton("Cargar datos");
		botonCargarDatos.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(validarDatos()==true){
			dispose();
			JOptionPane.showMessageDialog(null, "Marque en el mapa las coordenadas","Mensaje",JOptionPane.INFORMATION_MESSAGE);	
			}	
		}
		});
		botonCargarDatos.setBounds(182, 166, 118, 23);
		contentPanel.add(botonCargarDatos);
		getRootPane().setDefaultButton(botonCargarDatos);
		}
		
		JLabel provincia = new JLabel("Ingrese provincia:");
		provincia.setBounds(41, 35, 146, 14);
		contentPanel.add(provincia);
		
		comboBoxProvincias = new JComboBox<>();
		comboBoxProvincias.setBounds(197, 32, 200, 20);
		comboBoxProvincias.setBackground(Color.WHITE);
		comboBoxProvincias.setModel(new DefaultComboBoxModel<String>(new String[] {"(seleccione una provincia)", 
				"Buenos Aires","Catamarca","Chaco", "Chubut", "Córdoba","Corrientes", "Entre Ríos", "Formosa",
				"Jujuy","La Pampa","La Rioja","Mendoza","Misiones", "Neuquén","Río Negro","Salta","San Juan",
				"San Luis","Santa Cruz","Santa Fe", "Santiago del Estero", "Tierra del Fuego", "Tucumán"}));
		
		contentPanel.add(comboBoxProvincias);
	
		
		JLabel localidad = new JLabel("Ingrese localidad:");
		localidad.setBounds(41, 76, 146, 14);
		contentPanel.add(localidad);
		
		textLocalidad = new JTextField();
		textLocalidad.setBounds(197, 73, 200, 20);
		contentPanel.add(textLocalidad);
		textLocalidad.setColumns(10);
	
		
		JLabel cantidadHabitantes = new JLabel("Cantidad de habitantes:");
		cantidadHabitantes.setBounds(41, 118, 146, 14);
		contentPanel.add(cantidadHabitantes);
		
		comboBoxHabitantes = new JComboBox<>();
		comboBoxHabitantes.setBounds(197, 115, 200, 20);
		comboBoxHabitantes.setBackground(Color.WHITE);
		comboBoxHabitantes.setModel(new DefaultComboBoxModel<String>(new String[] {"(seleccione una opci\u00F3n)", "menos de 1000", "2000-3000", "4000-5000", "6000-7000", "8000-9000", "m\u00E1s de 10.000"}));
		contentPanel.add(comboBoxHabitantes);
	}
	
	public boolean validarDatos(){
		boolean ret=true;
		if(textLocalidad.getText().equals("") || comboBoxProvincias.getSelectedIndex()==0|| 
			comboBoxHabitantes.getSelectedIndex()==0){ 
			JOptionPane.showMessageDialog(null,"Es obligatorio la carga de todos los datos", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
			ret=false;
		}
		return ret;
	}

	public JTextField getTextLocalidad() {
		return textLocalidad;
	}

	
}

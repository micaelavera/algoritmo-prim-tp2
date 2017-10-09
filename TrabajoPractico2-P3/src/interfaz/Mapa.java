package interfaz;

import java.awt.Color;
import java.util.ArrayList;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;

import interfaz.VentanaPrincipal;
import interfaz.Localidad;
import interfaz.Arista;

public class Mapa {
	
	private ArrayList<Coordinate> coordenadas;
	private JMapViewer mapa;
	private Localidad localidad;
//	private ArrayList<Arista> aristas;
	
	public Mapa(VentanaPrincipal ventana){
		coordenadas=new ArrayList<>();
		localidad=new Localidad();
//		aristas=new ArrayList<>();
		mapa= ventana.getMapa();
	}
	
	public void agregarLocalidad(){  
		if(coordenadas!=null){
			Color color;
			for(int i=0;i<coordenadas.size();i++){
				if(i>0){
					color=Color.RED; 
				}else{
					color=Color.GREEN; 
				}
			Coordinate puntoActual=coordenadas.get(i);
			MapMarkerDot marker=new MapMarkerDot(puntoActual);
			marker.getStyle().setBackColor(color);
//			marker.setName(localidad.getTextLocalidad().getText());
			mapa.addMapMarker(marker);		
			}
		}	
	}
	
	public ArrayList<Coordinate> getCoordenadas() {
		return coordenadas;
	}
	
	
	
	
	
	
	
}

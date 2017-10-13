package interfaz;

import java.awt.Color;
import java.util.ArrayList;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;

import grafo.GrafoConPesos;
import grafo.Prim;

import interfaz.VentanaPrincipal;
import interfaz.Localidad;


public class Mapa {
	
	private ArrayList<Coordinate> coordenadas;
	private JMapViewer mapa;
	private Localidad localidad;
	
	public Mapa(VentanaPrincipal ventana){
		coordenadas=new ArrayList<>();
		localidad=new Localidad();
		mapa= ventana.getMapa();
		
	}
	
	// Se añade al mapa un punto nuevo, en la posicion del mouse.
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
	
	public double calcularDistanciaCoord(Coordinate origen, Coordinate destino) {  
        double radioTierra = 6371;//en kilómetros  
        double dLat = Math.toRadians(destino.getLat() - origen.getLat());  
        double dLng = Math.toRadians(destino.getLon() - origen.getLon());  
        double sindLat = Math.sin(dLat / 2);  
        double sindLng = Math.sin(dLng / 2);  
        double va1 = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)  
                * Math.cos(Math.toRadians(origen.getLat())) * Math.cos(Math.toRadians(destino.getLat()));  
        double va2 = 2 * Math.atan2(Math.sqrt(va1), Math.sqrt(1 - va1));  
        double distancia = radioTierra * va2;  
   
        return distancia;  
    }  
	
	
	
	
	public ArrayList<Coordinate> getCoordenadas() {
		return coordenadas;
	}
	
	
	
	
	
	
	
}

package interfaz;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Set;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.interfaces.MapPolygon;

import grafo.GrafoConPesos;


import grafo.Prim;
import interfaz.VentanaPrincipal;

public class Mapa {
	
	private ArrayList<Coordinate> coordenadas;
	private ArrayList<AristaGrafica> aristasAGM;
	private JMapViewer mapa;



	public Mapa(VentanaPrincipal ventana){
		coordenadas=new ArrayList<>();
		aristasAGM=new ArrayList<>();
		mapa=ventana.getMapa();
	}

	// Se añade al mapa un punto nuevo, en la posicion del mouse.
	// A medida que se van agregando los puntos en el mapa se los guarda en un array de coordenadas.
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
			mapa.addMapMarker(marker);		
			}
		}
		agregarRutas();
	}
	

	public void agregarRutas(){
		for(AristaGrafica arista: aristasAGM){
			arista.poligono(mapa);
		}
	}
	
	//Calcula la distancia entre dos coordenadas georgraficas
	public static double calcularDistanciaCoordenadas(Coordinate origen, Coordinate destino) {  
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
	
	//Carga el grafo y agrega aristas entre todos los vertices(grafo completo)
	  public GrafoConPesos cargarGrafo(ArrayList<Coordinate> coordenadas) {
	        GrafoConPesos grafo = new GrafoConPesos(coordenadas.size());
	        for (int i = 0; i < coordenadas.size(); ++i) {
	            for (int j = i; j < coordenadas.size(); ++j) {
	            	if(i!=j){
	                 grafo.agregarArista(i, j, calcularDistanciaCoordenadas(coordenadas.get(i), coordenadas.get(j)));
	            	}
	            }  
	       }
	     return grafo;
	    }
	
	  
	public ArrayList<Coordinate> getCoordenadas() {
		return coordenadas;
		
	}

	

	@Override
	public String toString() {
			String coord="";
		int i=0;
		while(i<coordenadas.size()){
		for(Coordinate e :coordenadas){
			coord=coord+"Localidad N°="+i+" Latitud:"+ e.getLat()+" Longitud:"+e.getLon()+"\n";
			i++;
		}
		}
		return coord;
	}

	public void setCoordenadas(ArrayList<Coordinate> coordenadas) {
		this.coordenadas = coordenadas;
	}
	
	
	
	
	
	
	
	
	
}

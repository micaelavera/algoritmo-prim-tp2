package inteligencia;

import org.openstreetmap.gui.jmapviewer.Coordinate;

public class Coordenada{

	private double latitud;
	private double longitud;
	
	public Coordenada(){
		latitud=0.0;
		longitud=0.0;
	}
	public Coordenada(double lat,double lon){
		latitud=lat;
		longitud=lon;
	}

	public double getLatitud() {
		return latitud;
	}

	public double getLongitud() {
		return longitud;
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
	
	
}

package interfaz;

import java.util.ArrayList;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.interfaces.MapPolygon;

public class AristaGrafica {

	private Coordenada origen;
	private Coordenada destino;
	
	public AristaGrafica(Coordenada a,Coordenada b){
		verificarCoordenadas(a, b,"agregar coordenadas");
		this.origen=a;
		this.destino=b;
	}

	private void verificarCoordenadas(Coordenada a, Coordenada b,String accion) {
		if(a==null || b==null){
			 throw new RuntimeException("Se intento "+accion+" nulas");
		}
	}
	
	public void agregarRuta(JMapViewer mapa) {
        ArrayList<Coordinate> rutaMapa = new ArrayList<> ();
        rutaMapa.add(new Coordinate(origen.getLatitud(),origen.getLongitud()));
        rutaMapa.add(new Coordinate(destino.getLatitud(),destino.getLongitud()));
        rutaMapa.add(new Coordinate(origen.getLatitud(),origen.getLongitud()));

        MapPolygon polygon = new MapPolygonImpl(rutaMapa);
        /*-- Crea un poligono que en realidad va a tener --*/
		/*-- forma de una linea --*/
        mapa.addMapPolygon(polygon);

    }
}

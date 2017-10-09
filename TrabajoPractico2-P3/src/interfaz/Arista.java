package interfaz;

import java.util.ArrayList;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.interfaces.MapPolygon;

public class Arista {

	private Coordinate origen;
	private Coordinate destino;
	
	
	public Arista(Coordinate a,Coordinate b){
		verificarCoordenadas(a, b);
		this.origen=a;
		this.destino=b;
	}


	private void verificarCoordenadas(Coordinate a, Coordinate b) {
		if(a==null || b==null){
			 throw new RuntimeException("Null coordinate");
		}
	}
	
	
	
	public void poligono(JMapViewer mapa) {
        ArrayList<Coordinate> coordenadas2 = new ArrayList<> ();
        coordenadas2.add(origen);
        coordenadas2.add(destino);
        coordenadas2.add(origen);

        MapPolygon polygon = new MapPolygonImpl(coordenadas2);
        mapa.addMapPolygon(polygon);

    }
}

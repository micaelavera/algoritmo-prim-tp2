package interfaz;

import java.util.ArrayList;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.interfaces.MapPolygon;

public class AristaGrafica {

	private Coordinate origen;
	private Coordinate destino;
	
	public AristaGrafica(Coordinate a,Coordinate b){
		verificarCoordenadas(a, b,"agregar coordenadas");
		this.origen=a;
		this.destino=b;
	}

	private void verificarCoordenadas(Coordinate a, Coordinate b,String accion) {
		if(a==null || b==null){
			 throw new RuntimeException("Se intento "+accion+" nulas");
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

package interfaz;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.swing.JCheckBox;
import javax.swing.JTextField;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.interfaces.MapPolygon;

import grafo.Arista;
import grafo.GrafoConPesos;


import grafo.Prim;
import interfaz.VentanaPrincipal;

public class Mapa {
	
	private ArrayList<Coordinate> coordenadas;
	private ArrayList<AristaGrafica> aristasAGM;
	private JMapViewer mapa;
	private GrafoConPesos AGM,grafoCompleto;
	Localidad localidad;
	
	private double costo;


	public Mapa(VentanaPrincipal ventana){
		coordenadas=new ArrayList<>();
//		aristasAGM=new ArrayList<>();
		mapa=ventana.getMapa();
		localidad=new Localidad();
	
	}
	

	

	// Se a�ade al mapa un punto nuevo, en la posicion del mouse.
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
		
		}
	
	static void dibujarLineaEntrePuntos(Coordinate inicio, Coordinate destino, JMapViewer mapa) {
		List<Coordinate> ruta = new ArrayList<Coordinate>(Arrays.asList(inicio, destino, destino));
		MapPolygonImpl rutaMap = new MapPolygonImpl(ruta);
		
		rutaMap.setColor(Color.BLUE);
		mapa.addMapPolygon(rutaMap);
	}

//	//Calcula la distancia entre dos coordenadas georgraficas
	public static double calcularDistanciaCoordenadas(Coordinate origen, Coordinate destino) {  
       double radioTierra = 6371;//en kil�metros  
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

//	//Carga el grafo y agrega aristas entre todos los vertices(grafo completo) y les asigna el peso correspondiente
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

	     
	
	public double calcularCosto(Coordinate cor1,Coordinate cor2){
		double costo=100*calcularDistanciaCoordenadas(cor1,cor2);
		if(calcularDistanciaCoordenadas(cor1,cor2)>200){
			costo+=200;
		}
		return costo;
		
		
		
	}
	  
	  
	  public void toArista(GrafoConPesos gp,JMapViewer mapa){
		  
//		  ArrayList<AristaGrafica> arista=new ArrayList<>(gp.vertices());
		  for(int i=0;i<gp.vertices();++i){
			  Set<Integer> vecinos=gp.vecinos(i);
			  Coordinate cor1=coordenadas.get(i);
			  for(Integer j: vecinos){
				  Coordinate cor2=coordenadas.get(j);
				  AristaGrafica arista=new AristaGrafica(cor1,cor2);
				  calcularCosto(cor1,cor2);
				  arista.poligono(mapa);
				  costo=calcularCosto(cor1,cor2);
				  }
				  
			  }
		  }
	  
	  public double getCosto()
	  {
		  return costo;
	  }
		
		  
	  
//		private void llenaGrafoConAristas(GrafoConPesos grafo)
//		{
//			for (int i = 0; i <grafo.vertices()-1; i++) {
//				grafo.obtenerVertice(i).setId(i);
//				for (int j = i+1; j < grafo.vertices(); j++) {
//					grafo.obtenerVertice(j).setId(j);
//					grafo.agregarArista(grafo.,grafo.obtenerVertice(j));
//				}
//			}
//		}
		
//	
//	  private ArrayList<AristaGrafica> toArista(GrafoConPesos gp) {
//	        ArrayList<AristaGrafica> ret = new ArrayList<AristaGrafica>(gp.vertices());
//	        for (int i = 0; i < gp.vertices(); i++) {
//	            Set<Integer> vecinos = gp.vecinos(i);
//	            Coordinate cor1 = coordenadas.get(i);
//
//	            for (Integer j : vecinos) {
//	             
//	             
//	                Coordinate cor2 = coordenadas.get(j);
//	                AristaGrafica aristaGrafica = new AristaGrafica (cor1, cor2);
//	                if (!ret.contains(aristaGrafica)) {
//	                    ret.add(aristaGrafica);
//	                }
//	                }
//	            }
//
//	         
//	    
//	          
//	        return ret;
//	    }
////	  
//	  public ArrayList<AristaGrafica> muestraMapa(){
//		  AGM=Prim.AGM(cargarGrafo(coordenadas));
//		  aristasAGM = toArista(AGM);
//		  return aristasAGM;
//	  }
	public ArrayList<Coordinate> getCoordenadas() {
		return coordenadas;
		
	}
	
	@Override
	public String toString() {
		String coord="";
		int i=0;
		while(i<coordenadas.size()){
		for(Coordinate e :coordenadas){
			coord=coord+"Localidad N�="+i+" Latitud:"+ e.getLat()+" Longitud:"+e.getLon()+"\n";
			i++;
		}
		}
		return coord;
	}

	public void setCoordenadas(ArrayList<Coordinate> coordenadas) {
		this.coordenadas = coordenadas;
	}
	
	
	
	
	
	
	
	
	
}

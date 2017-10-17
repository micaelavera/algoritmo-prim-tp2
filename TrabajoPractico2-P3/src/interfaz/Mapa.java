package interfaz;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Set;

import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;

import grafo.GrafoConPesos;
import grafo.Prim;

public class Mapa {
	
	private ArrayList<Coordenada> coordenadas;
	private GrafoConPesos AGM,grafoCompleto;
	private double costo;
	
	public Mapa(){
		coordenadas=new ArrayList<>();
		costo=0.0;
	}
	
	// Se añade al mapa un punto nuevo, en la posicion del mouse.
	// A medida que se van agregando los puntos en el mapa se los guarda en un array de coordenadas.
	public void agregarLocalidad(JMapViewer mapa){  
		if(coordenadas!=null){
			Color color;
			for(int i=0;i<coordenadas.size();i++){
				if(i>0){
					color=Color.RED; 
				}else{
					color=Color.GREEN; 
				}
			Coordenada puntoActual=coordenadas.get(i);
			MapMarkerDot marker=new MapMarkerDot(puntoActual.getLatitud(),puntoActual.getLongitud());
			marker.getStyle().setBackColor(color);
			mapa.addMapMarker(marker);		
			}
		}
	}
	
	public boolean obtenerArbolGeneradorMinimo(JMapViewer mapa) throws Exception{
		try {
			cargarGrafoAGM(mapa);
			return true;
		} catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	private void cargarGrafoAGM(JMapViewer mapa) throws Exception{
		if(getCoordenadas().size()<2){
			throw new Exception("Debe ingresar al menos dos localidades");
		}
		grafoCompleto=cargarGrafo(getCoordenadas());
		AGM=Prim.AGM(grafoCompleto);
		cargarAristas(AGM,mapa);
	}
		  
	//Carga el grafo y agrega aristas entre todos los vertices(grafo completo) y les asigna el peso correspondiente
	  private GrafoConPesos cargarGrafo(ArrayList<Coordenada> coordenadas) {
		  GrafoConPesos grafo = new GrafoConPesos(coordenadas.size());
	      for (int i = 0; i < coordenadas.size(); ++i) {
	    	  for (int j = i; j < coordenadas.size(); ++j) {
	            	if(i!=j){
	                 grafo.agregarArista(i, j, calcularDistanciaCoordenadas(coordenadas.get(i), coordenadas.get(j)));            		  
	                 System.out.println(calcularDistanciaCoordenadas(coordenadas.get(i), coordenadas.get(j)));
	                 calcularCosto(coordenadas.get(i),coordenadas.get(j));   
	            	}
	          }  
	      }
	      return grafo;
	   }
	  
	  
	  private void cargarAristas(GrafoConPesos grafoPesado,JMapViewer mapa){
		  for(int i=0;i<grafoPesado.vertices();++i){
			  Set<Integer> vecinos=grafoPesado.vecinos(i);
			  Coordenada cor1=coordenadas.get(i);
			  for(Integer j: vecinos){
				  Coordenada cor2=coordenadas.get(j);
				  AristaGrafica arista=new AristaGrafica(cor1,cor2);
				  arista.agregarRuta(mapa);
			  }
		  }
		  
	  }
		
	//Calcula la distancia entre dos coordenadas georgraficas
	private static double calcularDistanciaCoordenadas(Coordenada origen, Coordenada destino) {  
	     double radioTierra = 6371;//en kilómetros  
	     double dLat = Math.toRadians(destino.getLatitud() - origen.getLatitud());  
	     double dLng = Math.toRadians(destino.getLongitud()- origen.getLongitud());  
	     double sindLat = Math.sin(dLat / 2);  
	     double sindLng = Math.sin(dLng / 2);  
	     double va1 = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)  
	                * Math.cos(Math.toRadians(origen.getLatitud())) * Math.cos(Math.toRadians(destino.getLatitud()));  
	     double va2 = 2 * Math.atan2(Math.sqrt(va1), Math.sqrt(1 - va1));  
	     double distancia = radioTierra * va2;  
	  
	     return distancia;  
	 }  	
	  
		private double calcularCosto(Coordenada cor1,Coordenada cor2){
			costo=100*calcularDistanciaCoordenadas(cor1,cor2);
			if(calcularDistanciaCoordenadas(cor1,cor2)>200){
				costo+=50;
			} 
			else if (!(cor1.getProvincia().equals(cor2.getProvincia()))){
				costo+=80;
			}else{
				costo=100*calcularDistanciaCoordenadas(cor1,cor2);
			}
			return costo;
			
		}

	public ArrayList<Coordenada> getCoordenadas(){
		return coordenadas;
	}
	
	public double getCosto() {
		return costo;
	}
	
}


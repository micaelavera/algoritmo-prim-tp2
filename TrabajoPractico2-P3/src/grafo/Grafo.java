package grafo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Grafo {

	//Representacion del grafo por lista de vecinos
	private ArrayList< HashSet<Integer> > vecinos;
	
	//El numero de vertices queda fijado en el constructor
	public Grafo(int cantVertices){
		vecinos=new ArrayList<HashSet<Integer>>(cantVertices);
		
		for(int i=0;i<cantVertices;++i){
			vecinos.add(new HashSet<Integer>());
		}
	}
	
//	// Algoritmo de Prim
//		public static GrafoPesado AGM(GrafoPesado grafo,Menu Menu){
//			GrafoPesado GRAFO = new GrafoPesado(grafo.vertices());
//			Set<Integer> visitados = new HashSet<> ();
//			visitados.add(0); // Cualquiera
//
//			for(int i=0; i<grafo.vertices()-1; ++i){
//				Arista a = menorArista(grafo, visitados); // De un amarillo a un negro
//				GRAFO.agregarArista(a.origen, a.destino, a.peso);
//				visitados.add(a.destino);
//				if(i%2==0)
//					Menu.setProgress("Calculando AGM...",(i*100)/grafo.vertices()-1);
//			}
//			
//			return GRAFO;
//		}
	
	public void agregarArista(int i,int j){
		verificarVertice(i,"agregar una arista");
		verificarVertice(j,"agregar una arista");
		verificarDistintos(i,j,"agregar una arista");
		
		vecinos.get(i).add(j);
		vecinos.get(j).add(i);
	}
	
	public boolean existeArista(int i,int j){
		verificarVertice(i,"consultar una arista");
		verificarVertice(j,"consultar una arista");
		
		return vecinos.get(i).contains(j);
	}
	
	public void eliminarArista(int i,int j){
		verificarVertice(i,"eliminar una arista");
		verificarVertice(j,"eliminar una arista");
		
		vecinos.get(i).remove(j);
		vecinos.get(j).remove(i);
	}
	
	//Grado de un v�rtice (cantidad de vecinos) 
	public int grado(int i){
		return vecinos.get(i).size();
	}
	
	// Conjunto de vecinos de un v�rtice
	@SuppressWarnings("unchecked")
	public Set<Integer> vecinos(int i){
		verificarVertice(i,"consultar los vecinos");
		return (Set<Integer>) vecinos.get(i).clone();
	}

	//Cantidad de v�rtices
	public int vertices(){
		return vecinos.size();
	}
	
	//Lanza una excepci�n si el indice v esta fuera de rango para los vertices
	private void verificarVertice(int v, String accion){
		if(v<0 || v>=vertices()){
			throw new IllegalArgumentException("Se intent�"+ accion +" con �ndice inexistente. V�rtice=" + v );
		}
	}
	//Lanza una excepci�n si los �ndices son iguales
	private void verificarDistintos(int i,int j,String accion){
		if(i==j){
			throw new IllegalArgumentException("Se intent� "+ accion +" con los dos v�rtices iguales. V�rtice=" + i );
		}
	}

}

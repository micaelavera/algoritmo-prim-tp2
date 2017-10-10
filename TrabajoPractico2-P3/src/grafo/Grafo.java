package grafo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Grafo {

	//Representación del grafo por lista de vecinos
	private ArrayList< HashSet<Integer> > vecinos;
	
	//El número de vértices queda fijado en el constructor
	public Grafo(int cantVertices){
		vecinos=new ArrayList<HashSet<Integer>>(cantVertices);
		
		for(int i=0;i<cantVertices;++i){
			vecinos.add(new HashSet<Integer>());
		}
	}
	
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
	
	//Grado de un vértice (cantidad de vecinos) 
	public int grado(int i){
		return vecinos.get(i).size();
	}
	
	// Conjunto de vecinos de un vértice
	@SuppressWarnings("unchecked")
	public Set<Integer> vecinos(int i){
		verificarVertice(i,"consultar los vecinos");
		return (Set<Integer>) vecinos.get(i).clone();
	}

	//Cantidad de vértices
	public int vertices(){
		return vecinos.size();
	}
	
	//Lanza una excepción si el indice v esta fuera de rango para los vertices
	private void verificarVertice(int v, String accion){
		if(v<0 || v>=vertices()){
			throw new IllegalArgumentException("Se intentó"+ accion +" con índice inexistente. Vértice=" + v );
		}
	}
	//Lanza una excepción si los índices son iguales
	private void verificarDistintos(int i,int j,String accion){
		if(i==j){
			throw new IllegalArgumentException("Se intentó "+ accion +" con los dos vértices iguales. Vértice=" + i );
		}
	}
	
	
	public static void main(String[] args)
	{
		// Algo de testing
		Grafo grafo = new Grafo(6);
		grafo.agregarArista(1, 4);
		grafo.agregarArista(1, 3);
		grafo.agregarArista(3, 2);
		
		System.out.println(grafo.existeArista(1, 4) ? "Ok" : "Todo mal!");
		System.out.println(grafo.existeArista(2, 3) ? "Ok" : "Todo mal!");
		System.out.println(grafo.existeArista(2, 4) ? "Todo mal!" : "Ok");
		
		try
		{
			grafo.existeArista(-1, 3);
			System.out.println("Todo mal!");
		}
		catch(IllegalArgumentException ex)
		{
			System.out.println("Ok");
		}
		catch(Exception ex)
		{
			System.out.println("Todo mal!");
		}
	}

}

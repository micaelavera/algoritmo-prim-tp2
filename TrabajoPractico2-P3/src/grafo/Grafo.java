package grafo;

import java.util.ArrayList;
import java.util.HashSet;

public class Grafo {

	//Representación del grafo por lista de vecinos
	private ArrayList< HashSet<Integer> > vecinos;
	
	//El numero de vértices queda fijado en el constructor
	public Grafo(int cantVertices){
		vecinos=new ArrayList<HashSet<Integer>>();
		
		for(int i=0;i<vecinos.size();i++){
			vecinos.add(new HashSet<Integer>());
		}
	}
	
	public void agregarArista(int i,int j){
		vecinos.get(i).add(j);
		vecinos.get(j).add(i);
	}
	
	public boolean existeArista(int i,int j){
		return vecinos.get(i).contains(j);
	}
	
	public void eliminarArista(int i,int j){
		vecinos.get(i).remove(j);
		vecinos.get(j).remove(i);
	}
	
	public int grado(int i){
		return vecinos.get(i).size();
	}
	
}

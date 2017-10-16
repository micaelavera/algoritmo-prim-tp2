package grafo;

import java.util.HashSet;
import java.util.Set;

public class Prim {
	
	// Algoritmo de Prim
	public static GrafoConPesos AGM(GrafoConPesos grafo){
		GrafoConPesos _grafo=new GrafoConPesos(grafo.vertices());
		Set<Integer> verticesVisitados=new HashSet<>();
		verticesVisitados.add(0);
		
		for(int i=0;i<grafo.vertices()-1;++i){
			Arista a = menorArista(grafo, verticesVisitados); 
			_grafo.agregarArista(a.getOrigen(), a.getDestino(), a.getPeso());
			verticesVisitados.add(a.getDestino());
		}
		return _grafo;
	}

	public static Arista menorArista(GrafoConPesos grafo,Set<Integer> verticesVisitados) {
		Arista ret = new Arista(0, 0, Double.MAX_VALUE);
		for(Integer i: verticesVisitados)
			for (Integer j : grafo.vecinos(i))
				if(!verticesVisitados.contains (j)){
					if( grafo.consultarPeso(i, j) < ret.getPeso() )
						ret = new Arista(i, j, grafo.consultarPeso(i, j));
				}
			return ret;
		}
}

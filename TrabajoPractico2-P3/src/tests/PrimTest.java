package tests;

import static org.junit.Assert.*;
import grafo.Arista;
import grafo.GrafoConPesos;
import grafo.Prim;


import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class PrimTest {

	@Test
	public void verticeVisitadoTest(){
		GrafoConPesos grafo = instancia();

		Set<Integer> visitados = new HashSet<> ();
		visitados.add(0);

		Arista arista = Prim.menorArista(grafo, visitados);
		assertEquals(new Arista(0, 1, 5), arista);
	}
	
	@Test
	public void cuatroVerticesVisitadosTest(){
		GrafoConPesos grafo = instancia();
		Set<Integer> visitados = new HashSet<> ();
		visitados.add(0);
		visitados.add(1);
		visitados.add(2);
		visitados.add(4);
		
		Arista arista = Prim.menorArista(grafo, visitados);
		assertEquals(new Arista(2, 3, 5), arista);
	}
	

	private GrafoConPesos instancia(){
		GrafoConPesos grafo = new GrafoConPesos(5);
		grafo.agregarArista(0, 1, 5);
		grafo.agregarArista(0, 2, 6);
		grafo.agregarArista(0, 3, 10);
		grafo.agregarArista(1, 2, 1);
		grafo.agregarArista(2, 3, 5);
		grafo.agregarArista(1, 4, 4);
		grafo.agregarArista(2, 4, 10);
		return grafo;
	}
}

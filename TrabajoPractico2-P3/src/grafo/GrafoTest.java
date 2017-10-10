package grafo;

import static org.junit.Assert.*;

import org.junit.Test;

public class GrafoTest {

	@Test(expected = IllegalArgumentException.class)
	public void verticeNegativoTest(){
		Grafo grafo = new Grafo(4);
		grafo.agregarArista(-1, 2);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void verticeExcedidoTest(){
		Grafo grafo = new Grafo(4);
		grafo.agregarArista(2, 4);
	}


	@Test(expected = IllegalArgumentException.class)
	public void sinBuclesTest(){
		Grafo grafo = new Grafo(4);
		grafo.agregarArista(2, 2);
	}
	
	@Test
	public void aristaExistenteTest(){
		Grafo grafo=new Grafo(4);
		grafo.agregarArista(1, 2);
		assertTrue(grafo.existeArista(1, 2));
	}	
	
	@Test
	public void aristaInexistenteTest(){
		Grafo grafo=new Grafo(4);
		grafo.agregarArista(1, 2);
		assertFalse(grafo.existeArista(0, 1));
	}
	
	@Test
	public void ordenIrrelevanteTest(){
		Grafo grafo=new Grafo(4);
		grafo.agregarArista(1, 2);
		assertTrue(grafo.existeArista(2, 1));
	}
	

}

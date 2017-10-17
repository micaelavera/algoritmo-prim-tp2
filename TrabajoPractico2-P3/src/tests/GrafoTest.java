package tests;

import static org.junit.Assert.*;
import grafo.Grafo;

import java.util.Set;

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
		grafo.agregarArista(0, 2);
		assertTrue(grafo.existeArista(0, 2));
	}	
	
	@Test
	public void aristaInexistenteTest(){
		Grafo grafo=new Grafo(4);
		grafo.agregarArista(0, 2);
		assertFalse(grafo.existeArista(0, 3));
	}
	
	@Test
	public void ordenIrrelevanteTest(){
		Grafo grafo=new Grafo(4);
		grafo.agregarArista(0,3);
		assertTrue(grafo.existeArista(3,0));
	}
	
	@Test
	public void agregarAristaExistenteTest(){
		Grafo grafo=new Grafo(4);
		grafo.agregarArista(0, 3);
		grafo.agregarArista(0, 3);
		assertTrue(grafo.existeArista(0, 3));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void eliminarAristaConVerticeNegativoTest(){
		Grafo grafo=new Grafo(4);
		grafo.eliminarArista(1,-2);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void eliminarAristConVerticeExcedidoTest(){
		Grafo grafo=new Grafo(4);
		grafo.eliminarArista(4, 1);
	}
	
	@Test
	public void eliminarAristaExistenteTest(){
		Grafo grafo=new Grafo(4);
		grafo.agregarArista(0, 3);
		grafo.eliminarArista(0, 3);
		assertFalse(grafo.existeArista(0, 3));
	}
	
	@Test
	public void eliminarAristaInexistenteTest(){
		Grafo grafo=new Grafo(4);
		grafo.eliminarArista(0, 3);
		assertFalse(grafo.existeArista(0, 3));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void gradoDeVerticeNegativoTest(){
		Grafo grafo=diamanteConVerticeAislado();
		grafo.grado(-1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void gradoDeVerticeExcedidoTest(){
		Grafo grafo=diamanteConVerticeAislado();
		grafo.grado(5);
	}
	
	@Test
	public void gradoTest(){
		Grafo grafo=diamanteConVerticeAislado();
		assertEquals(2,grafo.grado(0));
		assertEquals(0,grafo.grado(1));
		assertEquals(3,grafo.grado(2));
		assertEquals(3,grafo.grado(3));
	}
	
	@Test
	public void vecinosTest(){
		Grafo grafo=diamanteConVerticeAislado();
		Assert.setsIguales(new int[]{0,3,4},grafo.vecinos(2));
	}
		

	@Test
	public void vecinosVaciosTest(){
		Grafo grafo=diamanteConVerticeAislado();
		grafo.eliminarArista(0, 3);
		Assert.setsIguales(new int[]{2}, grafo.vecinos(0));
	}

	@Test
	public void unSoloVecinoTest(){
		Grafo grafo=diamanteConVerticeAislado();
		Set<Integer> vecinos=grafo.vecinos(3);
		vecinos.retainAll(grafo.vecinos(0));
		
		//El Set que retorna grafo.vecinos() es una copia!
		Assert.setsIguales(new int[]{0,2,4},grafo.vecinos(3));
	}

	private Grafo diamanteConVerticeAislado() {
		Grafo grafo=new Grafo(5);
		grafo.agregarArista(0, 2);
		grafo.agregarArista(0, 3);
		grafo.agregarArista(2, 3);
		grafo.agregarArista(2, 4);
		grafo.agregarArista(3, 4);
		return grafo;
	}
	
	
	
	
	
	
}

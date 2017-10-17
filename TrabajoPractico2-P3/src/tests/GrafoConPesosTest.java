package tests;

import static org.junit.Assert.*;
import grafo.GrafoConPesos;

import org.junit.Test;

public class GrafoConPesosTest {

	@Test(expected = IllegalArgumentException.class)
	public void pesoNegativoTest(){
		GrafoConPesos grafoPesado = new GrafoConPesos(4);
		grafoPesado.agregarArista(1, 2, -2);
	}
	
	@Test
	public void pesoPositivoTest(){
		GrafoConPesos grafoPesado = new GrafoConPesos(4);
		grafoPesado.agregarArista(1, 2, 2);
	}
	
	
	@Test
	public void aristaInexistenteConPesoTest(){
		GrafoConPesos grafoPesado=new GrafoConPesos(4);
		grafoPesado.agregarArista(0, 2, 3);
		assertFalse(grafoPesado.existeArista(0, 3));
	}
	
	
	@Test
	public void consultarPesoExistenteTest(){
		GrafoConPesos grafoPesado = new GrafoConPesos(4);
		grafoPesado.agregarArista(1, 2, 2);
		grafoPesado.agregarArista(1, 3, 3);
		assertEquals(2,grafoPesado.consultarPeso(1, 2), 0);
		assertEquals(3,grafoPesado.consultarPeso(1, 3), 0);
	}
	

}
package grafo;

import static org.junit.Assert.*;

import org.junit.Test;

public class GrafoConPesosTest {

	@Test(expected = IllegalArgumentException.class)
	public void pesoNegativoTest(){
		GrafoConPesos grafoPesado = new GrafoConPesos(4);
		grafoPesado.agregarArista(1, 2, -2);
	}
	
	@Test
	public void consultarPesoTest(){
		GrafoConPesos grafoPesado = new GrafoConPesos(4);
		grafoPesado.agregarArista(1, 2, 2);
		grafoPesado.agregarArista(1, 3, 3);
		assertEquals(2,grafoPesado.consultarPeso(1, 2), 0);
		assertEquals(3,grafoPesado.consultarPeso(1, 3), 0);
	}
	
	

}

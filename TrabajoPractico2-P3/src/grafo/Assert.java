package grafo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Set;

public class Assert {

	//Determina si el Set contiene los elementos del arreglo
	public static void setsIguales(int[] esperado,Set<Integer> obtenido){
		assertEquals(esperado.length,obtenido.size());
		for(Integer elemento: esperado)
			assertTrue(obtenido.contains(elemento));
		}
	}




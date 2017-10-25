package tests;

import static org.junit.Assert.*;
import interfaz.Coordenada;
import interfaz.Mapa;

import org.junit.Test;

public class MapaTest {

	@Test
	public void calcularDistanciaExacta(){
		Mapa mapa=inicializarMapa();
		double distancia=Mapa.calcularDistanciaCoordenadas(mapa.getCoordenadas().get(0), mapa.getCoordenadas().get(1));
		assertEquals(371.76283992918457,distancia,0);
	}

	@Test
	public void calcularCostoExacto(){
		Mapa mapa=inicializarMapa();
		double costo=Mapa.calcularCostos(mapa.getCoordenadas().get(0), mapa.getCoordenadas().get(1));
		assertEquals(18718.141996459228,costo,0);
	}
	
	// TODO: calcularcostoAGM, 
//	@Test
//	public void calcularCostoAGM(){
//		
//		Mapa mapaAGM=inicializarMapa();
//	}
	
	
	private Mapa inicializarMapa() {
		Mapa mapa=new Mapa();
		mapa.getCoordenadas().add(new Coordenada("Buenos Aires",
				 -34.52133782929332,
			     -58.70068073272705));
		mapa.getCoordenadas().add(new Coordenada( "Entre Ríos",
			    -31.201365, 
			    -59.170625));
		
		mapa.getCoordenadas().add(new Coordenada("Salta",
				-25.305592, 
				-65.497899 ));
		return mapa;
	}
	
}

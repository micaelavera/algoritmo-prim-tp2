package grafo;

import java.util.Objects;

public class Arista {
	
	public int origen;
	private int destino;
	private double peso;
	
	public Arista(int origen,int destino,double peso){
		this.origen=origen;
		this.destino=destino;
		this.peso=peso;
	}
	
	public int getOrigen(){
		return origen;
	}

	public int getDestino(){
		return destino;
	}

	public double getPeso(){
		return peso;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (obj == null)
			return false;

		if (obj instanceof Arista) {
			Arista otra = (Arista) obj;
			if (Objects.equals (origen, otra.origen) && Objects.equals (destino, otra.destino))
				return true;
		}
		return false;
	}
}

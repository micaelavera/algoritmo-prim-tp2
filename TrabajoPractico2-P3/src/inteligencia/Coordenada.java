package inteligencia;

import java.io.Serializable;

public class Coordenada implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String nombre;
	private double latitud;
	private double longitud;
	
	public Coordenada(){
		this.latitud=0.0;
		this.longitud=0.0;
	}
	public Coordenada(String nombre,double lat,double lon){
		this.nombre=nombre;
		this.latitud=lat;
		this.longitud=lon;
	}

	public String getNombre() {
		return nombre;
	}
	public double getLatitud() {
		return latitud;
	}

	public double getLongitud() {
		return longitud;
	}
	
	public Coordenada clonar(){
		return new Coordenada(nombre, latitud, longitud);
	}
	@Override
	public String toString() {
		return "Coordenada [nombre=" + nombre + ", latitud=" + latitud
				+ ", longitud=" + longitud + "]";
	}
	
	@Override
	public boolean equals(Object o){
		if ( !(o instanceof Coordenada) )
			throw new IllegalArgumentException("La coordenada que ingreso no es válida");
		Coordenada coord2 = (Coordenada) o;
		return latitud == coord2.getLatitud() && longitud == coord2.getLongitud();
	}
}

package interfaz;

public class Coordenada {

	private String provincia;
	private double latitud;
	private double longitud;
	
	public Coordenada(String n,double lat,double lon){
		provincia=n;
		latitud=lat;
		longitud=lon;
	}
	
	public double getLatitud() {
		return latitud;
	}

	public double getLongitud() {
		return longitud;
	}

	@Override
	public String toString(){
		return "Coord: { Nombre: " + provincia +"; Latitud: " + latitud + "; Longitud: " + longitud + " }";
	}
	
	@Override
	public boolean equals(Object o){
		if ( !(o instanceof Coordenada) )
			throw new IllegalArgumentException("El parametro ingresado no es una Coordenada");
		Coordenada coord2 = (Coordenada) o;
		return latitud == coord2.getLatitud() && longitud == coord2.getLongitud() ;
	}

	public String getProvincia() {
		return provincia;
	}
}

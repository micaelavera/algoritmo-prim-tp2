package grafo;

import java.util.Set;

import grafo.Grafo;

public class GrafoConPesos 
{
	private Grafo grafo;
	private double[][] pesos;
	
	public GrafoConPesos(int n){
		grafo=new Grafo(n);
		pesos=new double[n][n];
	}
	
	public void agregarArista(int i,int j, double peso){
		grafo.agregarArista(i, j);
		verificarPeso(peso,"agregar un peso");
		pesos[i][j]=peso;
		pesos[j][i]=peso;
	}

	public boolean existeArista(int i, int j){
		return grafo.existeArista(i, j);
	}

    public double consultarPeso(int i, int j){
		verificarArista(i, j,"consultar el peso");
		return pesos[i][j];		
	}

 	public int vertices(){
 		return grafo.vertices();
 	}
 	
 	public Set<Integer> vecinos(int i){
		return grafo.vecinos(i);
	}

    //Lanza una excepcion si se agrega arista con un peso negativo
    private void verificarPeso(double peso,String accion){
    	if (peso<0){
    		throw new IllegalArgumentException("Se intento "+ accion +" negativo.Peso: "+peso);
    	}
    }
    
  //Lanza una excepcion si se consulta el peso de una arista inexistente
	private void verificarArista(int i, int j, String accion) {
		if(!grafo.existeArista(i, j)){
			throw new IllegalArgumentException("Se intento "+ accion+ " de una arista inexistente. Vertices:" + i + ", " + j);
		}
	}
}
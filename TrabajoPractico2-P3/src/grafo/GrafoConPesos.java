package grafo;

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
		pesos[i][j]=peso;
		pesos[j][i]=peso;
	}

	public boolean existeArista(int i, int j){
		return grafo.existeArista(i, j);
	}

    public double getPeso(int i, int j){
		verificarArista(i, j,"consultar el peso");
		return pesos[i][j];		
	}

  //Lanza una excepcion si se consulta el peso de una arista inexistente
	private void verificarArista(int i, int j, String accion) {
		if(!grafo.existeArista(i, j)){
			throw new IllegalArgumentException("Se intento "+ accion+ " de una arista inexistente. Vertices:" + i + ", " + j);
		}
	}
}
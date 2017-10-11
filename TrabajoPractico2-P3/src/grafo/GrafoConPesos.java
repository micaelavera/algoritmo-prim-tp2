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

}

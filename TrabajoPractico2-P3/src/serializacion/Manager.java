package serializacion;

import interfaz.Coordenada;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Manager {
	private Gson gson;
	private String nombreArchivo;
	private ArrayList<Coordenada> listaCoordenadas;
		
	public Manager(String archivo)throws IOException{
		gson=new Gson();
		nombreArchivo=archivo;
		listaCoordenadas=this.desserializaJson(this.nombreArchivo);
	}

		
	// Desserializa y retorna una lista de coordenadas
	private ArrayList<Coordenada> desserializaJson(String nombreArchivo)throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(nombreArchivo));
		Type collectionType=new TypeToken<ArrayList<Coordenada>>(){}.getType();
		ArrayList<Coordenada> listaCoordenadas=gson.fromJson(br,collectionType);
		if(listaCoordenadas==null){
				listaCoordenadas=new ArrayList<Coordenada>();
		}
		this.asignaIds(listaCoordenadas);
		return listaCoordenadas;
	}

	private void asignaIds(ArrayList<Coordenada> listaCoordenadas){
		for(int i=0;i < listaCoordenadas.size();i++){
			listaCoordenadas.get(i);
		}	
	}
	
	public ArrayList<Coordenada> obtenerVertices(){
			return this.listaCoordenadas;
	}
}

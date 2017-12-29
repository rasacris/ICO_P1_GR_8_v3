package es.ufv.ico.generator;
//Importamos clases que se usaran
import java.io.File;
import java.io.FileWriter;
import java.util.Calendar;

import es.ufv.ico.input.Mapa;
import es.ufv.ico.input.Nodo;

public class Escribir{

	public void imprimir(){
		Mapa mapa = new Mapa();
		Calendar cal = Calendar.getInstance();

		try{
			File archivo=new File("src/es/ufv/ico/output/GR-08"+  cal.getTime().getSeconds() +".txt");
			FileWriter escribir=new FileWriter(archivo,true); 
			escribir.write("GR-08");
			escribir.write("\n");
			escribir.write("Raúl Sacristán");
			escribir.write("\n");
			escribir.write("Sergio Torre");
			escribir.write("\n");
			escribir.write("Manuel Muriana");
			escribir.write("\n");
			int metodo = 0;
			switch (metodo) {
			case 0:
				escribir.write("Algoritmo usado: Dijsktra");
				escribir.write("\n");
				break;
			case 1:
				escribir.write("Algoritmo usado: AVARA");
				escribir.write("\n");
				break;
			case 2:
				escribir.write("Algoritmo usado: A*");
				escribir.write("\n");
				break;
			default:
				break;
			}

			Nodo nodo = new Nodo();
			escribir.write("****************************");
			escribir.write("\n");
			escribir.write("H1");




			escribir.write("\n");
			escribir.write("****************************");
			//Cerramos la conexion
			escribir.close();
		}
		catch(Exception e){
			System.out.println("Error al escribir");
		}
	}

}

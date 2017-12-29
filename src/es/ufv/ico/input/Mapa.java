package es.ufv.ico.input;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;


public class Mapa {

	ArrayList<Nodo> abiertos = new ArrayList<>();
	ArrayList<Nodo> cerrados = new ArrayList<>();


	public static ArrayList<Nodo> limpiar(ArrayList<Nodo> listaParaLimpiar){
		HashSet<Nodo> hashSet = new HashSet<Nodo>();
		listaParaLimpiar.clear();
		listaParaLimpiar.addAll(hashSet);
		return listaParaLimpiar;
	}


	public void busqueda(Nodo nodo_actual,int metodo){

		/*
		 * BUCLE (Repetir el proceso mientras ABIERTO ≠ vacío) 1. 
		 * NODO-ACTUAL = EXTRAE-PRIMERO(ABIERTO) 
		 * 2. *CERRADO* = NODO-ACTUAL 
		 * 3. Si ES-ESTADO-FINAL(ESTADO(NODO-ACTUAL)) devolver CAMINO(NODO-ACTUAL) (
		 * 4. Si no, FUNCION SUCESORES(NODO-ACTUAL)
		 * GESTIONA-COLA(ABIERTO,SUCESORES) FIN DE BUCLE Devuelve FALLO
		 */
		abiertos.clear();
		cerrados.clear();

		abiertos.add(nodo_actual);
		ArrayList<Nodo> actual = new ArrayList<>();


		while(!abiertos.isEmpty()){
			
			
			nodo_actual = extrae_primero(abiertos);
			
			
			cerrados.add(nodo_actual);
			
			
			if(es_estado_final(nodo_actual)){
				System.out.println("Nodos Visitados");
				actual.addAll(cerrados);
				imprimirNodos(actual);
				System.out.println("Camino:");
				camino(nodo_actual);
				return;
			}else{
				ArrayList<Nodo> sucesores = funcion_sucesores(nodo_actual);
				// *********************LA MADRE DEL CORDERO*********************
				gestionaCola(sucesores, metodo);
				
			}
		}

		System.out.println("No hay Solución");

	}

	public Nodo extrae_primero(ArrayList<Nodo> abiertos) {
		return abiertos.remove(0);
	}


	public void gestionaCola(ArrayList<Nodo> sucesores, int metodo) {

		Collections.reverse(sucesores);

		for(Nodo nodo : sucesores) {

			if(!abiertos.contains(nodo) || !cerrados.contains(nodo)){//CONTROL DE BUCLES
				
				switch (metodo) {
				case 0:
					//disjktra
					abiertos.add(nodo);
					break;
				case 1:
					//AVARA
					abiertos.add(nodo);
					abiertos = ordenarH1(abiertos);
					//abiertos = ordenarH2(abiertos);
					break;
				case 2:
					//A*
					abiertos.add(nodo);
					abiertos = ordenarF1(abiertos);
					//abiertos = ordenarF2(abiertos);
					break;
				default:
					break;
				}

			}
		}
	}


	public ArrayList<Nodo> ordenarF1(ArrayList<Nodo> abiertos) {
		int aux;
		for (int i = 0; i < abiertos.size()-1; i++) {
			for (int x = i + 1; x < abiertos.size(); x++){
				
				if (abiertos.get(x).getF1() < abiertos.get(i).getF1()) {
					aux = abiertos.get(i).getF1();
					abiertos.get(i).setCoste(abiertos.get(x).getF1());
					abiertos.get(x).setCoste(aux);
				}
			}
		}
		return abiertos;
	}


	public ArrayList<Nodo> ordenarH1(ArrayList<Nodo> abiertos) {
		int aux;
		for (int i = 0; i < abiertos.size()-1; i++) {
			for (int x = i + 1; x < abiertos.size(); x++){
				
				if (abiertos.get(x).getH1() < abiertos.get(i).getH1()) {
					aux = abiertos.get(i).getH1();
					abiertos.get(i).setCoste(abiertos.get(x).getH1());
					abiertos.get(x).setCoste(aux);
				}
			}
		}
		return abiertos;
	}


	public ArrayList<Nodo> ordenarCoste(ArrayList<Nodo> abiertos) {
		int aux;
		for (int i = 0; i < abiertos.size()-1; i++) {
			for (int x = i + 1; x < abiertos.size(); x++){
				if (abiertos.get(x).getCoste() < abiertos.get(i).getCoste()) {
					aux = abiertos.get(i).getCoste();
					abiertos.get(i).setCoste(abiertos.get(x).getCoste());
					abiertos.get(x).setCoste(aux);
				}
			}
		}
		return abiertos;
	}


	public ArrayList<Nodo> funcion_sucesores(Nodo nodo_actual) {

		ArrayList<Nodo> hijos = new ArrayList<Nodo>();

		int iCero = 0, jCero = 0;

		for (int i = 0; i < nodo_actual.getTablero().length; i++) {
			for (int j = 0; j < nodo_actual.getTablero().length; j++) {
				if (nodo_actual.getTablero()[i][j] == 0) {
					iCero = i;
					jCero = j;
					break;
				}
			}
		}

		if (nodo_actual.getTablero()[iCero][jCero] != 0) {
			return hijos;
		}
		if (jCero < (nodo_actual.getTablero().length) - 1) {
			hijos.add(intercambiar(nodo_actual, iCero, jCero, 0, 1,"DERECHA"));
		}
		if (jCero > 0) {
			hijos.add(intercambiar(nodo_actual, iCero, jCero, 0, -1,"IZQUIERDA"));
		}
		if (iCero > 0) {
			//ARRIBA
			hijos.add(intercambiar(nodo_actual, iCero, jCero, -1, 0,"ARRIBA"));
		}
		if (iCero < (nodo_actual.getTablero().length) - 1) {
			//ABAJO		
			hijos.add(intercambiar(nodo_actual, iCero, jCero, 1, 0,"ABAJO"));
		}

		for (int i = 0; i < hijos.size(); i++) {
			hijos.get(i).setCoste(hijos.get(i).getCoste()+1);;
		}
		
		return hijos;
	}

	public Nodo intercambiar(Nodo nodo_actual, int i, int j, int iShift, int jShift,String operador) {

		int[][] tableroNuevo = new int[nodo_actual.getTablero().length][nodo_actual.getTablero().length];

		for (int k = 0; k < tableroNuevo.length; k++) {
			for (int k2 = 0; k2 < tableroNuevo.length; k2++) {
				tableroNuevo[k][k2] = nodo_actual.getTablero()[k][k2];
			}
		}

		int iAux = i + iShift;
		int jAux = j + jShift;

		tableroNuevo[i][j] = tableroNuevo[iAux][jAux];
		tableroNuevo[iAux][jAux] = 0;

		Nodo nodoNovo = new Nodo(nodo_actual, tableroNuevo, nodo_actual.getCoste(),operador);

		return nodoNovo;
	}


	public void camino(Nodo nodo){
		if (nodo.getPadre() != null){			
			camino(nodo.getPadre());	
			System.out.print(nodo.getOperador() + ">");
			System.out.println("");
			System.out.println("F1 "+nodo.getF1());
			System.out.println("H1 "+nodo.getH1());
			System.out.println("coste "+nodo.getCoste());
		}
	}

	
	public boolean es_estado_final(Nodo nodo) {
		Nodo nodo1 = new Nodo();
		//boolean b = nodo.getEstado_final().equals(nodo1.getEstado_final());
		int cont = 0;
		for (int i = 0; i < nodo.getEstado_final().length; i++) {
			for (int j = 0; j < nodo.getEstado_final().length; j++) {
				if(nodo.getTablero()[i][j] == nodo1.getEstado_final()[i][j]){
					cont++;
				}
			}
		}
		if(cont==9){
			return true;
		}
		return false;
	}

	public static void imprimirNodos(ArrayList<Nodo> a){
		for (int i = 0; i < a.size(); i++) {
			System.out.println(a.get(i));
		}
	}

	public static void imprimirMatriz(int[][] numeros){
		for (int i=0;i<numeros.length;i++){
			for (int j=0;j<numeros.length;j++){

				System.out.print("|"+numeros[i][j]+"|");

			}
			System.out.println("");
		}
	}


	public static void main(String[] args){

		Mapa mapa = new Mapa();
		Nodo nodo_actual = new Nodo();
		int metodo = 1;
		System.out.println("GR-8");
		System.out.println("Sergio Torre");
		System.out.println("Raúl Sacristán");
		System.out.println("Manuel Muriana");
		System.out.println("");
		mapa.busqueda(nodo_actual,metodo);

	}

}


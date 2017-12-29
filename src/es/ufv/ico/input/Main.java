package es.ufv.ico.input;

import java.util.Scanner;

import javafx.scene.control.Tab;

public class Main {




	public static void main(String[] args){

		//int metodo = metodo(); 
		int[][] tablero = tablero();



	}


	public static int metodo(){
		int metodo = -1;
		@SuppressWarnings("resource")
		Scanner consola = new Scanner(System.in);
		do{
			System.out.println("Eliga metodo de resolucion:");
			System.out.println("0: Dijsktra");
			System.out.println("1: AVARA");
			System.out.println("2: A*");

			metodo = consola.nextInt();

		}while(metodo!=0 && metodo!=1 && metodo!=2);
		return metodo;
	}



	public static int[][] tablero(){
		@SuppressWarnings("resource")
		Scanner consola = new Scanner(System.in);
		System.out.println("Introduce un Tablero entre {0-8} 0=FichaBlanca");

		int tablero[][] = new int[3][3];

		for (int x=0; x < tablero.length; x++) {
			for (int y=0; y < tablero[x].length; y++){
				System.out.println("Introduzca el elemento [" + x + "," + y + "]");

				String n1 = consola.nextLine();

				String[] abecedario={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","Ñ","O","P","Q","R","S","T","U","V","W","X","Y","Z"," ","+","-","*","/","=","%","&","#","!","?","^","“","~","\","|","<"," >","(",")","[","]","{","}};

				for (int j = 0; j < abecedario.length; j++) {
					if(n1.length()>1 || n1.equalsIgnoreCase(abecedario[j])){
						return tablero();
					}else{
						int n = Integer.parseInt(n1);

						if(n>=0 && n<=8){
							tablero[x][y] = consola.nextInt();
						}else{
							tablero();
						}
					}
				}
			}

		}
		return tablero;
	}

}

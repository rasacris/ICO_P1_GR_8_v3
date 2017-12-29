package es.ufv.ico.input;


public class Nodo {

	private int[][] tablero = new int[3][3];
	private int [][] estado_final =  {{1,2,3},{8,0,4},{7,6,5}};
	private int h1;
	private int h2;
	private int coste_acumulado;
	private int coste=0;
	private int f1;
	private int f2;
	private String operador;
	private Nodo padre;

	
	public Nodo(){
		super();
		this.tablero = rellenarTablero();
		this.estado_final = this.estado_final;
		this.h1 = heuristica1(tablero, this.estado_final);
		this.h2 = heuristica2(tablero,this.estado_final);
		this.coste = actualizarCoste();
		this.f1 = this.h1+this.coste;
		this.f2 = this.h2+this.coste;
		this.operador = "";
	}


	public Nodo(Nodo nodo_actual, int[][] tableroNuevo, int coste,String operador) {
		this.padre = nodo_actual;
		this.tablero = tableroNuevo;
		this.coste = coste;
		this.estado_final = this.estado_final;
		this.h1 = heuristica1(tablero, this.estado_final);
		this.h2 = heuristica2(tablero,this.estado_final);
		this.coste = coste;
		this.f1 = this.h1+this.coste;
		this.f2 = this.h2+this.coste;
		this.operador = operador;
	}


	private int actualizarCoste(){
		return coste += coste_acumulado;
	} 

	public int heuristica1(int[][] tablero, int[][] estado_final){
		//contar cuantas piezas esta descolocado
		int cont = 0;
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero.length; j++){
				if(tablero[i][j] != estado_final[i][j]){
					cont++;					
				}
			}
		}
		return cont;
	}

	public static int heuristica2(int[][] tablero, int[][] estado_final){
		double suma=0;
		for (int i=0; i < tablero.length; ++i){
			for (int j=0; j < tablero.length; ++j){

				if(estado_final[i][j]!=tablero[i][j]){
					//Buscamos la posición correcta para la ficha en puzzle[i][j]
					int i2=0, j2=0;
					while(i2<estado_final.length && (estado_final[i2][j2]!=tablero[i][j])){
						j2++;
						if (j2 >= estado_final[i2].length) {
							i2++; 
							j2=0;
						}
					}
					//En [i2][j2] está la posición correcta
					suma+=Math.abs(i2-i)+Math.abs(j2-j);
				}
			}
		}
		return (int) suma;
	}


	public int[][] rellenarTablero(){

		//1,3,0 || 6,2,4 || 8,7,5
		
		this.tablero[0][0] = 1;
		this.tablero[0][1] = 3;
		this.tablero[0][2] = 0;
		this.tablero[1][0] = 6;
		this.tablero[1][1] = 2;
		this.tablero[1][2] = 4;
		this.tablero[2][0] = 8;
		this.tablero[2][1] = 7;
		this.tablero[2][2] = 5;
		

		return this.tablero;
	}

	public static void imprimirMatriz(int[][] numeros){
		for (int i=0;i<numeros.length;i++){
			for (int j=0;j<numeros.length;j++){

				System.out.print("|"+numeros[i][j]+"|");

			}
			System.out.println("");
		}
	}


	
	//getters & setters
	public int[][] getTablero() {
		return tablero;
	}


	public void setTablero(int[][] tablero) {
		this.tablero = tablero;
	}


	public int[][] getEstado_final() {
		return estado_final;
	}


	public void setEstado_final(int[][] estado_final) {
		this.estado_final = estado_final;
	}


	public int getH1() {
		return h1;
	}


	public void setH1(int h1) {
		this.h1 = h1;
	}


	public int getH2() {
		return h2;
	}


	public void setH2(int h2) {
		this.h2 = h2;
	}

	public int getCoste() {
		return coste;
	}


	public void setCoste(int coste) {
		this.coste = coste;
	}


	public int getF1() {
		return f1;
	}


	public void setF1(int f1) {
		this.f1 = f1;
	}


	public int getF2() {
		return f2;
	}


	public void setF2(int f2) {
		this.f2 = f2;
	}


	public String getOperador() {
		return operador;
	}


	public void setOperador(String operador) {
		this.operador = operador;
	}


	public Nodo getPadre() {
		return padre;
	}


	public void setPadre(Nodo padre) {
		this.padre = padre;
	}

	public String toString(){
		String puzzle = "Tablero Inicial ";
		//System.out.println("TABLERO");
		
		for (int i = 0; i < this.tablero.length; i++) {
			for (int j = 0; j < this.tablero.length; j++) {
				//puzzle += this.tablero[i][j];
			}
		}
		imprimirMatriz(this.tablero);
		//return "**|" + " Operador "+this.getOperador()+" |f1:"+this.getF1()+ " |f2:"+this.getF2() + " |h1: "+this.getH1() + " |h2: " +this.getH2() +" |Coste:" + this.getCoste() + "|**";
		return "**|f1:"+this.getF1()+ " |f2:"+this.getF2() + " |h1: "+this.getH1() + " |h2: " +this.getH2() +" |Coste:" + this.getCoste() + "|** \n";
		
	}

	public static void main(String[] args) {

		Nodo nodo = new Nodo();
		System.out.println(nodo);
		
	}




}

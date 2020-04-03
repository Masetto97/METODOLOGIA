package Practica_final;


public class Voraz {

	// atributos que definen el solar y una lista de baldosas
	private int[][] solar;
	private int[] baldosas;
	private int[] solucion;

	// contructor
	public Voraz(int dim, int []baldosas) {
		solar = new int[dim][dim];
		this.baldosas=baldosas;
		solucion = new int[baldosas.length];
		Principal();

	}

	
	public void Principal() {
		for (int k = 0; k < baldosas.length; k++) {
			for (int i = 0; i < solar.length; i++) {
				for (int j = 0; j < solar[i].length; j++) {
					if (solar[i][j] == 0 && EsPosible(baldosas[k], i, j)) {
						colocar_baldosa(baldosas[k], i, j);
						solucion[k]++;
					}
				}
			}
		}

	}

	// metodo que comprueba si es posible colocar una baldosa de dimension
	// dada a partir de una posicion x e y dadas
	public boolean EsPosible(int dim, int x, int y) {
		int countx = 0, county = 0;
		for (int i = x; i < solar.length; i++) {
			if ((solar[i][y] == 0))
				countx++;
		}
		for (int j = y; j < solar[x].length; j++) {
			if ((solar[x][j] == 0))
				county++;
		}
		return (countx >= dim && county >= dim);
	}

	// metodo para mostrar el solar y las baldosas usadas
	public String toString() {
		String string = "";
		for (int i = 0; i < solar.length; i++) {
			for (int j = 0; j < solar[i].length; j++) {
				string += solar[i][j] + " ";
			}
			string += "\n";
		}
		string += "\nPara rellenar el solar se ha usado:  \n";
		for (int j = 0; j < solucion.length; j++)
			string += "\n" + solucion[j] + " baldosas de " + baldosas[j] + " metros. ";

		return string;
	}

	// metodo que coloca una baldosa de una determinada dimension a partir
	// de una posicion x e y dadas
	public void colocar_baldosa(int dim, int x, int y) {
		for (int i = x; i < x + dim; i++)
			for (int j = y; j < y + dim; j++)
				solar[i][j] = dim;
	}
}

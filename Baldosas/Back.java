package Practica_final;


public class Back {

	// atributos una lista de baldosas
	private int numero_posible[];
	private int baldosas_solucion[];
	private int[] baldosas;
	int Posible_Solucion[][];
	int Solucion_optima[][];

	// contructor que inicializa el solar y la lista de baldosas para crear el
	// objeto
	public Back(int dim, int[] b) {
		Posible_Solucion = new int[dim][dim];
		Solucion_optima = new int[dim][dim];
		numero_posible = new int[b.length];
		baldosas_solucion = new int[b.length];
		baldosas = b;
	}


	public void Inicio() {
		Principal(Posible_Solucion, Solucion_optima);
		if (!free(Solucion_optima))
			System.out.println("No existe solucion\n");
		else
			System.out.println(Mostrar_Solucion());
	}

	public void Principal(int sol[][], int solOpt[][]) {
		int i;
		int cell[] = new int[2];
		if (isSolution(sol)) {
			if ((free(sol) && amountoftiles(numero_posible) < amountoftiles(baldosas_solucion))
					|| amountoftiles(baldosas_solucion) == 0) {
				copiar_solucion(sol, solOpt);
				System.arraycopy(numero_posible, 0, baldosas_solucion, 0, numero_posible.length);
			}
		} else {
			for (i = 0; i < baldosas.length; i++) {
				if (isPossible(sol, baldosas[i], cell)) {
					Placetiles(sol, baldosas[i], cell[0], cell[1]);
					numero_posible[i]++;
					Principal(sol, solOpt);
					Removetiles(sol, baldosas[i], cell[0], cell[1]);
					numero_posible[i]--;
				}
			}
		}
	}

	public void Placetiles(int sol[][], int dimensiontile, int xcell, int ycell) {
		for (int i = xcell; i < xcell + dimensiontile; i++)
			for (int j = ycell; j < ycell + dimensiontile; j++)
				sol[i][j] = dimensiontile;
	}

	public void Removetiles(int sol[][], int dimensiontile, int xcell, int ycell) {
		for (int i = xcell; i < xcell + dimensiontile; i++)
			for (int j = ycell; j < ycell + dimensiontile; j++)
				sol[i][j] = 0;
	}

	public boolean isPossible(int sol[][], int dimensiontile, int cell[]) {
		boolean found = false;
		for (int i = 0; i < sol.length && !found; i++) {
			for (int j = 0; j < sol[i].length && !found; j++) {
				if (sol[i][j] == 0) {
					if (isPossibleinCell(sol, dimensiontile, i, j)) {
						found = true;
						cell[0] = i;
						cell[1] = j;
					}
				}
			}
		}
		return found;
	}

	public boolean isPossibleinCell(int sol[][], int dimensiontile, int xcell, int ycell) {
		int countx = 0, county = 0, i = xcell, j = ycell;
		boolean limit = false;
		// comprobamos las filas a 0 hasta la primera ocupada
		while (i < sol.length && !limit) {
			if (sol[i++][ycell] != 0)
				limit = true;
			else
				countx++;
		}
		// comprobamos las columnas a 0 hasta la primera ocupada
		limit = false;
		while (j < sol[xcell].length && !limit) {
			if (sol[xcell][j++] != 0)
				limit = true;
			else
				county++;
		}
		return (countx >= dimensiontile && county >= dimensiontile);
	}

	public boolean isSolution(int sol[][]) {
		boolean complete = true;
		for (int i = 0; i < sol.length && complete; i++) {
			for (int j = 0; j < sol[i].length && complete; j++) {
				if (sol[i][j] == 0) {
					for (int k = 0; k < baldosas.length && complete; k++) {
						if (isPossibleinCell(sol, baldosas[k], i, j)) {
							complete = false;
						}
					}
				}
			}
		}
		return complete;
	}

	public boolean free(int[][] sol) {
		int amount = 0;
		for (int i = 0; i < sol.length; i++) {
			for (int j = 0; j < sol[i].length; j++) {
				if (sol[i][j] == 0) {
					amount++;
				}
			}
		}
		return (amount == 0);
	}

	public int amountoftiles(int ntiles[]) {
		int amount = 0;
		for (int i = 0; i < ntiles.length; i++) {
			amount += ntiles[i];
		}
		return amount;
	}

	public void copiar_solucion(int[][] source, int[][] target) {
		for (int i = 0; i < source.length; i++) {
			for (int j = 0; j < source[i].length; j++) {
				target[i][j] = source[i][j];
			}
		}
	}

	// metodo que imprime como ha quedado el solar
	public String Mostrar_Solucion() {
		String string = "";
		for (int i = 0; i < Solucion_optima.length; i++) {
			for (int j = 0; j < Solucion_optima[i].length; j++) {
				string += Solucion_optima[i][j] + " ";
			}
			string += "\n";
		}

		string += "\nLas baldosas empleadas en orden son: ";
		for (int j = 0; j < baldosas_solucion.length; j++)
			string += baldosas_solucion[j] + " de " + baldosas[j] + " m. ";

		return string;
	}
}
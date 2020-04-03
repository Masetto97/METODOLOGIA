package Practica_3;

import Practica_3.leer;

class GENERAL {

	private int[] tamaño = { 10, 100, 1000, 10000, 100000, 1000000 };
	private int[] numero = { 1, 2, 10, 100, 1000 };
	private int roto;
	private int n;

	public void Ladrillos() throws Exception {

		Simulacion();
	}

	public String Lanzador(int cantidad_ladrillos, int bajo, int superior, int numero_tiradas) {
		String resultado_obtenido;
		if (bajo > superior)
			resultado_obtenido = "NO PUEDE SUPERAR EL PISO DE ABAJO AL DE ARRIBA";
		int media = (bajo + superior) / 2;
		if (superior == bajo - 1) {
			n = cantidad_ladrillos;
			resultado_obtenido = " SE ROMPE EN EL PISO: " + bajo + " CON ESTE NUMERO DE LANZAMIENTOS: "
					+ (numero_tiradas) + " QUEDANDO: " + (cantidad_ladrillos) + " LADRILLOS.";
		} else {
			if (cantidad_ladrillos == 1) {
				resultado_obtenido = un_ladrillo(bajo, superior, numero_tiradas, cantidad_ladrillos);
			} else {
				if (media < roto) {
					resultado_obtenido = Lanzador(cantidad_ladrillos, media + 1, superior, numero_tiradas + 1);
				} else {
					resultado_obtenido = Lanzador(cantidad_ladrillos - 1, bajo, media - 1, numero_tiradas + 1);
				}
			}
		}
		return resultado_obtenido;
	}
	
	public String un_ladrillo(int pisoinf, int pisosup, int lanzamientos, int nladrillos) {
		boolean encontrado = false;
		int i = pisoinf;
		while (i <= pisosup && !encontrado) {
			lanzamientos++;
			if (i == roto)
				encontrado = true;
			else
				i++;
		}
		// Al encontrar el piso, eliminamos un ladrillo
		if (encontrado)
			--nladrillos;
		return "Rotura en " + i + " Lanzamientos:" + (lanzamientos) + " Ladrillos restantes:" + (nladrillos);
	}

	public void Simulacion() throws Exception {

		System.err.println(
				"\nBIENVENIDOS AL PROGRAMA ''ROMPE-LADRILLOS'', EL CUAL PERMITE COMPROBAR LA RESISTENCIA DE LOS LADRILLOS.\n");
		
		boolean salir = false;
		int Opcion;
		do {
			System.out.println("\nEste es el menu principal del programa");
			System.out.println("1. Simulación con datos por teclado");
			System.out.println("2. Simulación automatica");
			System.out.println("3. Ver la complejidad del algorítmo");
			System.out.println("4. Salir");

			Opcion = leer.entero();

			switch (Opcion) {

			case 1:
				ejecucion_por_teclado();
				break;
			case 2:
				ejecucion_automatica();

				break;
			case 3:
				Complejidad();
				break;
			case 4:
				salir = true;
				break;
			}
		} while (!salir);
		
		System.err.println(
				"FIN DEL PROGRAMA ''ROMPE-LADRILLOS''.\nGRACIAS POR SU UTILIZACION\nUN SALUDO");
	}

	public long obtenerTiempo(char medida) {

		if (String.valueOf(medida).equals("N")) {
			return System.nanoTime();
		}

		else if (String.valueOf(medida).equals("n")) {
			return System.nanoTime();
		} else
			return System.currentTimeMillis();

	}

	private int numero_aleatorio(int n) {

		return (int) (Math.random() * n + 1);
	}

	private char eleccion_medida() {

		char med = leer.caracter("¿En qué unidad de medida quieres calcular:\n M=milisegundos\n N=nanosegundos ");
		System.out.println(
				"-----------------------------------------------------------------------------------------------------------------");
		while (med != 'n' && med != 'N' && med != 'm' && med != 'M') {
			System.out.println("Ha introducido una opción incorrecta. Vuelva a intentarlo");
			med = leer.caracter("¿En qué unidad de medida quieres calcular:\n M=milisegundos\n N=nanosegundos ");
			System.out.println(
					"-----------------------------------------------------------------------------------------------------------------");
		}
		return med;
	}
	
	private void Complejidad(){
	
		System.out.println(
				"-----------------------------------------------------------------------------------------------------------------");
		System.out.println("LA COMPLEJIDAD DEL ALGORITMO DEPENDE DEL NUMERO DE LADRILLOS: \nEN EL MEJOR DE LOS CASOS"
				+ " LA COMPLEJIDAD SERIA LOGARITMICA O(log(n)) \nPERO EN EL CASO DE 1 LADRILLO, ESTA SERIA O(n)");
		System.out.println(
				"-----------------------------------------------------------------------------------------------------------------");
	}
	
	private void ejecucion_automatica(){
		char medida = eleccion_medida();
		for (int i = 0; i < tamaño.length; i++) {
			roto = numero_aleatorio(tamaño[i]);
			System.out.println(
					"-----------------------------------------------------------------------------------------------------------------");
			System.out.println(
					"\n PARA " + tamaño[i] + " PISOS, EL NUMERO DE ROTURA DEL PISO ES: " + roto + "\n");

			for (int j = 0; j < numero.length; j++) {

				long tb0 = obtenerTiempo(medida);

				System.out
						.println("Para: " + numero[j] + " ladrillos, " + Lanzador(numero[j], 1, tamaño[i], 0));

				long tb1 = obtenerTiempo(medida);

				System.out.println("EL TIEMPO QUE TARDA ES: " + (tb1 - tb0) + "\n");

			}
			System.out.println("\nEL NUMERO OPTIMO DE LADRILLOS PARA " + tamaño[i] + " ES: " + (numero[4] - n)
					+ " ladrillos\n");
			System.out.println(
					"-----------------------------------------------------------------------------------------------------------------");
		}	
	}
	
	private void ejecucion_por_teclado(){
		char medida = eleccion_medida();
		int piso = leer.entero(" ¿Cuantos pisos quieres?");
		int ladrillo = leer.entero(" ¿Cuantos ladrillos? ");
		roto = numero_aleatorio(piso);
		System.out.println("PARA " + piso + " PISOS, EL NUMERO DE ROTURA DEL PISO ES: " + roto + "\n");
		long tb0 = obtenerTiempo(medida);
		System.out.println("Para: " + ladrillo + " ladrillos, " + Lanzador(ladrillo, 1, piso, 0));
		long tb1 = obtenerTiempo(medida);
		System.out.println("EL TIEMPO QUE TARDA ES: " + (tb1 - tb0));
		System.out.println(
				"-----------------------------------------------------------------------------------------------------------------");
	
	}
}

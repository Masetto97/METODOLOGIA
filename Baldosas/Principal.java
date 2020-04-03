package Practica_final;


public class Principal {

	private int dimension;
	private int ba[];

	public Principal() {
		System.out.println("---------------------------------------------");
		System.out.println("------------PROGRAMA PONE BALDOSAS-----------");
		System.out.println("---------------------------------------------");
		meter_datos();
	}

	public void Menu() {
		boolean salir = false;
		char medida;

		do {
			System.out.println("\n---------------------------------------------");
			System.out.println("----------------MENU PRINCIPAL---------------");
			int Opcion = leer
					.entero("\n Seleccione 1 para ejecutar el algoritmo voraz, \n Seleccione 2 para ver el algoritmo back, "
							+ "\n Seleccione 3 para volver a meter los datos, \n Seleccione 4 para ejecutar los dos algortimos a la vez, \n Seleccione 5 para salir");

			switch (Opcion) {

			case 1:
				medida = leer
						.caracter("\n¿En qué unidad de medida quieres calcular:\n M=milisegundos\n N=nanosegundos ");

				long tb0 = obtenerTiempo(medida);
				Voraz V = new Voraz(dimension, ba);
				System.out.print(V);
				long tb1 = obtenerTiempo(medida);
				System.out.println("\n\n El tiempo de ejecución del algoritmo voraz ha sido: " + (tb1 - tb0));
				break;
			case 2:
				medida = leer
						.caracter("\n¿En qué unidad de medida quieres calcular:\n M=milisegundos\n N=nanosegundos ");

				long ts0 = obtenerTiempo(medida);
				Back B = new Back(dimension, ba);
				B.Inicio();
				long ts1 = obtenerTiempo(medida);
				System.out.println("\n\n El tiempo de ejecución del algoritmo baack ha sido: " + (ts1 - ts0));
				break;
			case 3:
				meter_datos();
				break;
			case 4:
				medida = leer
						.caracter("\n¿En qué unidad de medida quieres calcular:\n M=milisegundos\n N=nanosegundos ");
				System.out.println("\nEste es el algoritmo voraz\n");
				long t0 = obtenerTiempo(medida);
				Voraz X = new Voraz(dimension, ba);
				System.out.print(X);
				long t1 = obtenerTiempo(medida);
				System.out.println("\n\nEste es el algoritmo back\n");
				long T0 = obtenerTiempo(medida);
				Back F = new Back(dimension, ba);
				F.Inicio();
				long T1 = obtenerTiempo(medida);
				System.out.println("\n\n El tiempo de ejecución del algoritmo back ha sido: " + (T1 - T0)+ "\n El del voraz ha sido: " +(t1-t0));
				break;
			case 5:
				salir = true;
				System.out.println("\n--------------FIN DEL PROGRAMA---------------");
				System.out.println("---------------------------------------------");
				break;
			}

		} while (!salir);
	}

	public void meter_datos() {
		System.out.println("\n------------PETICION DE LOS DATOS------------\n");
		dimension = leer.entero("¿Cuanto mide el solar?");
		if (dimension <= 0) {
			dimension = leer.entero("El solar no puede medir 0 metros, vuelve a seleccionar");
		}
		int numero = leer.entero("¿Cuantas baldosas quiere?");
		ba = new int[numero];
		int n;
		for (int i = 0; i < numero; i++) {

			n = leer.entero("¿Cual es el tamaño de la baldosa: " + i + " ?");
			if (n > 0) {
				ba[i] = n;
			} else {
				n = leer.entero("La baldosa no puede ser menor que 0, vueve a selecionar  " + i);
			}
		}

	}

	public long obtenerTiempo(char medida) {

		if (String.valueOf(medida).equals("N")) {
			return System.nanoTime();
		}

		else if (String.valueOf(medida).equals("n")) {
			return System.nanoTime();
		}
		return System.currentTimeMillis();

	}

}

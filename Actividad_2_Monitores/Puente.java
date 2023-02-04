package Actividad_2_Monitores;

public class Puente {

	private boolean norteCruza; // Booleano que indica que coche del norte cruza

	private boolean surCruza; // Booleano que indica que coche del sur cruza

	private int contadorNorte = 0; // Contador que nos llevará la cuenta de los coches que pasan por el Norte.

	private int contadorSur = 0; // Contador que llevará la cuenta de los coches que pasar por el Sur.

	// Método que nos permite sincronizar los coches desde el Norte y el Sur.
	public synchronized void cruzarDesdeNorte() {

		// Mientras los coches del Sur cruzan...
		while (surCruza) {

			System.out.println("Coche del NORTE esperando para pasar por el puente.");

			try {

				wait(); // Esperamos a que cruce.

			} catch (InterruptedException e) {

			}

		}

		norteCruza = true; // Conforme pasa el coche del Sur, validamos que pase el del Norte.
	}

	// Método que nos permite sincronizar los coches desde el Norte y el Sur.
	public synchronized void cruzarDesdeSur() {

		// Mientras los coches del Norte cruzan...
		while (norteCruza) {

			System.out.println("Coche del SUR esperando para pasar por el puente.");

			try {

				wait(); // Esperamos a que cruce.

			} catch (InterruptedException e) {

			}

		}

		surCruza = true; // Conforme pasa el coche del Norte, validamos que pase el del Sur.
	}

	// Métodos que se llaman cuando un coche ha terminado de cruzar el puente y
	// notifica a los otros hilos.
	public synchronized void cruzarNorteFinalizado() {

		System.out.println("Coche del NORTE pasando por el puente.");

		System.out.println("Coche del NORTE ha pasado el puente.");

		norteCruza = false; // Una vez cruza, ponemos el booleano a false

		notifyAll(); // Notificamos al resto de hilos.

		contadorNorte++; // Sumamos 1 al contador de coches del Norte.

		System.out.println("Ya han pasado el puente " + contadorNorte + " coches del NORTE.");

	}

	// Métodos que se llaman cuando un coche ha terminado de cruzar el puente y
	// notifica a los otros hilos.
	public synchronized void cruzarSurFinalizado() {

		System.out.println("Coche del SUR pasando por el puente.");

		System.out.println("Coche del SUR ha cruzado.");

		surCruza = false; // Una vez cruza, ponemos el booleano a false

		notifyAll(); // Notificamos al resto de hilos.

		contadorSur++; // Sumamos 1 al contador de coches del Sur.

		System.out.println("Ya han pasado el puente " + contadorSur + " coches del SUR.");

	}
}

class CocheNorte extends Thread {

	private Puente puente; // Creamos un objeto tipo Puente que nos dará acceso a los métodos creados.

	public CocheNorte(Puente puente) { // Constructor.

		this.puente = puente;
	}

	@Override
	public void run() { // Lanzamos la ejecución del hilo.

		puente.cruzarDesdeNorte(); // Cruza el puente.

		try {

			Thread.sleep(500);

		} catch (InterruptedException e) {

		}

		puente.cruzarNorteFinalizado(); // Termina de cruzar el puente.
	}
}

class CocheSur extends Thread { // Sumamos 1 al contador de coches del Norte.

	private Puente puente; // Creamos un objeto tipo Puente que nos dará acceso a los métodos creados.

	public CocheSur(Puente puente) { // Constructor.

		this.puente = puente;

	}

	@Override
	public void run() { // Lanzamos la ejecución del hilo.

		puente.cruzarDesdeSur(); // Cruza el puente.

		try {

			Thread.sleep(1);

		} catch (InterruptedException e) {

		}

		puente.cruzarSurFinalizado(); // Termina de cruzar el puente.
	}
}

package Actividad_2_Semaforos;

import java.util.concurrent.Semaphore;

//Creamos la clase Recurso que almacenará los recursos
public class Recurso {

	// Creamos la variable recursos
	private int k;

	// Semaforo que controla los recursos
	private Semaphore semaforo;

	// Constructor de recursos
	public Recurso(int k) {

		this.k = k;

		semaforo = new Semaphore(k);
	}

	// Método que reserva recursos
	public void reserva(int r) throws InterruptedException {

		// A través del semaforo reservamos los recursos
		semaforo.acquire(r);

	}

	// Método que libera recursos
	public void libera(int l) {

		// A través del semaforo liberamos los recursos
		semaforo.release(l);

	}
}
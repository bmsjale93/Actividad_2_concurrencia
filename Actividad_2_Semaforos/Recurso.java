package Actividad_2_Semaforos;

import java.util.concurrent.Semaphore;

class Recurso {

	static int k = 10; // cantidad de recursos disponibles

	static Semaphore recurso = new Semaphore(1); // semáforo para controlar el acceso al recurso

	static Semaphore contador = new Semaphore(k); // semáforo para controlar la cantidad de recursos disponibles

	static void reserva(int r) {

		try {

			System.out.println("Esperando para reservar " + r + " recursos. Recursos disponibles " + k);

			if (r < k) {

				contador.acquire(r); // necesito se me concedan, “de golpe”, r unidades del recurso

				recurso.acquire(); // solicitar el acceso al recurso

				k = k - r;

				System.out.println("Se han reservado " + r + " recursos. Recursos disponibles " + k);

			} else {

				System.out.println("No ha sido posible reservar esa cantidad de datos");

				recurso.release(); // liberar el acceso al recurso

				contador.release(r); // libero, “de golpe”, r unidades del recurso que previamente se me habían
										// concedido
			}

		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	static void libera(int l) {

		try {

			System.out.println("Esperando para liberar " + l + " recursos. Recursos disponibles " + k);

			recurso.acquire(); // solicitar el acceso al recurso

			k = k + l; // liberar l unidades del recurso

			System.out.println("Se han liberado " + l + " recursos. Recursos disponibles " + k);

			contador.release(l); // liberar l unidades del contador

			recurso.release(); // liberar el acceso al recurso

		} catch (InterruptedException e) {

			e.printStackTrace();

		}

	}

	public static void main(String[] args) {

		System.out.println("---------------------------------------------------");
		System.out.println("Actividad 2 - Parte 1 - Problema de uso de recursos");
		System.out.println("---------------------------------------------------\n");

		Recurso recurso = new Recurso();

		Recurso.reserva(5);

		Recurso.libera(8);

	}
}
package Actividad_2_Semaforos;

import java.util.Scanner;

public class Hilos {

	public static void controlHilos(int n, int k) {

		// Creamos un objeto tipo Recurso
		Recurso controlRecursos = new Recurso(k);

		// Iniciamos un Array de Hilos
		Thread[] hilos = new Thread[n];

		// Este for nos ayudará a recorrer un número n de hilos
		for (int i = 0; i < n; i++) {

			// Variable que nos ayudará a contar los hilos.
			final int nombreHilo = i + 1;

			// Crearemos un hilo por cada vez que recorramos el for
			hilos[i] = new Thread(() -> {

				try {

					// El entero a reservar será random
					int r = (int) (Math.random() * k + 1);

					// Aplicamos el método reserva
					controlRecursos.reserva(r);

					System.out.println("Hilo " + nombreHilo + " | Reserva: " + r + " recursos.");

					Thread.sleep(1000);

					// Aplicamos el método libera
					controlRecursos.libera(r);

					System.out.println("Hilo " + nombreHilo + " | Libera: " + r + " recursos.");

				} catch (InterruptedException e) {

					e.printStackTrace();

				}

			});

		}

		// For each que iniciará los hilos de ejecución
		for (Thread t : hilos) {

			t.start();

		}

	}

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		System.out.println("---------------------------------------------------");
		System.out.println("Actividad 2 - Parte 1 - Problema de uso de recursos");
		System.out.println("---------------------------------------------------\n");

		System.out.println("Establece el número de hilos a ejecutar");
		int hilos = scanner.nextInt();

		System.out.println("Establece el número de recursos disponibles");
		int rec = scanner.nextInt();

		// Llamamos a la función de controlHilos y le pasamos:
		// * 5 Hilos de ejecución independiente
		// * 10 Recursos Disponibles.
		controlHilos(hilos, rec);

	}
}
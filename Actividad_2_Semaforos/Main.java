package Actividad_2_Semaforos;

public class Main {

	public static void main(String[] args) {

		// Creamos el controlador de recursos
		Recurso controlRecursos = new Recurso(20);

		// Creamos un primer hilo de ejecución
		Thread proceso1 = new Thread(() -> {

			try {

				// Pasamos por semaforo el método reserva
				controlRecursos.reserva(2);

				System.out.println("Hilo 1 reserva 2 recursos");

				// Dormimos el hilo
				Thread.sleep(1000);

				// Pasamos por semáforo el método libera
				controlRecursos.libera(2);

				System.out.println("Hilo 1 libera 2 recursos");

			} catch (InterruptedException e) {

				e.printStackTrace();

			}

		});

		// Creamos un segundo hilo de ejecución
		Thread proceso2 = new Thread(() -> {

			try {

				// Pasamos por semaforo el método reserva
				controlRecursos.reserva(4);

				System.out.println("Hilo 2 reserva 4 recursos");

				// Dormimos el hilo
				Thread.sleep(1000);

				// Pasamos por semáforo el método libera
				controlRecursos.libera(4);

				System.out.println("Hilo 2 libera 4 recursos");

			} catch (InterruptedException e) {

				e.printStackTrace();

			}

		});

		// Creamos un tercer hilo de ejecución
		Thread proceso3 = new Thread(() -> {

			try {

				// Pasamos por semaforo el método reserva
				controlRecursos.reserva(8);

				System.out.println("Hilo 3 reserva 8 recursos");

				// Dormimos el hilo
				Thread.sleep(1000);

				// Pasamos por semáforo el método libera
				controlRecursos.libera(8);

				System.out.println("Hilo 3 libera 8 recursos");

			} catch (InterruptedException e) {

				e.printStackTrace();

			}

		});

		// Iniciamos los hilos de ejecución
		proceso1.start();
		proceso2.start();
		proceso3.start();

	}

}

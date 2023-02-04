package Actividad_2_Monitores;

public class Monitor {

	public static void main(String[] args) {

		Puente puente = new Puente();

		for (int i = 0; i < 5; i++) {

			new CocheNorte(puente).start();

			new CocheSur(puente).start();

		}
	}
}
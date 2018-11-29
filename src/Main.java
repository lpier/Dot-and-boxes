import java.io.*;
import java.util.*;

public class Main {


	public static void main(String[] args) {
		Table tablero = new Table(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
		int T = 1;
		int points1 = 0;
		int points2 = 0;
		int X;
		int Y;
		String P;
		Scanner scanner = new Scanner(System.in);

		do {
			
			tablero.print();
			System.out.println();
			System.out.println("Puntuacion del jugador 1: " + tablero.pointsP1());
			System.out.println("Puntuacion del jugador 2: " + tablero.pointsP2());
			System.out.println("Turno del jugador ");
			System.out.println(T +"modulo2:"+ T%2);
			
			if ((T % 2) != 0) {
				System.out.println("1");
			} else {
				System.out.println("2");
			}
			System.out.print("Ingresa la FILA del CUADRADO: ");
			X = Integer.parseInt(scanner.nextLine());
			System.out.print("Ingresa la COLUMNA del CUADRADO: ");
			Y = Integer.parseInt(scanner.nextLine());
			System.out.print("Ingresa la ORIENTACION del PALO: ");
			P = scanner.nextLine();

			tablero.insertPlay(X, Y, P, T);
			
			if ((T % 2) != 0) {
				int auxP1= tablero.pointsP1();
				if (points1 == auxP1) {
					T++;
				} else {
					T = T + 2;
					points1 = tablero.pointsP1();
				}
			}else if ((T % 2) == 0){
				int auxP2= tablero.pointsP2();
				if (points2 == auxP2) {
					T++;
				} else {
					T = T + 2;
					points2 = tablero.pointsP2();
				}
			}

		} while (points1 + points2 < tablero.i * tablero.j);
		scanner.close();
	}

}
import java.util.Random;
import java.util.Scanner;

public class Main {
	public static int rows, columns;

	public static void main(String[] args) {
		rows = Integer.parseInt(args[0]);
		columns = Integer.parseInt(args[1]);
		Table tablero = new Table(rows, columns);
		int T = 1;
		int points1 = 0;
		int points2 = 0;

		Scanner scanner = new Scanner(System.in);

		do {

			tablero.print();
			System.out.println();
			System.out.println("Puntuacion del jugador 1: " + tablero.pointsP1());
			System.out.println("Puntuacion del jugador 2: " + tablero.pointsP2());
			System.out.println("Turno del jugador " + getPlayer(T));

			if(getPlayer(T) == 1){
				agentPlay(tablero, T);
			}else {
				agentPlay(tablero, T);
			}
			
			if ((T % 2) != 0) {
				int auxP1 = tablero.pointsP1();
				if (points1 == auxP1) {
					T++;
				} else {
					T = T + 2;
					points1 = tablero.pointsP1();
				}
			} else if ((T % 2) == 0) {
				int auxP2 = tablero.pointsP2();
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

	public static int getPlayer(int T) {
		if ((T % 2) != 0) {
			return 1;
		} else {
			return 2;
		}
	}

	public static void humanPlay(Table tablero, int T, Scanner scanner) {
		int X, Y;
		String P;
		System.out.print("Ingresa la FILA del CUADRADO: ");
		X = Integer.parseInt(scanner.nextLine());
		System.out.print("Ingresa la COLUMNA del CUADRADO: ");
		Y = Integer.parseInt(scanner.nextLine());
		System.out.print("Ingresa la ORIENTACION del PALO: ");
		P = scanner.nextLine();

		tablero.insertPlay(X, Y, P, T);
	}

	public static void agentPlay(Table tablero, int T) {
		Random random = new Random();
		int x, y;
		String pos;
		do {
			x = random.nextInt(rows - 1) + 0;
			y = random.nextInt(columns - 1) + 0;
			String positions = "nseo";
			pos = String.valueOf(positions.charAt(random.nextInt(4)));
		} while (tablero.isEdgeFree(x, y, pos) == false);
		System.out.println("row " + x + " | column " + y + " | pos " + pos);
		tablero.insertPlay(x, y, pos, T);
	}

}
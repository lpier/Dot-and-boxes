import java.util.Scanner;

public class Main {
	public static int rows, columns;
	public static Player player1;
	public static Player player2;

	public static void main(String[] args) {
		rows = Integer.parseInt(args[0]);
		columns = Integer.parseInt(args[1]);
		Table tablero = new Table(rows, columns);
		int T = 1;
		int points1 = 0;
		int points2 = 0;

		Scanner scanner = new Scanner(System.in);
		selectMode(scanner);

		do {

			tablero.print();
			System.out.println();
			System.out.println("Puntuacion del jugador 1: " + tablero.getPointsP1());
			System.out.println("Puntuacion del jugador 2: " + tablero.getPointsP2());
			System.out.println("Turno del jugador " + getPlayer(T));

			if (getPlayer(T) == 1) {
				player1.makePlay(scanner, tablero, T);
				int auxP1 = tablero.getPointsP1();
				if (points1 == auxP1) {
					T++;
				} else {
					T = T + 2;
					points1 = tablero.getPointsP1();
				}
			} else {
				player2.makePlay(scanner, tablero, T);
				int auxP2 = tablero.getPointsP2();
				if (points2 == auxP2) {
					T++;
				} else {
					T = T + 2;
					points2 = tablero.getPointsP2();
				}	
			}
			if(tablero.isFinished()) {
				showWinner(tablero.getWinner());
			}
		} while (!tablero.isFinished());

		scanner.close();
	}

	public static int getPlayer(int T) {
		if ((T % 2) != 0) {
			return 1;
		} else {
			return 2;
		}
	}

	public static void selectMode(Scanner scanner) {
		System.out.println("Escoge un modo de juego: ");
		System.out.println(" (1) Humano vs Agente");
		System.out.println(" (2) Agente vs Agente");
		int modo = Integer.parseInt(scanner.nextLine());
		if (modo == 1) {
			System.out.println("Escoge una dificultad: ");
			System.out.println(" (1) Agente aleatorio");
			System.out.println(" (2) Agente inteligente");
			modo = modo * 10 + Integer.parseInt(scanner.nextLine());
		}else {
			System.out.println(" (1) Random vs Random");
			System.out.println(" (2) Random vs Inteligente");
			modo = modo * 10 + Integer.parseInt(scanner.nextLine());
		}

		switch (modo) {
		case 11:
			player1 = new HumanPlayer();
			player2 = new RandomPlayer();
			break;
		case 12:
			player1 = new HumanPlayer();
			player2 = new AgentPlayer();
			break;
		case 21:
			player1 = new RandomPlayer();
			player2 = new RandomPlayer();
			break;
		case 22:
			player1 = new RandomPlayer();
			player2 = new AgentPlayer();
			break;
		default:
			player1 = new RandomPlayer();
			player2 = new RandomPlayer();
			break;
		}

	}
	
	public static void showWinner(int winner) {
		switch(winner) {
		case 1:
			System.out.println("gano el 1");
			break;
		case 2: 
			System.out.println("gano el 2");
			break;
		case 0:
			System.out.println("empate");
			break;
		default:
			break;
		}
	}

}
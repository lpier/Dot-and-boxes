import java.util.Scanner;

public class HumanPlayer extends Player {

	@Override
	public void makePlay(Scanner scanner, Table tablero, int T) {	
		int X, Y;
		String P;
		System.out.print("Ingresa la FILA del CUADRADO: ");
		X = Integer.parseInt(scanner.nextLine());
		System.out.print("Ingresa la COLUMNA del CUADRADO: ");
		Y = Integer.parseInt(scanner.nextLine());
		System.out.print("Ingresa la ORIENTACION del PALO: ");
		P = scanner.nextLine();
		System.out.println();
		tablero.insertPlay(new Play(X, Y, P, T));
	}
	
}

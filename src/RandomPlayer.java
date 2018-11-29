import java.util.Random;
import java.util.Scanner;

public class RandomPlayer extends Player {

	@Override
	public void makePlay(Scanner scanner, Table tablero, int T) {
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

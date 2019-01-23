import java.util.Random;
import java.util.Scanner;

public class RandomPlayer extends Player {

	@Override
	public void makePlay(Scanner scanner, Table board, int T) {
		Random random = new Random();
		int x, y;
		String pos;
		Edge edge = new Edge();
		do {
			boolean horizontal = random.nextBoolean();
			if(horizontal) {
				x = random.nextInt(3 - 0 + 1) + 0;
				y = random.nextInt(2 - 0 + 1) + 0;
			}else {
				x = random.nextInt(2 - 0 + 1) + 0;
				y = random.nextInt(3 - 0 + 1) + 0;
			}
			edge.setHorizontal(horizontal);
			edge.setX(x);
			edge.setY(y);

		} while (board.isEdgeFree(edge) == false);		
		System.out.println("random play: " + "row " + x + " | column " + y + " | horizontal " + edge.isHorizontal());
		board.insertPlay(edge.getX(), edge.getY(), edge.isHorizontal(), T);
	}

}

import java.util.Scanner;

public class AgentPlayer extends Player {

	@Override
	public void makePlay(Scanner scanner, Table table, int T) {
		Edge edge = Minimax.getNextPlay(table, 2);
		System.out.println(edge);
		table.insertPlayAsAgent(edge.getX(), edge.getY(), edge.isHorizontal(), T);;
	}

}

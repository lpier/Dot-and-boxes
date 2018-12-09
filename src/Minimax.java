import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class Minimax {

	public static Edge getNextPlay(Table table, int player) {
		long timeout = 980000000;

		LinkedList<Node> queue = new LinkedList<Node>();
		LinkedList<Node> stack = new LinkedList<Node>();
		ArrayList<Edge> moves;
		Table currentBoard;
		int currentPlayer, currentScore;
		long oldTime = System.nanoTime();

		Node rootNode = new Node(table, player);
		Node levelNode = new Node();
		queue.add(rootNode);
		queue.add(levelNode);

		do {
			if ((System.nanoTime() - oldTime) > timeout)
				break;
			Node currentNode = queue.remove();
			if (currentNode != levelNode) { // esto no debería tirar
				stack.add(currentNode);
				currentBoard = currentNode.getTable();
				currentPlayer = currentNode.getPlayer();
				currentScore = currentBoard.getScore(currentPlayer);

				moves = currentBoard.getMoves();
				Collections.shuffle(moves);

				for (Edge e : moves) {
					Table child = currentBoard.getUpdated(e, currentPlayer);
					int newScore = child.getScore(currentPlayer);
					if (newScore == currentScore) {
						queue.add(new Node(child, currentNode, Util.togglePlayer(currentPlayer), e));
					} else {
						queue.add(new Node(child, currentNode, currentPlayer, e));
					}
				}

			} else {
				queue.add(levelNode);
			}
		} while (!queue.isEmpty());

		while (!queue.isEmpty()) {
			Node currentNode = queue.remove();
			if (currentNode != levelNode)
				stack.add(currentNode);
		}

		do {
			Node currentNode = stack.removeLast();
			Node parentNode = currentNode.getParent();
			int currentUtility = currentNode.getUtility();
			if (Node.MIN == currentUtility) {
				currentNode.setUtility(heuristic(currentNode.getTable(), currentNode.getPlayer(), player));
			}
			currentUtility = currentNode.getUtility();

			if (parentNode == null) { // nodo es raiz
				continue;
			}

			if (parentNode.getPlayer() == player && parentNode.getParent() == null) { // nodo cuelga del raiz
				System.out.println("POR QUÉ ME COPIAS");
				System.out.println(parentNode.getPlayer() + " - " + currentNode.getPlayer());
				System.out.println(player);
				System.out.println(currentNode.getEdge());
				rootNode.setEdge(currentNode.getEdge());
				// rootNode.setEdge(parentNode.getEdge());

				continue;
			} else {
				if (parentNode.getUtility() > currentUtility) {
					parentNode.setUtility(currentUtility);
				}
			}

//			if (parentNode.getPlayer() == player) {
//				if (parentNode.getUtility() < currentUtility) {
//					parentNode.setUtility(currentUtility);
//					if (parentNode.equals(rootNode)) {
//						rootNode.setEdge(currentNode.getEdge());
//					}
//				}
//			} else {
//				if (parentNode.getUtility() > currentUtility) {
//					parentNode.setUtility(currentUtility);
//				}
//			}
		} while (!stack.isEmpty());
		System.out.println(rootNode);
		return rootNode.getEdge();
	}

	public static int heuristic(Table table, int player, int referencePlayer) {
		int cScore = 20, cThree = 15, cTwo = 1;
		int value;
		if (player == Util.PLAYER1) {
			// value = cScore * table.getPointsP1() - cScore * table.getPointsP1();
			value = table.getPointsP1() - table.getPointsP1();
		} else {
			value = table.getPointsP2() - table.getPointsP1();
			// value = cScore * table.getPointsP2() - cScore * table.getPointsP1();
		}
		return value;
	}

}

import java.util.ArrayList;

public class Minimax {
	private static final long timeout = 1980000000;

	public static Tree constructTree(Table table) {
		long begin = System.nanoTime();
		Tree tree = new Tree();
		Node root = new Node(table, true);
		tree.setRoot(root);
		constructTree(root, begin);
		return tree;
	}

	private static void constructTree(Node parentNode, long begin) {
		ArrayList<Edge> listOfMoves = parentNode.getCurrentBoard().getMoves();
		boolean isChildMaxPlayer = !parentNode.isMaxPlayer();
		listOfMoves.forEach(edge -> {
			Node newNode = new Node(edge, parentNode.getCurrentBoard(), isChildMaxPlayer,
					Util.togglePlayer(parentNode.getPlayer()));
			parentNode.addChild(newNode);
			if (newNode.getCurrentBoard().getMoves().isEmpty() == false && (System.nanoTime() - begin) < timeout) {
				constructTree(newNode, begin);
			}else {
			}
		});
	}

}
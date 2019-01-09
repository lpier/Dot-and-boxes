import java.util.List;

public class Tree {
	private Node root;

	Tree() {
	}

	Node getRoot() {
		return root;
	}

	void setRoot(Node root) {
		this.root = root;
	}

	public Edge checkWin() { // TODO: change name
		Node root = this.getRoot();
		checkWin(root);
		Node bestPlay = root.findBestChild();
		return bestPlay.getEdge();
	}

	public void checkWin(Node node) { // TODO: change name
		List<Node> children = node.getChildren();
		boolean isMaxPlayer = node.isMaxPlayer(); // COMPROBAR SI VA A ASI O NEGAO
		children.forEach(child -> {
			if (child.getChildren().isEmpty()) {
				child.calcUtility(); // TODO COGER MAX PLAYER COMO PARÁMETRO!!!
			} else {
				checkWin(child);
			}
		});
		Node bestChild = node.findBestChild();
		node.setUtility(bestChild.getUtility());
	}
}
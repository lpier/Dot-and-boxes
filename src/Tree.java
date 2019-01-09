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

	public Edge checkBest() { // TODO: change name
		Node root = this.getRoot();
		checkBest(root);
		Node bestPlay = root.findBestChild();
		return bestPlay.getEdge();
	}

	public void checkBest(Node node) { // TODO: change name
		List<Node> children = node.getChildren();
		children.forEach(child -> {
			if (child.getChildren().isEmpty()) {
				child.calcUtility();
			} else {
				checkBest(child);
			}
		});
		Node bestChild = node.findBestChild();
		node.setUtility(bestChild.getUtility());
	}
}
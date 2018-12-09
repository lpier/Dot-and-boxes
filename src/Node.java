
public class Node {
	final static int MIN = -100000;

	private Table table;
	private Node parent;
	private int player, utility;
	private Edge edge;

	public Node(Table table, Node parent, int player, Edge edge) {
		this.table = table;
		this.parent = parent;
		this.player = player;
		this.edge = edge;
		this.utility = MIN;
	}

	public Node(Table table, int player) {
		this.table = table;
		this.player = player;
	}

	public Node() {
		super();
	}

	public Table getTable() {
		return table;
	}

	public Node getParent() {
		return parent;
	}

	public int getPlayer() {
		return player;
	}

	public int getUtility() {
		return utility;
	}

	public void setUtility(int utility) {
		this.utility = utility;
	}

	public Edge getEdge() {
		return edge;
	}

	public void setEdge(Edge edge) {
		this.edge = edge;
	}

	public String toString() {
		return "table: " + this.table + "\nparent: " + this.parent + "\nplayer: " + this.player + "\nedge: "
				+ this.edge;
	}

	public boolean isRoot() {
		return (this.getParent() == null);
	}
	
	public boolean equals(Node other) {
		if (this.table.equals(other.table)) {
			return true;
		} else
			return false;
	}

}

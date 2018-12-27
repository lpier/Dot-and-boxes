import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

public class Node {
    private Table currentBoard;
    private boolean isMaxPlayer;
    private int utility; // gain of this play
    private int player;
	private List<Node> children;
    private Edge edge;
    
	public Node(Table currentBoard, boolean isMaxPlayer) {
		super();
		this.currentBoard = currentBoard;
		this.isMaxPlayer = isMaxPlayer;
		this.children = new ArrayList<Node>();
	}
	
	public Node(Edge edge, Table parentBoard, boolean isMaxPlayer, int player) {
		super();
		this.currentBoard = parentBoard.getUpdated(edge, player);
		this.edge = edge;
		this.isMaxPlayer = isMaxPlayer;
		this.player = player;
		this.children = new ArrayList<Node>();
	}
	
	public Table getCurrentBoard() {
		return currentBoard;
	}
	public void setCurrentBoard(Table currentBoard) {
		this.currentBoard = currentBoard;
	}
	public boolean isMaxPlayer() {
		return isMaxPlayer;
	}
	public void setMaxPlayer(boolean isMaxPlayer) {
		this.isMaxPlayer = isMaxPlayer;
	}
	public int getUtility() {
		return utility;
	}
	public void setUtility(int utility) {
		this.utility = utility;
	}
    public int getPlayer() {
		return player;
	}
	public void setPlayer(int player) {
		this.player = player;
	}
	public List<Node> getChildren() {
		return children;
	}
	public void setChildren(List<Node> children) {
		this.children = children;
	}
	public Edge getEdge() {
		return edge;
	}
	public void setEdge(Edge edge) {
		this.edge = edge;
	}
    
    public void addChild(Node node) {
    	children.add(node);
	}
	
	public void calcUtility(){ // TODO: arg isMaxPlayer?? 
		int utility;
		if (this.getPlayer() == 1) {
			utility = this.getCurrentBoard().getPointsP1() - this.getCurrentBoard().getPointsP2();
		} else {
			utility = this.getCurrentBoard().getPointsP1() - this.getCurrentBoard().getPointsP2();
		}
		this.setUtility(utility);
	}
	
	public Node findBestChild() {
		Comparator<Node> byScoreComparator = Comparator.comparing(Node::getUtility);
		return this.children.stream().max(this.isMaxPlayer ? byScoreComparator : byScoreComparator.reversed())
				.orElseThrow(NoSuchElementException::new);
	}
}
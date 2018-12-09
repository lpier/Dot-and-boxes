
public class Edge {

	private int x, y;
	private boolean horizontal;

	public Edge(int x, int y, boolean horizontal) {
		super();
		this.x = x;
		this.y = y;
		this.horizontal = horizontal;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isHorizontal() {
		return horizontal;
	}

	public void setHorizontal(boolean horizontal) {
		this.horizontal = horizontal;
	}

	@Override
	public String toString() {
		// String coords = String.format("(%i,%i)", x,y);
		return ((horizontal ? "H " : "V ") + "(" + x + "," + y + ")	");
	}

}

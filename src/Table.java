import java.util.ArrayList;

public class Table {
	int rows;
	int columns;
	int[][] Hedge;
	int[][] Vedge;
	int[][] Cbox;
	int pointsPlayer1;
	int pointsPlayer2;

	public Table(int rows, int columns, int[][] hedge, int[][] vedge, int[][] cbox) {
		super();
		this.rows = rows;
		this.columns = columns;
		Hedge = hedge;
		Vedge = vedge;
		Cbox = cbox;
	}

	public Table(int rows, int columns) {
		super();
		this.rows = rows;
		this.columns = columns;
		Hedge = new int[rows][columns - 1];
		Vedge = new int[rows - 1][columns];
		Cbox = new int[rows - 1][columns - 1];
		init(Hedge);
		init(Vedge);
		init(Cbox);
		pointsPlayer1 = 0;
		pointsPlayer2 = 0;

	}

	public void init(int[][] arrayToFill) {
		for (int x = 0; x < rows - 1; x++) {
			for (int y = 0; y < columns - 1; y++) {
				arrayToFill[x][y] = 0;
			}

		}
	}

	public void calcPosession() {
		for (int i = 0; i < this.rows - 1; i++) {
			for (int j = 0; j < this.rows - 1; j++) {
				if (Hedge[i][j] * Hedge[i + 1][j] * Vedge[i][j] * Vedge[i][j + 1] > 0) {
					Cbox[i][j] = 2 * (mymax(Hedge[i][j], Hedge[i + 1][j], Vedge[i][j], Vedge[i][j + 1]) % 2) - 1;
				}
			}
		}
	}

	public int mymax(int k, int l, int m, int n) {
		return Math.max(k, Math.max(l, Math.max(m, n)));

	}

	public void insertPlay(Play play) {
		switch (play.pos) {
		case "n":
			this.Hedge[play.getX()][play.getY()] = play.getT();
			break;
		case "s":
			this.Hedge[play.getX() + 1][play.getY()] = play.getT();
			break;
		case "e":
			this.Vedge[play.getX()][play.getY() + 1] = play.getT();
			break;
		case "o":
			this.Vedge[play.getX()][play.getY()] = play.getT();
			break;
		default:
			break;
		}
		this.calcPosession();
	}

	public void insertPlayAsAgent(int x, int y, boolean horizontal, int T) {
		if (horizontal)
			this.Hedge[x][y] = T;
		else
			this.Vedge[x][y] = T;
		this.calcPosession();
	}

	public boolean isFinished() {
		return (pointsPlayer1 + pointsPlayer2) == (rows -1) * (columns-1);
	}

	public int getWinner() {
		if (pointsPlayer1 > pointsPlayer2)
			return 1;
		else if (pointsPlayer1 < pointsPlayer2)
			return 2;
		else
			return 0;
	}

	public int getPointsP1() {
		pointsPlayer1 = this.getScore(1);
		return pointsPlayer1;
	}

	public int getPointsP2() {
		pointsPlayer2 = this.getScore(2);
		return pointsPlayer2;
	}

	public boolean isEdgeFree(int x, int y, String pos) {
		switch (pos.trim().toLowerCase()) {
		case "n":
			if (this.Hedge[x][y] == 0)
				return true;
			else
				return false;
		case "s":
			if (this.Hedge[x + 1][y] == 0)
				return true;
			else
				return false;
		case "e":
			if (this.Vedge[x][y + 1] == 0)
				return true;
			else
				return false;
		case "o":
			if (this.Vedge[x][y] == 0)
				return true;
			else
				return false;
		default:
			return false;
		}
	}

	public ArrayList<Edge> getMoves() {
		ArrayList<Edge> freeEdges = new ArrayList<Edge>();
		for (int i = 0; i < this.rows; i++) {
			for (int j = 0; j < (this.columns - 1); j++) {
				if (Hedge[i][j] == 0)
					freeEdges.add(new Edge(i, j, true));
			}
		}
		for (int i = 0; i < (this.rows - 1); i++) {
			for (int j = 0; j < this.columns; j++) {
				if (Vedge[i][j] == 0)
					freeEdges.add(new Edge(i, j, false));
			}
		}
		return freeEdges;
	}

	public Table getUpdated(Edge edge, int player) {
		Table newTable = new Table(rows, columns);

		for (int i = 0; i < this.rows; i++)
			for (int j = 0; j < (this.columns - 1); j++)
				newTable.Hedge[i][j] = Hedge[i][j];
		for (int i = 0; i < (this.rows - 1); i++)
			for (int j = 0; j < this.columns; j++)
				newTable.Vedge[i][j] = Vedge[i][j];
		for (int i = 0; i < (rows - 1); i++)
			for (int j = 0; j < (columns - 1); j++)
				newTable.Cbox[i][j] = Cbox[i][j];

		if (edge.isHorizontal())
			newTable.Hedge[edge.getX()][edge.getY()] = 10000;
		else
			newTable.Vedge[edge.getX()][edge.getY()] = 10000;
		newTable.calcPosession();
		newTable.getPointsP1();
		newTable.getPointsP2();
		return newTable;
	}

	public int getScore(int player) {
		int player_id = player == 1 ? 1 : -1;

		int p = 0;
		for (int i = 0; i < this.rows - 1; i++) {
			for (int j = 0; j < this.rows - 1; j++) {
				if (this.Cbox[i][j] == player_id) {
					p++;
				}
			}
		}
		return p;

	}

	public void print() {
		for (int i = 0; i < this.rows - 1; i++) {
			for (int h = 0; h < this.columns - 1; h++) {
				if (this.Hedge[i][h] == 0) {
					System.out.print(" --");
				} else {
					System.out.print(" ==");
				}
			}
			System.out.println();
			for (int h = 0; h < this.columns; h++) {
				if (this.Vedge[i][h] == 0) {
					System.out.print("|  ");
				} else {
					System.out.print("l  ");
				}

			}
			System.out.println();
		}
		for (int h = 0; h < this.columns - 1; h++) {
			if (this.Hedge[this.rows - 1][h] == 0) {
				System.out.print(" --");
			} else {
				System.out.print(" ==");
			}
		}
	}

	public boolean equals(Table o) {
		for (int i = 0; i < this.rows; i++)
			for (int j = 0; j < (this.columns - 1); j++)
				if (this.Hedge[i][j] != o.Hedge[i][j]) {
					return false;
				}
		for (int i = 0; i < (this.rows - 1); i++)
			for (int j = 0; j < this.columns; j++)
				if (this.Vedge[i][j] != o.Vedge[i][j]) {
					return false;
				}
		for (int i = 0; i < (rows - 1); i++)
			for (int j = 0; j < (columns - 1); j++)
				if (this.Cbox[i][j] != o.Cbox[i][j]) {
					return false;
				}
		return true;
	}
}

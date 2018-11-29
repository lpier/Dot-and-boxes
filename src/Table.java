public class Table {
	int i;
	int j;
	int[][] Hedge;
	int[][] Vedge;
	int[][] Cbox;

	public Table(int i, int j, int[][] hedge, int[][] vedge, int[][] cbox) {
		super();
		this.i = i;
		this.j = j;
		Hedge = hedge;
		Vedge = vedge;
		Cbox = cbox;
	}

	public Table(int i, int j) {
		super();
		this.i = i;
		this.j = j;
		Hedge = new int[i][j - 1];
		Vedge = new int[i - 1][j];
		Cbox = new int[i - 1][j - 1];
		for (int x = 0; x < i - 1; x++) {
			for (int y = 0; y < j - 1; y++) {
				Cbox[x][y] = 0;
			}

		}
		for (int x = 0; x < i; x++) {
			for (int y = 0; y < j - 1; y++) {
				Hedge[x][y] = 0;
			}

		}
		for (int x = 0; x < i - 1; x++) {
			for (int y = 0; y < j; y++) {
				Vedge[x][y] = 0;
			}

		}
	}

	public void calcPosession() {
		for (int i = 0; i < this.i - 1; i++) {
			for (int j = 0; j < this.i - 1; j++) {
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
		switch(play.pos) {
		case "n":
			this.Hedge[play.x][play.y] = play.T;
			break;
		case "s":
			this.Hedge[play.x + 1][play.y] = play.T;
			break;
		case "e":
			this.Vedge[play.x][play.y + 1] = play.T;
			break;
		case "o":
			this.Vedge[play.x][play.y] = play.T;
			break;
		default:
			break; 	
		}
	}

	public void insertPlay(int x, int y, String P, int T) {
		switch (P.trim().toLowerCase()) {
		case "n":
			this.Hedge[x][y] = T;
			break;
		case "s":
			this.Hedge[x + 1][y] = T;
			break;
		case "e":
			this.Vedge[x][y + 1] = T;
			break;
		case "o":
			this.Vedge[x][y] = T;
			break;
		default:
			break; 
		}
		this.calcPosession();
	}

	public int pointsP1() {
		int p = 0;
		for (int i = 0; i < this.i - 1; i++) {
			for (int j = 0; j < this.i - 1; j++) {
				if (this.Cbox[i][j] == 1) {
					p++;
				}
			}
		}

		return p;
	}

	public int pointsP2() {
		int p = 0;
		for (int i = 0; i < this.i - 1; i++) {
			for (int j = 0; j < this.i - 1; j++) {
				if (this.Cbox[i][j] == -1) {
					p++;
				}
			}
		}

		return p;
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

	public void print() {

		for (int i = 0; i < this.i - 1; i++) {
			for (int h = 0; h < this.j - 1; h++) {
				if (this.Hedge[i][h] == 0) {
					System.out.print(" --");
				} else {
					System.out.print(" ==");
				}
			}
			System.out.println();
			for (int h = 0; h < this.j; h++) {
				if (this.Vedge[i][h] == 0) {
					System.out.print("|  ");
				} else {
					System.out.print("l  ");
				}

			}
			System.out.println();
		}
		for (int h = 0; h < this.j - 1; h++) {
			if (this.Hedge[this.i - 1][h] == 0) {
				System.out.print(" --");
			} else {
				System.out.print(" ==");
			}
		}

	}
}

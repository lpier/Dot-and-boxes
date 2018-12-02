import java.util.Scanner;

public abstract class Player {
    
	private int points;

	public Player() {
		super();
		this.points = 0;
	}
		
	public Player(int points) {
		super();
		this.points = points;
	}
	
	public abstract void makePlay(Scanner scanner, Table tablero, int T);
	
}	

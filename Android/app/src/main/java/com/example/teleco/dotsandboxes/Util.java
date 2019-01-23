package com.example.teleco.dotsandboxes;

public class Util {
	
	public static final int PLAYER1 = 1;
	public static final int PLAYER2 = 2;
	
	public static final int PLAYER1_ID = 1;
	public static final int PLAYER2_ID = -1;
	
	public static int togglePlayer(int player) {
		return player == 1 ? 2 : 1;
    }
	
}

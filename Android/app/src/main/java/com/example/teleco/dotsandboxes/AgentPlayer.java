package com.example.teleco.dotsandboxes;

import java.util.Scanner;

public class AgentPlayer extends Player {

	@Override
	public void makePlay(Scanner scanner, Table table, int T) {
		Tree tree = Minimax.constructTree(table);
		Edge edge = tree.checkBest();
		table.insertPlay(edge.getX(), edge.getY(), edge.isHorizontal(), T);
	}

}

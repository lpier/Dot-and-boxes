package com.example.teleco.dotsandboxes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class Minimax {

	public static Tree constructTree(Table table) {
		Tree tree = new Tree();
		Node root = new Node(table, true);
		tree.setRoot(root);
		int maxLevel = setMaxLevel(table);
		constructTree(root, 0, maxLevel);
		return tree;
	}

	private static void constructTree(Node parentNode, int level, int maxLevel) {
		int currentLevel = level + 1;
		ArrayList<Edge> listOfMoves = parentNode.getCurrentBoard().getMoves();
		boolean isChildMaxPlayer = !parentNode.isMaxPlayer();
		listOfMoves.forEach(edge -> {
			Node newNode = new Node(edge, parentNode.getCurrentBoard(), isChildMaxPlayer,
					Util.togglePlayer(parentNode.getPlayer()));
			parentNode.addChild(newNode);
			if (newNode.getCurrentBoard().getMoves().isEmpty() == false && currentLevel < maxLevel) {
				constructTree(newNode, currentLevel, maxLevel);
			}
		});
	}

	private static int setMaxLevel(Table table) {
		int available = table.getMoves().size();
		int totalMoves = table.rows * (table.columns-1)*2;
		int played = 100 - (available*100/totalMoves);
		if(played < 10)
			return 2;
		if(played < 15)
			return 3;
		if(played < 50)
			return 4;
		if(played < 75)
			return 6;
		else
			return 7;
	}
}

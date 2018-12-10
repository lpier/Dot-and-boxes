package com.example.teleco.dotsandboxes;

public class Play {
    private int x, y, T;
	String pos;
	boolean horizontal;
    
	public Play() {
	}
	
	public Play(int x, int y, String pos, int t) {
		super();
		this.x = x;
		this.y = y;
		T = t;
		this.pos = pos.trim().toLowerCase();
		this.getOrientationFromCardinality();
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

	public int getT() {
		return T;
	}
	public void setT(int t) {
		T = t;
	}

	public String getPos() {
		return pos;
	}
	public void setPos(String pos) {
		this.pos = pos;
	}

	private void getOrientationFromCardinality(){
		if(this.pos.equals("n") || pos.equals("s"))
			this.horizontal = false;
		else
			this.horizontal = true;

	}

}
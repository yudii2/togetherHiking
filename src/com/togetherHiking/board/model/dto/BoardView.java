package com.togetherHiking.board.model.dto;

public class BoardView extends Board {
	
	private int replyCnt;

	public BoardView() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getReplyCnt() {
		return replyCnt;
	}

	public void setReplyCnt(int replyCnt) {
		this.replyCnt = replyCnt;
	}

	@Override
	public String toString() {
		return super.toString() + "BoardView [replyCnt=" + replyCnt + "]";
	}
	
	
	
}

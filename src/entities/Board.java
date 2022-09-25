package entities;

public class Board {
	
	private Card[][] cards;
	
	public Board() {
		
	}
	
	public Board(int rows, int columns) {
		this.cards = new Card[rows][columns];
	}
	
}

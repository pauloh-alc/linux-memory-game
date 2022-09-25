package entities;

public class Board {
	
	private Card[][] cards;
	
	public Board() {
		
	}
	
	public Board(int rows, int columns) {
		this.startBoard(rows, columns);
	}
	
	private void startBoard(int rows, int columns) {
		
		this.cards = new Card[rows][columns];
		int pairs = (rows * columns) / 2;
		int i = pairs, j = 0;
		
		for (int row = 0; row < rows; row++) {
			for (int column = 0; column < columns; column++) {
				
				if (column % 2 == 0) { 
					this.cards[row][column] = new Card(i, new Point(row, column), "");										
					i--;
				} else { 
					this.cards[row][column] = new Card(j, new Point(row, column), "");
					j++;
				}
				System.out.print(cards[row][column].getNumber()+" ");
			}
			System.out.println();
		}
	}
	
}

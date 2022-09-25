package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {
	
	private Card[][] cards;
	
	public Board() {
		
	}
	
	public Board(int rows, int columns) {
		this.startBoard(rows, columns);
	}
	
	private void startBoard(int rows, int columns) {
		
		this.cards = new Card[rows][columns];
		List<Point> pointsList = new ArrayList<Point>();
		int pairs = (rows * columns) / 2;
		int i = pairs, j = 0;
		
		for (int row = 0; row < rows; row++) {
			for (int column = 0; column < columns; column++) {
				
				if (column % 2 == 0) { 
					this.cards[row][column] = new Card(i, choosePosition(pointsList, rows, columns), "");										
					i--;
				} else { 
					this.cards[row][column] = new Card(j, choosePosition(pointsList, rows, columns), "");
					j++;
				}
				System.out.print("("+cards[row][column].getPoint().getX()+","+cards[row][column].getPoint().getY()+") ");
			}
			System.out.println();
		}
	}
	
	private Point choosePosition(List<Point> pointsList, int rows, int columns) {
		
		Random random = new Random();
		Point point;
		boolean exist = true;
		
		do {
		
			int row = random.nextInt(rows);
			int column = random.nextInt(columns);
		
			point = new Point(row, column); 
			
			if ( ! contains(pointsList, point)) {
				pointsList.add(point);
				exist = false;
			}
			
		} while (exist);
				
		return point;
	}
	
	private boolean contains(List<Point> list, Point point) {
		for (Point p : list) {
			if (p.getX() == point.getX() && p.getY() == point.getY()) {
				return true;
			}
		}
		return false;
	}
}

package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import entities.enums.State;
import utils.FileResource;

public class Board {
	
	private Card[][] cards;
	
	public Board(int rows, int columns) {
		this.startBoard(rows, columns);
	}
	
	public Card[][] getCards() {
		return cards;
	}

	private void startBoard(int rows, int columns) {
		
		String path = "./src/teste.txt";
		FileResource resource = new FileResource(path);
		
		this.cards = new Card[rows][columns];
		List<Point> pointsList = new ArrayList<Point>();
		String[] roundCardList = getListOfPairs(resource.getLines(), rows, columns);

		int pairs = (rows * columns) / 2;
		int i = pairs, j = 0, count1 = 0, count2 = 0;
		
		System.out.println();
		
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < columns; c++) {
				
				Point point = choosePosition(pointsList, rows, columns);
				int row = point.getX(); int column = point.getY();
				
				if (c % 2 == 0) { 
					String slice1 = roundCardList[count1].split("\\|")[0]; count1++;
					this.cards[row][column] = new Card(i, point, slice1, State.DOWN, 'H');										
					i--;
				} else { 
					String slice2 = roundCardList[count2].split("\\|")[1]; count2++;
					this.cards[row][column] = new Card(j, point, slice2, State.DOWN, 'M');
					j++;
				}
				
				System.out.print(cards[row][column].getText() + "-");
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
	
	private boolean contains(String[] list, int limit, String line) {
		for (int i = 0; i < limit; i++) {
			if (list[i].equals(line)) {
				return true;
			}
		}
		return false;
	}
	
	private String[] getListOfPairs (List<String> list, int rows, int columns) {
		
		int numberOfPairs = (rows * columns) / 2;
		Random random = new Random();
		String[] chosenElements = new String[numberOfPairs];
		boolean exist;
	
		for (int i = 0; i < numberOfPairs; i++) {
			int value;
			exist = true;
			do {
				value = random.nextInt(list.size());
				if ( ! contains(chosenElements, i, list.get(value))) {
					chosenElements[i] = list.get(value);
					exist = false;
				}
			} while (exist);
		}
		
		return chosenElements;
	}
}

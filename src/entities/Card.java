package entities;

public class Card {
	
	private int number;
	private Point point;
	private String text;
	
	public Card() {
		
	}

	public Card(int number, Point point, String text) {
		this.number = number;
		this.point = point;
		this.text = text;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
}

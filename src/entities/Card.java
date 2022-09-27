package entities;

import entities.enums.State;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Card {
	
	private int number;
	private Point point;
	private String text;
	private State state;
	private char type;
	
	public Card(int number, Point point, String text, State state, char type) {
		this.number = number;
		this.point = point;
		this.text = text;
		this.state = state;
		this.type = type;
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

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public char getType() {
		return type;
	}

	public void setType(char type) {
		this.type = type;
	}
	
	public static ImageView generateImage() {
		
		String path = "./images/pinguin-linux.png";
		
		Image image = new Image(path);
		ImageView imageView = new ImageView(image);
		
		imageView.setPreserveRatio(true);
		imageView.setFitHeight(80);
		
		return imageView;
	}
}

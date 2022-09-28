package entities;

import application.Game;
import entities.enums.State;
import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.geometry.Point3D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import utils.ColorResource;

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

	public static void adjustSelectedCard(int row, int column) {
		Game.buttons[row][column].setGraphic(null);
		Game.buttons[row][column].setText(Game.cards[row][column].getText());
		Game.cards[row][column].setState(State.UP);

		RotateTransition rotateTransition = new RotateTransition();
		rotateTransition.setDuration(Duration.millis(1500));
		rotateTransition.setNode(Game.buttons[row][column]);
		rotateTransition.setAxis(new Point3D(0, 50, 0));
		rotateTransition.setByAngle(360);
		rotateTransition.setCycleCount(1);
		rotateTransition.setAutoReverse(false);
		rotateTransition.play();
	}

	public static void adjustMatchedCards(int row0, int column0, int row1, int column1) {
		Game.cards[row0][column0].setState(State.UP);
		Game.cards[row1][column1].setState(State.UP);

		String color = ColorResource.genarateHexadecimalColor();

		Game.buttons[row0][column0].setBorder(new Border(new BorderStroke(Color.web(color), BorderStrokeStyle.SOLID,
				CornerRadii.EMPTY, new BorderWidths(7, 7, 7, 7))));

		Game.buttons[row1][column1].setBorder(new Border(new BorderStroke(Color.web(color), BorderStrokeStyle.SOLID,
				CornerRadii.EMPTY, new BorderWidths(7, 7, 7, 7))));
	}

	public static void resetUnmatchedCards(int row0, int column0, int row1, int column1) {

		KeyFrame keyFrame1 = new KeyFrame(Duration.seconds(2.5),
				e -> Game.buttons[row0][column0].setGraphic(Card.generateImage()));
		KeyFrame keyFrame2 = new KeyFrame(Duration.seconds(2.5), e -> Game.buttons[row0][column0].setText(""));

		Timeline timeline1 = new Timeline(keyFrame1, keyFrame2);
		timeline1.setCycleCount(1);
		timeline1.play();

		KeyFrame keyFrame3 = new KeyFrame(Duration.seconds(2.5),
				e -> Game.buttons[row1][column1].setGraphic(Card.generateImage()));
		KeyFrame keyFrame4 = new KeyFrame(Duration.seconds(2.5), e -> Game.buttons[row1][column1].setText(""));

		Timeline timeline2 = new Timeline(keyFrame3, keyFrame4);
		timeline2.setCycleCount(1);
		timeline2.play();

		Game.cards[row0][column0].setState(State.DOWN);
		Game.cards[row1][column1].setState(State.DOWN);
	}
}

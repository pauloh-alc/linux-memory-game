package application;

import entities.Board;
import entities.Card;
import entities.enums.State;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Game extends Application {

	private final static int ROWS = 3;
	private final static int COLUMNS = 8;

	private Board board;

	public static Button[][] buttons;
	public static Card[][] cards;

	private static Card[] selectedPair = new Card[2];
	private static Integer[] arrOfChosenCardNumbers = new Integer[2];
	
	private static int currentCard = 0;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		
		final int WIDTH = 1280;
		final  int HEIGHT = 1024;

		HBox hbox = addHBox();
		GridPane grid = addGridPane();

		Button resetButton = (Button) hbox.getChildren().get(1);
		Button exitButton = (Button) hbox.getChildren().get(2);

		resetButton.setOnAction((ActionEvent e) -> {
			generateBoardWithCards(grid);
			listenUserActionOnTheBoard();
		});

		exitButton.setOnAction((ActionEvent e) -> {
			primaryStage.close();
		});

		listenUserActionOnTheBoard();

		BorderPane border = new BorderPane();
		border.setTop(hbox);
		border.setCenter(grid);

		Scene scene = new Scene(border, WIDTH, HEIGHT);

		primaryStage.setScene(scene);
		primaryStage.setTitle("Linux-Memory-Game");
		primaryStage.show();
	}

	private HBox addHBox() {

		HBox hbox = new HBox();

		hbox.setPadding(new Insets(10, 14, 10, 14));
		hbox.setStyle("-fx-background-color: #ffb229;");
		hbox.setAlignment(Pos.TOP_CENTER);
		hbox.setSpacing(100);

		Text textGameName = new Text("/ Linux-Memory-Game");
		textGameName.setFont(
				Font.loadFont(getClass().getClassLoader().getResourceAsStream("fonts/VCR_OSD_MONO_1.001.ttf"), 24));

		Button resetButton = new Button("Reset");
		resetButton.setMinWidth(140);
		resetButton.setEffect(null);
		resetButton.setBorder(new Border(new BorderStroke(Color.web("#000000"), BorderStrokeStyle.SOLID,
				CornerRadii.EMPTY, new BorderWidths(3, 3, 3, 3))));

		Button exitButton = new Button("Exit");
		exitButton.setMinWidth(140);
		exitButton.setBorder(new Border(new BorderStroke(Color.web("#ff0000"), BorderStrokeStyle.SOLID,
				CornerRadii.EMPTY, new BorderWidths(3, 3, 3, 3))));

		hbox.setSpacing(5);

		HBox.setMargin(textGameName, new Insets(10, 10, 10, 10));
		HBox.setMargin(resetButton, new Insets(10, 10, 10, 10));
		HBox.setMargin(exitButton, new Insets(10, 10, 10, 10));

		hbox.getChildren().addAll(textGameName, resetButton, exitButton);

		return hbox;
	}

	private GridPane addGridPane() {

		GridPane gridPane = new GridPane();
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setStyle("-fx-background-color: #000000;");
		gridPane.setVgap(5);
		gridPane.setHgap(5);

		generateBoardWithCards(gridPane);

		return gridPane;
	}

	private void generateBoardWithCards(GridPane gridPane) {

		if (!gridPane.getChildren().isEmpty()) {
			for (int i = 0; i < ROWS; i++) {
				for (int j = 0; j < COLUMNS; j++) {
					gridPane.getChildren().remove(buttons[i][j]);
				}
			}
		}

		board = new Board(ROWS, COLUMNS);
		cards = board.getCards();
		buttons = new Button[ROWS][COLUMNS];

		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				Card card = cards[i][j];
				int row = card.getPoint().getX();
				int column = card.getPoint().getY();

				Button btn = new Button();
				btn.setGraphic(Card.generateImage());
				btn.setMinSize(120, 200);
				btn.setMaxSize(120, 200);
				btn.setWrapText(true);
				btn.setTextAlignment(TextAlignment.CENTER);
				btn.setStyle("-fx-font-weight: bolder; -fx-font-size: 10px");

				gridPane.add(btn, column, row);
				buttons[i][j] = btn;
			}
		}
	}

	private static void listenUserActionOnTheBoard() {
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				int row = i;
				int column = j;
				Button btn = buttons[row][column];

				btn.setOnAction((ActionEvent e) -> {
					Game.analyzeCardClicked(row, column);
				});
			}
		}
	}

	private static void analyzeCardClicked(int row, int column) {

		Card card = cards[row][column];
		State cardStatus = card.getState();

		prepareForMatchVerification(card);

		if (cardStatus == State.DOWN) {
			Card.adjustSelectedCard(row, column);
			Game.checkIfPairHasBeenSelected();
		}
	}

	private static void prepareForMatchVerification(Card card) {
		arrOfChosenCardNumbers[currentCard] = card.getNumber();
		selectedPair[currentCard] = card;
	}

	private static void checkIfPairHasBeenSelected() {

		final int PAIR = 2;

		currentCard += 1;

		if (currentCard == PAIR) {

			int row0 = selectedPair[0].getPoint().getX();
			int column0 = selectedPair[0].getPoint().getY();

			int row1 = selectedPair[1].getPoint().getX();
			int column1 = selectedPair[1].getPoint().getY();

			if (checkMatch())
				Card.adjustMatchedCards(row0, column0, row1, column1);
			else
				Card.resetUnmatchedCards(row0, column0, row1, column1);

			currentCard = 0;
		}
	}

	private static boolean checkMatch() {

		boolean conformsToPattern = arrOfChosenCardNumbers[0] + arrOfChosenCardNumbers[1] == (ROWS * COLUMNS) / 2;
		boolean isDifferentType = selectedPair[0].getType() != selectedPair[1].getType();

		return (conformsToPattern && isDifferentType) ? true : false;
	}

}

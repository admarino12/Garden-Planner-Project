package src.main.java;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;

public class DrawGardenPane {

	// Season Styles
	final private String springStyle = "-fx-background-color: #81EEA4;";
	final private String summerStyle = "-fx-background-color: #FFF4B3;";
	final private String fallStyle = "-fx-background-color: #E8C696;";
	final private String winterStyle = "-fx-background-color: #E6FFFF;";
	final private String allSeasonStyle = "-fx-background-color: #bff5d0;";

	ObservableList<String> seasonOptions = FXCollections.observableArrayList("All Seasons", "Spring", "Summer",
			"Autumn", "Winter");
	final ComboBox<String> selectSeason = new ComboBox<String>(seasonOptions);

	// DrawGardenPane ToolBar
	private ToolBar drawGardenToolBar;
	private ToggleButton eraseButton = new ToggleButton("Erase");
	private ToggleButton drawButton = new ToggleButton("Draw");

	// DrawGardenPane MainPane
	private BorderPane holder;
	private BorderPane drawGardenBorder;
	private Canvas drawGardenCanvas;

	private Label gardenDimLabel;

	// DrawGardenPane Dimensions
	final private int DRAW_GARDENPANE_WIDTH = 800;
	final private int DRAW_GARDENPANE_HEIGHT = 800;

	// Incremeter
	final private double GRID_INCREMENT = DRAW_GARDENPANE_WIDTH / 10;

	public DrawGardenPane(View mainView, int width, int height) {

		drawGardenCanvas = new Canvas(DRAW_GARDENPANE_WIDTH, DRAW_GARDENPANE_HEIGHT);
		drawGardenCanvas.minWidth(DRAW_GARDENPANE_WIDTH);
		drawGardenCanvas.minHeight(DRAW_GARDENPANE_HEIGHT);

		holder = new BorderPane();

		// grid
		for (int i = 0; i < DRAW_GARDENPANE_WIDTH; i += GRID_INCREMENT) {
			Line vertical = new Line(i, 0, i, DRAW_GARDENPANE_WIDTH);
			vertical.setStroke(Color.DARKGRAY);
			vertical.setStrokeWidth(0.9);
			Line horizontal = new Line(0, i, DRAW_GARDENPANE_WIDTH, i);
			horizontal.setStroke(Color.DARKGRAY);
			horizontal.setStrokeWidth(0.9);
			holder.getChildren().addAll(vertical, horizontal);
		}

		holder.getChildren().add(drawGardenCanvas);

		mainView.control.setHandlerForDrawButton(drawButton);
		mainView.control.setHandlerForEraseButton(eraseButton);
		mainView.control.setHandlerForSeasonComboBox(selectSeason);

		holder.setStyle(allSeasonStyle);
		drawGardenToolBar = new ToolBar();
		HBox hb3 = new HBox();
		HBox hb2 = new HBox();
		Separator separator = new Separator();
		separator.setOrientation(Orientation.VERTICAL);
		selectSeason.setMaxWidth(400);
		hb2.setPadding(new Insets(5, 10, 5, 1));
		hb2.getChildren().addAll(drawButton, separator, eraseButton);
		hb3.setPadding(new Insets(5, 10, 5, 572));
		hb3.getChildren().addAll(selectSeason);
		drawGardenToolBar.getItems().addAll(hb2, hb3);

		drawGardenBorder = new BorderPane();
		drawGardenBorder.setMinWidth(DRAW_GARDENPANE_WIDTH);
		drawGardenBorder.setMinHeight(DRAW_GARDENPANE_HEIGHT);
		drawGardenBorder.setCenter(holder);
		drawGardenBorder.setTop(drawGardenToolBar);

		gardenDimLabel = new Label();
		gardenDimLabel.setText(width + "ft x " + height + "ft");
		gardenDimLabel.setLayoutX(0);
		gardenDimLabel.setLayoutY(0);
		gardenDimLabel.setFont(new Font(24));
		gardenDimLabel.setPadding(new Insets(0, 0, 0, 0));
		holder.getChildren().add(gardenDimLabel);

	}

	public void setSeason(Season season) {
		switch (season) {
		case SPRING:
			holder.setStyle(springStyle);
			break;
		case SUMMER:
			holder.setStyle(summerStyle);
			break;
		case AUTUMN:
			holder.setStyle(fallStyle);
			break;
		case WINTER:
			holder.setStyle(winterStyle);
			break;
		default:
			holder.setStyle(allSeasonStyle);
			break;
		}
	}

	public void setSeasonComboBox(Season season) {
		switch (season) {
		case SPRING:
			selectSeason.getSelectionModel().select("Spring");
			break;
		case SUMMER:
			selectSeason.getSelectionModel().select("Summer");
			break;
		case AUTUMN:
			selectSeason.getSelectionModel().select("Autumn");
			break;
		case WINTER:
			selectSeason.getSelectionModel().select("Winter");
			break;
		default:
			selectSeason.getSelectionModel().select("All Seasons");
			break;
		}
	}

	public void setGardenDim(int width, int height) {
		gardenDimLabel.setText(width + "ft x " + height + "ft");
	}

	public BorderPane getDrawGardenBorder() {
		return drawGardenBorder;
	}

	public Canvas getDrawGardenCanvas() {
		return drawGardenCanvas;
	}

	public ToggleButton getEraseButton() {
		return eraseButton;
	}

	public ToggleButton getDrawButton() {
		return drawButton;
	}

	public BorderPane getHolder() {
		return holder;
	}
}

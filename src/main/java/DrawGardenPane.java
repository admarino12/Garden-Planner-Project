package src.main.java;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class DrawGardenPane {

//	ObservableList<String> seasonOptions = FXCollections.observableArrayList("Spring", "Summer", "Fall", "Winter");
//
//	final ComboBox<String> seasons = new ComboBox<String>(seasonOptions);

	// Season Buttons
	private Button springButton = new Button("Spring");
	private Button summerButton = new Button("Summer");
	private Button fallButton = new Button("Autumn");
	private Button winterButton = new Button("Winter");

	// DrawGardenPane ToolBar
	private ToolBar drawGardenToolBar;
	private ToggleButton eraseButton = new ToggleButton("Erase");
	private ToggleButton drawButton = new ToggleButton("Draw");

	// DrawGardenPane MainPane
	private StackPane holder;
	private BorderPane drawGardenBorder;
	private Canvas drawGardenCanvas;

	// DrawGardenPane Dimensions
	final private int DRAW_GARDENPANE_WIDTH = 1000;
	final private int DRAW_GARDENPANE_HEIGHT = 750;

	public DrawGardenPane(View mainView) {

		drawGardenCanvas = new Canvas(DRAW_GARDENPANE_WIDTH, DRAW_GARDENPANE_HEIGHT);
		drawGardenCanvas.minWidth(DRAW_GARDENPANE_WIDTH);
		drawGardenCanvas.minHeight(DRAW_GARDENPANE_HEIGHT);

		holder = new StackPane();
		holder.getChildren().add(drawGardenCanvas);

		String defaultcolor = "-fx-background-color: #81EEA4, linear-gradient(from 0.5px 0px to 20.5px 0px, repeat, gray 1%, transparent 2%), linear-gradient(from 0px 0.5px to 0px 20.5px, repeat, gray 1%, transparent 2%);";
		holder.setStyle(defaultcolor);
		drawGardenToolBar = new ToolBar();
		HBox hb3 = new HBox();
		HBox hb2 = new HBox();
		Separator separator = new Separator();
		separator.setOrientation(Orientation.VERTICAL);
		Separator separator1 = new Separator();
		separator1.setOrientation(Orientation.VERTICAL);
		Separator separator2 = new Separator();
		separator2.setOrientation(Orientation.VERTICAL);
		Separator separator3 = new Separator();
		separator3.setOrientation(Orientation.VERTICAL);
		hb2.setPadding(new Insets(5, 10, 5, 1));
		hb2.getChildren().addAll(drawButton, separator, eraseButton);
		hb3.setPadding(new Insets(5, 10, 5, 470));
		hb3.getChildren().addAll(springButton, separator1, summerButton, separator2, fallButton, separator3,
				winterButton);
		drawGardenToolBar.getItems().addAll(hb2, hb3);

		drawGardenBorder = new BorderPane();
		drawGardenBorder.setMinWidth(DRAW_GARDENPANE_WIDTH);
		drawGardenBorder.setMinHeight(DRAW_GARDENPANE_HEIGHT);
		drawGardenBorder.setCenter(holder);
		drawGardenBorder.setTop(drawGardenToolBar);

	}

	public Button getWinterButton() {
		return winterButton;
	}

	public Button getFallButton() {
		return fallButton;
	}

	public Button getSummerButton() {
		return summerButton;
	}

	public Button getSpringButton() {
		return springButton;
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

//	public ComboBox<String> getSeasons() {
//		return seasons;
//	}

	public StackPane getHolder() {
		return holder;
	}
}

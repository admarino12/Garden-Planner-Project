package src.main.java;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class ToolBarPane {
	private ToolBar ratingToolBar;
	private Circle circle;
	
	
	public ToolBarPane(View mainView) {
		
		Label rating = new Label("Rating:");
		ratingToolBar = new ToolBar();
		
		Button save = new Button("Save");
		Button open = new Button("Open");
		mainView.control.setHandlerForSaveClicked(save);
		mainView.control.setHandlerForOpenClicked(open);
		
		HBox hb4 = new HBox();
		circle = new Circle();
		circle.setCenterX(25.0f);
		circle.setCenterY(25.0f);
		circle.setRadius(10.0f);
		circle.setFill(Color.GOLD);
		circle.setStroke(Color.BLACK);
		circle.setStrokeWidth(1);
		hb4.setPadding(new Insets(5, 10, 5, 5));
		hb4.getChildren().addAll(circle);
		ratingToolBar.getItems().addAll(rating, hb4, save, open);
	}
	
	public ToolBar getRatingToolBar() {
		return ratingToolBar;
	}
}

package src.main.java;


import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class ToolBarPane {
	private ToolBar ratingToolBar;
	private Circle[] ratingCircles;
	final private int RATING_TOTAL = 4;
	
	
	public ToolBarPane(View mainView) {
		
		Label rating = new Label("Rating:");
		rating.setPadding(new Insets(0,0,0,965));
		ratingToolBar = new ToolBar();
		ratingToolBar.setPadding(new Insets(0,0,0,5));
		
		
		Button save = new Button("Save");
		Button open = new Button("Open");
		mainView.control.setHandlerForSaveClicked(save);
		mainView.control.setHandlerForOpenClicked(open);
		
		HBox hb4 = new HBox();
		ratingCircles = new Circle[RATING_TOTAL];
		for(int i=0;i<ratingCircles.length;i++) {
			Circle circle = new Circle();
			circle.setCenterX(25.0f);
			circle.setCenterY(25.0f);
			circle.setRadius(10.0f);
			circle.setFill(Color.WHITE);
			circle.setStroke(Color.BLACK);
			circle.setStrokeWidth(1);
			ratingCircles[i] = circle;
		}
		hb4.setPadding(new Insets(5, 10, 5, 5));
		hb4.setSpacing(5);
		hb4.getChildren().addAll(ratingCircles);
		ratingToolBar.getItems().addAll(save, open, rating, hb4);
	}
	
	public ToolBar getRatingToolBar() {
		return ratingToolBar;
	}
	
	public void updateRating(int rating) {
		for(int i=0;i<ratingCircles.length;i++) {
			if(i<rating) {
				ratingCircles[i].setFill(Color.GOLD);
			}
			else ratingCircles[i].setFill(Color.WHITE);
		}
	}
}

package src.main.java;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class StartPageView {
	public StartPageView(View view) {
			//Front Page View
			Stage popupwindow = new Stage();
		      
			popupwindow.initModality(Modality.APPLICATION_MODAL);
			popupwindow.setTitle("Front Page");
			      
			//start button to get to garden view
			Button startBtn = new Button("Start New Garden"); 
			startBtn.setMaxSize(200, 200);
			startBtn.setStyle("-fx-text-alignment: center");
			startBtn.setStyle("-fx-background-color: #D1EBDC");
			startBtn.setOnAction(e -> popupwindow.close()); //close pop up on click
			VBox layout = new VBox(10);
		
			//import background image
			Image image;
			try {
				image = new Image(new FileInputStream("src/resources/images/Front_Page.png"));
				BackgroundImage bg = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, null, null);
				Background background = new Background(bg);
				
				layout.setBackground(background);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			
			//setting up layout of Front Page
			layout.setPadding(new Insets(220,10,10,10));
			layout.getChildren().addAll(startBtn);
			layout.setAlignment(Pos.CENTER);
			
			//set Front Page scene
			Scene scene1= new Scene(layout,view.getROOT_WIDTH(), view.getROOT_HEIGHT()); 
			popupwindow.setScene(scene1);
			popupwindow.showAndWait(); //once pop up is closed, Garden Builder View is shown
	}
}

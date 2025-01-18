package game.gui;
//import java.io.IOException;
//import javafx.application.Application;
//import javafx.stage.Stage;
//
//public class GameGUI extends Application {
//	
//
//	 public void start(Stage primaryStage) throws IOException {
//		 game.gui.GameControl.start();
//      primaryStage.close();    
//	 
//	 
//	 }  
//	
//	
//	  public static void main(String[] args) {
//	        launch(args);
//	    }
//	}
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameGUI extends Application {

    public static AudioClip hoverSound;
    public static AudioClip clickSound;
    public static  GameControl g=new GameControl();
    @Override
    public void start(Stage primaryStage) {
    	GameControl g=new GameControl();
    	
    	g.start();
        
    }

    private void showMainMenu(Stage primaryStage, ImageView background) {
        // Title
        Button title = new Button("");
        title.setStyle("-fx-font-size: 36px; -fx-background-color: transparent; -fx-text-fill: white;");

        // Menu options
        Button startButton = createButton("Start Game");
        Button Description = createButton("Description");
        Button settingsButton = createButton("Settings");
        Button exitButton = createButton("Exit");

        startButton.setOnAction(e -> {
            clickSound.play();
            System.out.println("Starting game...");
        });
        Description.setOnAction(e -> {
            clickSound.play();
            System.out.println("Loading game...");
        });
        settingsButton.setOnAction(e -> {
            clickSound.play();
            System.out.println("Opening settings...");
        });
        exitButton.setOnAction(e -> {
            clickSound.play();
            primaryStage.close();
        });

        VBox menu = new VBox(10, startButton, Description, settingsButton, exitButton);
        menu.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5); -fx-padding: 20px; -fx-alignment: center;");

        StackPane root = new StackPane();
        root.getChildren().addAll(background, title, menu);
        StackPane.setAlignment(title, javafx.geometry.Pos.TOP_CENTER);
        StackPane.setAlignment(menu, javafx.geometry.Pos.CENTER);

        Scene mainScene = new Scene(root, 800, 600);
        primaryStage.setScene(mainScene);

        // Add entrance animations
        animateButton(startButton, 0);
        animateButton(Description, 100);
        animateButton(settingsButton, 200);
        animateButton(exitButton, 300);
    }

    private Button createButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-font-size: 18px;");

        button.setOnMouseEntered(e -> {
            hoverSound.play();
            button.setStyle("-fx-font-size: 18px; -fx-background-color: #666;");
            animateButtonHover(button, true);
        });

        button.setOnMouseExited(e -> {
            button.setStyle("-fx-font-size: 18px;");
            animateButtonHover(button, false);
        });

        return button;
    }

    private void animateButton(Button button, int delay) {
        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(500), button);
        translateTransition.setFromY(-50);
        translateTransition.setToY(0);
        translateTransition.setDelay(Duration.millis(delay));
        translateTransition.play();

        FadeTransition fadeTransition = new FadeTransition(Duration.millis(500), button);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.setDelay(Duration.millis(delay));
        fadeTransition.play();
    }

    private void animateButtonHover(Button button, boolean hover) {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), button);
        scaleTransition.setToX(hover ? 1.1 : 1.0);
        scaleTransition.setToY(hover ? 1.1 : 1.0);
        scaleTransition.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

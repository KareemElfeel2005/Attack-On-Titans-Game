package game.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import game.engine.exceptions.InsufficientResourcesException;
import game.engine.exceptions.InvalidLaneException;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;


public class GameControl {
private static GameMode gamemode;
private static Stage stage= new Stage();
private static Scene scene;
private static GameView gameView ;
private static String Start = GameControl.class.getResource("Start.css").toExternalForm();
private static String play = GameControl.class.getResource("play.css").toExternalForm();
private static String Game = GameControl.class.getResource("Game.css").toExternalForm();
private static String Description = GameControl.class.getResource("Description.css").toExternalForm();
public static Button quit=new Button();
public static double xOffset = 0;
public static double yOffset = 0;

 
public  void play(){
 Button Easy =  createButton("Easy",300,200);
 Button Hard =  createButton("Hard",300,200);
    GridPane choose = createGridPane();
   choose.addRow(0, Easy);
   choose.addRow(1, Hard);
	
 Easy.setOnAction(new EventHandler <ActionEvent>(){
	 @Override
	 public void handle(ActionEvent event) {
		 gamemode=GameMode.EASY;
		 try{
        	 scene=callscene(gamemode);
			 stage.setScene(scene);
			 }
			 catch(Exception e){}
	 }
	 });
 Hard.setOnAction(new EventHandler <ActionEvent>(){
	 @Override
	 public void handle(ActionEvent event) {
		 gamemode=GameMode.HARD;
		 try{
			 scene=callscene(gamemode);
			 stage.setScene(scene);
			 }
			 catch(Exception e) {}
		 }
	 });
 

 createGridScene( choose, "Game play",1500, 800,play);
 
}
/////////////////////
public  void Description() throws IOException {

	
	BorderPane root=new BorderPane();
	Button Close = createButton("Close",100,100); ;
    Button GameGeneralRules=createButton("Game General Rules",500,100);
    Button TitansDescription=createButton("Titans Description",500,100);
    Button WeaponDescription=createButton("Weapons Description",500,100);
    VBox Des=new VBox();
    Des.getChildren().add(GameGeneralRules);
    Des.getChildren().add(TitansDescription);
    Des.getChildren().add(WeaponDescription);
    GameGeneralRules.setOnMouseEntered(new EventHandler<MouseEvent>(){
		
		@Override
		public void handle(MouseEvent event) {
		 VBox V=new VBox();
		// TextArea Gam=new TextArea();
		 Button Gam=new Button("Attack on Titan Utopia is a one-player endless tower defense game\n"
		 		+ " inspired by the hit anime Attack on Titan. \n"
		 		+ "The story of the anime revolves around how titans, \n"
		 		+ "gigantic humanoid creatures, emerged one day\n"
		 		+ "and wiped out most of humanity. The few surviving humans fled and hid\n"
		 		+ "behind 3 great walls that provided safe haven from the titan threats. Wall\n"
		 		+ "Maria is the outer wall, Wall Rose is the middle wall and Wall Sina is the inside wall.");
		 Gam.setPrefSize(700, 700);
		 Gam.setFont(Font.font(24));
		 V.getChildren().add(Gam);
		 root.setRight(V);
	 }});
    GameGeneralRules.setOnMouseExited(new EventHandler<MouseEvent>(){
		
		@Override
		public void handle(MouseEvent event) {
			VBox V=new VBox();
			 root.setRight(V);
		}});
    TitansDescription.setOnMouseEntered(new EventHandler<MouseEvent>(){
		
		@Override
		public void handle(MouseEvent event) {
   		 VBox V=new VBox();
   		// TextArea Gam=new TextArea();
   		 Button B1=new Button("There are multiple types of titans in this game.\n"
   		 		+ " However, all the titans will have some attributes in common.\n"
   		 		+ " Each titan will have the following stats:");
   		Button B2=new Button("1. HP: The health points of the titan.");
   		Button B3=new Button("2. Damage: The amount of damage the titan does when attacking a wall.");
   		Button B4=new Button("3. Height: The height of the titan in meters, doesn’t affect thegameplay.");
   		Button B5=new Button("4. Distance from walls: How far the titan is from the walls in“Distance Unit”");
   		Button B6=new Button("5. Speed: The distance that the titan moves per turn in “Distance Unit”.");
   		Button B7=new Button("6. Resources value: The amount of resources that the player gains by defeating this titan.");
   		Button B8=new Button("7. Danger level: How much this titan affects a lane’s danger level.");
   		B1.setPrefSize(700, 150);
  		B2.setPrefSize(700, 100);
  		B3.setPrefSize(700, 100);
  		B4.setPrefSize(700, 100);
  		B5.setPrefSize(700, 100);
  		B6.setPrefSize(700, 100);
  		B7.setPrefSize(700, 100);
  		B8.setPrefSize(700, 100);
  		
  		B1.setFont(Font.font(18));
  		B2.setFont(Font.font(18));
  		B3.setFont(Font.font(18));
  		B4.setFont(Font.font(18));
  		B5.setFont(Font.font(18));
  		B6.setFont(Font.font(18));
  		B7.setFont(Font.font(18));
  		B8.setFont(Font.font(18));
  		
   		

   		
   		 V.getChildren().addAll(B1,B2,B3,B4,B5,B6,B7,B8);
   		 root.setRight(V);
   	 }});
    
    TitansDescription.setOnMouseExited(new EventHandler<MouseEvent>(){
		
		@Override
		public void handle(MouseEvent event) {
			VBox V=new VBox();
			 root.setRight(V);
		}});
    WeaponDescription.setOnMouseEntered(new EventHandler<MouseEvent>(){
		
		@Override
		public void handle(MouseEvent event) {
      		 VBox V=new VBox();
      		// TextArea Gam=new TextArea();
      		 Button B1=new Button("There are multiple types of weapons in this game.\n"
      		 		+ " However, all theweapons will have some attributes in common.\n"
      		 		+ " Each weapon will have thefollowing stats:");
      		Button B2=new Button("1. Damage: The amount of damage the weapon does when attacking a Titan.");
      		Button B3=new Button("2. Price: The amount of resources needed to purchase and deploy a weapon of this type.");
      		Button B4=new Button("3.A specific type of weapons, called the “Volley Spread Cannon”\n"
      				+ " will have a couple of extra stats:");
      		Button B5=new Button("3.1. Min Range: The minimum range of the weapon from the wall in “Distance Unit”.");
      		Button B6=new Button("3.2. Max Range: The maximum range of the weapon from the wall in “Distance Unit”.");
      		
      		B1.setPrefSize(700, 150);
      		B2.setPrefSize(700, 100);
      		B3.setPrefSize(700, 100);
      		B4.setPrefSize(700, 100);
      		B5.setPrefSize(700, 100);
      		B6.setPrefSize(700, 100);
      		
      		B1.setFont(Font.font(18));
      		B2.setFont(Font.font(18));
      		B3.setFont(Font.font(18));
      		B4.setFont(Font.font(18));
      		B5.setFont(Font.font(18));
      		B6.setFont(Font.font(18));
      		

      		
      		 V.getChildren().addAll(B1,B2,B3,B4,B5,B6);
      		 root.setRight(V);
    }});
 WeaponDescription.setOnMouseExited(new EventHandler<MouseEvent>(){
		
		@Override
		public void handle(MouseEvent event) {
			VBox V=new VBox();
			 root.setRight(V);
		}});
	
    Close.setOnAction(e -> {this.start();
    });
    root.setLeft(Des);
    root.setBottom(Close);
    createGridScene( root, "Descriptionn",1500, 800,Description);
 
}

/////////////////////////////////
public  void start() {
	stage.setTitle("Attack on Titan Game");

      // Load sound effects
      try {
    	  GameGUI.hoverSound = new AudioClip(getClass().getResource("/button_hover.mp3").toExternalForm());
    	  GameGUI.clickSound = new AudioClip(getClass().getResource("/button_click.mp3").toExternalForm());
          System.out.println("Sound files loaded successfully.");
      } catch (Exception e) {
          System.err.println("Error loading sound files: " + e.getMessage());
          e.printStackTrace();
      }

      // Background image
      ImageView background = null;
      try {
          background = new ImageView(new Image(getClass().getResource("/wall-breach2.png").toExternalForm()));
          background.setFitWidth(800);
          background.setFitHeight(600);
          background.setPreserveRatio(true);
          System.out.println("Background image loaded successfully.");
      } catch (Exception e) {
          System.err.println("Error loading background image: " + e.getMessage());
          e.printStackTrace();
      }

      // Create media player for intro video
      Media introMedia = new Media(getClass().getResource("/intro33.mp4").toExternalForm());
      MediaPlayer mediaPlayer = new MediaPlayer(introMedia);
      MediaView mediaView = new MediaView(mediaPlayer);

      StackPane introRoot = new StackPane(mediaView);
      Scene introScene = new Scene(introRoot, 1100, 550);

      stage.setScene(introScene);
      stage.show();

      mediaPlayer.play();
    final  ImageView background2 = background;
      mediaPlayer.setOnEndOfMedia(() -> showMainMenu(stage, background2));

      mediaPlayer.setOnError(() -> {
          System.err.println("Error playing intro video: " + mediaPlayer.getError().getMessage());
          showMainMenu(stage, background2);
      });    
}


private static Button createButton(String text, int x, int y) {
    Button button = new Button(text);
    button.setPrefSize(x, y);
    button.getStyleClass().add("btn"); // Add the "btn" style class
    return button;
}
private static Button createButton(Button button , int x, int y) {
  
	
	
	
	
	
	
	button.setOnMousePressed((MouseEvent event) -> {
        xOffset = event.getSceneX() - button.getLayoutX();
        yOffset = event.getSceneY() - button.getLayoutY();
    });

   
	button.setOnMouseDragged((MouseEvent event) -> {
		button.setLayoutX(event.getSceneX() - xOffset);
		button.setLayoutY(event.getSceneY() - yOffset);
		
    });
	button.setOnMouseReleased((MouseEvent event) -> {
		button.setLayoutX(x);
		button.setLayoutY(y);
//		button.setLayoutX(event.getSceneX() - xOffset);
//		button.setLayoutY(event.getSceneY() - yOffset);
		gameView.Lanee.setSelected(true);
		 ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

			 Runnable task = () -> gameView.Lanee.setSelected(false);
		        
		        // Schedule the task to run after a 2-second delay
		        scheduler.schedule(task, 15, TimeUnit.MILLISECONDS);
		
       });
//	button.setStyle(
//            "-fx-font-size: 13px; " +
//          // "-fx-border-color: hsl(0, 0%, 10%); " +
//            "-fx-border-width: 1px; " +
//            "-fx-border-radius: 9999; " +
//           "-fx-background-color: transparent; " +
//            "-fx-cursor: hand; " +
//            "-fx-outline: transparent; " +
//            "-fx-outline-offset: 2px; "
//    );
    
	button.setLayoutX(x);
	button.setLayoutY(y);
// button.getStyleClass().add("btn"); // Add the "btn" style class
  return button;
}

private static GridPane createGridPane() {
    GridPane gridPane = new GridPane();
    gridPane.setAlignment(Pos.CENTER);
    return gridPane;
}
private static void createGridScene(Pane choose,String Title,int x,int y ,String css) {
	 Scene scene = new Scene(choose, x, y);
	    stage.setTitle(Title);
	    stage.setScene(scene);
	    
		   scene.getStylesheets().add(css);
	    stage.show();
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
    	GameGUI.clickSound.play();
    	play();
    });
    Description.setOnAction(e -> {
    	GameGUI.clickSound.play();
    	
    });
    settingsButton.setOnAction(e -> {
    	GameGUI.clickSound.play();
        System.out.println("Opening settings...");
    });
    exitButton.setOnAction(e -> {
    	GameGUI.clickSound.play();
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
    	GameGUI.hoverSound.play();
        button.setStyle("-fx-font-size: 18px; -fx-background-color: #666;");
        animateButtonHover(button, true);
      //  ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

//		 Runnable task = () -> GameGUI.hoverSound.play();
//	        
//	        // Schedule the task to run after a 2-second delay
//	        scheduler.schedule(task, 500, TimeUnit.MILLISECONDS);
//	
        
        
        
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


public  Scene callscene(GameMode gameMode) {
	
		 try{
		  gameView=new GameView(gameMode);
		 HBox v=gameView.Stats();
		 HBox v2=gameView.Stats2();
		 HBox v3=gameView.Stats3();
		 
		 
		 
		 
		 Pane game=new Pane();
			// v3.setTranslateX(-200);
		  quit = createButton(createButton("quit",100,40),1100,670);
		
		 Button PiercingCannon=createButton(gameView.getPiercingCannon(),0,670);
		 Button SniperCannon=createButton(gameView.getSniperCannon(),100,670);
		 Button VolleySpreadCannon=createButton(gameView.getVolleySpreadCannon(),200,670);
		 Button WallTrap=createButton(gameView.getWallTrap(),300,670);

		 
		 
		 
		 
		 v.setPrefSize(1500, 40);
		//v.setTranslateY(-280);
		
		v.setLayoutX(5);
		 v.setLayoutY(5);
		v.setMaxHeight(40);
		//v.set
		v2.setPrefSize(1500, 120);
		 v2.setTranslateY(670);
		 v2.setTranslateX(400);
		// v2.setMaxHeight(120);
		 v3.setPrefSize(1500,600);
		 v3.setTranslateY(60);
		 //v3.setTranslateX(-50);
		quit.setOnAction(new EventHandler <ActionEvent>(){
				 @Override
				 public void handle(ActionEvent event) {
					 
					 if ( GameView.timer != null) {
						 GameView.timer.cancel(); // Stop the Timer
				        }  
					 
					 start();
					 
				 
				 
				 
				 }});
	 
	
		
		v.getChildren().add(quit);
		    game.getChildren().add(v);
		   // game.getChildren().;
		    game.getChildren().add(v2);
		    game.getChildren().add(v3);
		   
		    game.getChildren().add(PiercingCannon);
			 game.getChildren().add(SniperCannon);
			 game.getChildren().add(VolleySpreadCannon);
			 game.getChildren().add(WallTrap);
			
			
		
		 Scene scene = new Scene(game, 1500, 800);
		 scene.getStylesheets().add(Game);
	 return scene;}
	 catch(Exception e){
	
		 return scene;
		}

}
}

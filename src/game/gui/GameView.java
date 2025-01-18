package game.gui;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import game.engine.Battle;
import game.engine.exceptions.InsufficientResourcesException;
import game.engine.exceptions.InvalidLaneException;
import game.engine.lanes.Lane;
import game.engine.titans.AbnormalTitan;
import game.engine.titans.ArmoredTitan;
import game.engine.titans.ColossalTitan;
import game.engine.titans.PureTitan;
import game.engine.titans.Titan;
import game.engine.weapons.PiercingCannon;
import game.engine.weapons.SniperCannon;
import game.engine.weapons.VolleySpreadCannon;
import game.engine.weapons.WallTrap;
import game.engine.weapons.Weapon;
import game.engine.weapons.WeaponRegistry;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class GameView {
private static GameMode gameMode; 	
private static TextArea score;
private static TextArea GamePhase;
private static TextArea ResourcesGatherd;
private static TextArea NumberOfTurns;
private static VBox Lanes;
private static TextArea[] lane;
private static VBox weaponsinfo;
private static TextArea[] weapon;
private static GridPane[] titan;
private static ArrayList<Lane> lanes;
private static VBox Titans;
private static VBox Titansinfo;
private static TextArea[] titans;
private static Button passTurn;
private static Button PiercingCannon;
private static Button SniperCannon;
private static Button VolleySpreadCannon;
private static Button WallTrap;
private static Button AI;
private static Button AiRun;
static TextArea view;
static HBox view2;
private static Button[] Buttons;
static GridPane viewButton ;
static  Battle battle;
static  String views;
static int WeaponCode;

private static VBox WeaponsVboxes;
private static TextArea[] WeaponsText;
private static ProgressBar[] healthBar;
private static VBox healthBars;
private static CheckBox animations;
 static CheckBox Lanee;
 static Lane laneSelected;
public static Timer timer = new Timer();

public  GameView(GameMode gamemode) throws IOException{
     gameMode=gamemode;
     WeaponCode=1;
     timer = new Timer();
      healthBar =new ProgressBar[5];
      healthBars=new VBox();
    
     Lanes =new VBox();
      lane=new TextArea[5];
      weaponsinfo=new VBox();
      weapon=new TextArea[5];
      titan=new GridPane[5];
      lanes=new ArrayList<Lane>();
      Titans=new VBox();
      Titansinfo=new VBox();
      titans=new TextArea[5];
      view2=new HBox();
      Buttons=new Button[5];
      viewButton =new GridPane();
      WeaponsVboxes=new VBox();
      WeaponsText=new TextArea[5];
      timer = new Timer();
      timer.cancel();
      //////////////************
      animations=new CheckBox();
      animations.setText("animations");
      
      Lanee=new CheckBox();
      Lanee.setText("lane");
      //////////////*************
       
      
    if(GameMode.EASY==gameMode)
			battle=new Battle(1,0,75,3,250);
			else
				battle=new Battle(1,0,75,5,125);
		
    
    for (Lane L : battle.getLanes())
		 lanes.add(L);
    
    
    String s ="Score: "+battle.getScore();
	score=createTextArea(s,150,30);
	s ="Game Phase: "+battle.getBattlePhase();
    GamePhase=createTextArea(s,150,30);
    s ="Resources Gathered : "+battle.getResourcesGathered();
    ResourcesGatherd=createTextArea(s,150,30);
    s ="Number of Turns : "+battle.getNumberOfTurns();
    NumberOfTurns=createTextArea(s,150,30);
 
    
   
	
    laneSelected=lanes.get(0);
     s ="";
    
    int c=0;
	for (Lane L : lanes)
	{
		s ="";
		int x=c+1;
		s += "lane : "+x+"\n"+"Wall health :   "+L.getLaneWall().getCurrentHealth()+"\n"
		 +"titans :"+L.getTitans().size()+"\n"
		 +"DangerLevel :  "+L.getDangerLevel();
		lane[c] =createTextArea(s,150,120);
		Lanes.getChildren().add(lane[c]);
		Lanes.setPrefWidth(150);
		titans[c] =createTextArea(s,150,120);
		 Titansinfo.getChildren().add(titans[c]);
		 Titansinfo.setPrefWidth(150);
    	  weapon[c] =createTextArea(s,150,120);
		   weaponsinfo.getChildren().add(weapon[c]);
		   weaponsinfo.setPrefWidth(150);
				titan[c] =new GridPane();
		titan[c].setPrefWidth(750);
		titan[c].setPrefHeight(120);
			Titans.getChildren().add(titan[c]);
			Buttons[c]= createButton("lane "+x,100,120);
			setLaneButtonAction(Buttons[c],L);
		view2.getChildren().add(Buttons[c]);
				WeaponsText[c]=createTextArea(" ",150, 120);
			WeaponsVboxes.getChildren().add(WeaponsText[c]);
			WeaponsVboxes.setPrefWidth(150);
			healthBar[c]=new ProgressBar();
			double h=((double)L.getLaneWall().getCurrentHealth()/L.getLaneWall().getBaseHealth());
			healthBar[c].setProgress(h);
			healthBar[c].setPrefSize(120, 30);
			healthBar[c].setRotate(270);
			healthBar[c].setStyle("-fx-accent: green;");
			healthBar[c].setTranslateY(40+90*c);
		      healthBars.getChildren().add(healthBar[c]);
			c++;
		
			
			
			if(c==5)
			break;
	}
	
	view=createTextArea(" ",300, 120);
	passTurn = createButton("passTurn",100,120);
	//////////// 
	passTurn.setStyle(
             "-fx-font-size: 16px; " +
           // "-fx-border-color: hsl(0, 0%, 10%); " +
             "-fx-border-width: 1px; " +
             "-fx-border-radius: 9999; " +
            "-fx-background-color: transparent; " +
             "-fx-cursor: hand; " +
             "-fx-outline: transparent; " +
             "-fx-outline-offset: 2px; "
     );
	
	////////////////
	
	
	
	
	
	
	
	passTurn.setOnAction(new EventHandler <ActionEvent>(){
		 @Override
		 public void handle(ActionEvent event) {
			// if(!battle.isGameOver()) 
			 if (timer != null) {
		            timer.cancel(); // Stop the Timer
		        }  
			 
			 try{
				 views=" ";
				 view.setText(views);
				 viewButton.getChildren().remove(view2)   ;
				 //setStats();
				 battle.passTurn();
				setStats();
				 moveTitans();
				
				
			 }
				 catch(Exception e){
					 //throw new IOException();
					setStats(); 
				 }
			
		 }
		 });
	
	
	
	
	
	     PiercingCannon = createButton("PiercingCannon",100,120);
	     SniperCannon = createButton("SniperCannon",100,120);
	     VolleySpreadCannon = createButton("VolleySpreadCannon",100,120);
	     WallTrap = createButton("WallTrap",100,120);
	    
	     AI = createButton("Ai",100,30);
	     AiRun= createButton("AiRun",100,30);
	     AI.setOnAction(new EventHandler <ActionEvent>(){
			 @Override
			 public void handle(ActionEvent event) {
				 if (timer != null) {
			            timer.cancel(); // Stop the Timer
			        }   
				 if(!battle.isGameOver()) 
				 try{
					 views=" ";
					 view.setText(views);
					 viewButton.getChildren().remove(view2)   ;
					 //setStats();
					Ai();
					 setStats();
					 moveTitans();
					
				 }
					 catch(Exception e){
						 //throw new IOException();
						setStats(); 
					 }
				//  setStats(); 
			 }
			 });
	     
	     
	   
	     
	     
	     
	     AiRun.setOnAction(new EventHandler <ActionEvent>(){
			 @Override
			 public void handle(ActionEvent event) {
				int x=0;
				 
			
				timer = new Timer();
			        timer.scheduleAtFixedRate(new TimerTask() {
			            @Override
			            public void run() {
			            	if (timer != null&&battle.isGameOver()) {
					            timer.cancel(); // Stop the Timer
					        }   
			            	
	        				 Platform.runLater(() -> views=" ");
	        				 Platform.runLater(() -> view.setText(views));
	        				 Platform.runLater(() -> viewButton.getChildren().remove(view2));
		            	
	        				 Platform.runLater(() -> Ai());
	        				 Platform.runLater(() -> setStats());
			            	Platform.runLater(() -> moveTitans());
			            	 
			            	
			            }
			        }, 0, 2000); 
					
			       
			 
			 } });
	     
	     
	    setButtonAction(PiercingCannon,1);
	    setButtonAction(SniperCannon,2);
	    setButtonAction(VolleySpreadCannon,3);
	    setButtonAction(WallTrap,4);
	    setStats();
}

public  HBox Stats(){
	HBox stats=new HBox();
	stats.getChildren().addAll(score,GamePhase,ResourcesGatherd,NumberOfTurns,animations,AI,AiRun/*,Lanee*/);/////////////////////////);
	stats.setPrefSize(1000,30);
  return stats;
	
}
public  HBox Stats2(){
	HBox stats=new HBox();
	stats.getChildren().addAll(/*PiercingCannon,SniperCannon,VolleySpreadCannon,WallTrap,*/passTurn,view,viewButton);
	view.setTranslateX(00);
	stats.setPrefSize(1500,120);
		return stats;
	
}
public  HBox Stats3(){
	//setStats();
	HBox stats=new HBox();
	stats.setPrefSize(1380,600);//***
	stats.getChildren().addAll(WeaponsVboxes,Lanes,healthBars,Titans,Titansinfo,weaponsinfo);

	healthBars.setTranslateX(-44);////***
	Titans.setTranslateX(-90);////***
	
		return stats;
	
}
public  void setStats(){
	String s ="Score:  "+battle.getScore();
	score.setText(s);
	
	s ="Game Phase:  "+battle.getBattlePhase();
	    GamePhase.setText(s);
	    
    s ="Resources Gathered :  "+battle.getResourcesGathered();
	    ResourcesGatherd.setText(s);
	    
	s ="Number of Turns :  "+battle.getNumberOfTurns();
	    NumberOfTurns.setText(s);
	    s ="";
	    int c=0;
		
		for (Lane L : lanes)
		{
			s ="";
			double H=((double)L.getLaneWall().getCurrentHealth()/L.getLaneWall().getBaseHealth());
			healthBar[c].setProgress(H);
			int x=c+1;
					titan[c].getChildren().clear();
			int PureTitan=0,AbnormalTitan=0,ColossalTitan=0,ArmoredTitan=0;
			int PiercingCannon=0,SniperCannon=0,VolleySpreadCannon=0,WallTrap=0;
			for (Titan t : L.getTitans()) {
				Circle cr = new Circle();
				Text Text =new Text();
				//cr.setRadius(15);
				String titaninfo="Titan Type :";
				String titaninfo2="";
				if(t instanceof PureTitan) {
					cr.setRadius(12);
					titaninfo+="PureTitan\n";
					cr.setFill(Color.BLUE);
				PureTitan++;
				}else if(t instanceof AbnormalTitan) {
					titaninfo+="AbnormalTitan\n";
					cr.setRadius(9);
					cr.setFill(Color.GREEN);
					cr.setTranslateY(21);
					Text.setTranslateY(21);
					AbnormalTitan++;
				}else if(t instanceof ArmoredTitan) {
					cr.setRadius(12);

					titaninfo+="ArmoredTitan\n";
					cr.setFill(Color.YELLOW);
					cr.setTranslateY(42);
					Text.setTranslateY(42);
					ArmoredTitan++;
				}else if(t instanceof ColossalTitan) {
					titaninfo+="ColossalTitan\n";
					cr.setRadius(17);

					cr.setFill(Color.RED);
					cr.setTranslateY(70);
					Text.setTranslateY(70);
					ColossalTitan++;
				}
				//titan[c].getChildren().add( Text);	
				Text.setTranslateX(t.getDistance()*10);
				titan[c].getChildren().add( cr);	
				cr.setTranslateX(t.getDistance()*10);
				double h=((double)t.getCurrentHealth()/t.getBaseHealth());
				cr.setOpacity(0.5*h+0.1);
				Text.setText(""+t.getCurrentHealth());
				
				titaninfo+="Tian Current Health : "+t.getCurrentHealth()
				+"\nTian Damage : "+t.getDamage()
				+"\nTian Speed : "+t.getSpeed()+"                Distance : "+t.getDistance()
				+"\nTian Resources Value : "+t.getResourcesValue()
				+"\nTian Danger Level : "+t.getDangerLevel();
				//final String titaninfoF=titaninfo;
				final String titaninfoF2=titaninfo+titaninfo2;
				
				
				cr.setOnMouseEntered(new EventHandler<MouseEvent>(){
					
					@Override
					public void handle(MouseEvent event) {
						//titan[x-1].getChildren().add( Text);
						if(!battle.isGameOver())	
						view.setText(titaninfoF2);
					}
					});
                  cr.setOnMouseExited(new EventHandler<MouseEvent>(){
					
					@Override
					public void handle(MouseEvent event) {
						//titan[x-1].getChildren().remove(Text);
						if(!battle.isGameOver())	
						view.setText(views);
					}
					});
						
                  cr.setOnMouseClicked(new EventHandler<MouseEvent>(){
  					
  					@Override
  					public void handle(MouseEvent event) {
                      	if(!battle.isGameOver()) 
                      try{
//              			 setStats();
              			views=titaninfoF2;
 						view.setText(views);
//              			 update();	
//              				 setStats(); 
              			}
              		
              	catch(Exception E){
//              		  update();	
//              			 setStats();  //throw new IOException();
              	}
              		
              		  
              		
              	 
                      	}
                  });
                  
                  
			}
			String Weaponsinfo="Lane "+x+" weapons :";
			for (Weapon w : L.getWeapons()) {//##
			

				if(w instanceof PiercingCannon) {
					Weaponsinfo+="\nPiercingCannon";
					PiercingCannon++;
				}else if(w instanceof VolleySpreadCannon) {
					Weaponsinfo+="\nVolleySpreadCannon";
					VolleySpreadCannon++;
				}else if(w instanceof SniperCannon) {
					Weaponsinfo+="\nSniperCannon";
					SniperCannon++;
				}else if(w instanceof WallTrap) {
					Weaponsinfo+="\nWallTrap";
					WallTrap++;
				}
				
			}
			WeaponsText[c].setText(Weaponsinfo);
			final int l=c;
			WeaponsText[c].setOnMouseEntered((MouseEvent event) -> {
				//System.out.println("Entered");
				//Lanee.setSelected(true);
				laneSelected=lanes.get(l);
				
				
				if(!battle.isGameOver()&&Lanee.isSelected())		
					 try{
						 views="";

						 battle.purchaseWeapon(WeaponCode, laneSelected);
						 ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

						 setStats(); 
						 moveTitans();
						 view.setText(views);
		        		 viewButton.getChildren().remove(view2)   ;
		        			Lanee.setSelected(false);
						 
						
						}
					 catch(InsufficientResourcesException r){
						
						 views="Insufficient Resources";
						 
						 view.setText(views);
		        		 viewButton.getChildren().remove(view2)   ;
		        			Lanee.setSelected(false);
						 setStats(); 	
					 }
				catch(InvalidLaneException la){
					views="Invalid Lane";
					view.setText(views);
	        		 viewButton.getChildren().remove(view2)   ;
	        			Lanee.setSelected(false);
					
					setStats(); 
				}
				catch(Exception E){
 //throw new IOException();
				}
				        
			        			
			        	
				
				
				
				
    });
			
		
		
			
			
			s += "lane : "+x+"\n"+"Wall health :   "+L.getLaneWall().getCurrentHealth()+"\n"
			 +"\nDangerLevel :  "+L.getDangerLevel();
			lane[c].setText(s);
			if(L.isLaneLost()) {
				lane[c].setStyle("-fx-background-color: red;");    //setText(l).    setTextFill(Color.RED)
				weapon[c].setStyle("-fx-background-color: red;"); 
				//titan[c].setStyle("-fx-background-color: red;"); 
				titans[c].setStyle("-fx-background-color: red;"); 
				s +="\nLane is lost !  ";
			}
			lane[c].setText(s);
			 s="";
			s +="PureTitan :  "+PureTitan+"\n"
			 +"AbnormalTitan :  "+AbnormalTitan+"\n"
			 +"ArmoredTitan :  "+ArmoredTitan+"\n"
			 +"ColossalTitan :  "+ColossalTitan+"\n"
			 +"titans :"+L.getTitans().size()+"\n";
			titans[c].setText(s);
			 s="";
			s +="PiercingCannon :  "+PiercingCannon+"\n"
			 + "SniperCannon :  "+ SniperCannon +"\n"
			 +"VolleySpreadCannon :  "+VolleySpreadCannon+"\n"
			 +"WallTrap :  "+WallTrap+"\n"
			 +"weapons : "+L.getWeapons().size()+"\n";
			weapon[c].setText(s);
			
			
			if(L.isLaneLost())
				Buttons[c].setTextFill(Color.RED);
		    else
		    	Buttons[c].setTextFill(Color.GREEN);
				c++;
				if(c==5)
					break;
		}  
		
		
		
		
		update();
		if (timer != null&&battle.isGameOver()) {
            timer.cancel(); // Stop the Timer
        }   		
if(battle.isGameOver()) {
		 viewButton.getChildren().remove(view2)   ;
	gameover("the game is over");}
}
	
/////////////////////

public  void moveTitans(){
	if(animations.isSelected()) {
	    int c=0;
	   
		for (Lane L : lanes)
		{
			
			
					titan[c].getChildren().clear();
					for (Titan t : L.getTitans()) {
				Circle cr = new Circle();
				Text Text =new Text();
				//cr.setRadius(15);
				String titaninfo="Titan Type :";
				String titaninfo2="";
				if(t instanceof PureTitan) {
					cr.setRadius(12);
					titaninfo+="PureTitan\n";
					cr.setFill(Color.BLUE);
					cr.setTranslateX((t.getDistance()+t.getSpeed())*10);
				}else if(t instanceof AbnormalTitan) {
					titaninfo+="AbnormalTitan\n";
					cr.setRadius(9);
					cr.setFill(Color.GREEN);
					cr.setTranslateY(21);
					Text.setTranslateY(21);
					cr.setTranslateX((t.getDistance()+t.getSpeed())*10);
				}else if(t instanceof ArmoredTitan) {
					cr.setRadius(12);
					
					titaninfo+="ArmoredTitan\n";
					cr.setFill(Color.YELLOW);
					cr.setTranslateY(42);
					Text.setTranslateY(42);
					cr.setTranslateX((t.getDistance()+t.getSpeed())*10);
				}else if(t instanceof ColossalTitan) {
					titaninfo+="ColossalTitan\n";
					cr.setRadius(17);

					cr.setFill(Color.RED);
					cr.setTranslateY(70);
					Text.setTranslateY(70);
					if(t.getSpeed()!=5)
					cr.setTranslateX((t.getDistance()+t.getSpeed()-1)*10);
					else cr.setTranslateX((t.getDistance()+t.getSpeed())*10);
				}
				
				
				
				//titan[c].getChildren().add( Text);	
				Text.setTranslateX(t.getDistance()*10);
				titan[c].getChildren().add( cr);	
				//cr.setTranslateX((t.getDistance()+t.getSpeed())*10);
				if(!t.hasReachedTarget()/*&&t.getDistance()!=battle.getTitanSpawnDistance()*/&&!L.isLaneLost()) {
				TranslateTransition translate = new TranslateTransition();
				//int m=Math.min(t.getDistance(), t.getSpeed());
				if(!(t instanceof ColossalTitan)||t.getDistance()==battle.getTitanSpawnDistance()/*||t.getDistance()==battle.getTitanSpawnDistance()-5*/)
				translate.setByX(-t.getSpeed()*10);
				else 
				translate.setByX(-(t.getSpeed()-1)*10);
				translate.setDuration(Duration.millis(1000));
				
				translate.setNode(cr);
				translate.play();
				
				}
				//titan[c].getChildren().remove(cr);
				//titan[c].getChildren().add( cr);
				cr.setTranslateX(t.getDistance()*10);
				double h=((double)t.getCurrentHealth()/t.getBaseHealth());
				cr.setOpacity(0.5*h+0.1);
				Text.setText(""+t.getCurrentHealth());
				
				titaninfo+="Tian Current Health : "+t.getCurrentHealth()
				+"\nTian Damage : "+t.getDamage()
				+"\nTian Speed : "+t.getSpeed()+"                Distance : "+t.getDistance()
				+"\nTian Resources Value : "+t.getResourcesValue()
				+"\nTian Danger Level : "+t.getDangerLevel();
				//final String titaninfoF=titaninfo;
				final String titaninfoF2=titaninfo+titaninfo2;
				
				
				cr.setOnMouseEntered(new EventHandler<MouseEvent>(){
					
					@Override
					public void handle(MouseEvent event) {
						//titan[x-1].getChildren().add( Text);
						if(!battle.isGameOver())	
						view.setText(titaninfoF2);
					}
					});
                  cr.setOnMouseExited(new EventHandler<MouseEvent>(){
					
					@Override
					public void handle(MouseEvent event) {
						//titan[x-1].getChildren().remove(Text);
						if(!battle.isGameOver())	
						view.setText(views);
					}
					});
						
                  cr.setOnMouseClicked(new EventHandler<MouseEvent>(){
  					
  					@Override
  					public void handle(MouseEvent event) {
                      	if(!battle.isGameOver()) 
                      try{
//              			 setStats();
              			views=titaninfoF2;
 						view.setText(views);
//              			 update();	
//              				 setStats(); 
              			}
              		
              	catch(Exception E){
//              		  update();	
//              			 setStats();  //throw new IOException();
              	}
              		
              		  
              		
              	 
                      	}
                  });
                  
                  
			}
		
			
			
			
			
				c++;
				if(c==5)
					break;
		}  
//		 try {
//             TimeUnit.SECONDS.sleep(2); // Sleep for 2 seconds
//         } catch (InterruptedException e) {
//             e.printStackTrace();
//         }
//		
		//Ai();
		
	}
}	


/////
public  void Ai(){
	if(!battle.isGameOver()) {
		
		if(battle.getResourcesGathered()<25) {
		   battle.passTurn();
		   return;
		}
	
		ArrayList<Lane>Lanes=new ArrayList<Lane>();
		
		
	for (Lane L :  battle.getLanes()) 
		if(!L.isLaneLost()) 
			Lanes.add(L);	
			
	Lane lane= Lanes.get(Lanes.size()-1);		
		
		
		
		
	
		
	
	
	
	int[] weaponcode = {1,2,3,4};	
	int[] weaponDamage = {0,0,0,0};	
	ArrayList<Titan> tmp = new ArrayList<>();	
	for (int i = 0; i < 5 && !lane.getTitans().isEmpty(); i++)
	{
		Titan nextTitan = lane.getTitans().poll();
		weaponDamage[0] +=(Math.min(nextTitan.getCurrentHealth(),battle.getWeaponFactory().getWeaponShop().get(1).getDamage()) -  battle.getWeaponFactory().getWeaponShop().get(1).getPrice()) * (75-nextTitan.getDistance()+nextTitan.getSpeed())  ;

		
			tmp.add(nextTitan);
		
	}	
	lane.getTitans().addAll(tmp);	
	tmp.clear();	
		
	
	Titan closestTitan = lane.getTitans().peek();
	

	if (closestTitan != null)
	{
		weaponDamage[1] +=(Math.min(closestTitan.getCurrentHealth(),battle.getWeaponFactory().getWeaponShop().get(2).getDamage()) -  battle.getWeaponFactory().getWeaponShop().get(2).getPrice())*(75-closestTitan.getDistance()+closestTitan.getSpeed())  ;
		if ( closestTitan.hasReachedTarget()&&battle.getResourcesGathered()>=75)
			weaponDamage[3] +=(Math.min(closestTitan.getCurrentHealth(),battle.getWeaponFactory().getWeaponShop().get(4).getDamage())     -  battle.getWeaponFactory().getWeaponShop().get(4).getPrice() )*(75-closestTitan.getDistance()+closestTitan.getSpeed()) ;
	
	}	
		
		
		
		
		
	tmp = new ArrayList<>();
	if ( battle.getResourcesGathered()>=100) {
	
	WeaponRegistry weapon =battle.getWeaponFactory().getWeaponShop().get(3);
	
	while (!lane.getTitans().isEmpty() && lane.getTitans().peek().getDistance() <= weapon.getMaxRange())
	{
		Titan nextTitan = lane.getTitans().poll();
		if (nextTitan.getDistance() >= weapon.getMinRange())
		{
			weaponDamage[2] +=(Math.min(closestTitan.getCurrentHealth(),weapon.getDamage()) - weapon.getPrice())*(75-closestTitan.getDistance()+closestTitan.getSpeed())    ;
		}

		if (!nextTitan.isDefeated())
		{
			tmp.add(nextTitan);
		}
	}

	lane.getTitans().addAll(tmp);	
	}
	
	int index =0;
	int max = weaponDamage[index]	;
		
	for(int i=0 ;i<4;i++) {
		
		if(weaponDamage[i]>max) {
			 max = weaponDamage[i];
			 index=i;
		}}
	int c=1;
	
	for (Lane L :  battle.getLanes()) {
		
		
		// int[] weaponcode2 = {1,2,3,4};	
		int[] weaponDamage2 = {0,0,0,0};	
		 tmp = new ArrayList<>();	
		 Titan Titan = L.getTitans().peek(); 
		 if(Titan!=null&&Titan.getDistance()-Titan.getSpeed()<=0) {
			 System.out.println(Titan);
		
			 for(int i = 0; i < 4; i++)
			 weaponDamage2[i]=1000;
		 }
		for (int i = 0; i < 5 && !lane.getTitans().isEmpty(); i++)
		{
			Titan	 nextTitan = lane.getTitans().poll();
			weaponDamage2[0] +=(Math.min(nextTitan.getCurrentHealth(),battle.getWeaponFactory().getWeaponShop().get(1).getDamage()) -  battle.getWeaponFactory().getWeaponShop().get(1).getPrice() )/*(75-closestTitan.getDistance()+closestTitan.getSpeed()) */  ;

			
				tmp.add(nextTitan);
			
		}	
		lane.getTitans().addAll(tmp);	
		tmp.clear();	
			
		
		 closestTitan = lane.getTitans().peek();
	

		if (closestTitan != null)
		{
			weaponDamage2[1] +=(Math.min(closestTitan.getCurrentHealth(),battle.getWeaponFactory().getWeaponShop().get(2).getDamage()) -  battle.getWeaponFactory().getWeaponShop().get(2).getPrice())*(75-closestTitan.getDistance()+closestTitan.getSpeed()) ;
			if ( closestTitan.hasReachedTarget()&&battle.getResourcesGathered()>=75)
				weaponDamage2[3] +=(Math.min(closestTitan.getCurrentHealth(),battle.getWeaponFactory().getWeaponShop().get(4).getDamage())     -  battle.getWeaponFactory().getWeaponShop().get(4).getPrice() )*(75-closestTitan.getDistance()+closestTitan.getSpeed());
		
		}	
			
			
			
			
			
		tmp = new ArrayList<>();
		if ( battle.getResourcesGathered()>=100) {
		
		WeaponRegistry weapon =battle.getWeaponFactory().getWeaponShop().get(3);
		
		while (!lane.getTitans().isEmpty() && lane.getTitans().peek().getDistance() <= weapon.getMaxRange())
		{
			Titan nextTitan = lane.getTitans().poll();
			if (nextTitan.getDistance() >= weapon.getMinRange())
			{
				weaponDamage2[2] +=(Math.min(closestTitan.getCurrentHealth(),weapon.getDamage()) - weapon.getPrice())*(75-closestTitan.getDistance()+closestTitan.getSpeed())  ;
			}

			if (!nextTitan.isDefeated())
			{
				tmp.add(nextTitan);
			}
		}

		lane.getTitans().addAll(tmp);	
		}
		
		int index2 =0;
		int max2 = weaponDamage2[index2]	;
			
		for(int i=0 ;i<4;i++) {
			
			//System.out.println(weaponDamage2[i]+"   "+index2+",   "+battle.getNumberOfTurns()+"      l :"+c);
			if(weaponDamage2[i]>max2) {
				max2 = weaponDamage2[i];
				 index2=i;
			}
			
		
		
		}
		
		//System.out.println(max2+"   "+index2+",   "+battle.getNumberOfTurns()+" max2     l :"+c);
		
		if(max2>max) {
			max=max2;
			index=	index2;
		
			lane=L;
		
		}
//		if(battle.getNumberOfTurns()<10   &&max2==max&&L.getDangerLevel()>lane.getDangerLevel()) {
//			max=max2;
//			index=	index2;
//		
//			lane=L;
//		}
	
	
		//System.out.println(max+"   "+index2+",   "+battle.getNumberOfTurns()+" max     l :"+c);
		c++;
		
	}
	
	
	 
	
	if(battle.getNumberOfTurns()>0&&battle.getResourcesGathered()>=75&&( max<=5||max>=1000)/*||battle.getResourcesGathered()>=75&& battle.getNumberOfTurns()>10&& max<=20*/)
		index=3;
	if(battle.getResourcesGathered()<75&&index==3)
		index=0;
	if(battle.getResourcesGathered()<100&&index==2)
		index=1;
//
	//System.out.println(max+"   "+index+",   "+battle.getNumberOfTurns());
	//System.out.println("////////////////////////////////////////////////////");
		try {
			battle.purchaseWeapon(weaponcode[index], lane);
		} catch (InsufficientResourcesException e) {
			
			//System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n**********************\n\n\n\n\n\n");
		} catch (InvalidLaneException e) {
			// TODO Auto-generated catch block
			//System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n**********************\n\n\n\n\n\n");
		//	e.printStackTrace();
		}
		
		
	}
	
	
	

		
    setStats();
		
}








////////////////////

private  void gameover( String message) {
	
	
	String s ="Score:  "+battle.getScore();
	score.setText(s);
	
	s ="Game Phase:  "+battle.getBattlePhase();
	    GamePhase.setText(s);
	    
    s ="Resources Gathered :  "+battle.getResourcesGathered();
	    ResourcesGatherd.setText(s);
	    
	s ="Number of Turns :  "+battle.getNumberOfTurns();
	    NumberOfTurns.setText(s);
	    s ="";
	
	views=message+"\nScore :\n"+battle.getScore();
		view.setText(views);
	 Button closeButton = createButton("new game",100,120);
   
    closeButton.setOnAction(new EventHandler <ActionEvent>(){
		 @Override
		 public void handle(ActionEvent event) {GameGUI.g.play();}});
		 

    viewButton.getChildren().add(closeButton);


}

public  void update() {
	if(battle.getWeaponFactory().getWeaponShop().get(1).getPrice()>battle.getResourcesGathered())
		PiercingCannon.setTextFill(Color.RED);
    else
    	PiercingCannon.setTextFill(Color.GREEN);
    
    if(battle.getWeaponFactory().getWeaponShop().get(2).getPrice()>battle.getResourcesGathered())
    	SniperCannon.setTextFill(Color.RED);
    else
    	SniperCannon.setTextFill(Color.GREEN);
    
    if(battle.getWeaponFactory().getWeaponShop().get(3).getPrice()>battle.getResourcesGathered())
    	VolleySpreadCannon.setTextFill(Color.RED);
    else
    	VolleySpreadCannon.setTextFill(Color.GREEN);
    
    if(battle.getWeaponFactory().getWeaponShop().get(4).getPrice()>battle.getResourcesGathered())
    	WallTrap.setTextFill(Color.RED);
    else
    	WallTrap.setTextFill(Color.GREEN);

}
private void setButtonAction(Button button, int code) {
    
    final String s;
    String v="";
    WeaponRegistry w =battle.getWeaponFactory().getWeaponShop().get(code);
    
    if(code==1 ) {
    	v+="Weapon Type : PiercingCannon"+"\nWeapon Name : Anti-Titan Shell \n";
		v+="Damage : "+w.getDamage() + "\nPrice : "+w.getPrice()+"\n";
    	
    s=v;
    
    } else
    	if(code==2 ) {
    		v+="Weapon Type : SniperCannon"+"\nWeapon Name : Long Range \n";
    		v+="Damage : "+w.getDamage() + "\nPrice : "+w.getPrice()+"\n";
    		
        s=v;
    	
    	}else
        	if(code==3 ){
        		v+="Weapon Type : VolleySpreadCannon"+"\nWeapon Name : Wall Spread Cannon \n";
        		v+="Damage : "+w.getDamage() + "\nPrice : "+w.getPrice()+"\n";
        		v+="Min Range : "+w.getMinRange() + "\nMax Range : "+w.getMaxRange();
            s=v;
        	
        	}
        		
            else
            	if(code==4 ){
            		v+="Weapon Type : WallTrap"+"\nWeapon Name : Proximity Trap \n";
            		v+="Damage : "+w.getDamage() + "\nPrice : "+w.getPrice()+"\n";
            		
                s=v;
            	
            	}
            		
                else
    s="";
    button.setOnMouseEntered(new EventHandler<MouseEvent>(){
		
		@Override
		public void handle(MouseEvent event) {
			if(!battle.isGameOver())	
			view.setText(s);
			
		}
		});
    button.setOnMouseExited(new EventHandler<MouseEvent>(){
		
		@Override
		public void handle(MouseEvent event) {
			if(!battle.isGameOver())	
			view.setText(views);
			}
		});
    
    
    button.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
			
        	if (timer != null) {
	            timer.cancel(); // Stop the Timer
	        } 
        	
        	if(!battle.isGameOver())	

        	try{
          		WeaponCode=code;
        		views=s;

			 viewButton.getChildren().add(view2)  ;
        	}
	catch(Exception E){}
}
    });
    
    
    button.setOnMouseReleased((MouseEvent event) -> {
    	System.out.println("lllll");
    	if(!battle.isGameOver()&&laneSelected!=null&&Lanee.isSelected())		
   		 try{
   			 views="";

//   			 setStats();
   			 battle.purchaseWeapon(WeaponCode, laneSelected);
   			// viewButton =view2;
   			 viewButton.getChildren().remove(view2)   ;
   			 setStats();
   			 moveTitans();
   			}
   		 catch(InsufficientResourcesException r){
   			
   			 views="Insufficient Resources";
   				view.setText(views);
   			
   			 viewButton.getChildren().remove(view2)   ;

   				 setStats(); 
   		 }
   	catch(InvalidLaneException l){
   		views="Invalid Lane";
   		view.setText(views);
   		 viewButton.getChildren().remove(view2)   ;

   			 setStats();
   	}
   	catch(Exception E){

   			 setStats();  //throw new IOException();
   	}
           	else	
           		 viewButton.getChildren().remove(view2)   ;
   		
   	 
           	
       });
    
    }
private void setLaneButtonAction(Button button,Lane l) {
	
	if(l.isLaneLost())
		button.setTextFill(Color.RED);
    else
    	button.setTextFill(Color.GREEN);
	
	
	
    final String s;
    String v="";
    int x=lanes.indexOf(l)+1;
    v += "lane : "+x+"\n"+"health :   "+l.getLaneWall().getCurrentHealth()+"";
	v +="\nDangerLevel :  "+l.getDangerLevel();
    s=v;
   button.setOnMouseEntered(new EventHandler<MouseEvent>(){
		
		@Override
		public void handle(MouseEvent event) {
			if(!battle.isGameOver())	
			view.setText(s);
			//view2.setText(s2);
		}
		});
    button.setOnMouseExited(new EventHandler<MouseEvent>(){
		
		@Override
		public void handle(MouseEvent event) {
			if(!battle.isGameOver())	
			view.setText(views);
			//view2.setText(view2s);
		}
		});
    
    
    button.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
        	//if(!battle.isGameOver()) 
        	if(!battle.isGameOver())		
		 try{
			 views="";

//			 setStats();
			 battle.purchaseWeapon(WeaponCode, l);
			// viewButton =view2;
			 viewButton.getChildren().remove(view2)   ;
			 setStats();
			 moveTitans();
			}
		 catch(InsufficientResourcesException r){
			
			 views="Insufficient Resources";
				view.setText(views);
			
			 viewButton.getChildren().remove(view2)   ;

				 setStats(); 
		 }
	catch(InvalidLaneException l){
		views="Invalid Lane";
		view.setText(views);
		 viewButton.getChildren().remove(view2)   ;

			 setStats();
	}
	catch(Exception E){

			 setStats();  //throw new IOException();
	}
        	else	
        		 viewButton.getChildren().remove(view2)   ;
		
	 
        	}
    });
}
private static Button createButton(String text,int x,int y) {
    Button button = new Button(text);
    button.setPrefSize(x, y);
    return button;
}
private static TextArea createTextArea(String text,int x,int y) {
	TextArea TextArea=new TextArea(text);
   	TextArea.setEditable(false);
	TextArea.setPrefWidth(300);
	TextArea.setPrefHeight(120);
      return TextArea;
}

public Button getPiercingCannon() {
	return PiercingCannon;
}

public static void setPiercingCannon(Button piercingCannon) {
	PiercingCannon = piercingCannon;
}

public static Button getSniperCannon() {
	return SniperCannon;
}

public static void setSniperCannon(Button sniperCannon) {
	SniperCannon = sniperCannon;
}

public static Button getVolleySpreadCannon() {
	return VolleySpreadCannon;
}

public static void setVolleySpreadCannon(Button volleySpreadCannon) {
	VolleySpreadCannon = volleySpreadCannon;
}

public static Button getWallTrap() {
	return WallTrap;
}

public static void setWallTrap(Button wallTrap) {
	WallTrap = wallTrap;
}


}

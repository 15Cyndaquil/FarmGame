import TileSets.Farm.FarmTile;
import TileSets.Player.MoveThread;
import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;


/**
 * Created by Cyndaquil on 7/10/2017.
 * Driver Class for my basic farm game
 */
public class Driver extends Application {
    private File dir = new File("src/TileSets/Player");
    private File playerFile = new File(dir, "PlayerTest.png");
    private ImageView playerImage = new ImageView(playerFile.toURI().toString());

    private BooleanProperty upPressed = new SimpleBooleanProperty();
    private BooleanProperty downPressed = new SimpleBooleanProperty();
    private BooleanProperty leftPressed = new SimpleBooleanProperty();
    private BooleanProperty rightPressed = new SimpleBooleanProperty();

    private MoveThread moveThread;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane pane = new Pane();
        pane.setManaged(false);
        Scene scene = new Scene(pane, 1280, 720);


        GridPane farm = new GridPane();
        farm.setGridLinesVisible(true);
        for(int x=0; x<25; x++){
            for(int y=0; y<25; y++){
                farm.add(new FarmTile().getImageView(), x, y);
            }
        }


        pane.getChildren().addAll(farm, playerImage);
        playerImage.setManaged(false);
        playerImage.relocate(643, 363);

        scene.setOnKeyPressed(e->{
            if(e.getCode()==KeyCode.UP){
                if(!upPressed.get()) {
                    downPressed.set(false);
                    leftPressed.set(false);
                    rightPressed.set(false);

                    upPressed.set(true);
                    moveThread = new MoveThread(upPressed, KeyCode.UP);
                    moveThread.setPlayerY(playerImage.layoutYProperty());
                    moveThread.setPaneY(pane.layoutYProperty());
                    moveThread.start();
                }
            }
            if(e.getCode()==KeyCode.DOWN){
                if(!downPressed.get()) {
                    upPressed.set(false);
                    leftPressed.set(false);
                    rightPressed.set(false);

                    downPressed.set(true);
                    moveThread = new MoveThread(downPressed, KeyCode.DOWN);
                    moveThread.setPlayerY(playerImage.layoutYProperty());
                    moveThread.setPaneY(pane.layoutYProperty());
                    moveThread.start();
                }
            }
            if(e.getCode()==KeyCode.LEFT){
                if(!leftPressed.get()) {
                    upPressed.set(false);
                    downPressed.set(false);
                    rightPressed.set(false);

                    leftPressed.set(true);
                    moveThread = new MoveThread(leftPressed, KeyCode.LEFT);
                    moveThread.setPlayerX(playerImage.layoutXProperty());
                    moveThread.setPaneX(pane.layoutXProperty());
                    moveThread.start();
                }
            }
            if(e.getCode()==KeyCode.RIGHT){
                if(!rightPressed.get()) {
                    upPressed.set(false);
                    downPressed.set(false);
                    leftPressed.set(false);

                    rightPressed.set(true);
                    moveThread = new MoveThread(rightPressed, KeyCode.RIGHT);
                    moveThread.setPlayerX(playerImage.layoutXProperty());
                    moveThread.setPaneX(pane.layoutXProperty());
                    moveThread.start();
                }
            }
            if(e.getCode().toString().equals("E")){
                setAction(farm);
            }
        });
        scene.setOnKeyReleased(e-> {
            if (e.getCode() == KeyCode.UP) {upPressed.set(false);}
            if (e.getCode() == KeyCode.DOWN) {downPressed.set(false);}
            if (e.getCode() == KeyCode.LEFT) {leftPressed.set(false);}
            if (e.getCode() == KeyCode.RIGHT) {rightPressed.set(false);}
        });


        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void setAction(GridPane farm){
        double playerX = playerImage.getLayoutX();
        double playerY = playerImage.getLayoutY();
        playerX = ((int) (playerX/16))*16d-8;
        playerY = ((int) (playerY/16))*16d-32;
        for(int i=0; i<farm.getChildren().size(); i++){
            double farmX = farm.getChildren().get(i).getLayoutX();
            double farmY = farm.getChildren().get(i).getLayoutY();
            if((playerX>=farmX-16&&playerX<=farmX)&&(farmY>=playerY-16&&farmY<=playerY)){
                farm.getChildren().get(i).idProperty().setValue("Till");
            }
        }
    }
}

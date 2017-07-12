import TileSets.Farm.FarmTile;
import javafx.application.Application;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

import java.io.File;
import java.util.EventListener;


/**
 * Created by Cyndaquil on 7/10/2017.
 */
public class Driver extends Application {


    Scene scene;
    SimpleBooleanProperty buttonPropert;
    File dir = new File("src/TileSets/Player");
    File playerFile = new File(dir, "PlayerTest.png");
    ImageView playerImage = new ImageView(playerFile.toURI().toString());
    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane pane = new Pane();
        pane.setManaged(false);
        scene = new Scene(pane, 1280, 720);


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
            if(e.getCode().toString().equals("UP")){
                double X = playerImage.getLayoutX();
                double Y = playerImage.getLayoutY();
                playerImage.relocate(X, Y-8);
                pane.relocate(pane.getLayoutX(), pane.getLayoutY()+8);
            }
            if(e.getCode().toString().equals("DOWN")){
                double X = playerImage.getLayoutX();
                double Y = playerImage.getLayoutY();
                playerImage.relocate(X, Y+8);
                pane.relocate(pane.getLayoutX(), pane.getLayoutY()-8);
            }
            if(e.getCode().toString().equals("LEFT")){
                double X = playerImage.getLayoutX();
                double Y = playerImage.getLayoutY();
                playerImage.relocate(X-8, Y);
                pane.relocate(pane.getLayoutX()+8, pane.getLayoutY());
            }
            if(e.getCode().toString().equals("RIGHT")){
                double X = playerImage.getLayoutX();
                double Y = playerImage.getLayoutY();
                playerImage.relocate(X+8, Y);
                pane.relocate(pane.getLayoutX()-8, pane.getLayoutY());
            }
            if(e.getCode().toString().equals("E")){
                setAction(farm);
            }
        });
        primaryStage.setScene(scene);
        primaryStage.show();


        System.out.println(farm.getChildren().get(0));

    }

    public Scene getScene(){return scene;}

    public void setAction(GridPane farm){
        double playerX = playerImage.getLayoutX();
        double playerY = playerImage.getLayoutY();
        playerX = ((int) (playerX/16))*16d;
        playerY = ((int) (playerY/16))*16d-32;
        for(int i=0; i<farm.getChildren().size(); i++){
            double farmX = farm.getChildren().get(i).getLayoutX();
            double farmY = farm.getChildren().get(i).getLayoutY();
            if((farmX==playerX)&&(farmY>=playerY-16&&farmY<=playerY)){
                farm.getChildren().get(i).idProperty().setValue("Till");
            }
        }
    }
}

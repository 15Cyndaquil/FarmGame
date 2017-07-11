import TileSets.Farm.FarmTile;
import javafx.application.Application;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

import java.util.EventListener;


/**
 * Created by Cyndaquil on 7/10/2017.
 */
public class Driver extends Application {


    Scene scene;
    SimpleBooleanProperty buttonPropert;
    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane pane = new Pane();
        pane.setManaged(false);
        scene = new Scene(pane, 1280, 720);


        GridPane farm = new GridPane();
        farm.setGridLinesVisible(true);
        farm.setVgap(1);
        farm.setHgap(1);
        for(int x=0; x<25; x++){
            for(int y=0; y<25; y++){
                farm.add(new FarmTile().getImageView(), x, y);
            }
        }

        pane.getChildren().add(farm);



        scene.setOnKeyPressed(e->{
            if(e.getCode().toString().equals("UP")){
                pane.relocate(pane.getLayoutX(), pane.getLayoutY()+10);
            }else if(e.getCode().toString().equals("DOWN")){
                pane.relocate(pane.getLayoutX(), pane.getLayoutY()-10);
            }else if(e.getCode().toString().equals("LEFT")){
                pane.relocate(pane.getLayoutX()+10, pane.getLayoutY());
            }else if(e.getCode().toString().equals("RIGHT")){
                pane.relocate(pane.getLayoutX()-10, pane.getLayoutY());
            }
        });
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public Scene getScene(){return scene;}
}

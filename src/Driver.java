import TileSets.Farm.FarmTile;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
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

    private void setAction(GridPane farm){
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

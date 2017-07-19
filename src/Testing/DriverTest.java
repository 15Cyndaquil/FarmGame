package Testing;

import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.File;

/**
 * Created by Cyndaquil on 7/18/2017.
 * Testing change of Player Sprite
 */


public class DriverTest extends Application{
    private IntegerProperty upP1 = new SimpleIntegerProperty(0);
    private IntegerProperty upP2 = new SimpleIntegerProperty(0);

    private File dir = new File("src/TileSets/Player");
    private File up0 = new File(dir, "PlayerF0.png");
    private File up1 = new File(dir, "PlayerF1.png");
    private File up2 = new File(dir, "PlayerF2.png");
    private File up3 = new File(dir, "PlayerF3.png");

    private ImageView upImage1 = new ImageView(new Image(up0.toURI().toString()));
    private ImageView upImage2 = new ImageView(new Image(up0.toURI().toString()));

    @Override
    public void start(Stage primaryStage) throws Exception {
        HBox pane = new HBox();
        pane.getChildren().addAll(upImage1,upImage2);
        System.out.println(dir.exists());



        upP1.addListener(e-> listen(upP1, upImage1));
        MoveUp moveUP1 = new MoveUp(upP1, 150);
        upP2.addListener(e-> listen(upP2, upImage2));
        MoveUp moveUP2 = new MoveUp(upP2, 200);


        primaryStage.setScene(new Scene(pane));
        primaryStage.setWidth(250);
        primaryStage.show();
        moveUP1.start();
        moveUP2.start();

        primaryStage.setOnCloseRequest(e->{
            moveUP1.setGo();
            moveUP2.setGo();
        });
    }

    private void listen(IntegerProperty up, ImageView upImage){
        switch (up.get()){
            case 0:
                upImage.setImage(new Image(up0.toURI().toString()));
                break;
            case 1:
                upImage.setImage(new Image(up1.toURI().toString()));
                break;
            case 2:
                upImage.setImage(new Image(up2.toURI().toString()));
                break;
            case 3:
                upImage.setImage(new Image(up3.toURI().toString()));
                break;
        }
    }
}

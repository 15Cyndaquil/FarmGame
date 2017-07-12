package TileSets;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Created by Cyndaquil on 7/10/2017.
 */
public class Tile {
    protected ImageView imageView;

    public Tile(){
        imageView = new ImageView();
        imageView.setFitHeight(32);
        imageView.setFitWidth(32);
    }

    public ImageView getImageView() {return imageView;}
    public double getX(){return imageView.getX();}
    public double getY(){return imageView.getY();}
}

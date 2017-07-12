package TileSets.Farm;

import Exceptions.TypeNotValidException;
import TileSets.Tile;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

/**
 * Created by Cyndaquil on 7/10/2017.
 */
public class FarmTile extends Tile {
    private  File[] imageFiles = new File[5];
    private  Image[] images = new Image[5];
    private  File dir = new File("src/TileSets/Farm/Images/Plantable");
    private  int type;

    public FarmTile(){
        super();
        type = 0;
        setImageFiles();
        setImages();

        imageView.setImage(images[0]);
        setActions();
    }
    public FarmTile(int type){
        super();
        if(type<0||type> imageFiles.length-1){
            this.type = type;
            setImageFiles();
            setImages();

            imageView.setImage(new Image(imageFiles[type].toURI().toString()));
            setActions();
        }else {
            System.out.println("Type not found defaulting to 0");

            type = 0;
            setImageFiles();
            setImages();


            imageView.setImage(images[0]);
            setActions();
        }
    }

    private void setImageFiles(){
        imageFiles[0] = new File(dir, "FarmMain.png");
        imageFiles[1] = new File(dir, "FarmPlant.png");
        imageFiles[2] = new File(dir, "FarmPlantWater.png");
        imageFiles[3] = new File(dir, "FarmTill.png");
        imageFiles[4] = new File(dir, "FarmTillWater.png");
    }
    private void setImages(){
        for(int i=0; i<images.length; i++){
            images[i] = new Image(imageFiles[i].toURI().toString());
        }
    }
    private void setActions(){
        imageView.idProperty().addListener(e->{
            switch (imageView.idProperty().get()){
                case "Till":
                    setImage(3);
                    break;
            }
//            switch (type){
//                case 0:
//                    setImage(3);
//                    break;
//                case 1:
//                    setImage(2);
//                    break;
//                case 3:
//                    if((int)(Math.random()*2)==0){
//                       setImage(1);
//                    }else {
//                        setImage(4);
//                    }
//                    break;
//                case 4:
//                    setImage(2);
//                    break;
//            }
        });
    }

    public void setImage(int type){
        imageView.setImage(images[type]);
        this.type = type;
    }
}

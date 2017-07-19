package TileSets.Player;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.input.KeyCode;

/**
 * Created by Cyndaquil on 7/18/2017.
 * Controls Player and Map movement
 */
public class MoveThread implements Runnable {
    private BooleanProperty keyPressed;
    private DoubleProperty playerX, playerY, paneX, paneY;
    private Thread thread;
    private KeyCode keyPressedName;

    public MoveThread(BooleanProperty keyPressed, KeyCode keyPressedName){
        this.keyPressed = keyPressed;
        this.playerX = new SimpleDoubleProperty(); this.playerY = new SimpleDoubleProperty();
        this.paneX = new SimpleDoubleProperty(); this. paneY = new SimpleDoubleProperty();
        this.keyPressedName = keyPressedName;
        thread = new Thread(this);
    }

    @Override
    public void run() {
        while(keyPressed.get()){
            switch (keyPressedName){
                case UP:
                    paneY.setValue(paneY.get()+2);
                    playerY.set(playerY.getValue()-2);
                    break;
                case DOWN:
                    paneY.setValue(paneY.get()-2);
                    playerY.set(playerY.get()+2);
                    break;
                case LEFT:
                    paneX.setValue(paneX.get()+2);
                    playerX.set(playerX.get()-2);
                    break;
                case RIGHT:
                    paneX.setValue(paneX.get()-2);
                    playerX.set(playerX.get()+2);
                    break;
            }
            try {
                Thread.sleep(24);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public void start(){thread.start();}

    public void setPlayerX(DoubleProperty playerX){this.playerX = playerX;}
    public void setPlayerY(DoubleProperty playerY){this.playerY = playerY;}

    public void setPaneX(DoubleProperty paneX){this.paneX = paneX;}
    public void setPaneY(DoubleProperty paneY){this.paneY = paneY;}
}

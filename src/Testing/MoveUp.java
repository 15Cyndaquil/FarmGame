package Testing;

import javafx.beans.property.IntegerProperty;

/**
 * Created by Cyndaquil on 7/18/2017.
 * Testing change of player sprite
 */
public class MoveUp implements Runnable {
    private IntegerProperty up;
    private Thread thread;
    private volatile boolean go;
    private int sleep;

    MoveUp(IntegerProperty up, int sleep){
        this.up = up;
        this.sleep = sleep;
        thread = new Thread(this);
        go = true;
    }
    @Override
    public void run() {
        while(go){
            switch (up.get()){
                case 0:
                    up.setValue(1);
                    break;
                case 1:
                    up.setValue(2);
                    break;
                case 2:
                    up.setValue(3);
                    break;
                case 3:
                    up.setValue(0);
                    break;
            }
            try {
                Thread.sleep(sleep);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }

    }


    void start(){thread.start();}
    void setGo(){this.go= false;}
}

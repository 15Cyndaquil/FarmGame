import javafx.beans.property.SimpleBooleanProperty;
import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

/**
 * Created by Cyndaquil on 7/10/2017.
 */
public class ButtonThread implements Runnable {
    private Thread thread;
    private volatile  boolean go;
    private SimpleBooleanProperty buttonProperty;
    private int controller, component;

    public ButtonThread(SimpleBooleanProperty buttonProperty, int controller, int component){
        thread = new Thread(this);
        go = true;

        this.buttonProperty = buttonProperty;
        this.controller = controller;
        this.component = component;
    }
    @Override
    public void run() {
        while(go){
            Controller[] ca = ControllerEnvironment.getDefaultEnvironment().getControllers();
            Component[] components = ca[controller].getComponents();

            if(components[component].getPollData() == 0.0f){
                buttonProperty.setValue(false);
            }else {
                buttonProperty.setValue(true);
            }
            System.out.println(components[component].getPollData());
            try{
                thread.sleep(25);
            }catch (InterruptedException e){

            }
        }
    }

    public void start(){
        thread.start();
    }

    public void stop(){
        go = false;
    }
}

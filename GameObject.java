import java.awt.*;

public abstract class GameObject extends Rectangle{

    public GameObject(){
        
    }

    public GameObject(int x, int y, int width, int height){
        setBounds(x, y, width, height);
    }

    public abstract void tick();
    public abstract boolean status();
}

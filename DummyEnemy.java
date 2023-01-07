import java.awt.*;

public class DummyEnemy extends Enemy{

    public DummyEnemy(int x, int y, int width, int height, int hp, Color color){
        super(x, y, width, height, 0, 0, hp, color);
    }

    @Override
    public void hit(){
        
        super.hp--;
        
        if (hp <= 0){
            super.die();
        } else if(hp <= 1){
            super.color = Color.red;
        } else if(hp <= 2){
            super.color = Color.orange;
        } else if(hp <= 3){
            super.color = Color.yellow;
        }
    }
    
}

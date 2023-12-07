package monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;

public class MON_gengar extends Entity {
    public MON_gengar(GamePanel gp) {
        super(gp);
        type = 2;
        direction = "sDown";
        speed = 1;
        maxLife = 4;
        life = maxLife;
        getImage("monster/gengar");
    }
    public void setAction() {
        actionLockCounter++;
        if (actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1;
            if (i <= 25) {
                direction = "wUp";
            } else if (i > 25 && i <= 50) {
                direction = "wDown";
            } else if(i > 50 && i <= 75) {
                direction = "wLeft";
            } else if (i > 75 && i <= 100) {
                direction = "wRight";
            }
            actionLockCounter = 0;
        }
    }
}

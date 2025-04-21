package entity.action;

import panel.GamePanel;
import setting.GameFrame;
import setting.KeyHandler;

public class EntityAction {
    GameFrame gf;
    GamePanel gp;
    KeyHandler keyH;

    public EntityAction(GameFrame gf, KeyHandler keyH) {
        this.gf = gf;
        this.keyH = keyH;
        this.gp = gf.gamePanel;
    }
}

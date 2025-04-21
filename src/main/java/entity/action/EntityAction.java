package entity.action;

import panel.GamePanel;
import panel.LevelPanel;
import setting.GameFrame;
import setting.KeyHandler;

public class EntityAction {
    GameFrame gf;
    GamePanel gp;
    LevelPanel lv1p;
    KeyHandler keyH;

    public EntityAction(GameFrame gf, KeyHandler keyH) {
        this.gf = gf;
        this.keyH = keyH;
        this.gp = gf.gamePanel;
        this.lv1p = gf.level1Panel;
    }
}

package entity.action;

import panel.LevelPanel;
import setting.GameFrame;
import setting.KeyHandler;

public class EntityAction_LVP extends EntityAction {

    public EntityAction_LVP(GameFrame gf, KeyHandler keyH) {
        super(gf, keyH);
    }

    public void touchObject(LevelPanel lvp, int i) {
        if (i != 999) {
            if (lvp.obj[i].name == "Key") {
                gf.player.numberOfKeys++;
                lvp.obj[i] = null;
                // System.out.println(gf.player.numberOfKeys);
            }
        }
    }

    public void touchDoor(LevelPanel lvp, int i, int numberOfKeys) {
        if (i != 999) {
            if (keyH.enterPressed) {
                if (lvp.obj_Door[i].Enbale && numberOfKeys > 0) {
                    lvp.gameState = lvp.openDoorState;
                } else if (lvp.obj_Door[i].isOpen) {
                    // do nothing
                } else {
                    lvp.gameState = lvp.offLimitsState;
                }
            }

        }
    }

    public void touchSign(LevelPanel lvp, int i) {
        if (i != 999) {
            if (keyH.enterPressed) {
                lvp.gameState = lvp.watchingSign;
            }
        }
    }

}

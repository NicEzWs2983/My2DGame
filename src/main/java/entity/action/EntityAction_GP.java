package entity.action;

import setting.*;

public class EntityAction_GP extends EntityAction {

    public EntityAction_GP(GameFrame gf, KeyHandler keyH) {
        super(gf, keyH);
    }

    public void touchObject(int i) {
        if (i != 999) {

        }
    }

    public void touchDoor(int i) {
        if (i != 999) {
            if (keyH.enterPressed && !gp.obj_Door[i].isOpen && gp.obj_Door[i].Enbale) {
                gp.gameState = gp.openDoorState;
                gf.player.doorIndex = i;
            }
        }
    }

    public void touchNPC(int i) {
        if (i != 999) {
            if (keyH.enterPressed) {
                if (gp.npc[i].dialogues[gp.npc[i].dialogueIndex] != null) {
                    gp.gameState = gp.dialogueState;
                    gp.npc[i].speak();
                    gp.npc[i].dialogueIndex++;
                }
            }
        }
    }
}

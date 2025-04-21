package setting;

import panel.GamePanel;
import panel.LevelPanel;

public class CheckState {
    GameFrame gf;
    GamePanel gp;
    LevelPanel lvp[];

    public int gameState;
    public int titleState;
    public int playState;
    public int pauseState;
    public int dialogueState;
    public int openDoorState;
    public int offLimitsState;

    public int previousMapState;
    public int nextMapState;

    public CheckState(GameFrame gf) {
        this.gf = gf;
        this.gp = gf.gamePanel;
        this.lvp = new LevelPanel[gf.numberOfLevel];
        for (int i = 0; i < gf.numberOfLevel; i++) {
            this.lvp[i] = gf.levelPanel[i];
        }

        this.titleState = gp.titleState;
        this.playState = gp.playState;
        this.pauseState = gp.pauseState;
        this.dialogueState = gp.dialogueState;
        this.openDoorState = gp.openDoorState;
        this.offLimitsState = gp.offLimitsState;

        this.previousMapState = gp.previousMapState;
        this.nextMapState = gp.nextMapState;
    }

    public int getGameState() {
        if (gf.player.inGamePanel) {
            return gp.gameState;
        }

        else if (gf.player.inLevel1Panel) {
            return lvp[1].gameState;
        }

        else if (gf.player.inLevel2Panel) {
            return lvp[2].gameState;
        }

        else if (gf.player.inLevel3Panel) {
            return lvp[3].gameState;
        }

        return 999;
    }
}

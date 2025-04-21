package setting;

import panel.GamePanel;
import panel.LevelPanel;

public class CheckState {
    GameFrame gf;
    GamePanel gp;
    LevelPanel lv1p;
    LevelPanel lv2p;
    LevelPanel lv3p;

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
        this.lv1p = gf.level1Panel;
        this.lv2p = gf.level2Panel;
        this.lv3p = gf.level3Panel;

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
            return lv1p.gameState;
        }

        else if (gf.player.inLevel2Panel) {
            return lv2p.gameState;
        }

        else if (gf.player.inLevel3Panel) {
            return lv3p.gameState;
        }

        return 999;
    }
}

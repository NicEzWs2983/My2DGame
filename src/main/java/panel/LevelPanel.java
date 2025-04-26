package panel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import object.*;
import setting.GameFrame;
import tile.TileManager;

public class LevelPanel extends OriginalPanel {

    public String mapFilePath = "/map/level1Map.txt";
    public TileManager tileM = new TileManager(this, mapFilePath);

    public OBJ_Door obj_Door[] = new OBJ_Door[4];
    public SuperObject[] obj = new SuperObject[2];
    public Signs[] signs = new Signs[5];

    public Rectangle nextMapDoor_1 = new Rectangle(tileSize * 1, 0, tileSize * 2, tileSize / 3);
    public Rectangle nextMapDoor_2 = new Rectangle(tileSize * 7, 0, tileSize * 2, tileSize / 3);
    public Rectangle nextMapDoor_3 = new Rectangle(tileSize * 13, 0, tileSize * 2, tileSize / 3);

    public int nextMapDoorCapacity_1 = 15;
    public int nextMapDoorCapacity_2 = 10;
    public int nextMapDoorCapacity_3 = 5;

    public int doorPercent[] = new int[3];

    public int x, y;

    public LevelPanel(GameFrame gf) {
        super(gf);
        gameState = previousMapState;
    }

    public void setupGame() {
        drawLockCounter = 0;
        tileM = new TileManager(this, mapFilePath);

        y = tileSize * 10;
        gf.aSetter.setPlayer(x, y);

        gf.keyH.setKeyReleased();

        gameState = playState;
    }

    @Override
    public void run() {

        super.run();

    }

    @Override
    public void update() {
        if (gameState == previousMapState) {
        }
        /*----------------------------------*/

        if (gameState == tileSize) {
        }
        if (gameState == playState) {
            gf.player.update();
        }
        if (gameState == pauseState) {
            // do nothing
        }
        if (gameState == openDoorState) {
        }
        if (gameState == offLimitsState) {
        }
        if (gameState == watchingSignState) {
        }

        /*----------------------------------*/
        if (gameState == nextMapState) {

        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2D = (Graphics2D) g;

        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        gf.drawAnything.setGraphics2D(g2D);
        tileM.draw(g2D);

        for (int i = 0; i < obj.length; i++) {
            if (obj[i] != null) {
                obj[i].draw(g2D);
            }
        }

        for (int i = 0; i < obj_Door.length; i++) {
            if (obj_Door[i] != null) {
                obj_Door[i].draw(g2D);
            }
        }

        int y = 0;
        while (y < screenHeigth) {

            int playerY = gf.player.entityY + gf.player.height;
            if (y == playerY) {
                gf.player.draw(g2D);
            }
            for (int i = 0; i < signs.length; i++) {
                if (signs[i] != null) {
                    int signsY = signs[i].objectY + signs[i].height;
                    if (y == signsY) {
                        signs[i].draw(g2D);
                        gf.drawAnything.drawPercent(this, i);
                    }
                }
            }
            y++;
        }

        // for (int i = 0; i < signs.length; i++) {
        // if (signs[i] != null) {
        // signs[i].draw(g2D);
        // }
        // }

        // gf.player.draw(g2D);
        gf.drawAnything.draw(g2D);
        gf.ui.draw(g2D);
    }

}

package panel;

import java.awt.*;

import entity.*;
import object.*;
import object.UIimage.*;
import setting.*;
import tile.*;

public class GamePanel extends OriginalPanel {

    public TileManager tileM = new TileManager(this, "/map/gamePanelMap.txt");

    // Entity & Object

    public SuperObject[] obj = new SuperObject[10];
    public OBJ_Door[] obj_Door = new OBJ_Door[2];
    public Entity[] npc = new Entity[2];
    public Keyboard[] keyboard = new Keyboard[5];
    public DirectionSign[] directionSign = new DirectionSign[4];

    public int nextMapDoorCapacity_1 = 15;
    public int nextMapDoorCapacity_2 = 10;
    public int nextMapDoorCapacity_3 = 5;

    public GamePanel(GameFrame gf) {
        super(gf);
    }

    public void setupGame() {
        drawLockCounter = 0;
        int x = (screenWidth - gf.player.width) / 2;
        int y = tileSize * 8;
        gf.aSetter.setPlayer(x, y);
        gf.aSetter.setDoor_GP();
        gf.aSetter.setNPC_GP();
        gf.aSetter.setKeyboard_GP();
        gf.keyH.setKeyReleased();

        gameState = playState;
        gf.player.inGamePanel = true;
    }

    @Override
    public void run() {

        super.run();
        // System.out.println("GamePanel stop");
    }

    @Override
    public void update() {
        if (gameState == previousMapState) {
        }
        if (gameState == tileSize) {
        }
        if (gameState == playState) {

            gf.player.update();

            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null)
                    npc[i].update();
            }

        }
        if (gameState == pauseState) {
            // do nothing
        }
        if (gameState == openDoorState) {

        }
        if (gameState == nextMapState) {

        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2D = (Graphics2D) g;

        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (gameState == titleState) {

        } else if (gameState == nextMapState) {

        } else {
            tileM.draw(g2D);

            for (int i = 0; i < obj.length; i++) {
                if (obj[i] != null)
                    obj[i].draw(g2D);
            }

            for (int i = 0; i < obj_Door.length; i++) {
                if (obj_Door[i] != null)
                    obj_Door[i].draw(g2D);
            }

            int playerY = gf.player.entityY + gf.player.height;
            if (npc[0] != null) {
                int npcY = npc[0].entityY + npc[0].height;
                if (npcY < playerY) {
                    npc[0].draw(g2D);
                    gf.player.draw(g2D);
                } else {
                    gf.player.draw(g2D);
                    npc[0].draw(g2D);
                }
            } else {
                gf.player.draw(g2D);
            }

            gf.ui.draw(g2D);
        }

        if (drawLockCounter < 180) {
            gf.ui.drawGuideWindow_GP();
        }

        g2D.dispose();
        drawLockCounter++;
    }

}

// public void run() {

// double drawInterval = 1000000000 / FPS; // 1000000000 = 1s , drawInterval =
// 0.1667s
// double nextDrawTime = System.nanoTime() + drawInterval;

// while (gamThread != null) {

// update();

// repaint();

// try {

// double remainingTime = nextDrawTime - System.nanoTime();
// remainingTime /= 1000000;

// if (remainingTime < 0)
// remainingTime = 0;

// Thread.sleep((long) remainingTime);

// nextDrawTime += drawInterval;

// } catch (InterruptedException e) {

// }
// }
// }

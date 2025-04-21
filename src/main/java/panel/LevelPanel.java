package panel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import object.OBJ_Door;
import object.SuperObject;
import setting.GameFrame;
import tile.TileManager;

public class LevelPanel extends OriginalPanel {

    public String mapFilePath = "/map/level1Map.txt";
    public TileManager tileM = new TileManager(this, mapFilePath);

    public OBJ_Door obj_Door[] = new OBJ_Door[4];
    public SuperObject[] obj = new SuperObject[2];

    public Rectangle nextMapDoor_1 = new Rectangle(tileSize * 1, 0, tileSize * 2, tileSize / 3);
    public Rectangle nextMapDoor_2 = new Rectangle(tileSize * 7, 0, tileSize * 2, tileSize / 3);
    public Rectangle nextMapDoor_3 = new Rectangle(tileSize * 13, 0, tileSize * 2, tileSize / 3);

    public int nextMapDoorCapacity_1 = 15;
    public int nextMapDoorCapacity_2 = 10;
    public int nextMapDoorCapacity_3 = 5;

    public int x, y;

    public LevelPanel(GameFrame gf) {
        super(gf);
        gameState = previousMapState;
    }

    public void setupGame() {
        tileM = new TileManager(this, mapFilePath);

        y = tileSize * 10;
        gf.aSetter.setPlayer(x, y);

        gf.keyH.setKeyReleased();

        gameState = playState;
    }

    @Override
    public void run() {

        super.run();
        System.out.println("GamePanel stop");
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

        /*----------------------------------*/
        if (gameState == nextMapState) {

        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2D = (Graphics2D) g;

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

        gf.player.draw(g2D);

        gf.ui.draw(g2D);
    }

}

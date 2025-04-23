package object;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import panel.LevelPanel;
import setting.GameFrame;
import setting.UtilityTool;

public class SuperObject {
    public GameFrame gf;
    public LevelPanel lv0;

    public BufferedImage image;
    public String name;
    public boolean collision = false;

    public int objectX, objectY;
    public int width, height;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);

    public int solidAreaDefaultX, solidAreaDefaultY;

    public String imagePath;
    public UtilityTool uTool = new UtilityTool();
    public int tileSize;

    public SuperObject(GameFrame gf) {
        this.gf = gf;
        this.lv0 = gf.levelPanel[0];
        tileSize = lv0.tileSize;
        width = tileSize;
        height = tileSize;
    }

    public void setSolidArea() {
        solidArea.x = objectX;
        solidArea.y = objectY;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

    public void loadImage() {
        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath));
            image = uTool.scaleImage(image, width, height);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2D) {
        g2D.drawImage(image, objectX, objectY, null);

        // draw solidArea
        // g2D.setColor(Color.BLUE);
        // g2D.drawRect(solidArea.x, solidArea.y, solidArea.width, solidArea.height);

    }
}

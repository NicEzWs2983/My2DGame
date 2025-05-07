package object;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import setting.GameFrame;

public class OBJ_Door extends SuperObject {
    BufferedImage[] image = new BufferedImage[2];

    public String leftImagePath, rightImagePath;
    public String nOpenLeftImagePath, nOpenRightImagePath;
    public String openLeftImagePath, openRightImagePath;
    public boolean Enbale = true;
    public boolean isOpen = false;

    public int[] pairValues;
    public boolean trueDoor = false;

    public OBJ_Door(GameFrame gf) {
        super(gf);
        name = "Door";
        collision = true;

        setSolidArea();
        setDoorImage();
    }

    public void setSolidArea() {
        solidArea.width = tileSize * 2;
        super.setSolidArea();
    }

    public void setDoorImage() {
        if (nOpenLeftImagePath != null && nOpenRightImagePath != null) {
            if (!isOpen) {
                collision = true;

                leftImagePath = nOpenLeftImagePath;
                rightImagePath = nOpenRightImagePath;

            } else {
                collision = false;

                leftImagePath = openLeftImagePath;
                rightImagePath = openRightImagePath;

            }
            loadImage();
        }
    }

    @Override
    public void loadImage() {
        try {
            // image =
            // ImageIO.read(getClass().getResourceAsStream("/res/objects/DoorLeft.png"));
            this.image[0] = ImageIO.read(getClass().getResourceAsStream(leftImagePath));
            this.image[1] = ImageIO.read(getClass().getResourceAsStream(rightImagePath));
            for (int i = 0; i < image.length; i++)
                this.image[i] = uTool.scaleImage(image[i], tileSize, tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2D) {

        for (int i = 0; i < image.length; i++) {
            g2D.drawImage(image[i], objectX + (i * tileSize), objectY, null);
        }

        // draw solidArea
        // g2D.setColor(Color.BLUE);
        // g2D.drawRect(solidArea.x, solidArea.y, solidArea.width, solidArea.height);

    }

}

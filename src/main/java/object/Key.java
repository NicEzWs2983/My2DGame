package object;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import setting.GameFrame;

public class Key extends SuperObject {

    BufferedImage[] image = new BufferedImage[7];
    String[] imagePath = new String[7];
    int drawLockCounter = 0;
    int imageIndex = 0;
    int k = 1;

    public Key(GameFrame gf) {
        super(gf);
        name = "Key";
        setImagePath();
        setSolidArea();
    }

    public void setImagePath() {
        imagePath[0] = "/objects/KeyUp_3.png";
        imagePath[1] = "/objects/KeyUp_2.png";
        imagePath[2] = "/objects/KeyUp_1.png";
        imagePath[3] = "/objects/Key.png";
        imagePath[4] = "/objects/KeyUp_1.png";
        imagePath[5] = "/objects/KeyUp_2.png";
        imagePath[6] = "/objects/KeyUp_3.png";
    }

    @Override
    public void loadImage() {
        try {
            // image =
            // ImageIO.read(getClass().getResourceAsStream("/res/objects/DoorLeft.png"));
            for (int i = 0; i < image.length; i++) {
                this.image[i] = ImageIO.read(getClass().getResourceAsStream(imagePath[i]));
            }

            for (int i = 0; i < image.length; i++) {
                this.image[i] = uTool.scaleImage(image[i], tileSize, tileSize);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics2D g2D) {

        // System.out.println(imageIndex);
        g2D.drawImage(image[imageIndex], objectX, objectY, null);

        drawLockCounter++;

        if (drawLockCounter > 10) {
            imageIndex += k;
            drawLockCounter = 0;
            if (imageIndex >= image.length - 1 || imageIndex <= 0) {
                k *= -1;
            }
        }

    }

    @Override
    public void setSolidArea() {
        solidArea.x = objectX + 12;
        solidArea.y = objectY;
        solidArea.width = width - 12 * 2;
        solidArea.height = height;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
}

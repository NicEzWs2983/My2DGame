package object;

import java.awt.Rectangle;

import setting.GameFrame;

public class Key extends SuperObject {

    public Key(GameFrame gf) {
        super(gf);
        name = "Key";
        imagePath = "/objects/Key.png";
        setSolidArea();
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

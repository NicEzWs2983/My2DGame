package object;

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
        solidArea.width = tileSize - 12 * 2;
        solidArea.height = tileSize;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
}

package object;

import java.awt.Rectangle;

import setting.GameFrame;

public class Signs extends SuperObject {

    public Signs(GameFrame gf) {
        super(gf);
        name = "Signs";
        imagePath = "/objects/Signs.png";
        setSolidArea();
    }

    @Override
    public void setSolidArea() {
        solidArea.x = objectX + 3;
        solidArea.y = objectY + 4;
        solidArea.width = width - 3 * 2;
        solidArea.height = height - 4;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        solidArea2 = new Rectangle();
        solidArea2.x = objectX + 21;
        solidArea2.y = objectY + height - 6;
        solidArea2.width = 6;
        solidArea2.height = 6;
        solidAreaDefaultX2 = solidArea2.x;
        solidAreaDefaultY2 = solidArea2.y;
    }

}

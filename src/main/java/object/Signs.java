package object;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import setting.GameFrame;

public class Signs extends SuperObject {
    public boolean collision2 = true;
    public Rectangle solidArea2;
    public int solidAreaDefaultX2, solidAreaDefaultY2;

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
        solidArea.height = height + 7;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        solidArea2 = new Rectangle();
        solidArea2.x = objectX + 3;
        solidArea2.y = objectY + height - 6;
        solidArea2.width = width - 3 * 2;
        solidArea2.height = 6;
        solidAreaDefaultX2 = solidArea2.x;
        solidAreaDefaultY2 = solidArea2.y;
    }

    @Override
    public void draw(Graphics2D g2D) {
        super.draw(g2D);

        // draw solidArea2
        if (solidArea2 != null) {
            g2D.setColor(Color.RED);
            g2D.drawRect(solidArea2.x, solidArea2.y, solidArea2.width, solidArea2.height);
        }
    }

}

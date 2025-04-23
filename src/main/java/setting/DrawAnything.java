package setting;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import panel.LevelPanel;

public class DrawAnything extends UI {

    public DrawAnything(GameFrame gf) {
        super(gf);
    }

    public void setGraphics2D(Graphics2D g2D) {
        this.g2D = g2D;
    }

    @Override
    public void draw(Graphics2D g2D) {
        for (int i = 1; i < gf.numberOfLevel; i++) {
            if (gf.player.inLevelPanel[i]) {
                darkLight();
                break;
            }
        }
        drawLevel();
    }

    public void darkLight() {
        int top = screenHeigth - (screenHeigth - gf.player.entityY) - tileSize / 2;
        int bottom = screenHeigth - gf.player.entityY - tileSize * 3 / 2;
        int left = screenWidth - (screenWidth - gf.player.entityX) - tileSize / 2;
        int right = screenWidth - gf.player.entityX - tileSize * 3 / 2;

        Color c = new Color(0, 0, 0, 150);
        g2D.setColor(c);
        g2D.fillRoundRect(0, 0, screenWidth, top, 0, 0);
        g2D.fillRoundRect(0, gf.player.entityY + tileSize * 3 / 2, screenWidth, bottom, 0, 0);
        g2D.fillRoundRect(0, top, left, screenHeigth - top - bottom, 0, 0);
        g2D.fillRoundRect(gf.player.entityX + tileSize * 3 / 2, top, right, screenHeigth - top - bottom, 0, 0);
    }

    public void drawPercent(LevelPanel lvp, int i) {
        g2D.setFont(unifont);
        g2D.setFont(g2D.getFont().deriveFont(Font.BOLD, 16F));
        g2D.setColor(Color.BLACK);

        String text = lvp.doorPercent[0] + "%";
        int length = (int) g2D.getFontMetrics().getStringBounds(text, g2D).getWidth();

        if (i == 0) {

            text = lvp.doorPercent[0] + "%";

            int x = 3 * tileSize + (tileSize - length) / 2;
            int y = tileSize - 3;
            g2D.drawString(text, x, y);
        }

        if (i == 1) {

            text = lvp.doorPercent[1] + "%";

            int x = 9 * tileSize + (tileSize - length) / 2;
            int y = tileSize - 3;

            g2D.drawString(text, x, y);
        }

        if (i == 2) {

            text = lvp.doorPercent[2] + "%";

            int x = 14 * tileSize + (tileSize - length) / 2;
            int y = 2 * tileSize - 3;

            g2D.drawString(text, x, y);
        }
    }

    public void drawLevel() {
        int level = gf.player.level;

        int x = 12 * tileSize;
        int y = 12 * tileSize - tileSize / 3;
        g2D.setFont(unifont);
        g2D.setFont(g2D.getFont().deriveFont(Font.BOLD, 30F));
        g2D.setColor(Color.magenta);
        if (level != -1) {
            g2D.drawString("Level " + level, x, y);
        }
    }

}

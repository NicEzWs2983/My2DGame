package setting;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.IOException;
import java.io.InputStream;

public class UI {
    GameFrame gf;

    CheckState cState;

    int tileSize, screenWidth, screenHeigth, gameState;
    Font maruMonica, unifont, HWMCT, Zomzi;
    Graphics2D g2D;

    public String message;
    public boolean messageOn = false;
    public boolean openDoor = false;
    public String currentDialogue = "";
    public int choiceIndex = 0;

    public UI(GameFrame gf) {
        this.gf = gf;

        this.cState = gf.cState;

        this.tileSize = gf.gamePanel.tileSize;
        this.screenWidth = gf.gamePanel.screenWidth;
        this.screenHeigth = gf.gamePanel.screenHeigth;

        InputStream is;

        try {
            is = getClass().getResourceAsStream("/font/x12y16pxMaruMonica.ttf");
            maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);

            is = getClass().getResourceAsStream("/font/unifont_jp-16.0.02.otf");
            unifont = Font.createFont(Font.TRUETYPE_FONT, is);

            is = getClass().getResourceAsStream("/font/hui wen ming chao ti.otf");
            HWMCT = Font.createFont(Font.TRUETYPE_FONT, is);

            is = getClass().getResourceAsStream("/font/Zomzi.TTF");
            Zomzi = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2D) {

        this.g2D = g2D;

        g2D.setFont(maruMonica);
        g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2D.setColor(Color.WHITE);

        gameState = cState.getGameState();

        if (gameState == cState.titleState) {
        }
        if (gameState == cState.playState) {
        }
        if (gameState == cState.pauseState) {
            drawPauseGame();
        }
        if (gameState == cState.dialogueState) {
            drawDialogueScreen();
        }
        if (gameState == cState.openDoorState) {
            drawOpenDoorQA();
        }
        if (gameState == cState.offLimitsState) {
            drawOffLimits();
        }

        if (gf.player.inLevel1Panel || gf.player.inLevel2Panel || gf.player.inLevel3Panel) {
            darkLight();
        }
        drawLevel();
    }

    public void drawLevel() {
        int level = -1;
        if (gf.player.inGamePanel) {
            level = 0;
        } else if (gf.player.inLevel1Panel) {
            level = 1;
        } else if (gf.player.inLevel2Panel) {
            level = 2;
        } else if (gf.player.inLevel3Panel) {
            level = 3;
        }

        int x = 12 * tileSize;
        int y = 12 * tileSize - tileSize / 3;
        g2D.setFont(unifont);
        g2D.setFont(g2D.getFont().deriveFont(Font.BOLD, 30F));
        g2D.setColor(Color.magenta);
        if (level != -1) {
            g2D.drawString("Level " + level, x, y);
        }
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

    public void drawPauseGame() {
        g2D.setFont(g2D.getFont().deriveFont(Font.BOLD, 80F));
        String text = "PAUSE";
        int x = getXForCenterText(text);
        int y = screenHeigth / 2;
        g2D.drawString(text, x, y);
    }

    public void drawDialogueScreen() {

        int x = tileSize * 2;
        int y = tileSize / 2;
        int width = screenWidth - (tileSize * 4);
        int height = tileSize * 3;

        drawSubWindow(x, y, width, height);

        x += tileSize / 2;
        y += tileSize;
        g2D.setColor(Color.white);
        g2D.setFont(g2D.getFont().deriveFont(Font.PLAIN, 30F));
        g2D.drawString(currentDialogue, x, y);

    }

    public void drawSubWindow(int x, int y, int width, int height) {
        Color c = new Color(0, 0, 0, 200);
        g2D.setColor(c);
        g2D.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255, 255);
        g2D.setColor(c);
        g2D.setStroke(new BasicStroke(5));
        g2D.drawRoundRect(x + 3, y + 3, width - 6, height - 6, 25, 25);
    }

    public void drawOpenDoorQA() {
        int x = tileSize * 2;
        int y = screenHeigth - tileSize * 3;
        int width = screenWidth - x * 2;
        int height = tileSize * 2;
        drawOpenDoorSubWindow(x, y, width, height);

        String text = "Make sure you really want to go in.";

        x += tileSize / 2;
        y += tileSize;
        g2D.setColor(Color.white);
        g2D.setFont(g2D.getFont().deriveFont(Font.PLAIN, 25F));
        g2D.drawString(text, x, y);

        x += (int) g2D.getFontMetrics().getStringBounds(text, g2D).getWidth() + tileSize;
        if (choiceIndex == 0)
            g2D.drawString(">", x, y);

        x += tileSize / 3;
        g2D.drawString("Yes!", x, y);

        x += (int) g2D.getFontMetrics().getStringBounds("Yes", g2D).getWidth() + tileSize;
        if (choiceIndex == 1)
            g2D.drawString(">", x, y);

        x += tileSize / 3;
        g2D.drawString("No!", x, y);
    }

    public void drawOffLimits() {
        int x = tileSize * 2;
        int y = screenHeigth - tileSize * 3;
        int width = screenWidth - x * 2;
        int height = tileSize * 2;
        drawOpenDoorSubWindow(x, y, width, height);

        String text = "";

        if (gf.player.inLevel1Panel || gf.player.inLevel2Panel || gf.player.inLevel3Panel)
            if (gf.player.doorIndex == 0) {
                text = "You are not allowed to return there.";
            } else {
                text = "You don't have enough keys to open this door.";
            }

        x += tileSize / 2;
        y += tileSize;
        g2D.setColor(Color.white);
        g2D.setFont(g2D.getFont().deriveFont(Font.PLAIN, 25F));
        g2D.drawString(text, x, y);
    }

    public void drawOpenDoorSubWindow(int x, int y, int width, int height) {
        Color c = new Color(0, 0, 0, 230);
        g2D.setColor(c);
        g2D.fillRoundRect(x, y, width, height, 10, 10);
    }

    public int getXForCenterText(String text) {
        int length = (int) g2D.getFontMetrics().getStringBounds(text, g2D).getWidth();
        int x = (screenWidth - length) / 2;
        return x;
    }
}

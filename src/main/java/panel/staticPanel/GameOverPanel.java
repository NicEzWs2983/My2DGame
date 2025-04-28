package panel.staticPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import button.RestartButton;
import setting.GameFrame;

public class GameOverPanel extends StaticPanel_O {

    RestartButton restartButton = new RestartButton(this);

    public GameOverPanel(GameFrame gf) {
        super(gf);

        Font btFont = maruMonica.deriveFont(Font.PLAIN, 50F);
        int x = (screenWidth - restartButton.getWidth()) / 2;
        int y = screenHeigth * 3 / 5;
        restartButton.setFont(btFont);
        restartButton.setForeground(Color.WHITE);
        restartButton.setLocation(x, y);

        this.add(restartButton);

        restartButton.addActionListener(e -> {
            gf.layout.show(gf.cardPanel, gf.title);
            gameState = nextMapState;
        });
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        drawGameOverScreen();
    }

    public void drawGameOverScreen() {
        g2D.setFont(Zomzi);
        g2D.setFont(g2D.getFont().deriveFont(Font.PLAIN, 80F));
        g2D.setColor(Color.RED);

        // gf.getText.setCompletedLevel();
        String GameOverText[] = { "Game Over", "You've completed " + (gf.player.level - 1) + " levels" };
        int x = getXForCenterText(GameOverText[0]);
        int y = tileSize * 3;

        g2D.drawString(GameOverText[0], x, y);

        g2D.setFont(maruMonica);
        g2D.setFont(g2D.getFont().deriveFont(Font.PLAIN, 60F));
        g2D.setColor(Color.WHITE);
        x = getXForCenterText(GameOverText[1]);
        y += tileSize * 2;

        g2D.drawString(GameOverText[1], x, y);
    }

}

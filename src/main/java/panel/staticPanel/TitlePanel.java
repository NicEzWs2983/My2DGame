package panel.staticPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import button.StartButton;
import setting.GameFrame;

public class TitlePanel extends StaticPanel_O {
    StartButton startButton = new StartButton(this);

    public TitlePanel(GameFrame gf) {
        super(gf);

        Font btFont = maruMonica.deriveFont(Font.PLAIN, 50F);
        int x = (screenWidth - startButton.getWidth()) / 2;
        int y = screenHeigth * 3 / 5;
        startButton.setFont(btFont);
        startButton.setForeground(Color.WHITE);
        startButton.setLocation(x, y);

        this.add(startButton);

        startButton.addActionListener(e -> {
            gf.layout.show(gf.cardPanel, gf.game);
            gf.gamePanel.setupGame();
            gf.gamePanel.requestFocusInWindow();
            gf.gamePanel.startGameThread();
            gf.player.level = 0;
        });

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2D = (Graphics2D) g;
        this.g2D = g2D;

        drawTitleScreen();

    }

    public void drawTitleScreen() {
        g2D.setFont(HWMCT);
        g2D.setFont(g2D.getFont().deriveFont(Font.PLAIN, 75F));
        g2D.setColor(Color.RED);

        String titleText[] = { "The Door of Destiny", "命 運 之 門" };
        int x = getXForCenterText(titleText[0]);
        int y = tileSize * 3;

        g2D.drawString(titleText[0], x, y);

        // g2D.setFont(maruMonica);
        // g2D.setFont(g2D.getFont().deriveFont(50F));
        // String text = "START";
        // x = getXForCenterText(text);
        // y = screenHeigth * 2 / 3;

        // g2D.setColor(Color.WHITE);
        // g2D.drawString(text, x, y);
    }
}

package panel.staticPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import button.SelectButton;
import button.StartButton;
import setting.GameFrame;

public class TitlePanel extends StaticPanel_O {
    StartButton startButton = new StartButton(this);
    SelectButton selectButton;

    public TitlePanel(GameFrame gf) {
        super(gf);
        selectButton = gf.selectButton;

        // start btn
        Font btFont = maruMonica.deriveFont(Font.PLAIN, 50F);
        int x = (screenWidth - startButton.getWidth()) / 2;
        int y = screenHeigth * 3 / 5;
        startButton.setFont(btFont);
        startButton.setForeground(Color.WHITE);
        startButton.setLocation(x, y);

        // select btn
        x = screenWidth - 3 * tileSize;
        y = screenHeigth - 2 * tileSize;
        selectButton.setBounds(x, y, tileSize * 5 / 2, tileSize * 3 / 2);
        selectButton.setFont(maruMonica, Font.PLAIN, 30F);
        selectButton.title = gf.getText.option[0][0];
        selectButton.fontColor = Color.GREEN;
        selectButton.arcWidth = 30;
        selectButton.arcHeight = 30;

        this.add(startButton);
        this.add(selectButton);

        startButton.addActionListener(e -> {
            gf.layout.show(gf.cardPanel, gf.game);
            gf.gamePanel.setupGame();
            gf.gamePanel.requestFocusInWindow();
            gf.gamePanel.startGameThread();
        });
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        drawTitleScreen();

    }

    public void drawTitleScreen() {
        g2D.setFont(HWMCT);
        g2D.setFont(g2D.getFont().deriveFont(Font.PLAIN, 75F));
        g2D.setColor(Color.RED);

        String titleText = gf.getText.titleName[0][0];
        int x = getXForCenterText(titleText);
        int y = tileSize * 3;

        g2D.drawString(titleText, x, y);

        // g2D.setFont(maruMonica);
        // g2D.setFont(g2D.getFont().deriveFont(50F));
        // String text = "START";
        // x = getXForCenterText(text);
        // y = screenHeigth * 2 / 3;

        // g2D.setColor(Color.WHITE);
        // g2D.drawString(text, x, y);
    }

}

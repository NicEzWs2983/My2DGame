package panel.staticPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.RenderingHints;

import button.*;
import setting.GameFrame;

public class TitlePanel extends StaticPanel_O {
    StartButton startButton = new StartButton(this);
    MineButton selectButton = new MineButton();

    public TitlePanel(GameFrame gf) {
        super(gf);
        gameState = playState;

        btnSetter();

        this.add(startButton);
        this.add(selectButton);

    }

    @Override
    public void checkLanguage() {
        selectButton.setLanguage(gf.getText.option); // Option
    }

    @Override
    public void btnSetter() {
        // start btn
        Font btFont = maruMonica.deriveFont(Font.PLAIN, 50F);
        int x = (screenWidth - startButton.getWidth()) / 2;
        int y = screenHeigth * 3 / 5;
        startButton.setFont(btFont);
        startButton.setForeground(Color.WHITE);
        startButton.setLocation(x, y);

        startButton.addActionListener(e -> {
            gf.layout.show(gf.cardPanel, gf.game);
            gf.gamePanel.setupGame();
            gf.gamePanel.requestFocusInWindow();
            gf.gamePanel.startGameThread();
            gameState = nextMapState;
        });

        // select btn
        x = screenWidth - 3 * tileSize;
        y = screenHeigth - 2 * tileSize;
        selectButton.setBounds(x, y, tileSize * 5 / 2, tileSize * 3 / 2);
        selectButton.setText(Color.GREEN, maruMonica, Font.PLAIN, 30F, 30, 30);

        selectButton.setOnClick(() -> {
            gf.optionPanel.setCloseBTN_OnClick(gf.title);
            gf.layout.show(gf.cardPanel, gf.option);
            gf.optionPanel.gameState = gf.optionPanel.playState;
            gf.optionPanel.startGameThread();
        });
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        drawTitleScreen();
        startButton.setVisible(true);
        selectButton.setVisible(true);
        repaint();

    }

    public void drawTitleScreen() {
        g2D.setFont(HWMCT);
        g2D.setFont(g2D.getFont().deriveFont(Font.PLAIN, 75F));
        g2D.setColor(Color.RED);

        String titleText = gf.getText.titleName[0][0]; // The Door of Destiny
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

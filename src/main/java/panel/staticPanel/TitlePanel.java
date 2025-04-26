package panel.staticPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.RenderingHints;

import button.*;
import button.StartButton;
import setting.GameFrame;

public class TitlePanel extends StaticPanel_O {
    StartButton startButton = new StartButton(this);
    SelectButton selectButton;

    OptionPanel op;

    public TitlePanel(GameFrame gf) {
        super(gf);
        selectButton = gf.selectButton;
        op = gf.optionPanel;

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
        selectButton.setText(gf.getText.option[0][0], Color.GREEN, maruMonica, Font.PLAIN, 30F, 30, 30);
        selectButton.og = this;

        this.add(startButton);
        this.add(selectButton);
        this.add(op);

        op.setVisible(true);

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

        if (gameState == optionUIState) {
            this.setBackground(Color.GRAY);
            startButton.setVisible(false);
            selectButton.setVisible(false);
            op.setVisible(true);
            repaint();
        } else {
            drawTitleScreen();
        }

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

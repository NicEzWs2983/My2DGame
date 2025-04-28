package panel.staticPanel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import button.MineButton;
import setting.GameFrame;

public class OptionPanel extends StaticPanel_O {

    MineButton[] mbtns = new MineButton[10];
    // language btn
    MineButton[] lbtns = new MineButton[10];

    int settingIndex = 999;

    public OptionPanel(GameFrame gf) {
        super(gf);
        this.setAutoscrolls(true);
        this.setBackground(new Color(64, 64, 64));

        btnSetter();

        for (int i = 0; i < mbtns.length; i++) {
            if (mbtns[i] != null) {
                this.add(mbtns[i]);
            }
        }

        for (int i = 0; i < lbtns.length; i++) {
            if (lbtns[i] != null) {
                this.add(lbtns[i]);
                lbtns[i].setVisible(false);
            }
        }

    }

    @Override
    public void update() {
        super.update();
        if (settingIndex == 1) {
            for (int i = 0; i < lbtns.length; i++) {
                if (lbtns[i] != null) {
                    lbtns[i].setVisible(true);
                }
            }
        }
    }

    @Override
    public void checkLanguage() {
        mbtns[1].setLanguage(gf.getText.selectLanguage); // Language
    }

    @Override
    public void btnSetter() {
        // close btn
        mbtns[0] = new MineButton();
        MineButton btn = mbtns[0];

        int width = 30;
        int height = 30;
        int x = tileSize / 4;
        int y = (tileSize - height) / 2;
        int textX = 5;
        int textY = 25;
        int size = 30;
        int style = Font.BOLD;

        btn.setBounds(x, y, width, height);
        btn.setText("x");
        btn.setText(Color.BLACK, unifont, style, size, 0, 0);
        btn.boundColor = Color.RED;
        btn.backgroundColor = Color.WHITE;
        // btn.textX = textX;
        btn.textY = textY;

        // select language btn
        mbtns[1] = new MineButton();
        btn = mbtns[1];

        width = tileSize * 4;
        height = tileSize;
        x += tileSize / 2;
        y = tileSize * 5 / 4;
        size = 40;

        btn.setBounds(x, y, width, height);
        btn.setText(Color.BLACK, maruMonica, style, size, 0, 0);
        btn.backgroundColor = Color.WHITE;
        btn.boundColor = Color.BLACK;

        textY = btn.getHeight() / 2 + 15;

        // btn.textX = textX;
        btn.textY = textY;

        btn.setOnClick(() -> {
            this.settingIndex = 1;
        });

        // enlgish btn
        lbtns[0] = new MineButton();
        btn = lbtns[0];

        width = screenWidth - tileSize * 7 - tileSize / 2;
        height = tileSize / 2;
        x = tileSize * 6 + tileSize * 3 / 4;
        size = 20;

        btn.setBounds(x, y, width, height);
        btn.setText("English");
        btn.setText(Color.BLACK, maruMonica, style, size, 0, 0);
        btn.backgroundColor = Color.WHITE;
        btn.boundColor = Color.BLACK;

        textY -= 20;

        btn.textY = textY;
        btn.textX = textX;

        btn.setOnClick(() -> {
            gf.getText.setLanguage(gf.getText.English);
        });

        // chinese btn
        lbtns[1] = new MineButton();
        btn = lbtns[1];

        y += height + tileSize / 8;

        btn.setBounds(x, y, width, height);
        btn.setText("中文(Traditional)");
        btn.setText(Color.BLACK, maruMonica, style, size, 0, 0);
        btn.backgroundColor = Color.WHITE;
        btn.boundColor = Color.BLACK;
        btn.textY = textY;
        btn.textX = textX;

        btn.setOnClick(() -> {
            gf.getText.setLanguage(gf.getText.Chinese);
        });
    }

    public void setCloseBTN_OnClick(String panelName) {
        mbtns[0].setOnClick(() -> {
            if (panelName != null) {
                gf.layout.show(gf.cardPanel, panelName);
            }
        });
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2D = (Graphics2D) g;

        int x = tileSize / 4;
        int y = tileSize;
        int width = 5 * tileSize;
        int height = screenHeigth - tileSize * 3 / 2;

        drawFrame(g2D, x, y, width, height);

        x += width + tileSize;
        width = screenWidth - x - tileSize / 4;

        drawFrame(g2D, x, y, width, height);
    }

    public void drawFrame(Graphics2D g2D, int x, int y, int width, int height) {
        g2D.setColor(new Color(50, 50, 50));
        g2D.fillRoundRect(x, y, width, height, 0, 0);

        g2D.setColor(Color.WHITE);
        g2D.setStroke(new BasicStroke(2));
        g2D.drawRoundRect(x, y, width, height, 0, 0);
    }

}

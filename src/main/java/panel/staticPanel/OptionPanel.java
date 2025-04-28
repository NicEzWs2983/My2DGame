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
    MineButton[] sbtns = new MineButton[10];

    public OptionPanel(GameFrame gf) {
        super(gf);
        this.setAutoscrolls(true);
        this.setBackground(new Color(64, 64, 64));

        btnSetter();

        this.add(mbtns[0]);
        this.add(mbtns[1]);

    }

    public void btnSetter() {
        // close btn
        mbtns[0] = new MineButton();
        MineButton btn = mbtns[0];

        int width = 30;
        int height = 30;
        int x = tileSize / 4;
        int y = (tileSize - height) / 2;
        btn.setBounds(x, y, width, height);
        btn.setText("X", Color.BLACK, unifont, Font.BOLD, 30, 0, 0);
        btn.boundColor = Color.RED;
        btn.backgroundColor = Color.WHITE;
        btn.textY = 25;

        // select language btn
        mbtns[1] = new MineButton();
        btn = mbtns[1];

        width = tileSize * 4;
        height = tileSize;
        x += tileSize / 2;
        y = tileSize * 5 / 4;
        btn.setBounds(x, y, width, height);
        btn.setText(gf.getText.selectLanguage[0][0], Color.BLACK, maruMonica, Font.BOLD, 40, 0, 0);
        btn.backgroundColor = Color.WHITE;
        btn.boundColor = Color.BLACK;
        btn.textY = btn.getHeight() / 2 + 15;

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

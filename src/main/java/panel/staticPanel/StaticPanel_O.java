package panel.staticPanel;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;
import java.io.InputStream;

import panel.OriginalPanel;
import setting.GameFrame;

public class StaticPanel_O extends OriginalPanel {
    Graphics2D g2D;

    public Font maruMonica, unifont, HWMCT, Zomzi;

    public StaticPanel_O(GameFrame gf) {
        super(gf);
        this.setLayout(null);
        loadNewFont();
    }

    public void loadNewFont() {
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

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2D = (Graphics2D) g;
        this.g2D = g2D;

    }

    public int getXForCenterText(String text) {
        int length = (int) g2D.getFontMetrics().getStringBounds(text, g2D).getWidth();
        int x = (screenWidth - length) / 2;
        return x;
    }

}
